package com.example.joel.sendinrequestobjects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by joel on 7/14/2018.
 */

public interface UserClient {
    @Headers({"Content-Type: application/x-www-form-urlencoded",
               "User-Agent:Android"
    })
    @POST("user/register")
    Call<User> createAccounts(@Body User user);


}
