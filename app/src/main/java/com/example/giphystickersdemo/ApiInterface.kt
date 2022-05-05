package com.example.giphystickersdemo

import BaseResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("trending")
    suspend fun doGetListResources(@Query("api_key") api_key: String
                           ,@Query("limit") limit: String
                           ,@Query("rating") rating: String)
    : Response<BaseResponseModel>

}