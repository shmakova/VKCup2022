package ru.shmakova.vkcup.data

import kotlinx.coroutines.flow.Flow

interface TagItemRepository {
    val tagItems: Flow<List<TagItem>>

    suspend fun toggle(item: TagItem)
}

