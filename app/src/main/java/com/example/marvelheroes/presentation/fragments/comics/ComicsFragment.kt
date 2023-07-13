package com.example.marvelheroes.presentation.fragments.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelheroes.databinding.FragmentSeriesBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ComicsFragment : MyFragmentRoot() {

    private lateinit var binding: FragmentSeriesBinding
    private val viewModel: ComicsViewModel by activityViewModel()

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

        viewModel.comics.observe(viewLifecycleOwner) {

        }
    }
}