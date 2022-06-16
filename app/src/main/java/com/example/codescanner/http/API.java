package com.example.codescanner.http;

import com.example.codescanner.model.Apreensao;
import com.example.codescanner.model.ValueScanned;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @POST("/produto-apreensao/receber-id")
    Call<Apreensao> recebeId (
            @Body ValueScanned valueScanned
    );

    @GET("/produto-apreensao/receber-itens")
    Call<String[]> recebeItens();
}
