package com.example.marvelheroes.presentation


import androidx.fragment.app.Fragment


open class MyFragmentRoot: Fragment() {
    override fun onDestroy() {
        super.onDestroy()
        activity?.viewModelStore?.clear()
    }

}