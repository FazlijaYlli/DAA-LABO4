package com.example.daa_labo4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.daa_labo4.models.Note
import com.example.daa_labo4.models.Schedule
import kotlin.concurrent.thread

@Database(entities = [Note::class, Schedule::class],
    version = 1,
    exportSchema = true)
@TypeConverters(CalendarConverter::class)
abstract class NasDatabase : RoomDatabase() {
    abstract fun nasDao() : NaSDAO
    abstract fun noteDao() : NoteDAO
    abstract fun scheduleDao() : ScheduleDAO

    companion object {
        private var INSTANCE : NasDatabase? = null
        fun getDatabase(context: Context) : NasDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    NasDatabase::class.java, "database.db")
                    //.addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .addCallback(MyDatabaseCallBack())
                    .build()
                INSTANCE!!
            }
        }
    }
    private class MyDatabaseCallBack : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {database ->
                if (database.noteDao().count().value == 0) {
                    thread {
                    }
                }
            }
        }
    }
}

