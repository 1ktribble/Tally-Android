package com.wordpress.kaitribble.unnamedvotingapplication;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kai on 4/24/2017.
 */
public class EventTypeAdapter extends RecyclerView.Adapter<EventTypeAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<EventType> listOfEventTypes = Collections.emptyList();

    public EventTypeAdapter(Context context, List<EventType> data){
        inflater = LayoutInflater.from(context);
        this.listOfEventTypes = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.event_type_row, parent);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EventType eventType = listOfEventTypes.get(position);

        holder.eventType.setText(eventType.getEventTypeTitle());
        holder.icon.setImageResource(eventType.iconId);
    }

    @Override
    public int getItemCount() {
        return listOfEventTypes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eventType;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            eventType = (TextView) itemView.findViewById(R.id.eventTypeTextview);
            icon = (ImageView) itemView.findViewById(R.id.handsUp);
        }
    }
}
