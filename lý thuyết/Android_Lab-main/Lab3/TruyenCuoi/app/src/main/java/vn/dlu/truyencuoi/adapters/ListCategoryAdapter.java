package vn.dlu.truyencuoi.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.dlu.truyencuoi.classes.ItemClickListener;
import vn.dlu.truyencuoi.activities.MainActivity;
import vn.dlu.truyencuoi.activities.SecondActivity;
import vn.dlu.truyencuoi.classes.StoryEntity;
import vn.dlu.truyencuoi.R;

import java.io.IOException;
import java.io.InputStream;

public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.ViewHolder> {
    private StoryEntity[] localDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView thumbnail;
        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.txtStoryTitle);
            thumbnail = view.findViewById(R.id.imgStoryThumbnail);
            view.setOnClickListener(this);
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }

    public ListCategoryAdapter(StoryEntity[] dataSet, Context context) {
        localDataset = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        StoryEntity e = localDataset[position];
        viewHolder.getTitle().setText(e.getTitle());
        try {
            InputStream s = context.getAssets().open(e.getThumbnail());
            Drawable d = Drawable.createFromStream(s, null);
            viewHolder.getThumbnail().setImageDrawable(d);
            s.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent secondScreen = new Intent(context, SecondActivity.class);
                secondScreen.putExtra(MainActivity.CATEGORY, e.getTitle());
                context.startActivity(secondScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataset.length;
    }
}
