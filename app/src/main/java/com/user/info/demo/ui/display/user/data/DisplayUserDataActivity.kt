package com.user.info.demo.ui.display.user.data

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.user.info.demo.R
import com.user.info.demo.base.BaseActivity
import com.user.info.demo.databinding.ActivityDisplayUserDataBinding
import com.user.info.demo.ui.add.user.data.AddUserDataActivity
import com.user.info.demo.utilities.toast

class DisplayUserDataActivity : BaseActivity() {

    private lateinit var binding: ActivityDisplayUserDataBinding
    private lateinit var viewModel: DisplayUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[DisplayUserViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_user_data)

        init()
        setupObservers()

        viewModel.getUserData()
    }

    private fun init() {
        binding.viewModel = viewModel
    }

    private fun setupObservers() {
        viewModel.toastMessage.observe(this, Observer {
            toast(it)
        })

        viewModel.resetData.observe(this, Observer {
            startActivity(Intent(this, AddUserDataActivity::class.java))
            finish()
        })

        viewModel.userData.observe(this, Observer {
            binding.user = it
        })
    }
}
