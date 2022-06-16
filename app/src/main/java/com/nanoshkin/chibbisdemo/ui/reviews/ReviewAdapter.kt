package com.nanoshkin.chibbisdemo.ui.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nanoshkin.chibbisdemo.R
import com.nanoshkin.chibbisdemo.data.model.Review
import com.nanoshkin.chibbisdemo.databinding.ItemReviewBinding
import com.nanoshkin.chibbisdemo.utils.Utils

class ReviewAdapter :
    ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(ReviewDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding =
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val hit = getItem(position)
        holder.bind(hit)
    }

    class ReviewViewHolder(
        private val binding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            with(binding) {
                reviewUserNameTextView.text = review.user
                reviewDateTextView.text = Utils.formattingReviewDate(review.date)
                reviewMessageTextView.text = review.message
                reviewRestaurantNameTextView.text = review.restaurantName
                reviewPositiveImageView.load(
                    if (review.isPositive) R.drawable.ic_thumb_up_24 else R.drawable.ic_thumb_down_24
                )
            }
        }
    }

    private object ReviewDiffItemCallback : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.user == newItem.user && oldItem.date == newItem.date
        }
    }
}