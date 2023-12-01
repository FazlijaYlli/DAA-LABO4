package com.example.daa_labo4.db

import NaSDAO
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.daa_labo4.models.NoteAndSchedule

@Database(entities = [NoteAndSchedule::class],
    version = 1,
    exportSchema = true)
@TypeConverters(CalendarConverter::class)
abstract class NasDatabase : RoomDatabase() {
    abstract fun nasDao() : NaSDAO
    companion object {
        private var INSTANCE : NasDatabase? = null
        fun getDatabase(context: Context) : NasDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    NasDatabase::class.java, "database.db")
                    //.addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    //.addCallback(MyDatabaseCallBack())
                    .build()
                INSTANCE!!
            }
        }
    }
}