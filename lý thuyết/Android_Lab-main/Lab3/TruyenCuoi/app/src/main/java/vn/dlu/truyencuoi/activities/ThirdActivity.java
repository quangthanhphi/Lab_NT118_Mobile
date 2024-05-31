package vn.dlu.truyencuoi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.dlu.truyencuoi.R;

public class ThirdActivity extends AppCompatActivity {
    private TextView txtArticle, txtTitle, txtSubHeadingTitle;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        txtArticle = findViewById(R.id.article);
        txtTitle = findViewById(R.id.titleHeading);
        txtSubHeadingTitle = findViewById(R.id.subHeadingTitle);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getContent();
    }

    private void getContent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString(SecondActivity.STORY_TITLE);
            String content = extras.getString(SecondActivity.STORY_CONTENT);
            if (title != null && content != null) {
                txtTitle.setText(title);
                txtArticle.setText(content);
                txtSubHeadingTitle.setText(title);
            }
        }
    }
}