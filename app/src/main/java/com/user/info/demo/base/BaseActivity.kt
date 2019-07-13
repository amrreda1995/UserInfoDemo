package com.user.info.demo.base

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.user.info.demo.app.UserApplication
import com.user.info.demo.di.ApplicationComponent
import javax.inject.Inject

/**
 * instead of re-declaring these variables in both AddUserDataActivity and DisplayUserDataActivity;
 * I've created BaseActivity to include them in it and both of these activities can extend it to use them
 * */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    val component: ApplicationComponent
        get() = (application as UserApplication).component

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}