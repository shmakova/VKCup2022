package ru.shmakova.vkcup.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.shmakova.vkcup.data.TagItem
import ru.shmakova.vkcup.data.TagItemDao

@Database(entities = [TagItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tagItemDao(): TagItemDao
}
