package com.example.notepadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepadapplication.noteModel.Note;

public class ViewNote extends AppCompatActivity {

    private TextView title;
    private TextView date;
    private TextView representation;
    private ImageButton closeBtn;
    private ImageButton editBtn;
    private ImageButton deleteBtn;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        databaseHandler=new DatabaseHandler(this);

        title=findViewById(R.id.txtViewTitle);
        date=findViewById(R.id.txtViewDate);
        representation=findViewById(R.id.txtViewDescription);
        closeBtn=findViewById(R.id.btnImgClose);
        editBtn=findViewById(R.id.btnImgEdit);
        deleteBtn=findViewById(R.id.btnImgDelete);

        Intent intent=getIntent();
        if(intent.hasExtra("ID")){
            int id=intent.getIntExtra("ID",-1);
            Note note=databaseHandler.getSingleNote(id);

            title.setText(note.getTitle());
            date.setText(note.getDate());
            representation.setText(note.getBody());
        }


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=intent.getIntExtra("ID",-1);

            Intent intent1=new Intent(getApplicationContext(), SaveNote.class);
            intent1.putExtra("ID",id);
            startActivity(intent1);
            }


        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=intent.getIntExtra("ID",-1);
                databaseHandler.deleteNote(id);
                Intent intent1=new Intent(getApplicationContext(), Notes.class);
                startActivity(intent1);
                Toast.makeText(ViewNote.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(), Notes.class);
                startActivity(intent1);
            }
        });
    }


}