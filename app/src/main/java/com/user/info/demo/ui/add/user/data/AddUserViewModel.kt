package com.user.info.demo.ui.add.user.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.user.info.demo.utilities.EditTextError
import com.user.info.demo.R
import com.user.info.demo.model.User
import com.user.info.demo.repositories.UserDataRepositoryInterface
import com.user.info.demo.utilities.SharedPreferencesManagerInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddUserViewModel @Inject constructor(
        private val userDataRepository: UserDataRepositoryInterface,
        private val sharedPreferencesManager: SharedPreferencesManagerInterface
) : ViewModel() {

    val userIsSaved = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()
    val editTextError = MutableLiveData<EditTextError>()

    fun saveUserData(user: User?) {
        user?.let {
            if (validateUserData(it)) {
                toastMessage.value = "Saving.."

                CoroutineScope(Dispatchers.IO).launch {
                    userDataRepository.saveUserData(user)

                    withContext(Dispatchers.Main) {
                        toastMessage.value = "Saved!"
                        userIsSaved.value = true
                        sharedPreferencesManager.put("userSaved", true)
                    }
                }
            }
        }
    }

    private fun validateUserData(user: User): Boolean {
        var valid = true

        if (user.name.isNotEmpty()) {
            observeEditText(R.id.userNameEditText, "")
        } else {
            valid = false
            observeEditText(R.id.userNameEditText, "Invalid user name")
        }

        if (user.age > 0) {
            observeEditText(R.id.userAgeEditText, "")
        } else {
            valid = false
            observeEditText(R.id.userAgeEditText, "Invalid user age")
        }

        if (user.jobTitle.isNotEmpty()) {
            observeEditText(R.id.userJobTitleEditText, "")
        } else {
            valid = false
            observeEditText(R.id.userJobTitleEditText, "Invalid job title")
        }

        return valid
    }

    fun isThereUserSaved(): Boolean {
        return sharedPreferencesManager.get("userSaved", false)
    }

    private fun observeEditText(byEditTextId: Int, byErrorMessage: String) {
        editTextError.value = EditTextError(byEditTextId, byErrorMessage)
    }
}