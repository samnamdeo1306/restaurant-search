package com.sample.android.restaurantsearch.data

import com.google.gson.annotations.SerializedName

data class MenuItems(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("images") var images: ArrayList<String> = arrayListOf()
)
