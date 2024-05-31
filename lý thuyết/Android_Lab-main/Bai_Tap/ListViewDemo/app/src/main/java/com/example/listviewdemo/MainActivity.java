package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCustomerLV();
    }

    private void initCustomerLV() {
        ArrayList<OS> os = new ArrayList<>();
        os.add(new OS("Android", "Android is ...", R.drawable.android));
        os.add(new OS("IOS", "IOS is ...", R.drawable.ios));
        os.add(new OS("WindowsPhone", "WindowsPhone is ...", R.drawable.winphone));

        this.lstMain = findViewById(R.id.listviewMain);
        MyAdapter adapter = new MyAdapter(this.getApplicationContext(), 0, os);
        lstMain.setAdapter(adapter);
    }
}