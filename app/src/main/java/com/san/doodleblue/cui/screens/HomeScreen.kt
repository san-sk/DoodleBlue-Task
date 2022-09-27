package com.san.doodleblue.cui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.san.doodleblue.R.drawable.top_view_indian_food
import com.san.doodleblue.cui.theme.DoodleBlueTheme
import com.san.doodleblue.cui.widgets.MenuItemList
import com.san.doodleblue.ui.restaurant.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navigateToCart: () -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    val items by viewModel.menuItems.observeAsState(listOf())
    val cartCount by viewModel.cartCount.observeAsState(0)

    val image: Painter = painterResource(id = top_view_indian_food)

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

        Row {
            Icon(Icons.Default.ArrowBack, "back", tint = Color.White)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = image,
                contentDescription = "restaurant banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                contentScale = ContentScale.FillBounds
            )
            MenuItemList(
                modifier = modifier.weight(6f),
                menus = items
            ) {
                coroutineScope.launch {
                    viewModel.updateMenuItem(it)
                }
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .weight(1f),
                shape = Shapes().extraSmall,
                onClick = {
                    navigateToCart.invoke()
                }) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "cart"
                )
                Text(text = "View Cart ( $cartCount )")
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    DoodleBlueTheme() {
        HomeScreen() {

        }
    }
}