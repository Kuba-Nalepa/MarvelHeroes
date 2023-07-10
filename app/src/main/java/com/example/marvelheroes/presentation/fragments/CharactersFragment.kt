package com.example.marvelheroes.presentation.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelheroes.databinding.FragmentCharactersBinding
import com.example.marvelheroes.presentation.MyFragmentRoot
import com.example.marvelheroes.presentation.viewmodels.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CharactersFragment : MyFragmentRoot() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionAndStatusBar()
//        setTranslucentStatusBar()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiObserver()

    }

    private fun uiObserver() {
        viewModel.characters.observe(viewLifecycleOwner) {
            Log.d("frag", it.toString())
        }
    }
}