package vn.dlu.chuyendoitienle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DoiDoDai extends AppCompatActivity {

    private String[] units = {
            "Hải lý", "Dặm", "Kilometer", "Lý", "Met", "Yard", "Foot", "Inches"
    };

    private double[][] ratio = {
            {1, 1.5077945, 1.8520000, 20.2537183, 1852.0000, 2025.37183, 6076.11549, 72913.38583},
            {0.06897624, 1, 1.6093440, 17.6000000, 1609.3440, 1760.00000, 5280.00000, 63360.00000},
            {0.53995680, 0.62137119, 1, 10.9361330, 1000.0000, 1093.61330, 3280.83990, 39370.07874},
            {0.04937365, 0.05681818, 0.0914400, 1, 91.4400, 100.00000, 300.00000, 3600.00000},
            {0.00053996, 0.00062137, 0.0010000, 0.0109361, 1.0000, 1.09361, 3.28084, 39.37008},
            {0.00049374, 0.00056818, 0.0009144, 0.0100000, 0.9144, 1.00000, 3, 36},
            {0.00016458, 0.00018939, 0.0003048, 0.0033333, 0.3048, 0.33333, 1, 12},
            {0.00001371, 0.00001578, 0.0000254, 0.0002778, 0.0254, 0.02778, 0.08333, 1}
    };

    private EditText txtNumber;
    private Spinner spnUnit;
    RecyclerView doDaiRecycle;
    private List<DoDai> mdata;
    RecyclerView.Adapter doDaiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_do_dai);
        doDaiRecycle = findViewById(R.id.rv_DoDai);
        initUI();
    }

    private void initUI() {
        txtNumber = findViewById(R.id.txt_numberDoDai);
        spnUnit = findViewById(R.id.spinerDoDai);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                DoiDoDai.this, android.R.layout.simple_spinner_item, units
        );

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnUnit.setAdapter(adapter);

        doDaiRecycle.setLayoutManager(new LinearLayoutManager(this));
        doDaiRecycle.setHasFixedSize(true);

        mdata = new ArrayList<>();
        mdata.add(new DoDai("Hải lý", 0));
        mdata.add(new DoDai("Dặm", 0));
        mdata.add(new DoDai("Kilometer", 0));
        mdata.add(new DoDai("Lý", 0));
        mdata.add(new DoDai("Met", 0));
        mdata.add(new DoDai("Yard", 0));
        mdata.add(new DoDai("Foot", 0));
        mdata.add(new DoDai("Inches", 0));

        doDaiAdapter = new DoDaiAdapter(mdata);
        doDaiAdapter.notifyDataSetChanged();
        doDaiRecycle.setAdapter(doDaiAdapter);
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
        doDaiRecycle.setLayoutAnimation(layoutAnimationController);
    }

    private void changeMoneyUnit() {
        int index = spnUnit.getSelectedItemPosition();
        if (index < 0) index = 0;
        String input = txtNumber.getText().toString();
        if (input.isEmpty()) input = "0";

        double money = Double.valueOf(input);
        for (int i = 0; i < mdata.size(); i++) {
            double temp = money * ratio[index][i];
            mdata.get(i).setDoDai(temp);
            doDaiAdapter.notifyDataSetChanged();
            setAnimation(R.layout.layout_down_to_up);
        }
    }
}