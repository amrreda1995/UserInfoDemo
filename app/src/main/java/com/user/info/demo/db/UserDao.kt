package com.user.info.demo.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.user.info.demo.model.User

@Dao
interface UserDao {

    @Insert
    fun saveUserData(user: User)

    @Query("select * from Users")
    fun getUserSavedData(): List<User>

    @Delete
    fun deleteUser(user: User)
}