package deso1.doanquanghuy.dlu_2015597;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private ImageButton add;

    public static ArrayList<Food> getFoodArrayList() {
        return foodArrayList;
    }

    private static ArrayList<Food> foodArrayList = new ArrayList<Food>() {{
        add(new Food(1, "Chả Bò", 140000, "Đồng", R.drawable.chabo));
        add(new Food(2, "Chả Lụa", 150000, "Đồng", R.drawable.chalua));
        add(new Food(3, "Phở Xào", 130000, "Đồng", R.drawable.phoxao));

    }};
    private FoodAdapter foodAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        foodAdapter = new FoodAdapter(MainActivity.this, R.layout.layout_food_item, foodArrayList);
        lv.setAdapter(foodAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, activityaddfood.class);
                startActivity(myIntent);
            }
        });
        getDataToTheIntent("data", 0);
        getDataToTheIntent("data1", 1);
        getDataToTheIntent("data2", 2);
    }

    private void AnhXa() {
        lv = (ListView) findViewById(R.id.lv_food);
        add = (ImageButton) findViewById(R.id.imagebtn_add);

    }

    private void getDataToTheIntent(String key, int type) {
        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getBundleExtra(key);
        if (bundle != null) {
            switch (type) {
                case 0:
                    int id = bundle.getInt("ID");
                    removeByID(id);
                    foodAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    Food foodUpdate = (Food) bundle.getSerializable("food");
                    int index = findIndexFoodByID(foodUpdate.getID());
                    Food foodItem = foodArrayList.get(index);
                    foodItem.setNameFood(foodUpdate.getNameFood());
                    foodItem.setPrice(foodUpdate.getPrice());
                    foodItem.setUnit(foodUpdate.getUnit());
                    foodItem.setImageFood(foodUpdate.getImageFood());
                    foodAdapter.notifyDataSetChanged();

                    break;
                case 2:
                    Food foodAdd = (Food) bundle.getSerializable("food");

                    getFoodArrayList().add(foodAdd);
                    foodAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }

        }

    }

    private int findIndexFoodByID(int id) {
        int index = -1;
        for (int i = 0; i < foodArrayList.size(); i++) {
            if (foodArrayList.get(i).getID() == id) {
                return index = i;
            }
        }
        return index;
    }

    private void removeByID(int id) {
        foodArrayList.remove(findIndexFoodByID(id));
    }
}