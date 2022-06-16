package com.nanoshkin.chibbisdemo.ui.hits

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nanoshkin.chibbisdemo.R
import com.nanoshkin.chibbisdemo.databinding.FragmentHitsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HitsFragment : Fragment(R.layout.fragment_hits) {

    private val viewModel: HitsViewModel by viewModels()
    private val binding by viewBinding(FragmentHitsBinding::bind)
    private val adapter = HitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.dataHits.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hitsRecyclerView.adapter = adapter
    }
}