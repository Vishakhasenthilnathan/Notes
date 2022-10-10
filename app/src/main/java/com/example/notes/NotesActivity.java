package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NotesActivity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Intent intent = getIntent();
        int noteId = intent.getIntExtra("noteId",-1);
        EditText text = findViewById(R.id.NotesText);

        if (noteId!=-1)
        {
            text.setText(MainActivity.notesList.get(noteId));
        }
        else
        {
            MainActivity.notesList.add("");
            noteId = MainActivity.notesList.size()-1;
        }

        int finalNoteId = noteId;
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notesList.set(finalNoteId,String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes",MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notesList);
                sharedPreferences.edit().putStringSet("notesSet", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}