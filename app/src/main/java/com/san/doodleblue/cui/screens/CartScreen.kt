package com.san.doodleblue.cui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.san.doodleblue.cui.theme.DoodleBlueTheme
import com.san.doodleblue.cui.widgets.CartList
import com.san.doodleblue.ui.restaurant.CartViewModel
import kotlinx.coroutines.launch

@Composable
fun CartScreen(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    val viewModel: CartViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    val items by viewModel.cartItemVisibleList.observeAsState(listOf())
    val totalAmount by viewModel.totalAmount.observeAsState(0.0)
    val showMore by viewModel.showMore.observeAsState()

    Column(modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .weight(30f)
        ) {
            Row(Modifier.padding(10.dp)) {
                IconButton(onClick = { onBackPressed.invoke() }) {
                    Icon(Icons.Default.ArrowBack, "back", tint = Color.White)
                }
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                    text = "My Cart",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Card(
                Modifier
                    .size(150.dp, 100.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(1f)
                        .padding(top = 14.dp),
                    text = "Total Cost",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(1f),
                    text = "$totalAmount",
                    style = MaterialTheme.typography.titleLarge
                )

            }
        }

        CartList(
            Modifier.weight(70f),
            menus = items ?: listOf()
        ) {
            coroutineScope.launch {
                viewModel.updateMenuItem(it)
            }
        }

        if (showMore == true) {
            TextButton(onClick = {
                viewModel.showMore.value = false
            }) {
                Text(text = "Show More")
            }
        }


    }
}


@Preview
@Composable
fun CartPreview() {
    DoodleBlueTheme() {
        CartScreen() {

        }
    }
}