package vn.dlu.chuyendoitienle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cvTienTe, cvDoDai;
    ConstraintLayout cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    private void initUI() {
        cvDoDai = findViewById(R.id.btn_DoDai);
        cvTienTe = findViewById(R.id.btn_TienTe);
        cont = findViewById(R.id.constraint);

        cvTienTe.setOnClickListener(this);
        cvDoDai.setOnClickListener(this);
        setAnimation(R.layout.layout_down_to_up);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_DoDai:
                startActivity(new Intent(HomeActivity.this, DoiDoDai.class));
                break;
            case R.id.btn_TienTe:
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                break;
        }
    }

    private void setAnimation(int anim) {
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(this, anim);
        cont.setLayoutAnimation(layoutAnimationController);
    }
}
