package vn.dlu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtNumber;
    Button btnRandom;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
    }

    private void initControl() {
        txtNumber = (TextView) findViewById(R.id.txtNumber);
        btnRandom = (Button) findViewById(R.id.btnRandom);
       btnRandom.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Random rand = new Random();
               int randNumber = rand.nextInt(100);
               txtNumber.setText(String.valueOf(randNumber));
           }
       });
    }
}