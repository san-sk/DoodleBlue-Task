package com.san.doodleblue.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.san.doodleblue.data.entity.MenuItem


@Database(
    entities = [MenuItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao

}
