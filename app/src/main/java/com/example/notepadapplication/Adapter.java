package com.example.notepadapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadapplication.noteModel.Note;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Note> notes;
    LayoutInflater inflater;

    public Adapter(Context ctx, List<Note>notes){
        this.notes=notes;
        this.inflater=LayoutInflater.from(ctx);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       TextView topic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topic=itemView.findViewById(R.id.txtTopic);
        }
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter.ViewHolder holder, int position) {
        holder.topic.setText(notes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
