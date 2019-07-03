package com.example.kalandarz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    ArrayList<Event> events;

    public EventAdapter(ArrayList<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View listItem=layoutInflater.inflate(R.layout.row, viewGroup, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Event event=events.get(position);
        viewHolder.title.setText(event._date);
        viewHolder.description.setText(event._name);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        RelativeLayout relativeLayout;

        ViewHolder(View item) {
            super(item);
            title= item.findViewById(R.id.firstLine);
            description=item.findViewById(R.id.secondLine);
            relativeLayout=item.findViewById(R.id.relativelayout);
        }
    }
}
