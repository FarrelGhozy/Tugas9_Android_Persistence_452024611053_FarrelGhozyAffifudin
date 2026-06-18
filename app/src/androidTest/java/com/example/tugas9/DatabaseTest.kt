package com.example.tugas9

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tugas9.data.Note
import com.example.tugas9.data.NoteDao
import com.example.tugas9.data.NoteDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDatabase

    private val note1 = Note(title = "Belajar Android", content = "Mempelajari Room Database")
    private val note2 = Note(title = "Belajar Coroutines", content = "Mempelajari Kotlin Coroutines")
    private val note3 = Note(title = "Belajar Compose", content = "Mempelajari Jetpack Compose")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()
        noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieve() = runBlocking {
        noteDao.insertAll(note1, note2, note3)
        val notes = noteDao.getAllNotes().first()
        assert(notes.size == 3)
        assert(notes[0].title == "Belajar Android")
        assert(notes[0].content == "Mempelajari Room Database")
    }

    @Test
    @Throws(Exception::class)
    fun insertAndDelete() = runBlocking {
        noteDao.insert(note1)
        noteDao.insert(note2)
        val notesBefore = noteDao.getAllNotes().first()
        val noteToDelete = notesBefore.first { it.title == "Belajar Android" }
        noteDao.delete(noteToDelete)
        val notesAfter = noteDao.getAllNotes().first()
        assert(notesAfter.size == 1)
        assert(notesAfter[0].title == "Belajar Coroutines")
    }

    @Test
    @Throws(Exception::class)
    fun insertAndUpdate() = runBlocking {
        noteDao.insert(note1)
        val insertedNote = noteDao.getNoteById(1)
        val updatedNote = insertedNote!!.copy(title = "Updated Title")
        noteDao.update(updatedNote)
        val notes = noteDao.getAllNotes().first()
        assert(notes.size == 1)
        assert(notes[0].title == "Updated Title")
    }
}
