package com.app.foodnote.data.repository

import androidx.lifecycle.LiveData
import com.app.foodnote.data.local.dao.ScheduleDao
import com.app.foodnote.data.local.entity.Schedule

class ScheduleRepository private constructor(
    private val scheduleDao: ScheduleDao,
) {
    companion object {
        @Volatile
        private var instance: ScheduleRepository? = null

        fun getInstance(
            scheduleDao: ScheduleDao,
        ): ScheduleRepository =
            instance ?: synchronized(this) {
                instance ?: ScheduleRepository(
                    scheduleDao = scheduleDao,
                ).also { instance = it }
            }
    }

    suspend fun insertSchedule(schedule: Schedule) {
        scheduleDao.insertSchedule(schedule)
    }

    suspend fun updateSchedule(schedule: Schedule) {
        scheduleDao.updateSchedule(schedule)
    }

    suspend fun deleteSchedule(schedule: Schedule) {
        scheduleDao.deleteSchedule(schedule)
    }

    fun getSchedules(): LiveData<List<Schedule>> {
        return scheduleDao.getSchedules()
    }
}