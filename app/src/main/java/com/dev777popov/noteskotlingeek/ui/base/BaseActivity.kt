package com.dev777popov.noteskotlingeek.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.data.errors.NoAuthException
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<S> : AppCompatActivity(), CoroutineScope {

    companion object {
        const val RC_SIGN_IN = 2222
    }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    private lateinit var dataJob: Job
    private lateinit var errorJob: Job

    abstract val viewModel: BaseViewModel<S>
    abstract val layoutRes: Int?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let {
            setContentView(it)
        }
    }

    override fun onStart() {
        super.onStart()
        dataJob = launch {
            viewModel.getViewState().consumeEach {
                renderData(it)
            }
        }

        errorJob = launch {
            viewModel.getErrorChannel().consumeEach {
                renderError(it)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        dataJob.cancel()
        errorJob.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }


    abstract fun renderData(data: S)

    private fun renderError(error: Throwable?) {
        when (error) {
            is NoAuthException -> startLogin()
            else -> error?.message?.let { message ->
                showError(message)
            }
        }
    }

    private fun startLogin(){
        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setLogo(R.drawable.ic_launcher_foreground)
            .setTheme(R.style.LoginTheme)
            .setAvailableProviders(providers)
            .build()

        startActivityForResult(intent,
            RC_SIGN_IN
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK){
            finish()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}