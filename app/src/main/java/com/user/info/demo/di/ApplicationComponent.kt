package com.user.info.demo.di

import com.user.info.demo.ui.add.user.data.AddUserDataActivity
import com.user.info.demo.ui.display.user.data.DisplayUserDataActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        RepositoriesModule::class,
        ViewModelModule::class,
        SharedPreferencesModule::class
    ]
)
@Singleton
interface ApplicationComponent {

    fun inject(addUserDataActivity: AddUserDataActivity)
    fun inject(addUserDataActivity: DisplayUserDataActivity)
}