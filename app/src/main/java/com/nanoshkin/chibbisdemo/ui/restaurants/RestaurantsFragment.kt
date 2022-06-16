package com.nanoshkin.chibbisdemo.ui.restaurants

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nanoshkin.chibbisdemo.R
import com.nanoshkin.chibbisdemo.databinding.FragmentRestaurantsBinding
import com.nanoshkin.chibbisdemo.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantsFragment : Fragment(R.layout.fragment_restaurants) {

    private val viewModel: RestaurantsViewModel by viewModels()
    private val binding by viewBinding(FragmentRestaurantsBinding::bind)
    private val adapter = RestaurantsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.dataRestaurants.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restaurantsRecyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    lifecycleScope.launch {
                        binding.restaurantsRecyclerView.scrollToPosition(0)
                        viewModel.search(query)
                        binding.searchView.clearFocus()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.searchView.setOnCloseListener {
            lifecycleScope.launch {
                viewModel.updateData()
                binding.restaurantsRecyclerView.scrollToPosition(0)
                Utils.hideKeyboard(requireView())
            }
            true
        }
    }
}