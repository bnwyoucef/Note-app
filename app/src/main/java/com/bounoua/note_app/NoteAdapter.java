package com.bounoua.note_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    ArrayList<Note> notes = new ArrayList<>();
    Context context;

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoteDetails.class);
                intent.putExtra("noteObject",notes.get(position));
                context.startActivity(intent);
            }
        });
        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete note")
                        .setMessage("Are you sure to delete" + notes.get(position).getTitle())
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notes.remove(notes.get(position));
                                notifyDataSetChanged();
                            }
                        }).show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private TextView title;
        private TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parentCARD);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
