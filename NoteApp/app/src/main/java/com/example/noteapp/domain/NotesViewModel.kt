
package com.example.noteapp.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.NoteModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class NotesViewModel(
    private val noteDao: NoteDao
) : ViewModel() {

    var notes by mutableStateOf(listOf<NoteModel>())
        private set

    var title by mutableStateOf("")
    var note by mutableStateOf("")
    var editingNoteId by mutableStateOf<String?>(null)
    var searchQuery by mutableStateOf("")
    var sortBy by mutableStateOf("Date")  // Sort by date or title
    var ascending by mutableStateOf(true)

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            notes = when(sortBy) {
                "Title" -> if (ascending) noteDao.getAllNotesByTitleAsc() else noteDao.getAllNotesByTitleDesc()
                else -> if (ascending) noteDao.getAllNotesByDateAsc() else noteDao.getAllNotesByDateDesc()
            }
        }
    }

    // CRUD operations
    fun addNote() {
        if (title.isBlank() || note.isBlank()) return

        val newNote = NoteModel(
            title = title,
            note = note
        )

        viewModelScope.launch {
            noteDao.insertNote(newNote)
            loadNotes()
        }

        clearInputs()
    }

    fun updateNote() {
        if (editingNoteId == null) return

        val updatedNote = notes.find { it.id == editingNoteId }?.copy(
            title = title,
            note = note,
            updatedAt = LocalDateTime.now()
        ) ?: return

        viewModelScope.launch {
            noteDao.updateNote(updatedNote)
            loadNotes()
        }

        editingNoteId = null
        clearInputs()
    }

    fun deleteNote(noteToDelete: NoteModel) {
        viewModelScope.launch {
            noteDao.deleteNote(noteToDelete)
            loadNotes()
        }
    }

    fun editNote(noteToEdit: NoteModel) {
        editingNoteId = noteToEdit.id
        title = noteToEdit.title
        note = noteToEdit.note
    }

    fun clearInputs() {
        title = ""
        note = ""
    }

    // Filtering and Sorting
    val filteredAndSortedNotes: List<NoteModel>
        get() = notes
            .filter { it.title.contains(searchQuery, ignoreCase = true) || it.note.contains(searchQuery, ignoreCase = true) }
            .sortedWith(
                comparator = when(sortBy) {
                    "Title" -> compareBy<NoteModel> { it.title.lowercase() }
                    else -> compareBy { it.createdAt }
                }.let { if (ascending) it else it.reversed() }
            )
}
