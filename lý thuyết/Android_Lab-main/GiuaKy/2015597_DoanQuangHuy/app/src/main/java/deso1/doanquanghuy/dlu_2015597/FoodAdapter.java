package deso1.doanquanghuy.dlu_2015597;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }



    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);
        //anhxa
        ImageView image=(ImageView) view.findViewById(R.id.img_food);
        TextView name=(TextView) view.findViewById(R.id.txt_namefood);
        TextView price=(TextView) view.findViewById(R.id.txt_pricefood);
        ImageButton btnupdate=(ImageButton) view.findViewById(R.id.imgbtn_update);
        //gan gia tri
        Food item=foodList.get(i);
        image.setImageResource(item.getImageFood());
        name.setText(item.getNameFood());
        price.setText("Giá :"+item.getPrice()+" "+item.getUnit());
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(item);


            }
        });

        return  view;

    }
    private void goToDetail(Food item)
    {
        Intent myIntent=new Intent(context,activityupdatefood.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("Food", item);
        myIntent.putExtra("data",bundle);
        context.startActivity(myIntent);
    }

}
