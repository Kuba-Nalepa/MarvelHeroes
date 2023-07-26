package com.example.marvelheroes.presentation.fragments.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelheroes.databinding.FragmentEventsBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class EventsFragment : MyFragmentRoot() {

    private val viewModel: EventsViewModel by activityViewModel()
    private lateinit var binding: FragmentEventsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionAndStatusBar()
//        setTranslucentStatusBar()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager2 = binding.seriesViewpager

        setUI(viewPager2)

    }

    private fun setUI(viewPager2: ViewPager2) {
        viewModel.series.observe(viewLifecycleOwner) { listSeries ->
            Log.d("fragment", listSeries.toString())
            val adapter = ViewPagerAdapter(listSeries)
            viewPager2.adapter = adapter
            val tabLayout = binding.tabLayout
            TabLayoutMediator(tabLayout, viewPager2) {  _, _ -> }.attach()

        }

    }
}