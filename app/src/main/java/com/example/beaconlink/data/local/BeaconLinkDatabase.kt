package com.example.beaconlink.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.beaconlink.data.local.dao.ContactDao
import com.example.beaconlink.data.local.dao.MessageDao
import com.example.beaconlink.data.local.dao.SOSLogDao
import com.example.beaconlink.data.local.entity.ContactEntity
import com.example.beaconlink.data.local.entity.MessageEntity
import com.example.beaconlink.data.local.entity.SOSLogEntity

@Database(
    entities = [
        MessageEntity::class,
        ContactEntity::class,
        SOSLogEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BeaconLinkDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun contactDao(): ContactDao
    abstract fun sosLogDao(): SOSLogDao

    companion object {
        @Volatile
        private var INSTANCE: BeaconLinkDatabase? = null

        fun getDatabase(context: Context): BeaconLinkDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BeaconLinkDatabase::class.java,
                    "beaconlink_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
