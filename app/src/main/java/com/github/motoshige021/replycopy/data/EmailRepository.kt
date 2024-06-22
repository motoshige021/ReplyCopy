package com.github.motoshige021.replycopy.data

import kotlinx.coroutines.flow.Flow

interface EmailRepository {
    fun getAllEmails(): Flow<List<Email>>
    fun getCategoryEmails(category: MailboxType): Flow<List<Email>>
    fun getAllFolders(): List<String>
    fun getEmailFromId(id: Long): Flow<Email>
}