package com.example.beaconlink.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beaconlink.data.local.entity.SOSLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SOSLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSOSLog(log: SOSLogEntity)

    @Query("SELECT * FROM sos_logs ORDER BY timestamp DESC")
    fun getAllSOSLogs(): Flow<List<SOSLogEntity>>
}
