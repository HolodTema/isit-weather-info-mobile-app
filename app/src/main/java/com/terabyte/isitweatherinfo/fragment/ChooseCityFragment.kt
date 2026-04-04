package com.terabyte.isitweatherinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.terabyte.isitweatherinfo.activity.MainActivity
import com.terabyte.isitweatherinfo.databinding.FragmentChooseCityBinding
import com.terabyte.isitweatherinfo.di.component.FragmentComponent
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
        binding.buttonGetWeather.setOnClickListener {
            val cityName = binding.editCityName.text.toString()
//            viewModel.
        }
    }


    companion object {
        fun newInstance(): ChooseCityFragment {
            return ChooseCityFragment()
        }
    }
}