package com.capstone.skinory.data.remote.retrofit

import com.capstone.skinory.data.remote.response.LoginRequest
import com.capstone.skinory.data.remote.response.LoginResponse
import com.capstone.skinory.data.remote.response.ProductListResponse
import com.capstone.skinory.data.remote.response.RegisterRequest
import com.capstone.skinory.data.remote.response.RegisterResponse
import com.capstone.skinory.data.remote.response.RoutineListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("routine/{user_id}/day")
    suspend fun getDayRoutines(
        @Path("user_id") userId: String,
        @Header("Authorization") token: String
    ): RoutineListResponse

    @GET("routine/{user_id}/night")
    suspend fun getNightRoutines(
        @Path("user_id") userId: String,
        @Header("Authorization") token: String
    ): RoutineListResponse

    @GET("routine/{user_id}/{category}")
    suspend fun getProductsByCategory(
        @Path("user_id") userId: String,
        @Path("category") category: String,
        @Header("Authorization") token: String
    ): Response<ProductListResponse>

    @POST("routine/{user_id}/{category}/day/{product_id}")
    suspend fun saveRoutineDay(
        @Path("user_id") userId: String,
        @Path("category") category: String,
        @Path("product_id") idProduct: Int,
        @Body selectedProducts: Map<String, Int>,
        @Header("Authorization") token: String
    ): Response<Void>

    @POST("routine/{user_id}/{category}/night/{product_id}")
    suspend fun saveRoutineNight(
        @Path("user_id") userId: String,
        @Path("category") category: String,
        @Path("product_id") idProduct: Int,
        @Body selectedProducts: Map<String, Int>,
        @Header("Authorization") token: String
    ): Response<Void>

    @DELETE("routine/{user_id}/day")
    suspend fun deleteDayRoutine(
        @Path("user_id") userId: String,
        @Header("Authorization") token: String
    ): Response<Void>

    @DELETE("routine/{user_id}/night")
    suspend fun deleteNightRoutine(
        @Path("user_id") userId: String,
        @Header("Authorization") token: String
    ): Response<Void>
}