package vn.dlu.menu_ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class act_word extends AppCompatActivity {
    public ArrayList<String> arr_word = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    ListView lv_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_word);
        initUI();
    }

    private void initUI() {
        lv_word = (ListView) findViewById(R.id.lv_word);
        arr_word = (ArrayList<String>) getIntent().getExtras().get("arr_word");
        adapter = new ArrayAdapter<String>(
                act_word.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr_word
        );
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        lv_word.setAdapter(adapter);
    }
}
