package com.san.doodleblue.repository

import com.san.doodleblue.data.entity.MenuItem
import com.san.doodleblue.data.local.database.MenuDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(private val menuDao: MenuDao) : MenuRepository {

    override suspend fun updateMenuItem(item: MenuItem) {
        menuDao.updateMenu(item)
    }

    override suspend fun insertMenuItem(item: MenuItem) {
        menuDao.insertMenu(item)
    }

    override fun getAllMenu(): Flow<List<MenuItem>> {
        return menuDao.getMenuList()
    }

    override fun getCartList(): Flow<List<MenuItem>> {
        return menuDao.getCartList()
    }


}