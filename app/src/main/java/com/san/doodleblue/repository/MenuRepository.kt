package com.san.doodleblue.repository

import com.san.doodleblue.data.entity.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun updateMenuItem(item: MenuItem)
    suspend fun insertMenuItem(item: MenuItem)
    fun getAllMenu(): Flow<List<MenuItem>>
    fun getCartList(): Flow<List<MenuItem>>
}