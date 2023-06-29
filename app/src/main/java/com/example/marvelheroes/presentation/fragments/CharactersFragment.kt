package com.example.marvelheroes.presentation.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelheroes.R
import com.example.marvelheroes.presentation.MyFragmentRoot

class CharactersFragment : MyFragmentRoot() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionAndStatusBar()
//        setTranslucentStatusBar()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }
}