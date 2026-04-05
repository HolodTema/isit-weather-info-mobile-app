package com.terabyte.isitweatherinfo.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.terabyte.isitweatherinfo.activity.MainActivity
import com.terabyte.isitweatherinfo.databinding.FragmentChooseCityBinding
import com.terabyte.isitweatherinfo.di.component.FragmentComponent
import com.terabyte.isitweatherinfo.navigation.ScreenState
import com.terabyte.isitweatherinfo.viewmodel.MainViewModel
import com.terabyte.isitweatherinfo.viewmodel.ViewModelFactory
import javax.inject.Inject

class ChooseCityFragment : Fragment() {

    lateinit var fragmentComponent: FragmentComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
    }

    private lateinit var binding: FragmentChooseCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent =
            (requireActivity() as MainActivity).activityComponent.fragmentComponentFactory()
                .create()
        fragmentComponent.inject(this)
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
        val screenState = viewModel.stateFlowScreenState.value
        if (screenState is ScreenState.ChooseCity) {
            binding.editCityName.setText(screenState.cityName)
        }

        binding.editCityName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //do nothing
            }

            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                //do nothing
            }

            override fun onTextChanged(
                text: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                binding.buttonGetWeather.isEnabled = text?.isNotBlank() ?: false
            }
        })

        binding.buttonGetWeather.isEnabled = binding.editCityName.text.isNotEmpty()
        binding.buttonGetWeather.setOnClickListener {
            val cityName = binding.editCityName.text.toString().trim()
            viewModel.loadWeatherDetails(cityName)
        }
    }

    override fun onStop() {
        super.onStop()
        val cityName = binding.editCityName.text.toString().trim()
        viewModel.saveChooseCityScreenState(cityName)
    }


    companion object {
        fun newInstance(): ChooseCityFragment {
            return ChooseCityFragment()
        }
    }
}