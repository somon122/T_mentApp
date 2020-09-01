package com.example.pta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileSetUpActivity extends AppCompatActivity {

    TextInputLayout Fname,Uname,UPhone,Pass;
    Button regbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_set_up);


        Fname = findViewById(R.id.Rfullname);
        Uname = findViewById(R.id.RUsername);
        UPhone = findViewById(R.id.RPhone);
        Pass = findViewById(R.id.Rpassword);
        regbtn = findViewById(R.id.reg);






    }


    private Boolean Validatename(){
        String Name = Fname.getEditText().getText().toString();

        if (Name.isEmpty()){
            Fname.setError("Field Can't Be Empty");
            return false;
        }
        else
            Fname.setError(null);
            Fname.setErrorEnabled(false);
            return true;

    }
    private Boolean validateUsername() {
        String val = Uname.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            Uname.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            Uname.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
           Uname.setError("White Spaces are not allowed");
            return false;
        } else {
            Uname.setError(null);
            Uname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        String val = UPhone.getEditText().getText().toString();

        if (val.isEmpty()) {
            UPhone.setError("Field cannot be empty");
            return false;
        } else {
            UPhone.setError(null);
            UPhone.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = Pass.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            Pass.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            Pass.setError("Password is too weak You can Use @#$%*& To Make Strong");
            return false;
        } else {
            Pass.setError(null);
            Pass.setErrorEnabled(false);
            return true;
        }
    }



    public void singin(View view) {

        Intent i = new Intent( ProfileSetUpActivity.this, SignInActivity.class);
        startActivity(i);
        finish();
    }
}
