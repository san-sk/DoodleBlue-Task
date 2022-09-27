package com.san.doodleblue.cui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CounterView(modifier: Modifier = Modifier, count: Int = 0, callBack: (value: Int) -> Unit) {
    var countValue by remember { mutableStateOf(count) }

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        if (countValue <= 0) {
            OutlinedButton(shape = Shapes().extraSmall,
                onClick = {
                    countValue++
                    callBack.invoke(countValue)
                }) {
                Text(text = "Add")
            }
        } else {
            IconButton(
                onClick = {
                    countValue--
                    callBack.invoke(countValue)
                }) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "minus")
            }
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = countValue.toString(),
                style = MaterialTheme.typography.labelLarge
            )
            IconButton(
                onClick = {
                    countValue++
                    callBack.invoke(countValue)
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }


    }

}

@Preview
@Composable
fun CounterPreview() {
    CounterView(count = 0) {

    }
}