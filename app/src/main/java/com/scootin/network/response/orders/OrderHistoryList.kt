package com.scootin.network.response.orders

import com.google.gson.annotations.SerializedName

data class OrderHistoryList (
//     val id:Int,
//     val orderId:String,
//     val deliveryType:String,
//     val orderDate:String,
//     val status:String,
//     val amount:String
    @SerializedName("id") val id : Int,
    @SerializedName("totalAmount") val totalAmount : String,
    @SerializedName("orderStatus") val orderStatus : String,
    @SerializedName("rejectReason") val rejectReason : String,
    @SerializedName("orderDate") val orderDate : OrderDate,
    @SerializedName("userInfo") val userInfo : UserInfo,
    @SerializedName("addressDetails") val addressDetails : AddressDetails,
    @SerializedName("expressDelivery") val expressDelivery : Boolean,
    @SerializedName("directOrder") val directOrder : Boolean

 )



data class AddressDetails (

    @SerializedName("id") val id : Int,
    @SerializedName("addressLine1") val addressLine1 : String,
    @SerializedName("addressLine2") val addressLine2 : String,
    @SerializedName("city") val city : String,
    @SerializedName("pincode") val pincode : Int,
    @SerializedName("addressType") val addressType : String,
    @SerializedName("stateDetails") val stateDetails : StateDetails,
    @SerializedName("userInfo") val userInfo : UserInfo,
    @SerializedName("deleted") val deleted : Boolean,
    @SerializedName("hasDefault") val hasDefault : Boolean
)

data class StateDetails (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("countryDetails") val countryDetails : CountryDetails
)

data class CountryDetails (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)

data class OrderDate (

    @SerializedName("nano") val nano : Int,
    @SerializedName("epochSecond") val epochSecond : Int
)

data class UserInfo (

    @SerializedName("id") val id : Int,
    @SerializedName("profilePicture") val profilePicture : String,
    @SerializedName("password") val password : String,
    @SerializedName("mobileNumber") val mobileNumber : String,
    @SerializedName("email") val email : String,
    @SerializedName("lastName") val lastName : String,
    @SerializedName("firstName") val firstName : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("modified") val modified : String,
    @SerializedName("deleted") val deleted : Boolean,
    @SerializedName("active") val active : Boolean,
    @SerializedName("fcmId") val fcmId : String,
    @SerializedName("otp") val otp : String,
    @SerializedName("otpExpireTime") val otpExpireTime : String,
    //@SerializedName("walletInfoDetails") val walletInfoDetails : String
)