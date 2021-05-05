package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.data.DatabaseHelper;
import com.example.notes.model.Note;
import com.example.notes.util.Util;

public class ViewNoteActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText updateNoteEditText;
    Button updateNoteButton;
    Button deleteNoteButton;

    int noteId = -1;
    String noteContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        setUpWidgets();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Retrieve note
            noteId = extras.getInt(Util.NOTE_ID);
            noteContents = extras.getString(Util.NOTE_CONTENTS);

            setUpNote();
        }
        else
        {
            // Error
            Toast.makeText(ViewNoteActivity.this, "Note id and contents were not passed to ViewNoteActivity", Toast.LENGTH_LONG).show();
            finish();
        }

        databaseHelper = new DatabaseHelper(this);

    }

    private void setUpWidgets()
    {
        updateNoteEditText = findViewById(R.id.updateNoteEditText);
        updateNoteButton = findViewById(R.id.updateNoteButton);
        deleteNoteButton = findViewById(R.id.deleteNoteButton);
    }

    private void setUpNote()
    {
        updateNoteEditText.setText(noteContents);
    }

    public void updateNote(View view)
    {
        String newNoteContents = updateNoteEditText.getText().toString();

        if (newNoteContents.isEmpty())
        {
            Toast.makeText(ViewNoteActivity.this, "Cannot save empty note", Toast.LENGTH_LONG).show();
        }
        else
        {
            Note note = new Note(noteId, newNoteContents);

            if (databaseHelper.updateNote(note))
            {
                // Success
                Toast.makeText(ViewNoteActivity.this, "Note was successfully updated", Toast.LENGTH_LONG).show();

                setResult(RESULT_OK);

                finish();
            }
            else
            {
                Toast.makeText(ViewNoteActivity.this, "Note could not be updated", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteNote(View view)
    {
        if (databaseHelper.removeNote(noteId))
        {
            // Success
            Toast.makeText(ViewNoteActivity.this, "Note was successfully deleted", Toast.LENGTH_LONG).show();

            finish();
        }
        else
        {
            // Error
            Toast.makeText(ViewNoteActivity.this, "Note could not be deleted", Toast.LENGTH_LONG).show();

        }
    }
}