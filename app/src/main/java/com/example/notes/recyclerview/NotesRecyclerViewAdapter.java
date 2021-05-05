package com.example.notes.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.ViewNoteActivity;
import com.example.notes.model.Note;
import com.example.notes.util.Util;

import java.util.LinkedList;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder>  {

    private LinkedList<Note> notes;
    private Context context;

    public NotesRecyclerViewAdapter(LinkedList<Note> notes, Context context)
    {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.note_row, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.noteTextView.setText("Note " + position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start View Note activity
                Intent intent = new Intent(context, ViewNoteActivity.class);
                // Pass Note data
                intent.putExtra(Util.NOTE_ID, notes.get(position).getNote_id());
                intent.putExtra(Util.NOTE_CONTENTS, notes.get(position).getNote_description());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTextView = itemView.findViewById(R.id.noteNameTextView);
        }
    }
}
