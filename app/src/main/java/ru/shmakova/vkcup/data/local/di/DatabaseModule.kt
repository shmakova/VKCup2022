package ru.shmakova.vkcup.data.local.di

import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.shmakova.vkcup.data.TagItem
import ru.shmakova.vkcup.data.local.database.AppDatabase
import ru.shmakova.vkcup.data.TagItemDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideTagItemDao(appDatabase: AppDatabase): TagItemDao {
        return appDatabase.tagItemDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "TagItem"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                list.forEach {
                    val contentValues = ContentValues()
                    contentValues.put("name", it.name)
                    contentValues.put("isEnabled", it.isEnabled)
                    db.insert("tagitem", OnConflictStrategy.REPLACE, contentValues)
                }
            }
        }).build()
    }
}

private val list = listOf(
    TagItem(name = "Юмор"),
    TagItem(name = "Еда"),
    TagItem(name = "Кино"),
    TagItem(name = "Рестораны"),
    TagItem(name = "Прогулки"),
    TagItem(name = "Политика"),
    TagItem(name = "Новости"),
    TagItem(name = "Автомобили"),
    TagItem(name = "Сериалы"),
    TagItem(name = "Рецепты"),
    TagItem(name = "Работа"),
    TagItem(name = "Отдых"),
    TagItem(name = "Спорт"),
    TagItem(name = "Юмор"),
    TagItem(name = "Еда"),
    TagItem(name = "Кино"),
    TagItem(name = "Рестораны"),
    TagItem(name = "Прогулки"),
    TagItem(name = "Политика"),
    TagItem(name = "Новости"),
    TagItem(name = "Юмор"),
    TagItem(name = "Еда"),
    TagItem(name = "Кино"),
)
