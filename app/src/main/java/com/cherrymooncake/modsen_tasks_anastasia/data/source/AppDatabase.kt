package com.cherrymooncake.modsen_tasks_anastasia.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cherrymooncake.modsen_tasks_anastasia.data.model.FavoritePostDbModel

@Database(
    entities = [FavoritePostDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritePostsDao(): FavoritePostsDao
}