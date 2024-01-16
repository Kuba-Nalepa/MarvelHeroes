package com.example.marvelheroes.presentation.fragments.events


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.databinding.FragmentEventsBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class EventsFragment : MyFragmentRoot() , OnEventClick {

    private val viewModel: EventsViewModel by activityViewModel()
    private lateinit var binding: FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager2 = binding.eventsViewpager
        setViewPager(viewPager2)
        setAllEvents()

    }

    private fun setViewPager(viewPager2: ViewPager2) {
        viewModel.homePageEvents().observe(viewLifecycleOwner) { mainSectionEvents ->
            val viewPagerAdapter = ViewPagerAdapter(mainSectionEvents)
            viewPager2.adapter = viewPagerAdapter
            val tabLayout = binding.tabLayout
            TabLayoutMediator(tabLayout, viewPager2) { _, _ ->
                Log.d("TABY event", tabLayout.tabCount.toString())
            }.attach()

            setViewPagerInfinite(mainSectionEvents.size + 2)

        }
    }

    private fun setAllEvents() {
        viewModel.allEvents().observe(viewLifecycleOwner) { allEvents ->
            binding.eventsRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
            binding.eventsRecyclerView.adapter = EventsAdapter(allEvents, this)

            setSearchView(allEvents)
        }
    }

    private fun setSearchView(list: List<Event>) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = list.filter {
                    newText!= null && it.title.contains(newText, true)
                }
                (binding.eventsRecyclerView.adapter as EventsAdapter).update(filtered)
                return false
            }
        })
    }

    private fun setViewPagerInfinite(listSize: Int) {
        binding.eventsViewpager.currentItem = 1
        val first = binding.tabLayout.getTabAt(0)
        val last = binding.tabLayout.getTabAt(4)
        first?.view?.visibility = View.GONE
        last?.view?.visibility = View.GONE
        binding.eventsViewpager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding.eventsViewpager.currentItem) {
                        listSize - 1 -> binding.eventsViewpager.setCurrentItem(1, false)
                        0 -> binding.eventsViewpager.setCurrentItem(listSize - 2, false)
                    }
                }
            }
        })
    }

    override fun onEventClick(event: Event, position: Int) {
        binding.tabLayout.removeTabAt(0)
        val action = EventsFragmentDirections.actionEventFragmentToEventDetailsFragment(event.id)
        findNavController().navigate(action)
        binding.searchView.setQuery("", false)
    }
}