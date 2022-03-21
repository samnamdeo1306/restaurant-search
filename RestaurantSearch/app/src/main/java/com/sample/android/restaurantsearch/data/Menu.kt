package com.sample.android.restaurantsearch.data

import com.google.gson.annotations.SerializedName


data class Menu(

    @SerializedName("restaurantId") var restaurantId: Long,
    @SerializedName("categories") var categories: ArrayList<Category> = arrayListOf()

)