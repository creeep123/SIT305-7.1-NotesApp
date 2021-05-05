package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.notes.data.DatabaseHelper;
import com.example.notes.model.Note;
import com.example.notes.recyclerview.NotesRecyclerViewAdapter;

import java.util.LinkedList;

public class ShowNotes extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView notesRecyclerView;
    NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    LinkedList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        databaseHelper = new DatabaseHelper(this);
        setUpRecyclerView();

    }

    private void setUpRecyclerView()
    {
        notes = databaseHelper.fetchNotes();
        notesRecyclerView = findViewById(R.id.noteRecyclerView);
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(notes, this);
        notesRecyclerView.setAdapter(notesRecyclerViewAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onResume() {
        super.onResume();

        setUpRecyclerView();
    }

}