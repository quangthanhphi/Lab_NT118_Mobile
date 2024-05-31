package vn.dlu.truyencuoi.activities;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.dlu.truyencuoi.classes.StoryEntity;
import vn.dlu.truyencuoi.adapters.ListCategoryAdapter;
import vn.dlu.truyencuoi.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<StoryEntity> categories;
    private ListCategoryAdapter categoryAdapter;
    private RecyclerView listView;
    private Fragment splash;

    public static final String CATEGORY = "Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadCategories();

        listView = findViewById(R.id.list_categories);
        categoryAdapter = new ListCategoryAdapter(categories.toArray(new StoryEntity[categories.size()]), this);
        listView.setAdapter(categoryAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        splash = new Splash();
        showFragment();
    }

    private void showFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.linearlayout_main, splash, null).commit();
    }

    public void hideFragment() {
        getSupportFragmentManager().beginTransaction().hide(splash).commit();
    }

    private void loadCategories() {
        this.categories = new ArrayList<>();
        String[] filePaths = listAssetFiles("story");
        for (String filePath : filePaths) {
            String file = filePath.replace(".txt", "");
            this.categories.add(new StoryEntity(file, "photo/" + file + ".png", null));
        }
    }

    private String[] listAssetFiles(String path) {
        Resources res = getResources(); //if you are in an activity
        AssetManager am = res.getAssets();
        try {
            String fileList[] = am.list(path);
            return fileList;
        } catch (IOException e) {
            return null;
        }
    }
}