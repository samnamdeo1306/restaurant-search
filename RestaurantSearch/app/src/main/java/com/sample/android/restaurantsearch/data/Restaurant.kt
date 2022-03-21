package com.sample.android.restaurantsearch.data

import com.google.gson.annotations.SerializedName


data class Restaurant(
    @SerializedName("id") var id: Long,
    @SerializedName("name") var name: String,
    @SerializedName("neighborhood") var neighborhood: String,
    @SerializedName("photograph") var photograph: String? = null,
    @SerializedName("address") var address: String,
    @SerializedName("latlng") var latlng: Latlng? = Latlng(),
    @SerializedName("cuisine_type") var cuisineType: String,
    @SerializedName("operating_hours") var operatingHours: OperatingHours? = OperatingHours(),
    @SerializedName("reviews") var reviews: ArrayList<Reviews> = arrayListOf(),
    var menu: Menu?
)