package restapitester;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ClientService {
    @GET("client/{id}")
    @Headers("X-API-VERSION:1")
    Call<Client> getClient(@Path("id") Integer id);


}
