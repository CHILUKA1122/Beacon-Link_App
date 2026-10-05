package com.example.beaconlink.data.repository

import com.example.beaconlink.data.local.dao.MessageDao
import com.example.beaconlink.data.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

class MessageRepository(private val messageDao: MessageDao) {
    fun getAllMessages(): Flow<List<MessageEntity>> = messageDao.getAllMessages()

    fun getMessagesForContact(contactId: String): Flow<List<MessageEntity>> = 
        messageDao.getMessagesForContact(contactId)

    suspend fun insertMessage(message: MessageEntity) {
        messageDao.insertMessage(message)
    }
}
