package com.shopping.framework.network;


import com.shopping.BuildConfig;
import com.shopping.feature.product.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    String API_BASE_URL = "https://api.stackexchange.com/2.2/";

    @GET("answers")
    Call<Model> getAnswers(@Query("page") int page, @Query("pagesize") int pagesize, @Query("site") String site);


}
