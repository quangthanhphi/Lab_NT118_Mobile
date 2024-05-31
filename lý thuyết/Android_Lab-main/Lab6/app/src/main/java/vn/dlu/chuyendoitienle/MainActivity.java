package vn.dlu.chuyendoitienle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] units = {
            "USD", "EUR", "GBP", "INR", "AUD", "CAD", "ZAR", "NZD", "JPY", "VND"
    };

    private double[][] ratio = {
            {1.00000, 0.80518, 0.6407, 63.3318, 1.21828, 1.16236, 11.7129, 1.2931, 118.337, 21385.7},
            {1.24172, 1.00000, 0.79575, 78.6084, 1.51266, 1.44314, 14.5371, 1.60576, 146.927, 26561.8},
            {1.56044, 1.25667, 1.00000, 98.7848, 1.90091, 1.81355, 18.2683, 2.01791, 184.638, 33374.9},
            {0.0158, 0.01272, 0.01012, 1.00000, 0.01924, 0.01836, 0.18493, 0.02043, 1.8691, 337.811},
            {0.82114, 0.66119, 0.5262, 52.086, 1.00000, 0.95416, 9.61148, 1.06158, 97.112, 17567.9},
            {0.86059, 0.69296, 0.55148, 54.5885, 1.04804, 1.00000, 10.0732, 1.11258, 101.777, 18401.7},
            {0.08541, 0.06877, 0.05473, 5.40852, 0.10398, 0.09924, 1.00000, 0.11037, 10.0996, 1825.87},
            {0.77402, 0.62319, 0.49597, 49.0031, 0.94215, 0.89951, 9.06754, 1.00000, 91.5139, 16552.1},
            {0.00846, 0.00681, 0.00542, 0.53547, 0.0103, 0.00983, 0.09908, 0.01093, 1.00000, 180.837},
            {0.00005, 0.00004, 0.00003, 0.00296, 0.00006, 0.00005, 0.00055, 0.00006, 0.00553, 1.00000}
    };
    private EditText txtNumber;
    private Spinner spnUnit;
    RecyclerView flagRecycle;
    private List<Flag> mdata;
    RecyclerView.Adapter flagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flagRecycle = findViewById(R.id.rv_unit);
        initUI();
    }

    private void initUI() {
        txtNumber = findViewById(R.id.txt_number);
        spnUnit = findViewById(R.id.spiner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_spinner_item, units
        );
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnUnit.setAdapter(adapter);

        flagRecycle.setLayoutManager(new LinearLayoutManager(this));
        flagRecycle.setHasFixedSize(true);

        mdata = new ArrayList<>();
        mdata.add(new Flag("USD", R.drawable.usd, 0));
        mdata.add(new Flag("EUR", R.drawable.eur, 0));
        mdata.add(new Flag("GBP", R.drawable.gbp, 0));
        mdata.add(new Flag("INR", R.drawable.inr, 0));
        mdata.add(new Flag("AUD", R.drawable.aud, 0));
        mdata.add(new Flag("CAD", R.drawable.cad, 0));
        mdata.add(new Flag("ZAR", R.drawable.zar, 0));
        mdata.add(new Flag("NZD", R.drawable.nzd, 0));
        mdata.add(new Flag("JPY", R.drawable.jpy, 0));
        mdata.add(new Flag("VNĐ", R.drawable.vnd, 0));

        flagAdapter = new FlagsAdapter(mdata);
        flagAdapter.notifyDataSetChanged();
        flagRecycle.setAdapter(flagAdapter);
        setAnimation(R.layout.layout_down_to_up);

        spnUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeMoneyUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeMoneyUnit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setAnimation(int anim) {
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(this, anim);
        flagRecycle.setLayoutAnimation(layoutAnimationController);
    }

    private void changeMoneyUnit() {
        int index = spnUnit.getSelectedItemPosition();
        if (index < 0) index = 0;
        String input = txtNumber.getText().toString();
        if (input.isEmpty()) input = "0";

        double money = Double.valueOf(input);
        for (int i = 0; i < mdata.size(); i++) {
            double temp = money * ratio[index][i];
            mdata.get(i).setMoney(temp);
            flagAdapter.notifyDataSetChanged();
            setAnimation(R.layout.layout_down_to_up);
        }
    }
}