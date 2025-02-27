package com.app.foodnote.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.foodnote.data.local.Converters
import com.app.foodnote.data.local.dao.ScheduleDao
import com.app.foodnote.data.local.entity.Schedule

@Database(
    entities = [Schedule::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        private const val DATABASE_NAME = "schedule_database"

        @Volatile
        private var INSTANCE: ScheduleDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ScheduleDatabase {
            if (INSTANCE == null){
                synchronized(ScheduleDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ScheduleDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE as ScheduleDatabase
        }
    }
}