package com.example.test22byte.fragments

import android.os.Bundle
import android.view.View
import com.example.test22byte.R
import com.example.test22byte.basefragment.BaseFragment
import com.example.test22byte.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPlay.setOnClickListener {
            goToNextFragment()
        }
    }

    private fun goToNextFragment() {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, StartFragment())
            ?.commit()
    }
}