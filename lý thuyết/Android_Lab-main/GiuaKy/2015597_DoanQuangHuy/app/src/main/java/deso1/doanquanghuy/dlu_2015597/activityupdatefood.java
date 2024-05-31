package deso1.doanquanghuy.dlu_2015597;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activityupdatefood extends AppCompatActivity {

    EditText Id;
    EditText namefood;
    EditText price;
    EditText unit;
    Button btnBack;
    Button btnRemove;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityupdatefood);
        AnhXa();
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        if(bundle!=null)
        {
            Food foodItem=(Food) bundle.getSerializable("Food");
            Id.setText(Integer.toString(foodItem.getID()));
            namefood.setText(foodItem.getNameFood());
            price.setText(Integer.toString(foodItem.getPrice()));
            unit.setText(foodItem.getUnit());
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(Id.getText().toString());
                String name=namefood.getText().toString();
                int priceFood=Integer.parseInt(price.getText().toString());
                String unitFood=unit.getText().toString();
                int imageFood= R.drawable.chabo;
                Food food=new Food(id,name,priceFood,unitFood,imageFood);
                Intent myIntent=new Intent(activityupdatefood.this,MainActivity.class);
                Bundle bundleUpdate=new Bundle();
                bundleUpdate.putSerializable("food",food);
                myIntent.putExtra("data1",bundleUpdate);
                startActivity(myIntent);
                finish();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(activityupdatefood.this,MainActivity.class);
                Bundle bundleRemove=new Bundle();
                bundleRemove.putInt("ID",Integer.parseInt(Id.getText().toString()));
                myintent.putExtra("data",bundleRemove);
                startActivity(myintent);

                finish();



            }
        });

    }
    private  void AnhXa(){
        Id=(EditText) findViewById(R.id.edittext_id);
        namefood=(EditText) findViewById(R.id.edittext_namefood);
        price=(EditText) findViewById(R.id.edittext_price);
        unit=(EditText) findViewById(R.id.edittext_unit);
        btnBack=(Button) findViewById(R.id.btn_back);
        btnRemove=(Button) findViewById(R.id.btn_remove);
        btnUpdate=(Button) findViewById(R.id.btn_update);

    }


}