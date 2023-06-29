package com.example.marvelheroes.presentation.fragments.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelheroes.databinding.FragmentSeriesBinding
import com.example.marvelheroes.presentation.MyFragmentRoot


class SeriesFragment : MyFragmentRoot() {

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

        val pager = binding.photosViewpager
//        val adapter: PagerAdapter = PhotosAdapter(childFragmentManager, photosUrl)
//        pager.adapter = adapter

        val tabLayout = binding.tabLayout
        tabLayout.setupWithViewPager(pager, true)

        binding.tabLayout.addTab(binding.tabLayout.newTab())
        binding.tabLayout.addTab(binding.tabLayout.newTab())
        binding.tabLayout.addTab(binding.tabLayout.newTab())

    }
}