package com.example.androidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends AppCompatActivity {

    private Button notSignUp;
    private EditText userName;
    private EditText password;
    private String token;

    private String savedToken;

    final String SAVED_TOKEN = "savedToken";
    SharedPreferences sPref;
    public static final String MyPreferences = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userName = findViewById(R.id.userNameLogIn);
        password =  findViewById(R.id.passwordLogIn);
        notSignUp = findViewById(R.id.notSignUp);

        sPref = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        // открываем другую activity
        notSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySignUp();
            }
        });

        // получаем token
        findViewById(R.id.logIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToken();
            }
        });
    }

    public void openActivitySignUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void openActivityUsersList(){
        Intent intent = new Intent(this, UsersList.class);
        startActivity(intent);
    }

    public void getToken() {
        User user = new User(userName.getText().toString(),
                password.getText().toString());
        UserClient client = NetworkService.createService(UserClient.class);
        Call<User> call = client.request(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    APIError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(LogIn.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(LogIn.this, response.body().getToken(), Toast.LENGTH_LONG).show();
                    token = response.body().getToken();
                    saveToken();
                    openActivityUsersList();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LogIn.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveToken() {
        SharedPreferences.Editor editor = sPref.edit(); // получаем объект SharedPreferences.Editor
        editor.putString(SAVED_TOKEN, token);
        editor.apply();
    }




}
