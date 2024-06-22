package com.github.motoshige021.replycopy.ui

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.motoshige021.replycopy.data.Email
import com.github.motoshige021.replycopy.data.EmailRepository
import com.github.motoshige021.replycopy.data.EmailRepositoryImpl
import com.github.motoshige021.replycopy.ui.util.ReplyContentType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ReplyHomeViewModel(private val emailRepository: EmailRepository = EmailRepositoryImpl())
    : ViewModel() {
    private val _uiState = MutableStateFlow(ReplyHomeUIState(loading = true))
    val uiState: StateFlow<ReplyHomeUIState> = _uiState

    init {
        observeEmails()
    }

    private fun observeEmails() {
        viewModelScope.launch {
            emailRepository.getAllEmails().catch { ex ->
                _uiState.value = ReplyHomeUIState(error = ex.message)
            }
            .collect { emails ->
                // Flowのデータが準備できた処理をcollect()のラムダに記述
                _uiState.value = ReplyHomeUIState(
                    emails = emails,
                    selectedEmail = emails.first()
                )
            }
        }
    }

    fun setSelectedEmail(emailId: Long, contentType: ReplyContentType) {
        val email = uiState.value.emails.find { it.id == emailId }
        _uiState.value = _uiState.value.copy(
            selectedEmail = email,
            isDetailOnlyOpen = contentType == ReplyContentType.SINGLE_PANE
        )
    }

    fun closeDetailScreen() { // 詳細が閉じた時の処理
        _uiState.value = _uiState.value.copy(
            isDetailOnlyOpen = false,
            selectedEmail = _uiState.value.emails.first()
        )
    }
}

data class ReplyHomeUIState (
    val emails: List<Email> = emptyList(),
    val selectedEmail: Email? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null,
)