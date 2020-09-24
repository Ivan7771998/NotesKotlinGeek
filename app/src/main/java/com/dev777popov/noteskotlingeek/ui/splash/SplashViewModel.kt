package com.dev777popov.noteskotlingeek.ui.splash

import com.dev777popov.noteskotlingeek.data.NotesRepository
import com.dev777popov.noteskotlingeek.data.errors.NoAuthException
import com.dev777popov.noteskotlingeek.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean?>() {

    fun requestUser() = launch {
        notesRepository.getCurrentUser()?.let {
            setData(true)
        } ?: let {
            setError(NoAuthException())
        }
    }
}
