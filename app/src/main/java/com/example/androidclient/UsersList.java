package com.example.androidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersList extends LogIn {

    private TextView textViewResult;
    private String savedToken;
    private String finalToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        textViewResult = findViewById(R.id.textViewResult);
        loadText();
        usersList();
    }

    // получаем список пользователей
    public void usersList(){
        UserClient client = NetworkService.createService(UserClient.class);
        Call<List<User>> call = client.usersList(finalToken);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    APIError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(UsersList.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                }
                else {
                    List<User> users = response.body();
                    for (User user: users) {
                        String content = "";
                        content += "Имя: " + user.getFirstName() + "\n";
                        content += "Фамилия: " + user.getLastName() + "\n";
                        content += "Логин: " + user.getUsername() + "\n\n";

                        textViewResult.append(content);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void loadText() {
        savedToken = sPref.getString(SAVED_TOKEN, "No name defined");
        finalToken = "Bearer " + savedToken;
    }
}
