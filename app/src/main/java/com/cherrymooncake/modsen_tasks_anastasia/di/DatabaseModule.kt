package com.cherrymooncake.modsen_tasks_anastasia.di

import androidx.room.Room
import com.cherrymooncake.modsen_tasks_anastasia.data.source.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app_database.db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single {
        get<AppDatabase>().favoritePostsDao()
    }
}