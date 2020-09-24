package com.dev777popov.noteskotlingeek.ui.main

import com.dev777popov.noteskotlingeek.data.NotesRepository
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.data.model.NoteResult
import com.dev777popov.noteskotlingeek.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MainViewModel(notesRepository: NotesRepository) : BaseViewModel<List<Note>?>() {

    private val notesChannel = notesRepository.getNotes()

    init {
        launch {
            notesChannel.consumeEach {
                when(it){
                    is NoteResult.Success<*> -> setData(it.data as? List<Note>)
                    is NoteResult.Error -> setError(it.error)
                }
            }
        }
    }

    public override fun onCleared() {
        notesChannel.cancel()
        super.onCleared()
    }
}


