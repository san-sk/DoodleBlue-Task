package com.san.doodleblue.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val count: Int
) {
    val totalPrice: Double
        get() = count * price
}