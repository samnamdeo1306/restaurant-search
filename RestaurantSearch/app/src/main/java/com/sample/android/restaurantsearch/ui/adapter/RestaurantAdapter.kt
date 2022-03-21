package com.sample.android.restaurantsearch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.sample.android.restaurantsearch.data.Restaurant

class RestaurantAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val allRestaurants: List<Restaurant>
) :
    ArrayAdapter<Restaurant>(context, layoutResource, allRestaurants),
    Filterable {
    private var restaurantList: List<Restaurant> = allRestaurants

    override fun getCount(): Int {
        return restaurantList.size
    }

    override fun getItem(p0: Int): Restaurant? {
        return restaurantList[p0]

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context)
            .inflate(layoutResource, parent, false) as TextView
        view.text = "${restaurantList[position].name}"
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: Filter.FilterResults
            ) {
                if (filterResults.values != null) {
                    restaurantList = filterResults.values as List<Restaurant>
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString == null || queryString.isEmpty()) {
                    allRestaurants
                } else {
                    val list = mutableListOf<Restaurant>()
                    for (restaurant in allRestaurants) {
                        if (restaurant.name.toLowerCase().contains(queryString)) {
                            list.add(restaurant)
                        } else if (restaurant.cuisineType.toLowerCase().contains(queryString)) {
                            list.add(restaurant)
                        } else {
                            if (restaurant.menu != null) {
                                var added = false
                                for (category in restaurant.menu!!.categories) {
                                    for (menuItem in category.menuItems) {
                                        if (menuItem.name.toLowerCase().contains(queryString)) {
                                            list.add(restaurant)
                                            added = true
                                            break;
                                        }
                                    }
                                    if(added)
                                        break;
                                }
                            }
                        }
                    }
                    list
                }

                return filterResults
            }

        }
    }
}