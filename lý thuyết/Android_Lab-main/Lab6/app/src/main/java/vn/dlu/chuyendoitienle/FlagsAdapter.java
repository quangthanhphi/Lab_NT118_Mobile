package vn.dlu.chuyendoitienle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlagsAdapter extends RecyclerView.Adapter<FlagsAdapter.flagsviewHolder> {

    List<Flag> mdata;

    public FlagsAdapter(List<Flag> mdata) {
        this.mdata = mdata;
    }

    @Override
    public flagsviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_itenr_rv, parent, false);
        return new flagsviewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlagsAdapter.flagsviewHolder holder, int position) {
        Flag flags = mdata.get(position);
        Glide.with(holder.itemView.getContext()).load(mdata.get(position).getImgFlag())
                .transforms(new CenterCrop(), new RoundedCorners(16)).into(holder.imgFlag);
        holder.name.setText(flags.getName());
        holder.number.setText(String.valueOf(flags.getMoney()));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class flagsviewHolder extends RecyclerView.ViewHolder {

        ImageView imgFlag;
        TextView name, number;

        public flagsviewHolder(View itemView) {
            super(itemView);
            imgFlag = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.lb_name);
            number = itemView.findViewById(R.id.lb_number);
        }
    }
}
