package com.user.info.demo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.user.info.demo.ui.add.user.data.AddUserViewModel
import com.user.info.demo.ui.display.user.data.DisplayUserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddUserViewModel::class)
    internal abstract fun bindAddUserViewModel(viewModel: AddUserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DisplayUserViewModel::class)
    internal abstract fun bindDisplayUserViewModel(viewModel: DisplayUserViewModel): ViewModel
}