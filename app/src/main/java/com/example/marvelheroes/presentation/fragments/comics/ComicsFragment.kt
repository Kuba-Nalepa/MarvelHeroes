package com.example.marvelheroes.presentation.fragments.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelheroes.databinding.FragmentComicsBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ComicsFragment : MyFragmentRoot() {

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

        viewModel.comics.observe(viewLifecycleOwner) {

        }
    }
}