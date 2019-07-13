package com.user.info.demo.ui.display.user.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.user.info.demo.model.User
import com.user.info.demo.repositories.UserDataRepositoryInterface
import com.user.info.demo.utilities.SharedPreferencesManagerInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DisplayUserViewModel @Inject constructor(
        private val userDataRepository: UserDataRepositoryInterface,
        private val sharedPreferencesManager: SharedPreferencesManagerInterface
): ViewModel() {

    val userData = MutableLiveData<User>()
    val resetData = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userDataRepository.getUserSavedData()

            user?.let {
                withContext(Dispatchers.Main) {
                    userData.value = it
                }
            }
        }
    }

    fun resetUserSavedData(user: User?) {
        user?.let {
            toastMessage.value = "Wait.."
            CoroutineScope(Dispatchers.IO).launch {

                userDataRepository.deleteUser(user)

                withContext(Dispatchers.Main) {
                    toastMessage.value = "Done!"
                    sharedPreferencesManager.put("userSaved", false)
                    resetData.value = true
                }
            }
        }
    }
}