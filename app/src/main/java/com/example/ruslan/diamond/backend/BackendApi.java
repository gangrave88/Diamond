package com.example.ruslan.diamond.backend;

import com.example.ruslan.diamond.db.Price;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BackendApi {

    @GET("{token_app}/{token_rest}/data/price")
    Observable<List<Price>> getPrice(@Path("token_app") String token_app, @Path("token_rest") String token_rest);

}
