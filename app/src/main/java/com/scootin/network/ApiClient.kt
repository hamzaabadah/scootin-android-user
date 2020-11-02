package com.scootin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient{

    companion object{
        private var retrofit:Retrofit? = null
        private val BASE_URL = "https://ferrous-agency-286401.el.r.appspot.com/"

        fun getRetrofitInstance(): Retrofit?{
            if (retrofit==null){
                retrofit= Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }


}