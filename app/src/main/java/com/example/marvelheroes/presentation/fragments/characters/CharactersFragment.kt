package com.example.marvelheroes.presentation.fragments.characters


import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.databinding.FragmentCharactersBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CharactersFragment : MyFragmentRoot() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager2 = binding.viewPager
        setViewPager(viewPager2)

    }

    private fun setViewPager(viewPager2: ViewPager2) {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->

            // ViewPager animations and appearance
            binding.viewPager.animate()
                .alpha(0f)
                .withEndAction {
                    viewPager2.adapter = characters?.let { CharactersViewPagerAdapter(it) }
                    binding.rightNav.visibility = View.VISIBLE
                    binding.leftNav.visibility = View.VISIBLE

                    binding.viewPager.visibility = View.VISIBLE
                    binding.viewPager.animate()
                        .alpha(1f)
                        .setDuration(200L)
                        .start()
                }
                .start()

            binding.viewPager.apply {
                clipChildren = false
                clipToPadding = false
                offscreenPageLimit = 3
                (getChildAt(0) as RecyclerView).overScrollMode =
                    RecyclerView.OVER_SCROLL_NEVER
            }

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer((20 * Resources.getSystem().displayMetrics.density).toInt()))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = (0.70f + r * 0.30f)
            }
            binding.viewPager.setPageTransformer(compositePageTransformer)

            setViewPagerNavigation(characters)
        }


    }

    private fun setViewPagerNavigation(characters: List<Character>) {
        binding.viewPager.registerOnPageChangeCallback(object: OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                binding.leftNav.visibility = if(position == 0)  View.INVISIBLE else View.VISIBLE

                binding.rightNav.visibility = if(position == characters.lastIndex) View.INVISIBLE else View.VISIBLE

                super.onPageSelected(position)
            }
        })

        if(binding.viewPager.currentItem == 0) {
            binding.leftNav.visibility = View.INVISIBLE
        }

        // Navigation arrows setting
        binding.rightNav.setOnClickListener {
            if (binding.viewPager.currentItem < binding.viewPager.right)
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)

        }

        binding.leftNav.setOnClickListener {
            if (binding.viewPager.currentItem > binding.viewPager.left)
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
        }
    }
}