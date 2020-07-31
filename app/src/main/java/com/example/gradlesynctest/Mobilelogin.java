package com.example.gradlesynctest;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Mobilelogin extends AppCompatActivity {

  ImageButton Otpbutton,loginbutton;
  EditText phonenumber,otp;
  FirebaseAuth mAuth;
  String codesent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilelogin);

        phonenumber=(EditText) findViewById(R.id.phonenumber);
        otp=(EditText) findViewById(R.id.otp);
        Otpbutton=(ImageButton) findViewById(R.id.Otpbutton);
        loginbutton=(ImageButton) findViewById(R.id.loginbutton);

        mAuth=FirebaseAuth.getInstance();

        Otpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtp();
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verification();
            }
        });
    }

    private void sendOtp(){
        String mobile=phonenumber.getText().toString();

        if(mobile.isEmpty()){
            phonenumber.setError("Phone number is required");
            phonenumber.requestFocus();
            return;
        }
        if(mobile.length()<10 || mobile.length()>10){
            phonenumber.setError("Enter a valid phone number");
            phonenumber.requestFocus();
            return;
        }

        // To send otp

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
    }

    // code sent via sms to the user is s
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codesent=s;
        }
    };

    private void verification() {
        String code = otp.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesent, code);
    signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Mobilelogin.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                                Toast.makeText(getApplicationContext(),"Login failure",Toast.LENGTH_LONG).show();
                                }
                        }
                    });
        }

    }


