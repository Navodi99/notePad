package com.example.notepadapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.example.notepadapplication.noteModel.Note;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {

    private ImageButton addButton;
    private List<Note> notes;
    private RecyclerView listNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        addButton=findViewById(R.id.addBtn);
        listNote=findViewById(R.id.notesList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SaveNote.class);
                startActivity(intent);
            }
        });
        notes=new ArrayList();
        notes.add(new Note(1,"first","2022/1/2","jdbbgbwbgwubgigiegubgu"));
        notes.add(new Note(2,"Second","2022/1/3","jdbhhhgbwbgwubgigiegubgu"));
        notes.add(new Note(3,"Third","2022/1/4","jdbbgbmghjgjgwubgigiegubgu"));
        notes.add(new Note(4,"Forth","2022/1/5","jdbbgbhdhhthrrrrrurwubgigiegubgu"));
        notes.add(new Note(5,"Fifth","2022/1/6","jdbbgbwbgfdgdrgdgrdvvfgggrgrsg bgigiegubgu"));

        Adapter adapter=new Adapter(this,notes);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4,GridLayoutManager.VERTICAL,false);
        listNote.setLayoutManager(gridLayoutManager);
        listNote.setAdapter(adapter);
    }
}