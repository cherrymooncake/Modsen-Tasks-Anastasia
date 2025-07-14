package com.cherrymooncake.modsen_tasks_anastasia.data.source

import androidx.room.*
import com.cherrymooncake.modsen_tasks_anastasia.data.model.FavoritePostDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePostsDao {
    @Query("SELECT id FROM favorite_posts")
    fun getFavoritePostIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(post: FavoritePostDbModel)

    @Delete
    suspend fun remove(post: FavoritePostDbModel)
}