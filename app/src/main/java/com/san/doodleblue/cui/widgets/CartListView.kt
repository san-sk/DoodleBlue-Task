package com.san.doodleblue.cui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.san.doodleblue.core.data.entity.MenuItem

@Composable
fun CartListView(
    modifier: Modifier = Modifier,
    menus: List<MenuItem>,
    canShow: Boolean,
    callBack: (onChange: MenuItem) -> Unit
) {
    var showMore by remember { mutableStateOf(!canShow) }

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {

        if (menus.size > 2 && showMore) {
            CartListView(cartItems = menus.take(2), callBack = callBack)
        } else {
            CartListView(cartItems = menus, callBack = callBack)
        }

        //uncomment this if you only want to show more option
        //  if (showMore) {
        TextButton(onClick = {
            showMore = showMore.not()
        }) {
            Text(
                text = if (showMore) "Show more" else "Show less"
            )
        }
        //  }
    }
}

@Composable
fun CartListView(cartItems: List<MenuItem>, callBack: (onChange: MenuItem) -> Unit) {
    LazyColumn(Modifier.padding(10.dp)) {
        items(cartItems) {
            MenuItem(item = it) {
                callBack.invoke(it)
            }
        }
    }
}

@Preview
@Composable
fun CLVP() {
    CartListView(menus = listOf(
        MenuItem(0, "biryani", "de", 50.0, 2),
        MenuItem(0, "biryani", "de", 50.0, 2),
        MenuItem(0, "biryani", "de", 50.0, 2),
        MenuItem(0, "biryani", "de", 50.0, 2),
    ), canShow = true, callBack = {})
}