package com.dev777popov.noteskotlingeek.data

import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.data.provider.FireStoreDataProvider
import com.dev777popov.noteskotlingeek.data.provider.RemoteDataProvider

class NotesRepository(private val remoteProvider: FireStoreDataProvider) {
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun deleteNote(id: String) = remoteProvider.deleteNote(id)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
}