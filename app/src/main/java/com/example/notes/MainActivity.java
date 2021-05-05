package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notes.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewNote(View view)
    {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }

    public void showAllNotes(View view)
    {
        Intent intent = new Intent(this, ShowNotes.class);
        startActivity(intent);
    }
}