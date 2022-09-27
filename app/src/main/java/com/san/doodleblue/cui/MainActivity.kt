package com.san.doodleblue.cui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.san.doodleblue.cui.screens.CartScreen
import com.san.doodleblue.cui.screens.HomeScreen
import com.san.doodleblue.cui.theme.DoodleBlueTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoodleBlueTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }

}


@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen(
                navigateToCart = { navController.navigate("cart") },
            )
        }
        composable("cart") { CartScreen(onBackPressed = { navController.navigateUp() }) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DoodleBlueTheme {
        MyAppNavHost()
    }
}