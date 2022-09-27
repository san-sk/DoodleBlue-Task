package com.san.doodleblue.cui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.san.doodleblue.core.data.entity.MenuItem


@Composable
fun CartList(
    modifier: Modifier = Modifier,
    menus: List<MenuItem>,
    callBack: (onChange: MenuItem) -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        LazyColumn(Modifier.padding(10.dp)) {
            items(menus) {
                MenuItem(item = it) {
                    callBack.invoke(it)
                }
            }

        }
    }
}

@Composable
fun MenuItemList(
    modifier: Modifier,
    menus: List<MenuItem>,
    callBack: (onChange: MenuItem) -> Unit
) {
    LazyColumn(
        modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        items(menus) {
            MenuItem(item = it) {
                callBack.invoke(it)
            }
        }
    }
}

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    item: MenuItem,
    callBack: (onChange: MenuItem) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row {
            Column(Modifier.weight(7f)) {
                Text(
                    modifier = modifier.padding(4.dp),
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = modifier.padding(4.dp),
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = modifier.padding(4.dp),
                    text = item.price.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            CounterView(Modifier.weight(3f), count = item.count) {
                callBack.invoke(item.copy(count = it))
            }
        }
        Divider()
    }
}