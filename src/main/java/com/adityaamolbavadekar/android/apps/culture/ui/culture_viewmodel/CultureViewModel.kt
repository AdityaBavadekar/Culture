package com.adityaamolbavadekar.android.apps.culture.ui.culture_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.adityaamolbavadekar.android.apps.culture.room.note.NoteDatabaseClass
import com.adityaamolbavadekar.android.apps.culture.room.note.NotesDataClass
import com.adityaamolbavadekar.android.apps.culture.room.note.NotesRepository
import kotlinx.coroutines.launch

class CultureViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepo: NotesRepository

    init {
        val noteDao = NoteDatabaseClass.getDatabaseInstance(application).noteDao()
        notesRepo = NotesRepository(noteDao)
    }


    fun createNote(notesDataClass: NotesDataClass) {
        viewModelScope.launch {
            notesRepo.insertTheNote(notesDataClass)
        }
    }

    fun getNotes(): LiveData<List<NotesDataClass>> {
        return notesRepo.getAllTheNotes()
    }

    fun getTheNotesFromId(indexId: Long): LiveData<NotesDataClass> {
        return notesRepo.getTheNotesFromId(indexId)
    }

    fun updateTheNote(notesDataClass: NotesDataClass) {
        viewModelScope.launch {
            notesRepo.updateTheNote(notesDataClass)
        }
    }

    fun deleteAllNotes() = notesRepo.deleteAllNotes()
    fun deleteNote(indexId: Long) = notesRepo.deleteNote(indexId)

}