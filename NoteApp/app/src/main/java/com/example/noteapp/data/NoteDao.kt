package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    suspend fun getAllNotesByDateDesc(): List<NoteModel>

    @Query("SELECT * FROM notes ORDER BY createdAt ASC")
    suspend fun getAllNotesByDateAsc(): List<NoteModel>

    @Query("SELECT * FROM notes ORDER BY title DESC")
    suspend fun getAllNotesByTitleDesc(): List<NoteModel>

    @Query("SELECT * FROM notes ORDER BY title ASC")
    suspend fun getAllNotesByTitleAsc(): List<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)

}