package deso1.doanquanghuy.dlu_2015597;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activityaddfood extends AppCompatActivity {

    EditText namefood;
    EditText price;
    EditText unit;
    Button btnAdd;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityaddfood);
        AnhXa();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(activityaddfood.this, MainActivity.class);
                int id = SinhMa();
                String name = namefood.getText().toString();
                int priceFood = Integer.parseInt(price.getText().toString());
                String unitFood = unit.getText().toString();
                int imageFood = R.drawable.chabo;
                Food food = new Food(id, name, priceFood, unitFood, imageFood);

                Bundle bundleUpdate = new Bundle();
                bundleUpdate.putSerializable("food", food);
                myIntent.putExtra("data2", bundleUpdate);
                startActivity(myIntent);
                finish();

            }
        });
    }

    private int SinhMa() {
        int i = MainActivity.getFoodArrayList().size();
        while (exitsId(i)) {
            i++;
        }
        return i;

    }

    private boolean exitsId(int id) {
        for (int i = 0; i < MainActivity.getFoodArrayList().size(); i++) {
            if (MainActivity.getFoodArrayList().get(i).getID() == id) {
                return true;
            }
        }
        return false;
    }


    private void AnhXa() {
        namefood = (EditText) findViewById(R.id.edittext_addnamefood);
        price = (EditText) findViewById(R.id.edittext_addprice);
        unit = (EditText) findViewById(R.id.edittext_addunit);
        btnBack = (Button) findViewById(R.id.btn_addback);
        btnAdd = (Button) findViewById(R.id.btn_addfood);
    }
}