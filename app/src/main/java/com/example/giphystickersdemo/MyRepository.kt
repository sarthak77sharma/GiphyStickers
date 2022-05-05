package com.example.giphystickersdemo

class MyRepository () {

    suspend fun getGGifs(api_key : String,limit : String, rating : String  ) = RetrofitService().getClient()?.create(ApiInterface::class.java)
        ?.doGetListResources(api_key,limit,rating)

}