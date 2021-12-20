package com.example.mytodoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText editTextPassword, editTextUsers, editReTextPassword;
    Button btnRegister;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        register();
    }

    private void register() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsers.getText().toString();
                String password = editTextPassword.getText().toString();
                String repasswd = editReTextPassword.getText().toString();

                // Check the string if empty or not
                if (username.equals("")||password.equals("")||repasswd.equals("")) {
                    Toast.makeText(SignUp.this, "Please, enter something", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(repasswd)) {
                        Boolean checkUser = database.checkUsr(username);
                        if (!checkUser) {
                            Boolean insert = database.insertData(username, password);
                            if (insert) {
                                Toast.makeText(SignUp.this,"Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUp.this, "Registered failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignUp.this, "This account already exist, please enter another one", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignUp.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        btnRegister = findViewById(R.id.btnRegister);
        editTextUsers = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        editReTextPassword = findViewById(R.id.editReTextPassword);
        database = new DBHelper(this);
    }


}