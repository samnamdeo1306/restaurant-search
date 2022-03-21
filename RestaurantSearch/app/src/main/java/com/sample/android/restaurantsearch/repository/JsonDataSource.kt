package com.sample.android.restaurantsearch.repository

import android.content.Context
import com.google.gson.Gson
import com.sample.android.restaurantsearch.data.MenusWrapper
import com.sample.android.restaurantsearch.data.Restaurant
import com.sample.android.restaurantsearch.data.RestaurantsWrapper
import java.io.InputStream

class JsonDataSource(private val context: Context) {
    private val restaurantMap = mutableMapOf<Long, Restaurant>()
     init {
         val restString = readJSONFromAsset(context, "restaurants.json")
         val menuString = readJSONFromAsset(context, "menus.json")
         val restaurants = Gson().fromJson(restString, RestaurantsWrapper::class.java)
         val menus = Gson().fromJson(menuString, MenusWrapper::class.java)

         for(restaurant in restaurants.restaurants) {
             restaurantMap[restaurant.id] = restaurant
         }

         for(menu in menus.menus) {
             restaurantMap[menu.restaurantId]?.menu = menu
         }
     }

    fun getRestaurants(): List<Restaurant> {
        val list = mutableListOf<Restaurant>()
        for (entry in restaurantMap.values) {
            list.add(entry)
        }
        return list
    }

    private fun readJSONFromAsset(context: Context, fileName: String): String {
        val json: String
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use {
                it.readText()
            }
        } catch (ex: Exception) {
            ex.localizedMessage
            return ""
        }
        return json
    }
}