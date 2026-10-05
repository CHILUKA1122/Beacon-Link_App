package com.example.beaconlink.data.repository

import com.example.beaconlink.data.local.dao.SOSLogDao
import com.example.beaconlink.data.local.entity.SOSLogEntity
import kotlinx.coroutines.flow.Flow

class SOSLogRepository(private val sosLogDao: SOSLogDao) {
    fun getAllSOSLogs(): Flow<List<SOSLogEntity>> = sosLogDao.getAllSOSLogs()

    suspend fun insertSOSLog(log: SOSLogEntity) {
        sosLogDao.insertSOSLog(log)
    }
}
