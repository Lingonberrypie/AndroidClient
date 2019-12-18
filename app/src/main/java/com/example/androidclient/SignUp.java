package com.example.androidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private Button alreadySignUp;
    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        userName = findViewById(R.id.userNameSignUp);
        password = findViewById(R.id.passwordSignUp);

        alreadySignUp = findViewById(R.id.alreadySignUp);
        alreadySignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLogIn();
            }
        });

        // добавление нового пользователя в систему
        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    public void openActivityLogIn(){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void createUser(){
        User user = new User(firstName.getText().toString(),
                lastName.getText().toString(),
                userName.getText().toString(),
                password.getText().toString());
        UserClient client = NetworkService.createService(UserClient.class);
        Call<User> call = client.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignUp.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
                else {
                    APIError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(SignUp.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
