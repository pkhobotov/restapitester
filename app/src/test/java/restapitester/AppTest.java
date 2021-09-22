package restapitester;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static restapitester.AccountsEndpoint.*;

public class AppTest {
    private RequestSpecification givenRequest;


    @BeforeEach
    public void setGivenRequest() {
        this.givenRequest = given()
                .baseUri(BASE_URL)
                .port(PORT)
                .basePath(BASE_PATH)
                .header("X-API-VERSION", 1)
//                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
        ;
    }

    @Test
    public void shouldAnswerWithTrue() {
        givenRequest.when()
                .get()
                .then()
                .statusCode(anyOf(is(SC_CREATED), is(SC_OK)))
                .body(
                        "size()", is(3),
                        "[1].id", is(2));

    }
}
