package com.example.notepadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notepadapplication.noteModel.Note;

public class SaveNote extends AppCompatActivity {

    private EditText title;
    private EditText date;
    private EditText body;
    private Button save;
    private Button close;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_note);

        title=findViewById(R.id.editTitle);
        date=findViewById(R.id.editDate);
        body=findViewById(R.id.editNote);
        save=findViewById(R.id.saveBtn);
        close=findViewById(R.id.closeBtn);
        databaseHandler=new DatabaseHandler(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic=title.getText().toString();
                String dateVal=date.getText().toString();
                String description=body.getText().toString();
                Note note1=new Note(topic,dateVal,description);

                databaseHandler.saveNote(note1);

                Intent intent=new Intent(getApplicationContext(),Notes.class);
                startActivity(intent);
            }
        });
    }
}