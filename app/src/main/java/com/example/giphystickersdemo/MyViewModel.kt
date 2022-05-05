package com.example.giphystickersdemo

import BaseResponseModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel(val repository: MyRepository) : ViewModel() {

    private val gifLiveData = MutableLiveData<BaseResponseModel>()
    private val TAG = "MyViewModel"

    fun initData(api_key : String,limit : String, rating : String){

        viewModelScope.launch {
            getGifData(api_key,limit, rating)
        }

    }

    fun getGifLiveData() : LiveData<BaseResponseModel>{
        return gifLiveData
    }

    suspend fun getGifData(api_key : String,limit : String, rating : String) {
        var response = repository.getGGifs(api_key,limit, rating)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<BaseResponseModel>?) {
        if (response != null){
//            Log.d(TAG, "handleResponse: ${response.body()}")
            gifLiveData.postValue(response.body())
        }
    }

}