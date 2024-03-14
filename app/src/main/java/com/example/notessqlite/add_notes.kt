package com.example.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqlite.databinding.ActivityAddNotesBinding
import com.example.notessqlite.datamodel.Note
import com.example.notessqlite.sqlhelper.NotesSqlDatabaseHelper

class add_notes : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var notesSqlDatabaseHelper: NotesSqlDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesSqlDatabaseHelper = NotesSqlDatabaseHelper(this)

        saveData()



    }

    private fun saveData() {
        binding.saveBttton.setOnClickListener{
            val title = binding.tittleeditText.text.toString()
            val content = binding.contenteditText.text.toString()

            if (title.isNotBlank() && content.isNotBlank()){
                val note = Note(0,title,content)
                notesSqlDatabaseHelper.insertNotes(note)
                finish()
                Toast.makeText(this,"Add Successfully",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "all", Toast.LENGTH_SHORT).show()
            }

        }
    }
}