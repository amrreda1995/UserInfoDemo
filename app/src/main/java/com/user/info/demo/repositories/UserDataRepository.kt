package com.user.info.demo.repositories

import android.content.Context
import com.user.info.demo.db.UserDatabase
import com.user.info.demo.model.User
import javax.inject.Inject

interface UserDataRepositoryInterface {

    fun saveUserData(user: User)
    fun getUserSavedData(): User?
    fun deleteUser(user: User)
}

class UserDataRepository @Inject constructor(context: Context) : UserDataRepositoryInterface {

    private var userDatabase = UserDatabase.getInstance(context)
    private var userDao = userDatabase?.userDao()

    override fun saveUserData(user: User) {
        userDao?.saveUserData(user)
    }

    override fun getUserSavedData(): User? {
        return userDao?.getUserSavedData()?.get(0)
    }

    override fun deleteUser(user: User) {
        userDao?.deleteUser(user)
    }
}