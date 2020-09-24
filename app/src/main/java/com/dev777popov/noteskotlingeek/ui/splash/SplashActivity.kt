package com.dev777popov.noteskotlingeek.ui.splash

import com.dev777popov.noteskotlingeek.ui.base.BaseActivity
import com.dev777popov.noteskotlingeek.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<Boolean?>() {

    override val viewModel: SplashViewModel by viewModel()

    override val layoutRes: Nothing? = null

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity(){
        MainActivity.start(this)
        finish()
    }
}