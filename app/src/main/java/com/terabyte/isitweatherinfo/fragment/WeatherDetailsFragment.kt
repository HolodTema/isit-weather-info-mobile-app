package com.terabyte.isitweatherinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.terabyte.core.util.DateHelper
import com.terabyte.isitweatherinfo.R
import com.terabyte.isitweatherinfo.activity.MainActivity
import com.terabyte.isitweatherinfo.databinding.FragmentWeatherDetailsBinding
import com.terabyte.isitweatherinfo.di.component.FragmentComponent
import com.terabyte.isitweatherinfo.navigation.ScreenState
import com.terabyte.isitweatherinfo.viewmodel.MainViewModel
import com.terabyte.isitweatherinfo.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherDetailsFragment : Fragment() {
    lateinit var fragmentComponent: FragmentComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
    }

    private lateinit var binding: FragmentWeatherDetailsBinding

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
        binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonChangeCity.setOnClickListener {
            viewModel.navigateToChooseCityScreen()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlowScreenState.collect { screenState ->
                    if (screenState is ScreenState.WeatherDetails) {
                        binding.textCityName.text = screenState.cityModel.name
                        binding.textCityCoordinates.text = getString(
                            R.string.city_lat_lon,
                            screenState.cityModel.latitude,
                            screenState.cityModel.longitude
                        )
                        binding.textDate.text = DateHelper.dateToString(screenState.cityModel.date)

                        binding.textSunriseTime.text = getString(
                            R.string.sunrise_time,
                            screenState.weatherModel.sunriseTime
                        )
                        binding.textSunsetTime.text = getString(
                            R.string.sunset_time,
                            screenState.weatherModel.sunsetTime
                        )
                        binding.textDayLen.text = getString(
                            R.string.day_len,
                            screenState.weatherModel.dayLen
                        )
                        binding.textDayTemperature.text = getString(
                            R.string.day_temperature,
                            screenState.weatherModel.dayTemp
                        )
                        binding.textNightTemperature.text = getString(
                            R.string.night_temperature,
                            screenState.weatherModel.nightTemp
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): WeatherDetailsFragment {
            return WeatherDetailsFragment()
        }
    }
}