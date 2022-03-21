package com.sample.android.restaurantsearch.data

import com.google.gson.annotations.SerializedName


data class Category(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("menu-items") var menuItems: ArrayList<MenuItems> = arrayListOf()
)