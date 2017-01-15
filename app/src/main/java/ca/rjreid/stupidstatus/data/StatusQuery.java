package ca.rjreid.stupidstatus.data;

import retrofit2.Call;
import retrofit2.http.GET;


public interface StatusQuery {
    @GET("user/status")
    Call<Status> getStatus();
}
