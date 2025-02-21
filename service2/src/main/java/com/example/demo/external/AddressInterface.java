package com.example.demo.external;

import com.example.demo.oth.Address;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.UUID;

public interface AddressInterface {
    @GET("/api/v1/address/{id}")
    public Call<Address> getAddress(@Path("id") UUID id);

    @POST("/api/v1/address/")
    public Call<Address> postAddress(@Body Address address);
}
