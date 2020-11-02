package com.scootin.network.api

import com.scootin.network.response.orders.OrderHistoryList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiOrdersService {

    @GET("order-history/users/{userId}/get-all")
    fun getAllOrdersForUser(
        @Path("userId")userId: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize:Int
    ): Call<List<OrderHistoryList>>

    @GET("/maps/api/geocode/json?sensor=false")
    fun getPositionByZip(
        @Query("address") address: String?,
        cb: Callback<String?>?
    )

}