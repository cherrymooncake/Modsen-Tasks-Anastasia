package com.cherrymooncake.modsen_tasks_anastasia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_posts")
data class FavoritePostDbModel(
    @PrimaryKey val id: Int
)