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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
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

import static java.util.concurrent.TimeUnit.SECONDS;

public class Mobilelogin extends AppCompatActivity {
    ImageButton Otpbutton,loginbutton;
    EditText phonenumber,otp;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthCredential credential;
    private String codesent,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilelogin);

        phonenumber = (EditText) findViewById(R.id.phonenumber);
        otp = (EditText) findViewById(R.id.otp);
        Otpbutton = (ImageButton) findViewById(R.id.Otpbutton);
        loginbutton = (ImageButton) findViewById(R.id.loginbutton);
        mAuth=FirebaseAuth.getInstance();

        Otpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobile = phonenumber.getText().toString();
                if (mobile.isEmpty()) {
                    phonenumber.setError("Phone number is required");
                }
                if (mobile.length() < 10) {
                    phonenumber.setError("Enter valid phone number");
                }
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        mobile,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        Mobilelogin.this,      // Activity (for callback binding)
                        callbacks);        // OnVerificationStateChangedCallbacks
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String verifcationcode=otp.getText().toString();
                if(verifcationcode.isEmpty()){
                    Toast.makeText(Mobilelogin.this,"Invalid code",Toast.LENGTH_LONG).show();
                }else{
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(codesent,verifcationcode);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                //signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(Mobilelogin.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                Otpbutton.setVisibility(View.VISIBLE);
                loginbutton.setVisibility(View.INVISIBLE);
                otp.setVisibility(View.INVISIBLE);

            }
            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // Save verification ID and resending token so we can use them later
                codesent = verificationId;
                mResendToken = token;
                Toast.makeText(Mobilelogin.this,"Code sent",Toast.LENGTH_LONG).show();

                Otpbutton.setVisibility(View.INVISIBLE);
                loginbutton.setVisibility(View.VISIBLE);
                otp.setVisibility(View.VISIBLE);
            }
        };

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           Intent intent=new Intent(Mobilelogin.this,MainActivity.class);
                           startActivity(intent);
                        }
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(Mobilelogin.this,"Error:"+task.getException().toString(),Toast.LENGTH_LONG).show();

                        }
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(Mobilelogin.this,"Error:"+task.getException().toString(),Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }
    
    }


