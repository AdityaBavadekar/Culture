package com.adityaamolbavadekar.android.apps.culture.room.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "culture_notes_table")
data class NotesDataClass(
    @PrimaryKey(autoGenerate = true)
    var indexId: Long,
    @ColumnInfo(name = "Title")
    var noteTitle: String?,
    @ColumnInfo(name = "Body")
    var noteBody: String?,
    var noteTags: String?,
    var noteColor: CultureColours,
    var createdOn: String,
    var editedOn: String?,
    var attachments: Long = 0
)