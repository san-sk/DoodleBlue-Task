package com.san.doodleblue.core.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.san.doodleblue.core.data.entity.MenuItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM MenuItem")
    fun getMenuList(): Flow<List<MenuItem>>


    @Query("SELECT * FROM MenuItem WHERE count > 0")
    fun getCartList(): Flow<List<MenuItem>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMenu(user: MenuItem)

    @Update
    suspend fun updateMenu(user: MenuItem)

    @Query("DELETE FROM MenuItem")
    suspend fun deleteAll()


}