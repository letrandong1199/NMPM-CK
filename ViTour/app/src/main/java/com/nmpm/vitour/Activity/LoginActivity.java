package com.nmpm.vitour.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nmpm.vitour.Api.MyAPIClient;
import com.nmpm.vitour.Api.UserService;
import com.nmpm.vitour.model.LoginRequest;
import com.nmpm.vitour.model.LoginResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    Button signinButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        signinButton=findViewById(R.id.SignInButton);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        EditText emailPhone = findViewById(R.id.edit_login_username);
        EditText password = findViewById(R.id.edit_login_pasword);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailPhone(emailPhone.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        UserService userService;
        userService = MyAPIClient.getInstance().getAdapter().create(UserService.class);
        userService.login(loginRequest, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                switch (error.getKind()) {
                    case HTTP:
                        if (error.getResponse().getStatus() == 400)
                            Toast.makeText(LoginActivity.this, "Missing email/phone or password", Toast.LENGTH_LONG).show();
                        else if (error.getResponse().getStatus() == 404)
                            Toast.makeText(LoginActivity.this, "Wrong email/phone or password", Toast.LENGTH_LONG).show();
                        break;
                    case NETWORK:
                    case UNEXPECTED:
                        Toast.makeText(LoginActivity.this, "Có vấn đề về mạng", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(LoginActivity.this, "Lỗi không xác định", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void Signup(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}