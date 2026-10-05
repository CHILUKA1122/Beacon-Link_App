package com.example.beaconlink.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val content: String,
    val senderId: String,
    val receiverId: String,
    val timestamp: Long,
    val isSent: Boolean
)
