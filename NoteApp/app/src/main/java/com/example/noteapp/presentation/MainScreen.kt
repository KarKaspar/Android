package com.example.noteapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.data.AppDatabase
import com.example.noteapp.domain.NotesViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context = context) }

    val viewModel: NotesViewModel = viewModel(

        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")

                return NotesViewModel(database.noteDao()) as T
            }
        }
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NotesHeader()

        Spacer(Modifier.height(8.dp))

        SearchAndSortBar(
            searchQuery = viewModel.searchQuery,
            onSearchChange = { viewModel.searchQuery = it },
            sortBy = viewModel.sortBy,
            onSortByChange = { viewModel.sortBy = it },
            ascending = viewModel.ascending,
            onOrderChange = { viewModel.ascending = it }
        )

        Spacer(Modifier.height(12.dp))

        AddOrEditNoteCard(
            title = viewModel.title,
            note = viewModel.note,
            isEditing = viewModel.editingNoteId != null,
            onTitleChange = { viewModel.title = it },
            onNoteChange = { viewModel.note = it },
            onSubmit = {
                if (viewModel.editingNoteId == null) viewModel.addNote() else viewModel.updateNote()
            }
        )

        HorizontalDivider(Modifier.padding(vertical = 12.dp))

        NotesListOrEmpty(
            notes = viewModel.filteredAndSortedNotes,
            onNoteClick = { viewModel.editNote(it) },
            onDeleteNote = {
                viewModel.deleteNote(it)
                viewModel.clearInputs()
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    val viewModel = remember { NotesViewModel() }
//
//    MainScreen(viewModel = viewModel)
//}