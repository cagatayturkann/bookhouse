package com.cagat.bookhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.icon_appbar2));

        ImageButton ibBackToMainPage = (ImageButton) findViewById(R.id.ibBackToMainPage);
        ibBackToMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBackToMainPage = new Intent(AddBookActivity.this, UserActivity.class);
                startActivity(iBackToMainPage);
            }
        });

        final EditText etBookName = (EditText) findViewById(R.id.etBookName);
        final EditText etBookAuthor = (EditText) findViewById(R.id.etBookAuthor);
        final EditText etBookPrintYear = (EditText) findViewById(R.id.etBookPrintYear);
        final EditText etBookTought = (EditText) findViewById(R.id.etBookTought);
        ImageButton ibAddButton2 = (ImageButton) findViewById(R.id.ibAddBook2);

        ibAddButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName, bookAuthor, bookPrintYear, bookTought;
                bookName = etBookName.getText().toString();
                bookAuthor = etBookAuthor.getText().toString();
                bookPrintYear = etBookPrintYear.getText().toString();
                bookTought = etBookTought.getText().toString();

                if (bookName.matches("") || bookAuthor.matches("") || bookPrintYear.matches("") || bookTought.matches("")) {
                    Toast.makeText(getApplicationContext(), "Fill All the Given Blank!", Toast.LENGTH_LONG).show();
                } else {
                    BookDatabase db = new BookDatabase(getApplicationContext());
                    db.bookAdd(bookName, bookAuthor, bookPrintYear, bookTought);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Book Added Succesfully!", Toast.LENGTH_LONG).show();
                    etBookName.setText("");
                    etBookAuthor.setText("");
                    etBookPrintYear.setText("");
                    etBookTought.setText("");
                }
            }
        });
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
            Intent iLogOut = new Intent(AddBookActivity.this, MainActivity.class);
            startActivity(iLogOut);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
