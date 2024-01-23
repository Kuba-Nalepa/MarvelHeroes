package com.example.marvelheroes.presentation.fragments.comics.comicsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvelheroes.R
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.databinding.FragmentComicsDetailsBinding
import com.example.marvelheroes.presentation.fragments.events.eventDetails.OnCharacterClick
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class ComicsDetailsFragment : OnCharacterClick, BottomSheetDialogFragment() {
    private lateinit var binding: FragmentComicsDetailsBinding
    private val navigationArgs: ComicsDetailsFragmentArgs by navArgs()
    private val vm: ComicsDetailsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicsDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getComicsDetails(navigationArgs.id)
        vm.getComicCharacters(navigationArgs.id)

        setUi()
        setObservers()
    }

    private fun setUi() {
        binding.apply {

            toolBar.setNavigationIcon(R.drawable.back_arrow)

            toolBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

    private fun setObservers() {
        binding.apply {
            vm.comicsDetails.observe(viewLifecycleOwner) { comicBook ->
                eventDescription.text = comicBook.description[0].text
                collapsingToolBarLayout.title = comicBook.title
                price.text = getString(R.string.comics_price, comicBook.prices[0].price)

                val currentComicsImage = vm.getComicsCurrentImage()
                Glide.with(requireView())
                    .load(currentComicsImage)
                    .fitCenter()
                    .transform(BlurTransformation())
                    .into(collapsingImage)

            }

            vm.comicCharacters.observe(viewLifecycleOwner) { characters ->
                comicsCharactersRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                comicsCharactersRecyclerView.adapter = ComicsCharactersAdapter(characters, this@ComicsDetailsFragment)
            }

            vm.isLoading.observe(viewLifecycleOwner) {
                progressBar.isVisible = it
                expandCreators.isVisible = !it

            }
        }
    }

    override fun onCharacterCLick(character: Character, position: Int) {
        TODO("Not yet implemented")
    }

}