package com.gita.allomalar.ui.fragments.info

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gita.allomalar.ui.MainActivity
import com.Bizmiz.allomalar.R
import com.Bizmiz.allomalar.databinding.FragmentInfoBinding
import com.gita.allomalar.utils.hayotiList
import com.gita.allomalar.utils.imageList
import com.gita.allomalar.utils.list
import com.gita.allomalar.utils.listSana

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireActivity(),
            R.color.purple_200
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("RestrictedApi", "WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val prefs = requireActivity().getSharedPreferences("theme", AppCompatActivity.MODE_PRIVATE)
        if (prefs.getInt("number", 0) == 1) {
            requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
        (activity as MainActivity).check = true
        binding =
            FragmentInfoBinding.bind(inflater.inflate(R.layout.fragment_info, container, false))
        val position = arguments?.getInt("position", 0)
        binding.nomi.text = "${list[position!!]}\n\n${listSana[position]}"
        binding.text.text = getString(hayotiList[position])
        binding.infoImg.setImageResource(imageList[position])
        binding.infoBack.setOnClickListener {
            val navController: NavController =
                Navigation.findNavController(
                    requireActivity(),
                    R.id.fragmentContainer
                )
            navController.popBackStack()
        }
        return binding.root
    }
}