package com.dev777popov.noteskotlingeek.data

import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.data.provider.FireStoreDataProvider
import com.dev777popov.noteskotlingeek.data.provider.RemoteDataProvider

object NotesRepository {

    private val remoteProvider: RemoteDataProvider = FireStoreDataProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)

//    private val notesLiveData = MutableLiveData<List<Note>>()
//
//    val notes = mutableListOf(
//        Note(
//            UUID.randomUUID().toString(),
//            "Первая заметка",
//            "Текст первой заметки. Не очень длинный, но интересный",
//            Note.Color.WHITE
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Вторая заметка",
//            "Текст второй заметки. Не очень длинный, но интересный",
//            Note.Color.YELLOW
//        ),
//
//        Note(
//            UUID.randomUUID().toString(),
//            "Третья заметка",
//            "Текст третьей заметки. Не очень длинный, но интересный",
//            Note.Color.GREEN
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Четвертая заметка",
//            "Текст четвертой заметки. Не очень длинный, но интересный",
//            Note.Color.BLUE
//        ),
//
//        Note(
//            UUID.randomUUID().toString(),
//            "Пятая заметка",
//            "Текст пятой заметки. Не очень длинный, но интересный",
//            Note.Color.RED
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Шестая заметка",
//            "Текст шестой заметки. Не очень длинный, но интересный",
//            Note.Color.VIOLET
//        )
//    )
//
//    init {
//        notesLiveData.value = notes
//    }
//
//    fun getNotes(): LiveData<List<Note>> {
//        return notesLiveData
//    }
//
//    fun saveNote(note: Note) {
//        addOrReplace(note)
//        notesLiveData.value = notes
//    }
//
//    private fun addOrReplace(note: Note) {
//        for (i in notes.indices) {
//            if (notes[i].id == note.id) {
//                notes[i] = note
//                return
//            }
//        }
//        notes.add(note)
//    }
}