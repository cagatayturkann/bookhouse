package com.cagat.bookhouse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.icon_appbar2));

        TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvSurname = (TextView) findViewById(R.id.tvSurname);

        Intent iLogin = getIntent();
        String name = iLogin.getStringExtra("name");
        String surname = iLogin.getStringExtra("surname");
        tvName.setText(name);
        tvSurname.setText(surname);

        ImageButton ibAddBook = (ImageButton) findViewById(R.id.ibAddBook);
        ibAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAddBook = new Intent(UserActivity.this, AddBookActivity.class);
                startActivity(iAddBook);
            }
        });

        ImageButton ibLibrary = (ImageButton) findViewById(R.id.ibLibrary);
        ibLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLibrary = new Intent(UserActivity.this, LibraryActivity.class);
                startActivity(iLibrary);
            }
        });


    }

    @Override
    public void onBackPressed() { //When pressing back button twice, closing the app.
        if(exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit!", Toast.LENGTH_LONG).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2* 500);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (R.id.logOut == id) {
            Intent iLogOut = new Intent(UserActivity.this, MainActivity.class);
            startActivity(iLogOut);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
