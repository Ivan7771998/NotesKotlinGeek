package com.dev777popov.noteskotlingeek.ui.viewstates

import com.dev777popov.noteskotlingeek.data.entity.Note

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)