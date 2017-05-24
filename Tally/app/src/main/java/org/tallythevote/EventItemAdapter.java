package org.tallythevote;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(org.tallythevote.R.layout.event_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(listOfEvents.get(position).eventName);
        holder.description.setText(listOfEvents.get(position).eventDescription);
        holder.address.setClickable(true);
        holder.address.setMovementMethod(LinkMovementMethod.getInstance());
        if(Build.VERSION.SDK_INT >= 24)
            holder.address.setText(Html.fromHtml(listOfEvents.get(position).address, 63));
        else
            holder.address.setText(Html.fromHtml(listOfEvents.get(position).address));


        String dateMonthName;

        switch (listOfEvents.get(position).getDate().get(Calendar.MONTH)) {
            case 1:
                dateMonthName = "JAN";
                break;
            case 2:
                dateMonthName = "FEB";
                break;
            case 3:
                dateMonthName = "MAR";
                break;
            case 4:
                dateMonthName = "APR";
                break;
            case 5:
                dateMonthName = "MAY";
                break;
            case 6:
                dateMonthName = "JUNE";
                break;
            case 7:
                dateMonthName = "JULY";
                break;
            case 8:
                dateMonthName = "AUG";
                break;
            case 9:
                dateMonthName = "SEPT";
                break;
            case 10:
                dateMonthName = "OCT";
                break;
            case 11:
                dateMonthName = "NOV";
                break;
            case 12:
                dateMonthName = "DEC";
                break;
            default:
                dateMonthName = "";
                break;
        }

        holder.date.setText(dateMonthName +
                "\n" + (listOfEvents.get(position).getDate().get(Calendar.DATE) < 10 ?
                "0" + listOfEvents.get(position).getDate().get(Calendar.DATE) :
                listOfEvents.get(position).getDate().get(Calendar.DATE)));

        final int tempPo = position;

        holder.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, listOfEvents
                        .get(tempPo).getDate().getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, listOfEvents
                        .get(tempPo).getDate().getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE, listOfEvents.get(tempPo).getEventName());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, listOfEvents.get(tempPo).getEventDescription());
                v.getContext().startActivity(intent);
            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = ShareCompat.IntentBuilder.from((Activity) v.getContext()).setType("text/plain")
                        .setText("Come with me to \n"
                                + listOfEvents.get(tempPo).getEventName() + "\n"
                                + ". Here is the description" + listOfEvents.get(tempPo).getEventDescription() + "\n"
                                + ". I found out about this event using Tally!").getIntent();
                if(shareIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    v.getContext().startActivity(shareIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfEvents.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, date, address;
        View horizontalLine;
        Button goButton, shareButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(org.tallythevote.R.id.eventName);
            description = (TextView) itemView.findViewById(org.tallythevote.R.id.eventDescriptionText);
            horizontalLine = itemView.findViewById(org.tallythevote.R.id.horizontalLine);
            goButton = (Button) itemView.findViewById(org.tallythevote.R.id.goButton);
            shareButton = (Button) itemView.findViewById(org.tallythevote.R.id.shareButton);
            date = (TextView) itemView.findViewById(org.tallythevote.R.id.dateView);
            address = (TextView) itemView.findViewById(org.tallythevote.R.id.addressLinkTextView);

        }
    }
}
