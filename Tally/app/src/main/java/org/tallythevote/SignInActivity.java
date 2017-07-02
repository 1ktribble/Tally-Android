package org.tallythevote;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private SharedPreferences mPref;

    private ProgressDialog mProgressDialog;

    private static final String TAG = "EmailPassword";
    private static final String Login_Key = "LOGIN_KEY";

    private EditText mSignUpEmailField, mSignUpPasswordField, mSignInEmailField, mSignInPasswordField;

    private RelativeLayout mSignInLayout, mSignUpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initialize();
    }

    private void initialize() {
        mPref = getSharedPreferences(Login_Key, MODE_PRIVATE);

        if (mPref.getBoolean(Login_Key, false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            bindResources();
            //firebaseAuth();
        }
    }

    private void bindResources() {
        findViewById(R.id.signUpTextView).setOnClickListener(this);
        findViewById(R.id.forgotPasswordLink).setOnClickListener(this);


        mSignUpLayout = (RelativeLayout) findViewById(R.id.signUpLayout);
        mSignUpEmailField = (EditText) findViewById(R.id.signUpAddressEditText);
        mSignUpPasswordField = (EditText) findViewById(R.id.signUpPasswordEditText);

        mSignInLayout = (RelativeLayout) findViewById(R.id.signInLayout);
        mSignInEmailField = (EditText) findViewById(R.id.signInAddressEditText);
        mSignInPasswordField = (EditText) findViewById(R.id.signInPasswordEditText);

        findViewById(R.id.signUpLink).setOnClickListener(this);
        findViewById(R.id.signInLink).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }


    private void showProgressDialog(CharSequence message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setMessage(message);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    private void SignUp() {
        String email = mSignUpEmailField.getText().toString().trim();
        String password = mSignUpPasswordField.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // email is blank
            mSignUpEmailField.setError("Please enter email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            mSignUpPasswordField.setError("Please enter password");
            return;
        }
        // Validations passed, show a progress bar.
           showProgressDialog("Signing Up...");
           mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // User successfully registered or logged in.
                                Log.d(TAG, "Registered Successfully");
                                hideProgressDialog();
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Log.d(TAG, "Registration Unsuccessful");
                                hideProgressDialog();
                            }
                        }
                    });
    }

    private void signIn(){
        String email = mSignInEmailField.getText().toString().trim();
        String password = mSignInPasswordField.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // email is blank
            mSignInEmailField.setError("Please enter email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            mSignInPasswordField.setError("Please enter password");
            return;
        }
        // Validations passed, show a progress bar.
        showProgressDialog("Signing In...");

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG, "successful log in");
                            hideProgressDialog();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        } else {
                            Log.w(TAG, "log in unsuccessful", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication Failure", Toast.LENGTH_SHORT)
                                    .show();
                            hideProgressDialog();
                        }
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case (R.id.signUpButton):
                SignUp();
                break;
            case (R.id.signInButton):
                signIn();
                break;
            case (R.id.signUpLink):
                updateUI(true);
                break;
            case (R.id.signInLink):
                updateUI(false);
                break;
            case (R.id.forgotPasswordLink):
                break;
            default:
                break;
        }
    }

    private void updateUI(boolean isSignInLayout) {
        if(isSignInLayout){
            mSignInLayout.setVisibility(View.GONE);
            mSignUpLayout.setVisibility(View.VISIBLE);
        }
        else{
            mSignUpLayout.setVisibility(View.GONE);
            mSignInLayout.setVisibility(View.VISIBLE);
        }
    }
}
