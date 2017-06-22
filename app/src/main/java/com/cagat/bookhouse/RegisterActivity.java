package com.cagat.bookhouse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

public class RegisterActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.icon_appbar2));

        ImageButton ibBackToMainFromRegister = (ImageButton) findViewById(R.id.ibBackToMainFromRegister);
        ibBackToMainFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_ibBackToMainFromRegister = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i_ibBackToMainFromRegister);
            }
        });

        final EditText etNameRegister = (EditText) findViewById(R.id.etNameRegister);
        final EditText etSurnameRegister = (EditText) findViewById(R.id.etSurnameRegister);
        final EditText etEmailRegister = (EditText) findViewById(R.id.etEmailRegister);
        final EditText etPasswordRegister = (EditText) findViewById(R.id.etPasswordRegister);
        ImageButton ibRegister = (ImageButton) findViewById(R.id.ibRegister);

        ibRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etNameRegister.getText().toString();
                final String surname = etSurnameRegister.getText().toString();
                final String email = etEmailRegister.getText().toString();
                final String password = etPasswordRegister.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Successful!")
                                        .setPositiveButton("Go Login!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent iRegister = new Intent(RegisterActivity.this, LoginActivity.class);
                                                startActivity(iRegister);
                                            }
                                        })
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed!")
                                        .setNegativeButton("Retry!", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(name, surname, email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


    }
}