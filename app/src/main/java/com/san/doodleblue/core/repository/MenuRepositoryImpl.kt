package com.san.doodleblue.core.repository

import com.san.doodleblue.core.data.entity.MenuItem
import com.san.doodleblue.core.data.local.database.MenuDao
import kotlinx.coroutines.flow.Flow
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