package com.example.pog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    Button register, backToLogin;
    EditText fName, lName, phoneNumber, email, password;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        db = openOrCreateDatabase("Customer_DB", Context.MODE_PRIVATE, null);
        register = findViewById(R.id.submitRegisterData);
        register.setOnClickListener(this);
        backToLogin = findViewById(R.id.btnBackToLogin);
        register.setOnClickListener(this);

        fName = findViewById(R.id.editTextFirstName);
        lName = findViewById(R.id.editTextLastName);
        phoneNumber = findViewById(R.id.editTextPhoneNumber);
        email = findViewById(R.id.editTextEmailAddressRegister);
        password = findViewById(R.id.editTextPasswordRegister);
    }


    @Override
    public void onClick(View v) {
        if(v == backToLogin){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(v == register){
            ContentValues cv = new ContentValues();

        }
    }
}