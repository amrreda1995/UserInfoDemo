package com.user.info.demo.di

import com.user.info.demo.repositories.UserDataRepository
import com.user.info.demo.repositories.UserDataRepositoryInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindUserDataRepository(repo: UserDataRepository): UserDataRepositoryInterface
}