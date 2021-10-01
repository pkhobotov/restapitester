package restapitester;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static restapitester.AccountsEndpoint.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {
    private Connection connection;

    @BeforeAll
    public void setUpDBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/dbo-db");

    }

    @AfterAll
    public void closeDBConnection() throws SQLException {
        connection.close();
    }

    @Nested
    public class AccountServiceTests {
        private RequestSpecification givenRequest;

        @BeforeEach
        public void setGivenRequest() {
            this.givenRequest = given()
                    .baseUri(BASE_URL)
                    .port(PORT)
                    .basePath(BASE_PATH)
                    .header("X-API-VERSION", 1)
                    .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }

        @Test
        public void shouldAnswerWithTrue() throws SQLException {
            int newAccountId;
            var testClientId = 9;
            try (var newAccount = connection.prepareStatement(
                    "INSERT INTO ACCOUNT(AMOUNT, CREATE_STAMP, CLIENT_ID) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                newAccount.setDouble(1, 45);
                newAccount.setTimestamp(2, Timestamp.from(Instant.now()));
                newAccount.setInt(3, testClientId);

                assumeTrue(newAccount.executeUpdate() == 1);

                var result = newAccount.getGeneratedKeys();
                assumeTrue(result.next());
                newAccountId = result.getInt(1);
            }

            int clientsCount;
            try (var countClients = connection.prepareStatement("select count(*) from account")) {
                var resultSet = countClients.executeQuery();
                assumeTrue(resultSet.next());
                clientsCount = resultSet.getInt(1);
            }
            givenRequest.when()
                    .get()
                    .then()
                    .statusCode(is(SC_OK))
                    .body("size()", is(clientsCount),
                            "id", hasItem(newAccountId));
            try (var deleteAccounts = connection.prepareStatement(
                    "DELETE FROM ACCOUNT WHERE ID = ?", Statement.RETURN_GENERATED_KEYS)) {
                deleteAccounts.setInt(1, newAccountId);

                assumeTrue(deleteAccounts.executeUpdate() == 1);

            }
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class ClientServiceTests {
        EntityManagerFactory entityManagerFactory;
        private EntityManager entityManager;
//        private Client defaultClient = Client.builder().secret("vrt34t23").salt("3424242442").login("eewfwef@mail.ru").build();
        private Client defaultClient = new Client("eewfwef@mail.ru", "3424242442","vrt34t23" );
        private ClientService service;

        @BeforeAll
        public void fixture() {
            var httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            var retrofit = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("http://localhost:8080/dbo/api/")
                    .client(httpClient.build())
                    .build();
            service = retrofit.create(ClientService.class);

            entityManagerFactory = Persistence.createEntityManagerFactory("dbo");
            entityManager = entityManagerFactory.createEntityManager();
        }

        @AfterAll
        public void JPATearDown() {
            entityManager.close();
            entityManagerFactory.close();
        }


        @Test
        public void shouldReturnAllClients() throws IOException {


            var response = service
                    .getAllClients()
                    .execute();
            var body = response.body();

            assert body != null;
            assertThat(body.size(), is(3));
        }

        @Test
        public void shouldReturnClientById() throws IOException {
            var response = service.getClientById(defaultClient.getId())
                    .execute();
            var responseClient = response.body();
            assertThat(responseClient, is(defaultClient));

            deleteClientFromBD(defaultClient);
        }

        private long createClientInBD(Client client) {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
            return client.getId();
        }

        private void deleteClientFromBD(Client client) {
            var clientToDelete = findClientInBD(client);
            entityManager.getTransaction().begin();
            entityManager.remove(clientToDelete);
            entityManager.getTransaction().commit();
        }

        private Client findClientInBD(Client client) {
            entityManager.getTransaction().begin();
            final var clientFound = entityManager.find(Client.class, client.getId());
            entityManager.getTransaction().commit();
            return clientFound;
        }

        @Test
        public void shouldAddNewClient() throws IOException {
            var response = service
                    .createNewClient(defaultClient)
                    .execute();
            var responseClient = response.body();

            assertThat(response.code(), is(SC_CREATED));
            assertThat(responseClient, is(defaultClient));
            assertThat(findClientInBD(responseClient), is(defaultClient));

            deleteClientFromBD(responseClient);
        }

        @Test
        public void shouldReturn404() throws IOException {

            var response = service
                    .getClientById(5).execute();

            assertThat(response.code(), is(SC_NOT_FOUND));


        }
@Disabled
        @Test
        public void shouldDeleteClientByLogin() throws IOException {
//            var client = Client.builder().login("ferferfer@freferf.ry").salt("wefqwefqwef").secret("wefwefqwef").id(53).build();
            var clientId = createClientInBD(defaultClient);
            var response = service
                    .deleteClientById(clientId).execute();

            assertThat(response.code(), is(SC_OK));
            var clientFound = findClientInBD(defaultClient);
            assertThat(clientFound, is(null));
        }
    }
}
