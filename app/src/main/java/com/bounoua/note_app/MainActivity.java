package com.bounoua.note_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list",noteList);
    }

    private ArrayList<Note> noteList = new ArrayList<>();
    private NoteAdapter noteAdapter;
    private TextView emptyMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            noteList = (ArrayList<Note>) savedInstanceState.getSerializable("list");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        emptyMsg = findViewById(R.id.empty);
        noteAdapter = new NoteAdapter(this);
        noteAdapter.setNotes(noteList);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(noteAdapter.getItemCount() > 0) {
            emptyMsg.setVisibility(View.GONE);
        }else {
            emptyMsg.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.popup);
            dialog.show();
            Button btn = dialog.findViewById(R.id.addNoteBtn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText addTitle = dialog.findViewById(R.id.addNoteTitle);
                    EditText addDescreption = dialog.findViewById(R.id.addNoteDescription);
                    Note addNote = new Note(addTitle.getText().toString(),addDescreption.getText().toString(), strDate);
                    noteList.add(addNote);
                    noteAdapter.notifyDataSetChanged();
                    emptyMsg.setVisibility(View.GONE);
                    dialog.dismiss();
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }
}