package com.wordpress.kaitribble.unnamedvotingapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kai on 4/24/2017.
 */
public class EventItemAdapter extends RecyclerView.Adapter<EventItemAdapter.MyViewHolder>{

    List<Event> listOfEvents = Collections.emptyList();

    public EventItemAdapter(List<Event> data){
        this.listOfEvents = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(listOfEvents.get(position).eventName);
        holder.description.setText(listOfEvents.get(position).eventDescription);
    }

    @Override
    public int getItemCount() {
        return listOfEvents.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description;
        View horizontalLine;
        FloatingActionButton floatingActionButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.eventName);
            description = (TextView) itemView.findViewById(R.id.eventDescriptionText);
            horizontalLine = itemView.findViewById(R.id.horizontalLine);
            floatingActionButton = (FloatingActionButton) itemView.findViewById(R.id.searchFAB);
        }
    }
}
