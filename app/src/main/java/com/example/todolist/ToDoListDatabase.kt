package com.example.todolist

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update


@Database(entities = [Note::class], version = 1)
abstract class ToDoListDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}


@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,


    val title: String,
    val time: String,
    val date: String,
    val entryTime: String
)

@Dao
interface NoteDao {
    @Insert
    fun insertData(note: Note)

    @Update
    fun updateData(note: Note)

    @Delete
    fun deleteData(note: Note)

    @Query("DELETE FROM Note")
    fun nukeTable()

    @Query("SELECT * From Note")
    fun getAllData(): List<Note>
}