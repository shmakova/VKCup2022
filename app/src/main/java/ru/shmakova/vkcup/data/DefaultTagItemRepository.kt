package ru.shmakova.vkcup.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultTagItemRepository @Inject constructor(
    private val tagItemDao: TagItemDao
) : TagItemRepository {

    override val tagItems: Flow<List<TagItem>> =
        tagItemDao.getTagItems()

    override suspend fun toggle(item: TagItem) {
        tagItemDao.updateTagItem(TagItemUpdate(uid = item.uid, isEnabled = !item.isEnabled))
    }
}
