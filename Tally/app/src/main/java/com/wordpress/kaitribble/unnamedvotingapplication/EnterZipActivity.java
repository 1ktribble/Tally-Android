package com.wordpress.kaitribble.unnamedvotingapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.InputMismatchException;

public class EnterZipActivity extends AppCompatActivity {
    public static final String USER_PREF = "User Preferences";
    private static final int SPEECH_REQUEST_CODE = 0;

    private int zipCode;

    private EditText userInput;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveSharedPreferences();
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(USER_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userZip", zipCode);

        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_zip);
    }

    @Override
    protected void onStart() {
        super.onStart();

        configureActions();
        loadPreferences();
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(USER_PREF, 0);
        zipCode = settings.getInt("userZip", 94088);
        if(Integer.toString(zipCode) != null )
            userInput.setText(Integer.toString(zipCode));

    }

    private void configureActions() {
        userInput = (EditText) findViewById(R.id.county_zipInput);

        ImageView backButton = (ImageView) findViewById(R.id.backToMain);
        ImageView speechToText = (ImageView) findViewById(R.id.speechToText);
        ImageView getLocation = (ImageView) findViewById(R.id.getLocation);
        ImageView appImage = (ImageView) findViewById(R.id.appLogo);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipCode = Integer.valueOf(userInput.getText().toString());
                finish();
            }
        });

        speechToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeech();
            }
        });

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        appImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    zipCode = Integer.valueOf(userInput.getText().toString());
                    finish();
                }catch (InputMismatchException e){
                    // handle non-integers
                    userInput.setError(getString(R.string.zip_code_error));
                }
            }
        });
    }

    private void getSpeech() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        saveSharedPreferences();
    }
}
