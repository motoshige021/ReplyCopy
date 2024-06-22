package com.github.motoshige021.replycopy.data

import com.github.motoshige021.replycopy.data.local.LocalEmailDataProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EmailRepositoryImpl : EmailRepository{
    override fun getAllEmails(): Flow<List<Email>> = flow {
        emit(LocalEmailDataProvider.allEmails)
    }

    override fun getAllFolders(): List<String>  {
        return LocalEmailDataProvider.getAllFolders()
    }

    override fun getCategoryEmails(category: MailboxType): Flow<List<Email>> = flow {
        val categoryEmails = LocalEmailDataProvider.allEmails.filter { it.mailbox == category }
        emit(categoryEmails)
    }

    override fun getEmailFromId(id: Long): Flow<Email> = flow {
        val categoryEmail = LocalEmailDataProvider.allEmails.firstOrNull { it.id == id }
    }
}