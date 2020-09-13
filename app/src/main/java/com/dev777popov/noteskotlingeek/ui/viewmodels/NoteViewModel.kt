package com.dev777popov.noteskotlingeek.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.dev777popov.noteskotlingeek.data.NotesRepository
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.data.model.NoteResult
import com.dev777popov.noteskotlingeek.ui.viewstates.NoteViewState

class NoteViewModel : BaseViewModel<Note?, NoteViewState>() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        NotesRepository.getNoteById(noteId).observeForever { result ->
            result ?: return@observeForever
            when (result) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NoteViewState(note = result.data as? Note)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = NoteViewState(error = result.error)
                }
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }

}