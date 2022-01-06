package com.gita.allomalar.ui.fragments.main

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gita.allomalar.ui.MainActivity
import com.Bizmiz.allomalar.R
import com.gita.allomalar.adapter.ItemAdapter
import com.Bizmiz.allomalar.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val prefs = requireActivity().getSharedPreferences("theme", AppCompatActivity.MODE_PRIVATE)
        if (prefs.getInt("number", 0) == 1) {
            requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(
                0,
                APPEARANCE_LIGHT_STATUS_BARS
            )
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            requireActivity().window.statusBarColor = ContextCompat.getColor(
                requireActivity(),
                R.color.black2
            )
        } else {
            requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(
                APPEARANCE_LIGHT_STATUS_BARS,
                APPEARANCE_LIGHT_STATUS_BARS
            )
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            requireActivity().window.statusBarColor = ContextCompat.getColor(
                requireActivity(),
                R.color.white
            )
        }
        (activity as MainActivity).supportActionBar?.hide()
        (activity as MainActivity).check = false
        binding =
            FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container, false))
        itemAdapter = ItemAdapter()
        binding.recView.adapter = itemAdapter
        itemAdapter.setOnclickListener { position ->
            navigate(R.id.action_mainFragment_to_infoFragment, position)
        }
        binding.settings.setOnClickListener {
            navigate(R.id.action_mainFragment_to_settingsFragment, 0)
        }
        return binding.root

    }

    private fun navigate(destination: Int, position: Int) {
        val bundle = bundleOf(
            "position" to position
        )
        val navController: NavController =
            Navigation.findNavController(
                requireActivity(),
                R.id.fragmentContainer
            )
        navController.navigate(
            destination, bundle
        )

    }
}