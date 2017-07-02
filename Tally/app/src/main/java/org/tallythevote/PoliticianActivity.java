package org.tallythevote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PoliticianActivity extends AppCompatActivity{

    Toolbar toolbar;
    TextView followerCount, responseRate, positionView;
    RelativeLayout smallDescriptionView, phoneNumberView, emailView;

    int followerCountTally;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician);

        Bundle extras = getIntent().getExtras();
        String name = "", phone = "", email = "", description = "", position = "";
        if(extras != null){
            name = extras.getString("NAME");
            phone = extras.getString("PHONE");
            email = extras.getString("EMAIL");
            position = extras.getString("POSITION");
            description = extras.getString("DESCRIPTION");
        }
        loadResources(phone, email, position, description);
        setupFAB(name);
        setupToolbar(name);
        setupCollapsingToolbar();
    }

    private void setupFAB(final String name) {
        followerCountTally = followerCount.toString().charAt(0);
        final StringBuilder sb = new StringBuilder();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.followUser);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followerCountTally = 0;
                followerCountTally++;
                sb.append(followerCountTally);
                sb.append("\nFollowers");
                followerCount.setText(sb.toString());
                sb.delete(0, sb.toString().indexOf('s') + 1);
                Snackbar.make(view, "Now Following " + name, Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                followerCountTally--;
                                sb.append(followerCountTally);
                                sb.append("\nFollowers");
                                followerCount.setText(sb.toString());
                                sb.delete(0, sb.toString().indexOf('s') + 1);
                            }
                        }).show();
            }
        });
    }

    private void setupToolbar(String name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.toolbar_layout);

        collapsingToolbarLayout.setTitleEnabled(false);
    }

    private void loadResources(final String phone, final String email, String position, String description){

        //ImageView imageView;

        positionView = (TextView) findViewById(R.id.smallDescription);
        followerCount = (TextView) findViewById(R.id.followerCount);
        responseRate = (TextView) findViewById(R.id.responseRate);
        positionView.setText(position);

        smallDescriptionView = (RelativeLayout) findViewById(R.id.smallDescriptionRL);
        phoneNumberView = (RelativeLayout) findViewById(R.id.phoneNumberRL);
        emailView = (RelativeLayout) findViewById(R.id.emailRL);

        smallDescriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        phoneNumberView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ phone));
                v.getContext().startActivity(intent);
            }
        });
        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Citizen Requesting Response");
                intent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(intent, "Send email using..."));
                }catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(v.getContext(), "No Email Clients Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
