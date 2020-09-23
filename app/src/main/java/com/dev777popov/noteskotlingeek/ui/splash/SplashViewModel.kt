package com.dev777popov.noteskotlingeek.ui.splash

import com.dev777popov.noteskotlingeek.data.NotesRepository
import com.dev777popov.noteskotlingeek.data.errors.NoAuthException
import com.dev777popov.noteskotlingeek.ui.base.BaseViewModel

class SplashViewModel(private val notesRepository: NotesRepository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        notesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(
                    authenticated = true
                )
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}
