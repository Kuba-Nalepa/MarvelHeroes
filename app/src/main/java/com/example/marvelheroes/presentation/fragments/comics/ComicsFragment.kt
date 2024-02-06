package com.example.marvelheroes.presentation.fragments.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.databinding.FragmentComicsBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import com.example.marvelheroes.presentation.fragments.comics.comicsDetails.OnComicsClick
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ComicsFragment : MyFragmentRoot(), OnComicsClick {

    private lateinit var binding: FragmentComicsBinding
    private val viewModel: ComicsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager2 = binding.comicsViewpager
        setViewPager(viewPager2)
        setAllComics()

    }

    private fun setAllComics() {
        viewModel.allComics.observe(viewLifecycleOwner) { allComics ->
            binding.comicsRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
            binding.comicsRecyclerView.adapter = ComicsAdapter(allComics, this)

            setSearchView(allComics)
        }
    }

    private fun setViewPager(viewPager2: ViewPager2) {

        viewModel.homePageCOmics.observe(viewLifecycleOwner) { mainSectionComics ->
            val viewPagerAdapter = ViewPagerAdapter(mainSectionComics)
            viewPager2.adapter = viewPagerAdapter
            val tabLayout = binding.tabLayout
            TabLayoutMediator(tabLayout, viewPager2) { _, _ -> }.attach()

            setViewPagerInfinite(mainSectionComics.size + 2)
        }
    }

    private fun setViewPagerInfinite(listSize: Int) {
        binding.comicsViewpager.currentItem = 1
        val first = binding.tabLayout.getTabAt(0)
        val last = binding.tabLayout.getTabAt(4)
        first?.view?.visibility = View.GONE
        last?.view?.visibility = View.GONE
        binding.comicsViewpager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding.comicsViewpager.currentItem) {
                        listSize - 1 -> binding.comicsViewpager.setCurrentItem(1, false)
                        0 -> binding.comicsViewpager.setCurrentItem(listSize - 2, false)
                    }
                }
            }
        })
    }

    private fun setSearchView(list: List<ComicBook>) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = list.filter {
                    newText != null && it.title.contains(newText, true)
                }
                (binding.comicsRecyclerView.adapter as ComicsAdapter).update(filtered)
                return false
            }
        })
    }

    override fun onComicsClick(comic: ComicBook, position: Int) {
        binding.tabLayout.removeTabAt(0)
        val action = ComicsFragmentDirections.actionComicsFragmentToComicsDetailsFragment(comic.id)
        findNavController().navigate(action)
        binding.searchView.setQuery("", false)
    }
}