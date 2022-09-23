package com.san.doodleblue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.san.doodleblue.R
import com.san.doodleblue.data.entity.MenuItem
import com.san.doodleblue.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

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
                    viewModel.insertSampleData(
                        listOf(
                            MenuItem(1, "Mutton Biryani", "Mutton Full", 250.0),
                            MenuItem(2, "Chicken Fry", "Fried Chicken 6 pcs", 100.0),
                            MenuItem(3, "Egg", "Boiled Egg", 15.0),
                            MenuItem(4, "Pron Fry", "Fired Pron 10pcs", 200.0),
                            MenuItem(5, "Chicken Biryani", "Chicken Full", 150.0),
                        )
                    )
                }
            }
        }
    }
}