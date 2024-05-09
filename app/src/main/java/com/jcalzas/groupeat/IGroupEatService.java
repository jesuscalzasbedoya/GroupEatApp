package com.jcalzas.groupeat;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGroupEatService {
    @GET("/{user_id}/amigos")
    Call<JSONObject> getAmigos(@Path("user_id") String user_id);

    @GET("/{user_id}/{amigos}/ciudad")
    Call<JSONObject> getCiudades(@Path("user_id") String user_id,
                                 @Path("amigos") String amigos);

    @GET("/{user_id}/{amigos}/{ciudad}/resultados")
    Call<JSONObject> getResultados(@Path("user_id") String user_id,
                                   @Path("amigos") String amigos,
                                   @Path("ciudad") String ciudad);
}
