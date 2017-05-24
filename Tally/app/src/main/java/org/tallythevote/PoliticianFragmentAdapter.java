package org.tallythevote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kai on 4/28/2017.
 *
 * The politician fragment adapter c
 */
public class PoliticianFragmentAdapter extends RecyclerView.Adapter<PoliticianFragmentAdapter.MyViewHolder> {

    List<Politician> politicianList;

    public PoliticianFragmentAdapter(List<Politician> data){
        this.politicianList = data;
    }

    @Override
    public PoliticianFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(org.tallythevote.R.layout.politician_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PoliticianFragmentAdapter.MyViewHolder holder, int position) {
        final Bundle mBundle = new Bundle();
        mBundle.putString("POSITION", politicianList.get(position).getPoliticianPosition());
        mBundle.putString("NAME", politicianList.get(position).getPoliticianName());
        mBundle.putString("DESCRIPTION", politicianList.get(position).getPoliticianDescription());
        mBundle.putString("EMAIL", politicianList.get(position).getPoliticianEmail());
        mBundle.putString("PHONE", politicianList.get(position).getPoliticianPhone());
        mBundle.putInt("IMAGE", politicianList.get(position).getPoliticianImage());

        holder.getCurrentPoliticianName().setText(politicianList.get(position).getPoliticianName());
        holder.getCurrentPoliticianRole().setText(politicianList.get(position).getPoliticianPosition());
        holder.getCurrentPoliticianImage().setImageResource(politicianList.get(position).getPoliticianImage());

        if(politicianList.get(position).getPoliticianRegion() == "County")
            holder.billHistory.setText("More Info");
        holder.billHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PoliticianActivity.class);
                intent.putExtras(mBundle);
                Activity tempActivity = (Activity) v.getContext();
                tempActivity.startActivity(intent);
            }
        });
//        holder.getCurrentPoliticianImage().setImageResource(politicianList.get(position).getPoliticianId());
    }

    @Override
    public int getItemCount() {
        return politicianList == null ? 0 : politicianList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private Button billHistory, followButton;
        private ImageView politicianProfilePicture;
        private TextView politicianName, politicianRole;

        public MyViewHolder(final View itemView) {
            super(itemView);

            billHistory = (Button) itemView.findViewById(org.tallythevote.R.id.billHistoryButton);
            followButton = (Button) itemView.findViewById(org.tallythevote.R.id.followButton);
            politicianProfilePicture = (ImageView) itemView.findViewById(org.tallythevote.R.id.politicianImage);
            politicianName = (TextView) itemView.findViewById(org.tallythevote.R.id.politicianName);
            politicianRole = (TextView) itemView.findViewById(org.tallythevote.R.id.politicianPosition);

            followButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(followButton.getText().toString() == "Follow")
                        followButton.setText(org.tallythevote.R.string.unfollow_politician);
                    else
                        followButton.setText("Follow");
                }
            });

        }

        public TextView getCurrentPoliticianName() {
            return politicianName;
        }

        public TextView getCurrentPoliticianRole() {
            return politicianRole;
        }

        public ImageView getCurrentPoliticianImage() {
            return politicianProfilePicture;
        }
    }

}
