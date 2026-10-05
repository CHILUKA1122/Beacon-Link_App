package com.example.beaconlink.data.repository

import com.example.beaconlink.data.local.dao.ContactDao
import com.example.beaconlink.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {
    fun getAllContacts(): Flow<List<ContactEntity>> = contactDao.getAllContacts()

    fun getTrustedContacts(): Flow<List<ContactEntity>> = contactDao.getTrustedContacts()
    
    suspend fun getContactById(id: Long): ContactEntity? = contactDao.getContactById(id)

    suspend fun insertContact(contact: ContactEntity) {
        contactDao.insertContact(contact)
    }

    suspend fun updateContact(contact: ContactEntity) {
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(contact: ContactEntity) {
        contactDao.deleteContact(contact)
    }
}
