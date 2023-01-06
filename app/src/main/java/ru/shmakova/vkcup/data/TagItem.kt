package ru.shmakova.vkcup.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class TagItem(
    val name: String,
    val isEnabled: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface TagItemDao {
    @Query("SELECT * FROM tagitem ORDER BY uid")
    fun getTagItems(): Flow<List<TagItem>>

    @Insert
    suspend fun insertTagItem(item: TagItem)

    @Insert
    suspend fun insertAll(items: List<TagItem>)

    @Update(entity = TagItem::class)
    suspend fun updateTagItem(item: TagItemUpdate)
}
