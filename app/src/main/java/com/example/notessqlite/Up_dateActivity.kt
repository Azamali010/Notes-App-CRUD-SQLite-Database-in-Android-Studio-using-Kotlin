package com.example.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqlite.databinding.ActivityUpDateBinding
import com.example.notessqlite.datamodel.Note
import com.example.notessqlite.sqlhelper.NotesSqlDatabaseHelper

class Up_dateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpDateBinding
    private lateinit var notesSqlDatabaseHelper: NotesSqlDatabaseHelper
    private var noteId :Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesSqlDatabaseHelper = NotesSqlDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
            finish()
            return
        }
        val note = notesSqlDatabaseHelper.getNotById(noteId)
        binding.updatetittleeditText.setText(note.title)
        binding.updatecontenteditText.setText(note.content)

        binding.updatesaveBttton.setOnClickListener {
            val newTitle = binding.updatetittleeditText.text.toString()
            val newContent = binding.updatecontenteditText.text.toString()
            val UpDateNote = Note(noteId,newTitle,newContent)
            notesSqlDatabaseHelper.upDateDataNote(UpDateNote)
            finish()
            Toast.makeText(this,"Changes Save",Toast.LENGTH_SHORT).show()
        }

    }
}