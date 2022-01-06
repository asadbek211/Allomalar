package com.gita.allomalar.ui.fragments.settings

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gita.allomalar.ui.MainActivity
import com.Bizmiz.allomalar.R
import com.Bizmiz.allomalar.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).check = true
        (activity as MainActivity).supportActionBar?.hide()
        binding = FragmentSettingsBinding.bind(
            inflater.inflate(
                R.layout.fragment_settings,
                container,
                false
            )
        )
        val prefs = requireActivity().getSharedPreferences("theme", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        binding.apply {
            switchView.isChecked = prefs.getInt("number", 0) == 1
            switchView.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    editor.putInt("number", 1)
                    requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(
                        0,
                        APPEARANCE_LIGHT_STATUS_BARS
                    )
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchView.isChecked = true
                    editor.apply()
                } else {
                    editor.putInt("number", 0)
                    requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchView.isChecked = false
                    editor.apply()
                }
            }
            settingsBack.setOnClickListener {
                val navController: NavController =
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.fragmentContainer
                    )
                navController.popBackStack()
            }
        }
        return binding.root
    }
}