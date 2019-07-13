package com.user.info.demo.ui.add.user.data

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.user.info.demo.R
import com.user.info.demo.base.BaseActivity
import com.user.info.demo.databinding.ActivityAddUserDataBinding
import com.user.info.demo.model.User
import com.user.info.demo.ui.display.user.data.DisplayUserDataActivity
import com.user.info.demo.utilities.toast

class AddUserDataActivity : BaseActivity() {

    private lateinit var binding: ActivityAddUserDataBinding

    private lateinit var viewModel: AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[AddUserViewModel::class.java]

        if (viewModel.isThereUserSaved()) {
            startActivity(Intent(this, DisplayUserDataActivity::class.java))
            finish()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user_data)

        init()
        setupObservers()
    }

    private fun init() {
        binding.viewModel = viewModel
        binding.user = User()
    }

    private fun setupObservers() {
        viewModel.toastMessage.observe(this, Observer {
            toast(it)
        })

        viewModel.userIsSaved.observe(this, Observer {
            startActivity(Intent(this, DisplayUserDataActivity::class.java))
            finish()
        })

        viewModel.editTextError.observe(this, Observer {
            val editText = findViewById<EditText>(it.editTextId)

            if (it.errorMessage.isNotEmpty()) {
                editText.error = it.errorMessage
            } else {
                editText.error = null
            }
        })
    }

    fun radioButtonChecked(view: View) {
        val checked = (view as RadioButton).isChecked

        when (view.id) {
            R.id.maleRadioButton -> {
                if (checked) {
                    binding.user?.gender = "Male"
                }
            }

            R.id.femaleRadioButton -> {
                if (checked) {
                    binding.user?.gender = "Female"
                }
            }
        }
    }
}
