package com.example.notepadapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.example.notepadapplication.noteModel.Note;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity implements Listener{

    private ImageButton addButton;
    private List<Note> notes;
    private RecyclerView listNote;
    private EditText searchBar;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        addButton=findViewById(R.id.addBtn);
        listNote=findViewById(R.id.notesList);
        searchBar=findViewById(R.id.edtTextSearch);
        databaseHandler=new DatabaseHandler(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SaveNote.class);
                startActivity(intent);
            }
        });

        notes=new DatabaseHandler(this).getNote();

        Adapter adapter=new Adapter(this,notes,this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4,GridLayoutManager.VERTICAL,false);
        listNote.setLayoutManager(gridLayoutManager);
        listNote.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            String title=searchBar.getText().toString().trim();
            List<Note> result=databaseHandler.search(title);

            Adapter adapter=new Adapter(Notes.this,result,Notes.this);
                listNote.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onItemClicked(Note note) {
        Intent intent=new Intent(getApplicationContext(), ViewNote.class);
        intent.putExtra("ID",note.getId());
        startActivity(intent);

    }
}