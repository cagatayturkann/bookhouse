package com.cagat.bookhouse;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;

public class BookDetailActivity extends AppCompatActivity {
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.icon_appbar2));

        ImageButton ibBackToLibrary = (ImageButton) findViewById(R.id.ibBackToLibrary);
        ibBackToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBacktoLibrary = new Intent(BookDetailActivity.this, LibraryActivity.class);
                startActivity(iBacktoLibrary);
            }
        });

        EditText etBookName2 = (EditText) findViewById(R.id.etBookName2);
        EditText etBookAuthor2 = (EditText) findViewById(R.id.etBookAuthor2);
        EditText etBookPrintYear2 = (EditText) findViewById(R.id.etBookPrintYear2);
        EditText etBookTought2 = (EditText) findViewById(R.id.etBookTought2);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        BookDatabase db = new BookDatabase(getApplicationContext());
        HashMap<String, String> map = db.bookDetail(id);

        etBookName2.setText(map.get("book_name"));
        etBookAuthor2.setText(map.get("book_author").toString());
        etBookPrintYear2.setText(map.get("book_print_year").toString());
        etBookTought2.setText(map.get("book_toughts"));

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
        if (id == R.id.logOut) {
            Intent iLogOut = new Intent(BookDetailActivity.this, MainActivity.class);
            startActivity(iLogOut);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
