package com.example.notes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.notes.model.Note;
import com.example.notes.util.Util;

import java.util.LinkedList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create Notes Table
        String CREATE_NOTES_TABLE = "CREATE TABLE " + Util.NOTES_TABLE_NAME
                + " ("
                + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.NOTE_CONTENTS + " TEXT"
                + ")";

        sqLiteDatabase.execSQL(CREATE_NOTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Drop Notes Table
        String DROP_NOTES_TABLE = "DROP TABLE IF EXISTS " + Util.NOTES_TABLE_NAME;
        sqLiteDatabase.execSQL(DROP_NOTES_TABLE);

    }

    public long insertNote(Note note)
    {
        try
        {
            // Get Writable Database
            SQLiteDatabase db = this.getWritableDatabase();

            // Set Values
            ContentValues contentValues = new ContentValues();
            contentValues.put(Util.NOTE_CONTENTS, note.getNote_description());

            // Insert note
            long newRowId = db.insert(Util.NOTES_TABLE_NAME, null, contentValues);

            // Close database
            db.close();

            // Return Row id
            return newRowId;
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception Caught: " + e.getMessage());
            return 0;
        }
    }

    public LinkedList<Note> fetchNotes()
    {
        try
        {
            // Get Readable Database
            SQLiteDatabase db = this.getReadableDatabase();

            String query = "SELECT * FROM " + Util.NOTES_TABLE_NAME;

            // Query Database
            Cursor cursor = db.rawQuery(query, new String[]{});

            LinkedList<Note> notes = new LinkedList<>();

            if (cursor.moveToFirst()) {
                do {

                    int noteId = cursor.getInt(cursor.getColumnIndex(Util.NOTE_ID));
                    String noteContents = cursor.getString(cursor.getColumnIndex(Util.NOTE_CONTENTS));

                    Note note = new Note(noteId, noteContents);
                    notes.add(note);

                } while (cursor.moveToNext());
            }

            // Close cursor
            cursor.close();

            // Close Database
            db.close();

            // Return notes
            return notes;
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception Caught: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    public boolean removeNote(int note_id)
    {
        try {
            // Get Writable Database
            SQLiteDatabase db = this.getWritableDatabase();

            return db.delete(Util.NOTES_TABLE_NAME, Util.NOTE_ID + "=?", new String[]{"" + note_id}) > 0;
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception Caught: " + e.getMessage());
            return false;
        }
    }

    public boolean updateNote(Note note)
    {
        try
        {
            // Get Writable Database
            SQLiteDatabase db = this.getWritableDatabase();

            // Set Values
            ContentValues contentValues = new ContentValues();
            contentValues.put(Util.NOTE_ID, note.getNote_id());
            contentValues.put(Util.NOTE_CONTENTS, note.getNote_description());

            // Update row
            return db.update(Util.NOTES_TABLE_NAME, contentValues, Util.NOTE_ID + " = " + note.getNote_id(), null) > 0;
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception Caught: " + e.getMessage());
            return false;
        }
    }


}
