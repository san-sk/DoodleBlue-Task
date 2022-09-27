package com.san.doodleblue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.san.doodleblue.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val viewModel by viewModels<CommonViewModel>()

    // private val navController by lazy { this.findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setupActionBarWithNavController(navController)

        insertSampleData()

    }

    private fun insertSampleData() {
        viewModel.menuItems.observe(this) {
            if (it?.isEmpty() == true) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertSampleData()
                }
            }
        }
    }
}