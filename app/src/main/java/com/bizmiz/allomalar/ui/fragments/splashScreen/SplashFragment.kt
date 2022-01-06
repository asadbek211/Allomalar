package com.gita.allomalar.ui.fragments.splashScreen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gita.allomalar.ui.MainActivity
import com.Bizmiz.allomalar.R
import com.Bizmiz.allomalar.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        (activity as MainActivity).supportActionBar?.hide()
        binding = FragmentSplashBinding.bind(inflater.inflate(R.layout.fragment_splash, container, false))
        Handler().postDelayed({
            val navController: NavController =
                Navigation.findNavController(
                    requireActivity(),
                    R.id.fragmentContainer
                )

            navController.navigate(
                R.id.action_splashFragment_to_mainFragment
            )
        }, 1000)
        return binding.root
    }
}