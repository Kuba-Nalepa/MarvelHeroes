package com.example.marvelheroes.presentation.fragments.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelheroes.databinding.FragmentSeriesBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class SeriesFragment : MyFragmentRoot() {

    private val viewModel: SeriesViewModel by activityViewModel()
    private lateinit var binding: FragmentSeriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionAndStatusBar()
//        setTranslucentStatusBar()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.photosViewpager

        binding.tabLayout.addTab(binding.tabLayout.newTab())
        binding.tabLayout.addTab(binding.tabLayout.newTab())
        binding.tabLayout.addTab(binding.tabLayout.newTab())

        setImages(viewPager)
    }

    private fun setImages(viewPager: ViewPager2) {
        viewModel.series.observe(viewLifecycleOwner) {listSeries ->
            val adapter = ViewPagerAdapter(listSeries)
            viewPager.adapter = adapter
        }

    }
}