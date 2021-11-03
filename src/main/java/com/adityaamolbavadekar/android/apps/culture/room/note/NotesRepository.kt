package com.adityaamolbavadekar.android.apps.culture.room.note

import androidx.lifecycle.LiveData

class NotesRepository(val noteDao: NoteDao) {

    fun getAllTheNotes(): LiveData<List<NotesDataClass>> = noteDao.getAllNotes()
    fun getTheNotesFromId(indexId: Long): LiveData<NotesDataClass> =
        noteDao.getTheNotesFromId(indexId)

    suspend fun insertTheNote(notesDataClass: NotesDataClass) =
        noteDao.insertTheNote(notesDataClass)

    suspend fun updateTheNote(notesDataClass: NotesDataClass) =
        noteDao.updateTheNote(notesDataClass)

    fun deleteAllNotes() = noteDao.deleteAllNotes()
    fun deleteNote(indexId: Long) = noteDao.deleteNote(indexId)

}