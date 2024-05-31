package vn.dlu.truyencuoi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.dlu.truyencuoi.classes.StoryEntity;
import vn.dlu.truyencuoi.adapters.ListStoryAdapter;
import vn.dlu.truyencuoi.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
    private ArrayList<StoryEntity> stories;
    private ListStoryAdapter storyAdapter;
    private RecyclerView listView;
    private ImageButton btnBack;
    private TextView txtTitle;

    public static final String STORY_TITLE = "Story_title";
    public static final String STORY_CONTENT = "Story_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView = findViewById(R.id.list_stories);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtTitle = findViewById(R.id.titleHeading);

        loadStories();

        storyAdapter = new ListStoryAdapter(stories.toArray(new StoryEntity[stories.size()]), this);
        listView.setAdapter(storyAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadStories() {
        stories = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String selectedCategory = extras.getString(MainActivity.CATEGORY);
            txtTitle.setText(selectedCategory);
            if (selectedCategory != null) {
                ArrayList<String> list = getContent("story/" + selectedCategory + ".txt");
                if (list != null) {
                    for (String story : list) {
                        String[] lines = story.split("\n");
                        String[] content = Arrays.copyOfRange(lines, 1, lines.length);
                        this.stories.add(new StoryEntity(lines[0], null, String.join("\n", content)));
                    }
                }
            }
        }
    }

    private ArrayList<String> getContent(String filename) {
        try {
            InputStream stream = getAssets().open(filename);
            ArrayList<String> stories = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.contains("','0');")) {
                    stories.add(sb.toString());
                    sb = new StringBuilder();
                } else
                    sb.append(line + "\n");
            }
            br.close();
            return stories;
        } catch (IOException ex) {
            return null;
        }
    }
}
