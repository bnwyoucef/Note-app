package com.bounoua.note_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class NoteDetails extends AppCompatActivity {
    private TextView title,description,noteDate;
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Note noteObj = (Note) getIntent().getSerializableExtra("noteObject");
        initViews();
        title.setText(noteObj.getTitle());
        description.setText(noteObj.getDescription());
        noteDate.setText(noteObj.getNoteDate());
        boolean textToSpeechIsInitialized = false;
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                    textToSpeech.speak(description.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

    }

    private void initViews() {
        title = findViewById(R.id.titleDetails);
        description = findViewById(R.id.descriptionDetails);
        noteDate = findViewById(R.id.dateDetails);
    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.stop();
    }
}