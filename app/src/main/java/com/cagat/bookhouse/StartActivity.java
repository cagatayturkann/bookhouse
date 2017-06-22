package com.cagat.bookhouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by cagat on 16.12.2016.
 */

public class StartActivity extends Activity {
    public void onCreate(Bundle startScreen) {
        super.onCreate(startScreen);
        setContentView(R.layout.activity_start);
        Thread timer = new Thread() { //timer adında bir thread yarattım.
            public void run() {
                try {
                    sleep(1750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i("tog", "timer didn't work");
                } finally {
                    Intent intent_main = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent_main);
                }
            }
        };
        timer.start();
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
