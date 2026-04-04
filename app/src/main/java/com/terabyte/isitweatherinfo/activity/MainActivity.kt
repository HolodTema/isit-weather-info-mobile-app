package com.terabyte.isitweatherinfo.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.terabyte.isitweatherinfo.application.MyApplication
import com.terabyte.isitweatherinfo.databinding.ActivityMainBinding
import com.terabyte.isitweatherinfo.di.component.ActivityComponent
import com.terabyte.isitweatherinfo.fragment.ChooseCityFragment
import com.terabyte.isitweatherinfo.navigation.ScreenState
import com.terabyte.isitweatherinfo.viewmodel.MainViewModel
import com.terabyte.isitweatherinfo.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent =
            (application as MyApplication).appComponent.activityComponentFactory().create()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureWindowInsets()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlowScreenState.collect { state ->
                    when(state) {
                        is ScreenState.ChooseCity -> {
                            replaceFragment(ChooseCityFragment.newInstance())
                        }
                        is ScreenState.Error -> {
                            replaceFragment(ChooseCityFragment())
                        }
                        is ScreenState.Loading -> {
                            replaceFragment(ChooseCityFragment())
                        }
                        is ScreenState.WeatherDetails -> {
                            replaceFragment(ChooseCityFragment())
                        }
                    }
                }
            }
        }
    }

    private fun configureWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameFragmentContainer.id, fragment)
            .commit()
    }
}