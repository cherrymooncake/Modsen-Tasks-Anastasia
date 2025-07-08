package com.cherrymooncake.modsen_tasks_anastasia.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetCommentsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.ui.error.parseToString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostCommentsViewModel (
    private val post: PostDomainModel,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PostCommentsState(post = post))
    val state = _state.asStateFlow()

    init {
        loadComments()
    }

    private fun loadComments() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getCommentsUseCase(post.id)) {
                is TResult.Success -> {
                    val uiComments = result.data.map { it.toCommentUiModel() }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            comments = uiComments
                        )
                    }
                }
                is TResult.Error -> {
                    _state.update { it.copy(
                        isLoading = false,
                        error = result.exception.parseToString()) }
                }
            }
        }
    }

}