package com.dev777popov.noteskotlingeek.ui.splash

import com.dev777popov.noteskotlingeek.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) :
    BaseViewState<Boolean?>(authenticated, error)