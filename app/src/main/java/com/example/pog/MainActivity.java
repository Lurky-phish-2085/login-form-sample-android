package com.example.pog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login, register;
    EditText email, password;
    SQLiteDatabase db;

    //region $Database Functions
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return super.openOrCreateDatabase(name, mode, factory);
    }

    public void dbAddProduct(String Name, int Quantity, String Price){

    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.submitRegisterData);
        login.setOnClickListener(this);
        register = findViewById(R.id.btnBackToLogin);
        register.setOnClickListener(this);


        email = findViewById(R.id.editTextEmailAddressRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        db = openOrCreateDatabase("Customer_DB", Context.MODE_PRIVATE, null);
        DisplayLog("Debug Successful", "The App Works");
        db.execSQL("CREATE TABLE IF NOT EXISTS UserData(UserID Integer Primary Key Autoincrement,  UserEmail VARCHAR, Password VARCHAR, FirstName VARCHAR, LastName VARCHAR, PhoneNumber INTEGER)");
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submitRegisterData:
                CheckIfValidLogin();
                break;
            case R.id.btnBackToLogin:
//               @ToDo Change Fragment Code
                Intent intent = new Intent(this, RegisterPage.class);
                db.close();
                startActivity(intent);
                break;
        }
    }

    public void CheckIfValidLogin(){
        Cursor c = db.rawQuery("SELECT Password FROM UserData WHERE UserEmail= '"+email.getText()+"'",null);
        if(c.moveToFirst()){
            StringBuilder builder = new StringBuilder();
            DisplayLog(builder.toString(), "User Already Exists");
            if(password.getText().equals(c.getString(0))){
                DisplayLog(builder.toString(), "Logged In!");
                db.close();
            }
            else{
                DisplayLog("Incorrect Username or Password", "Failed Log-in");
            }
            c.getString(0);
            return;
        }
        DisplayLog("No Such User Exists", "User not found");
    }


    public void DisplayLog(String message, String title){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setMessage(message);
        build.setTitle(title);
        build.show();
    }


}