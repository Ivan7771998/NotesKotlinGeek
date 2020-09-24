package com.dev777popov.noteskotlingeek.data.provider

import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.data.entity.User
import com.dev777popov.noteskotlingeek.data.model.NoteResult
import kotlinx.coroutines.channels.ReceiveChannel

interface RemoteDataProvider {
    fun subscribeToAllNotes(): ReceiveChannel<NoteResult>
    suspend fun getNoteById(id: String): Note
    suspend fun saveNote(note: Note): Note
    suspend fun getCurrentUser(): User?
    suspend fun deleteNote(noteId: String)
}