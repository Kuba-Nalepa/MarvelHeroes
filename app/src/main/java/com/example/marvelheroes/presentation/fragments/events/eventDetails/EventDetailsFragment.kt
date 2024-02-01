package com.example.marvelheroes.presentation.fragments.events.eventDetails


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
import com.example.marvelheroes.databinding.FragmentEventDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class EventDetailsFragment : OnCharacterClick, BottomSheetDialogFragment() {

    private lateinit var binding: FragmentEventDetailsBinding
    private val navigationArgs: EventDetailsFragmentArgs by navArgs()
    private val vm: EventDetailsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getEventDetails(navigationArgs.id)
        vm.getCharactersEvent(navigationArgs.id)
        vm.getEventCreators(navigationArgs.id)

        setUi()
        setObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.viewModelStore?.clear()
    }

    private fun setUi() {
        binding.apply {

            toolBar.setNavigationIcon(R.drawable.back_arrow)

            toolBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            expandCreators.setOnClickListener {
                vm.getFiveMoreCreators(navigationArgs.id)
            }
        }
    }

    private fun setObservers() {
        binding.apply {
            vm.eventDetails.observe(viewLifecycleOwner) { event ->
                releaseDate.text = vm.getFormattedDates().first
                endDate.text = vm.getFormattedDates().second
                eventDescription.text = event.description
                collapsingToolBarLayout.title = event.title

                val currentEventImage = vm.getEventCurrentImage()
                Glide.with(requireView())
                    .load(currentEventImage)
                    .fitCenter()
                    .transform(BlurTransformation())
                    .into(collapsingImage)
            }

            vm.eventCharacters.observe(viewLifecycleOwner) { characters ->
                eventCharactersRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
                eventCharactersRecyclerView.adapter = EventCharactersAdapter(characters,this@EventDetailsFragment)
            }

            vm.eventCreators.observe(viewLifecycleOwner) { creators ->
                eventCreatorsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                eventCreatorsRecyclerView.adapter = creators?.let { CreatorsAdapter(it) }
                expandCreators.isVisible = creators?.size!! < 20

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