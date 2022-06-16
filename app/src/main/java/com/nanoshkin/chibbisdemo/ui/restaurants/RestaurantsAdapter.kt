package com.nanoshkin.chibbisdemo.ui.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nanoshkin.chibbisdemo.data.model.Restaurant
import com.nanoshkin.chibbisdemo.databinding.ItemRestaurantBinding
import com.nanoshkin.chibbisdemo.utils.Utils

class RestaurantsAdapter :
    ListAdapter<Restaurant, RestaurantsAdapter.RestaurantViewHolder>(RestaurantDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }

    class RestaurantViewHolder(
        private val binding: ItemRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            with(binding) {
                restaurantNameTextView.text = restaurant.name
                restaurantSpecializationsTextView.text =
                    restaurant.specializations.reduce { acc, s -> "$acc/ $s" }
                restaurantPositiveReviewsTextView.text = restaurant.positiveReviews.toString()
                restaurantReviewsCountTextView.text = Utils.formattingReviewsCount(restaurant.reviewsCount)
                restaurantMinCostTextView.text = Utils.formattingMinCost(restaurant.minCost)
                restaurantDeliveryCostTextView.text =
                    Utils.formattingDeliveryCost(restaurant.deliveryCost)
                restaurantDeliveryTimeTextView.text =
                    Utils.formattingDeliveryTime(restaurant.deliveryTime)
                restaurantLogoImageView.load(restaurant.logo)
            }
        }
    }

    private object RestaurantDiffItemCallback : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.name == newItem.name && oldItem.logo == newItem.logo
        }
    }
}