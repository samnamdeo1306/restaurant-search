package com.sample.android.restaurantsearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sample.android.restaurantsearch.R
import com.sample.android.restaurantsearch.data.Restaurant
import com.sample.android.restaurantsearch.databinding.ActivitySearchBinding
import com.sample.android.restaurantsearch.repository.RestaurantRepository
import com.sample.android.restaurantsearch.ui.adapter.RestaurantAdapter

class SearchActivity  : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        val adapter = RestaurantAdapter(this, android.R.layout.simple_list_item_1, RestaurantRepository.getRestaurants(applicationContext))
        binding.searchRestaurant.setAdapter(adapter)
        binding.searchRestaurant.threshold = 1

        binding.searchRestaurant.setOnItemClickListener() { parent, _, position, id ->
            val selectedRestaurant = parent.adapter.getItem(position) as Restaurant?
            binding.searchRestaurant.setText(selectedRestaurant?.name)
        }
    }
}