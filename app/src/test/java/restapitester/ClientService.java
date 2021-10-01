package restapitester;


import retrofit2.Call;

import retrofit2.http.*;

import java.util.List;

public interface ClientService {
    @GET("client/{id}")
    @Headers("X-API-VERSION:1")
    Call<Client> getClientById(@Path("id") long id);

    @GET("client")
    @Headers("X-API-VERSION:1")
    Call<List<Client>> getAllClients();

    @POST("client")
    @Headers("X-API-VERSION:1")
    Call<Client> createNewClient(@Body Client client);

    @DELETE("client/{id}")
    @Headers("X-API-VERSION:1")
    Call deleteClientById(@Path("id") long id);

    @DELETE("client/login/{clientLogin}")
    @Headers("X-API-VERSION:1")
    Call deleteClientByLogin(@Path("login") String login);

}
