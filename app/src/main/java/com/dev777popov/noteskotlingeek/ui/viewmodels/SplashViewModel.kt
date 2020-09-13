package com.dev777popov.noteskotlingeek.ui.viewmodels

import com.dev777popov.noteskotlingeek.data.NotesRepository
import com.dev777popov.noteskotlingeek.data.errors.NoAuthException
import com.dev777popov.noteskotlingeek.ui.viewstates.SplashViewState

class SplashViewModel : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(authenticated = true)
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}
