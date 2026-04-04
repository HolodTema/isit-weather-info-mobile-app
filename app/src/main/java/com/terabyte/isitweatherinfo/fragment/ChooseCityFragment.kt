package com.terabyte.isitweatherinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.terabyte.isitweatherinfo.databinding.FragmentChooseCityBinding

class ChooseCityFragment : Fragment() {

    private lateinit var binding: FragmentChooseCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonGetWeather.setOnClickListener {

        }
    }


    companion object {
        fun newInstance(): ChooseCityFragment {
            return ChooseCityFragment()
        }
    }
}