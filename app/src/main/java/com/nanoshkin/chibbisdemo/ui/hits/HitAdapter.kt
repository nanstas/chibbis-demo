package com.nanoshkin.chibbisdemo.ui.hits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nanoshkin.chibbisdemo.data.model.Hit
import com.nanoshkin.chibbisdemo.databinding.ItemHitBinding
import com.nanoshkin.chibbisdemo.utils.Utils

class HitAdapter :
    ListAdapter<Hit, HitAdapter.HitViewHolder>(HitDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        val binding =
            ItemHitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        val hit = getItem(position)
        holder.bind(hit)
    }

    class HitViewHolder(
        private val binding: ItemHitBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hit: Hit) {
            with(binding) {
                hitProductNameTextView.text = hit.name
                hitProductDescriptionTextView.text = hit.description
                hitRestaurantNameTextView.text = hit.restaurantName
                hitPriceTextView.text = Utils.formattingHitCost(hit.price)
                hitProductImageView.load(hit.image)
                hitRestaurantLogoImageView.load(hit.restaurantLogo)
            }
        }
    }

    private object HitDiffItemCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.name == newItem.name && oldItem.restaurantName == newItem.restaurantName
        }
    }
}