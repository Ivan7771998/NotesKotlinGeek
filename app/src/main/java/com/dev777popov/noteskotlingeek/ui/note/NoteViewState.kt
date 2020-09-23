package com.dev777popov.noteskotlingeek.ui.note

import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) :
    BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}