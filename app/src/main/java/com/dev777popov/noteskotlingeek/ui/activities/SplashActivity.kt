package com.dev777popov.noteskotlingeek.ui.activities

import androidx.lifecycle.ViewModelProvider
import com.dev777popov.noteskotlingeek.ui.viewmodels.SplashViewModel
import com.dev777popov.noteskotlingeek.ui.viewstates.SplashViewState

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {
    override val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

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