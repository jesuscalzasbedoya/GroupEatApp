package com.jcalzas.groupeat;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGroupEatService {
    //Obtener amigos
    @GET("/{user_id}/amigos")
    Call<JsonArray> getAmigos(@Path("user_id") String user_id);

    //Obtener ciudades disponibles
    @GET("/{user_id}/{amigos}/ciudad")
    Call<JsonArray> getCiudades(@Path("user_id") String user_id,
                                 @Path("amigos") String amigos);

    //Obtener resultados
    @GET("/{user_id}/{amigos}/{ciudad}/resultados")
    Call<JsonArray> getResultados(@Path("user_id") String user_id,
                                   @Path("amigos") String amigos,
                                   @Path("ciudad") String ciudad);

    //Crear usuario
    @GET("/crearUsuario/{user_id}/{nombre}")
    Call<JsonArray> crearUsuario(@Path("user_id") String user_id,
                                 @Path("nombre") String nombre);

    //Obtener reseñas
    @GET("/resenias/{user_id}")
    Call<JsonArray> getResenias(@Path("user_id") String user_id);

    //Crear reseña
    @GET("/crearResenia/{user_id}/{restaurante_id}/{stars}")
    Call<JsonArray> crearResenia(@Path("user_id") String user_id,
                                 @Path("restaurante_id") String restaurante_id,
                                 @Path("stars") double stars);


    @GET("/infoUsuario/{user_id}")
    Call<JsonArray> getInfoUsuario(@Path("user_id") String user_id);

    @GET("/aniadirAmigo/{user_id}/{amigo_id}")
    Call<JsonArray> aniadirAmigo(@Path("user_id") String user_id,
                                 @Path("amigo_id") String amigo_id);
}

