package com.san.doodleblue.core.di

import android.app.Application
import androidx.room.Room
import com.san.doodleblue.core.data.local.database.AppDatabase
import com.san.doodleblue.core.data.local.database.MenuDao
import com.san.doodleblue.core.repository.MenuRepository
import com.san.doodleblue.core.repository.MenuRepositoryImpl
import com.san.doodleblue.utils.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppConfig.APP_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMenuDao(database: AppDatabase): MenuDao = database.menuDao()


    @Provides
    @Singleton
    fun provideMenuRepository(dao: MenuDao): MenuRepository {
        return MenuRepositoryImpl(dao)
    }

}