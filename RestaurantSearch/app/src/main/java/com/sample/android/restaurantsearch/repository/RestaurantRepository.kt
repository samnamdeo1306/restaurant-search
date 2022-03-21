package com.sample.android.restaurantsearch.repository

import android.content.Context
import com.sample.android.restaurantsearch.data.Restaurant

object RestaurantRepository : IRestaurantRepository{

    fun getRestaurants(context: Context): List<Restaurant> {
        val source = JsonDataSource(context)
        return source.getRestaurants()
    }
}