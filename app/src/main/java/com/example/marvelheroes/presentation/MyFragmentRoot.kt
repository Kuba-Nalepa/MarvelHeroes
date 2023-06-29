package com.example.marvelheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


open class MyFragmentRoot: Fragment() {

    fun hideActionAndStatusBar() {
//        requireActivity().window.statusBarColor = Color.parseColor("#20111111");
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    fun setTranslucentStatusBar() {
//        activity?.window?.statusBarColor = Color.parseColor("#55000000")
    }

    fun showActionAndStatusBar() {
//        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}