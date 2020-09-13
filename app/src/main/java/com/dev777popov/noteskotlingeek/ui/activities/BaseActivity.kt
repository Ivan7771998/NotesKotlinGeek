package com.dev777popov.noteskotlingeek.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dev777popov.noteskotlingeek.ui.viewmodels.BaseViewModel
import com.dev777popov.noteskotlingeek.ui.viewstates.BaseViewState

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.getViewState().observe(this, Observer { state ->
            state?:return@Observer
            state.error?.let { e ->
                renderError(e)
                return@Observer
            }
            renderData(state.data)
        })

    }

    abstract fun renderData(data: T)

    private fun renderError(error: Throwable?) {
        error?.message?.let { message ->
            showError(message)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}