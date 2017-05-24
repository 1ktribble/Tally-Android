package org.tallythevote;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PoliticianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.tallythevote.R.layout.activity_politician);

        Bundle extras = getIntent().getExtras();
        String name = "", phone = "", email = "", description = "", position = "";
        int image = org.tallythevote.R.drawable.ic_email_black_24dp;
        if(extras != null){
            name = extras.getString("NAME");
            phone = extras.getString("PHONE");
            email = extras.getString("EMAIL");
            position = extras.getString("POSITION");
            description = extras.getString("DESCRIPTION");
            image = extras.getInt("IMAGE");
        }

        loadResources(name, phone, email, position, description, image);
    }

    private void loadResources(String name, String phone, final String email, String position,
                               String description, int image) {
        TextView positionView, nameView, descriptionView;
        ImageButton emailView, phoneView;
        ImageView imageView;

        final String phoneNumber = phone;

        positionView = (TextView) findViewById(org.tallythevote.R.id.politicianPositionText);
        positionView.setText(position);
        nameView = (TextView) findViewById(org.tallythevote.R.id.politicianName);
        nameView.setText(name);
        descriptionView = (TextView) findViewById(org.tallythevote.R.id.politicianDescription);
        descriptionView.setText(description);

        imageView = (ImageView) findViewById(org.tallythevote.R.id.politicianImage);
        imageView.setImageResource(image);

        phoneView = (ImageButton) findViewById(org.tallythevote.R.id.callButton);
        phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ phoneNumber));
                v.getContext().startActivity(intent);
            }
        });
        emailView = (ImageButton) findViewById(org.tallythevote.R.id.emailButton);
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
