package com.example.notessqlite.noteAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notessqlite.Up_dateActivity
import com.example.notessqlite.databinding.NotesItemsBinding
import com.example.notessqlite.datamodel.Note
import com.example.notessqlite.sqlhelper.NotesSqlDatabaseHelper

class NotesAdapter(private var note :List<Note>, private var context: Context):
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
        private val db:NotesSqlDatabaseHelper = NotesSqlDatabaseHelper(context)
    inner class MyViewHolder(var binding: NotesItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = NotesItemsBinding.inflate(LayoutInflater.from(context,),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.titletextView.text = note.get(position).title
        holder.binding.contenttextView.text = note.get(position).content

        holder.binding.updatebuttton.setOnClickListener{
            val intent = Intent(holder.itemView.context,Up_dateActivity::class.java).apply {
                putExtra("note_id", note[position].id)
            }
            holder.itemView.context.startActivity(intent)

        }
        holder.binding.deleteButton.setOnClickListener {
            db.deleteNote(note[position].id)
            referesData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Delete Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun  referesData(newNotes: List<Note>){
        note = newNotes
        notifyDataSetChanged()
    }

}