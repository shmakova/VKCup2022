package ru.shmakova.vkcup.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.shmakova.vkcup.data.DefaultTagItemRepository
import ru.shmakova.vkcup.data.TagItemRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsTagItemRepository(
        tagItemRepository: DefaultTagItemRepository
    ): TagItemRepository
}
