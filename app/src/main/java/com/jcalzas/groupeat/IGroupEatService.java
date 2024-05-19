package com.jcalzas.groupeat;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGroupEatService {
    @GET("/{user_id}/amigos")
    Call<JsonArray> getAmigos(@Path("user_id") String user_id);

    @GET("/{user_id}/{amigos}/ciudad")
    Call<JsonArray> getCiudades(@Path("user_id") String user_id,
                                 @Path("amigos") String amigos);

    @GET("/{user_id}/{amigos}/{ciudad}/resultados")
    Call<JsonArray> getResultados(@Path("user_id") String user_id,
                                   @Path("amigos") String amigos,
                                   @Path("ciudad") String ciudad);

}

