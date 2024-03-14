package com.example.notessqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notessqlite.databinding.ActivityMainBinding
import com.example.notessqlite.noteAdapter.NotesAdapter
import com.example.notessqlite.sqlhelper.NotesSqlDatabaseHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var notesSqlDatabaseHelper: NotesSqlDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(4000)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        notesSqlDatabaseHelper = NotesSqlDatabaseHelper(this)

        notesAdapter = NotesAdapter(notesSqlDatabaseHelper.getAllNotes(),this)
       // notesSqlDatabaseHelper = NotesSqlDatabaseHelper(this)
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
      //  binding.recyclerViewId.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerViewId.adapter= notesAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this,add_notes::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.referesData(notesSqlDatabaseHelper.getAllNotes())
    }
}