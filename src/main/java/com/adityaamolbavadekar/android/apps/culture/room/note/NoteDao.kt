package com.adityaamolbavadekar.android.apps.culture.room.note

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM culture_notes_table ORDER BY Title ASC")
    fun getAllNotes(): LiveData<List<NotesDataClass>>


    @Query("SELECT * FROM culture_notes_table WHERE indexId = :indexId")
    fun getTheNotesFromId(indexId: Long): LiveData<NotesDataClass>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheNote(notesDataClass: NotesDataClass)

    @Update
    suspend fun updateTheNote(notesDataClass: NotesDataClass)

    @Query("DELETE FROM culture_notes_table")
    fun deleteAllNotes()

    @Query("DELETE FROM culture_notes_table WHERE indexId = :indexId")
    fun deleteNote(indexId: Long)

}