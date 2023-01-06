package ru.shmakova.vkcup.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class TagItemUpdate(
    @ColumnInfo(name = "uid")
    val uid: Int,
    @ColumnInfo(name = "isEnabled")
    val isEnabled: Boolean
)
