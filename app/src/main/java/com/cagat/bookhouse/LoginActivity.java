package com.cagat.bookhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //I set up the appBar settings
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.icon_appbar2));

        ImageButton ibBackToMainFromLogin = (ImageButton) findViewById(R.id.ibBackToMainFromLogin);
        ibBackToMainFromLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_backToMainFromLogin = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i_backToMainFromLogin);
            }
        });

        final EditText etEmailLogin = (EditText) findViewById(R.id.etEmailLogin);
        final EditText etPasswordLogin = (EditText) findViewById(R.id.etPasswordLogin);
        ImageButton ibLogin = (ImageButton) findViewById(R.id.ibLogin);

        ibLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmailLogin.getText().toString();
                final String password = etPasswordLogin.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && success) {
                                String name = jsonResponse.getString("name");
                                String surname = jsonResponse.getString("surname");

                                Intent iLogin = new Intent(LoginActivity.this, UserActivity.class);
                                iLogin.putExtra("name", name);
                                iLogin.putExtra("surname", surname);
                                startActivity(iLogin);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed!")
                                        .setNegativeButton("Retry!", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });


    }
}
