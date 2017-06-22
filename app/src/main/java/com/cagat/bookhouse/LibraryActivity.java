package com.cagat.bookhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryActivity extends AppCompatActivity {
    GridView gvBookList;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> book_list;
    String book_names[];
    int book_ids[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.icon_appbar2));

        ImageButton ibBackToMainPage2 = (ImageButton) findViewById(R.id.ibBackToMainPage2);
        ibBackToMainPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBackToMainPage2 = new Intent(LibraryActivity.this, UserActivity.class);
                startActivity(iBackToMainPage2);
            }
        });
    }

    public void onResume() {
        super.onResume();
        BookDatabase db = new BookDatabase(getApplicationContext()); // Creating database connection
        book_list = db.books();//kitap listesini alıyoruz
        if (book_list.size() == 0) {//kitap listesi boşsa
            Toast.makeText(getApplicationContext(), "Turn Main Page and Click Add Book Button!", Toast.LENGTH_LONG).show();
        } else {
            book_names = new String[book_list.size()]; // Creating string array which will hold book names.
            book_ids = new int[book_list.size()]; // Creating string array which will hold book ids
            for (int i = 0; i < book_list.size(); i++) {
                book_names[i] = book_list.get(i).get("book_name");
                //book_list.get(0) this method turns first hashmap array inside of the arraylist. Meaning: turns first row value of our table.

                book_ids[i] = Integer.parseInt(book_list.get(i).get("id"));
        }
            // Listing book and assigning listener to that list.
            gvBookList = (GridView) findViewById(R.id.gvBookList);

            adapter = new ArrayAdapter<String>(this, R.layout.list_book, R.id.tvBookName2, book_names);
            gvBookList.setAdapter(adapter);

            gvBookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    Intent intent = new Intent(getApplicationContext(), BookDetailActivity.class);
                    intent.putExtra("id", (int) book_ids[arg2]);
                    startActivity(intent);

                }
            });
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
        if (id == R.id.logOut) {
            Intent iLogOut = new Intent(LibraryActivity.this, MainActivity.class);
            startActivity(iLogOut);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
