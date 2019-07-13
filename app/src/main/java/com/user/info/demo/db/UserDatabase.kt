package com.user.info.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.user.info.demo.model.User
import javax.inject.Inject

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val dbName = "users.db"
        private var dbInstance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {

            if (dbInstance == null) {
                synchronized(UserDatabase::class) {
                    dbInstance = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java,
                            dbName
                    ).build()
                }
            }

            return dbInstance
        }
    }

}