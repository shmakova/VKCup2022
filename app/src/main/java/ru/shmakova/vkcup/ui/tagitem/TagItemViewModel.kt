package ru.shmakova.vkcup.ui.tagitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.shmakova.vkcup.data.TagItemRepository
import ru.shmakova.vkcup.data.TagItem
import ru.shmakova.vkcup.ui.tagitem.TagItemUiState.Error
import ru.shmakova.vkcup.ui.tagitem.TagItemUiState.Loading
import ru.shmakova.vkcup.ui.tagitem.TagItemUiState.Success
import javax.inject.Inject

@HiltViewModel
class TagItemViewModel @Inject constructor(
    private val tagItemRepository: TagItemRepository
) : ViewModel() {

    val uiState: StateFlow<TagItemUiState> = tagItemRepository
        .tagItems.map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun toggleTagItem(item: TagItem) {
        viewModelScope.launch {
            tagItemRepository.toggle(item)
        }
    }
}

sealed interface TagItemUiState {
    object Loading : TagItemUiState
    data class Error(val throwable: Throwable) : TagItemUiState
    data class Success(val data: List<TagItem>) : TagItemUiState
}
