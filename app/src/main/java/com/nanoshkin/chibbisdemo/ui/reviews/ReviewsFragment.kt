package com.nanoshkin.chibbisdemo.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nanoshkin.chibbisdemo.R
import com.nanoshkin.chibbisdemo.databinding.FragmentHitsBinding
import com.nanoshkin.chibbisdemo.databinding.FragmentReviewsBinding
import com.nanoshkin.chibbisdemo.ui.hits.HitAdapter
import com.nanoshkin.chibbisdemo.ui.hits.HitsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ReviewsFragment : Fragment(R.layout.fragment_reviews) {

    private val viewModel: ReviewsViewModel by viewModels()
    private val binding by viewBinding(FragmentReviewsBinding::bind)
    private val adapter = ReviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.dataReviews.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reviewsRecyclerView.adapter = adapter
    }
}