package com.dev777popov.noteskotlingeek.ui.viewstates

import com.dev777popov.noteskotlingeek.data.entity.Note

class NoteViewState(val note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)