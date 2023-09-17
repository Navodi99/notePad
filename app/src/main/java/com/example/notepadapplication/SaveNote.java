package com.example.notepadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        Intent intent=getIntent();
        if(intent.hasExtra("ID")){
            int id=intent.getIntExtra("ID",-1);
            Note note2=databaseHandler.getSingleNote(id);

            title.setText(note2.getTitle());
            date.setText(note2.getDate());
            body.setText(note2.getBody());
            save.setText("Update");
        }

        if (save.getText().toString().equalsIgnoreCase("Save")){
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String titleValue=title.getText().toString();
                    String dateValue=date.getText().toString();
                    String bodyVal=body.getText().toString();
                    Note noteObj=new Note(titleValue,dateValue,bodyVal);

                    databaseHandler.saveNote(noteObj);
                    Intent intent=new Intent(getApplicationContext(), Notes.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                }
            });
        }else {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id=intent.getIntExtra("ID",-1);
                    String titleValue=title.getText().toString();
                    String dateValue=date.getText().toString();
                    String bodyVal=body.getText().toString();
                    Note noteObj=new Note(id,titleValue,dateValue,bodyVal);

                    int status=databaseHandler.updateNote(noteObj);
                    Intent intent=new Intent(getApplicationContext(), Notes.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();


                }
            });
        }
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(), Notes.class);
                startActivity(intent1);
            }
        });



    }
}