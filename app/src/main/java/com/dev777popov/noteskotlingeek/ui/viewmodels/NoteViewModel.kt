package com.dev777popov.noteskotlingeek.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.dev777popov.noteskotlingeek.data.NotesRepository
import com.dev777popov.noteskotlingeek.data.entity.Note

class NoteViewModel : ViewModel() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }

}