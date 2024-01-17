package com.example.marvelheroes.presentation.fragments.comics.comicsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelheroes.R
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.databinding.FragmentEventDetailsBinding
import com.example.marvelheroes.presentation.fragments.events.eventDetails.EventDetailsFragmentArgs
import com.example.marvelheroes.presentation.fragments.events.eventDetails.OnCharacterClick
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class ComicsDetailsFragment : OnCharacterClick, BottomSheetDialogFragment() {
    private lateinit var binding: FragmentEventDetailsBinding
    private val navigationArgs: EventDetailsFragmentArgs by navArgs()
    private val vm: ComicsDetailsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

    }

    override fun onCharacterCLick(character: Character, position: Int) {
        TODO("Not yet implemented")
    }

}