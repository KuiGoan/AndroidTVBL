package com.example.kuirin.androidtvbl;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainMenuActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
//        getWindow().setBackgroundDrawableResource(R.drawable.grid_bg);
    }
}
