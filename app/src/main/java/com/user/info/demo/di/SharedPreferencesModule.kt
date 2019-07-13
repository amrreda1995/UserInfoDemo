package com.user.info.demo.di

import com.user.info.demo.utilities.SharedPreferencesManager
import com.user.info.demo.utilities.SharedPreferencesManagerInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
abstract class SharedPreferencesModule {

    @Binds
    @Singleton
    abstract fun bindSharedPreferences(sharedPreferencesManager: SharedPreferencesManager): SharedPreferencesManagerInterface
}