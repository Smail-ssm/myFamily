package com.emna.myfamily.login;

import android.content.Intent;
import android.os.Bundle;

import com.emna.myfamily.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.emna.myfamily.R;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText editTextCountryCode, editTextPhone;
    Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextCountryCode = findViewById(R.id.editTextCountryCode);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonContinue = findViewById(R.id.buttonContinue);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCountryCode.getText().toString().trim();
                String number = editTextPhone.getText().toString().trim();

                if (number.isEmpty() || number.length() < 8) {
                    editTextPhone.setError("Valid number is required");
                    editTextPhone.requestFocus();
                    return;
                }
                String phoneNumber = code + number;
                Intent intent = new Intent(login.this, VerifyPhoneActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}