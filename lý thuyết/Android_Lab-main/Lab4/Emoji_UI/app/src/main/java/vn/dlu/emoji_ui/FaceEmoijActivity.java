package vn.dlu.emoji_ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FaceEmoijActivity extends Fragment implements View.OnClickListener {
    private static final int[] ids = {R.id.iv_face1, R.id.iv_face2, R.id.iv_face3, R.id.iv_face4,
            R.id.iv_face5, R.id.iv_face6, R.id.iv_face7, R.id.iv_face8, R.id.iv_face9};
    private Context mContext;
    ImageButton btnRand;
    public static ArrayList<String> arrName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m001_frg_face_emoij, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    private void initViews(View v) {
        for (int id : ids) {
            v.findViewById(id).setOnClickListener(this);
        }
        btnRand = v.findViewById(R.id.btn_refres);
        btnRand.setOnClickListener(this);

        String[] face = getResources().getStringArray(R.array.arr_face);
        arrName = new ArrayList<>(Arrays.asList(face));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refres:
                //Tron mang cac hinh anh
                Collections.shuffle(arrName);
                int idHinh = getResources().getIdentifier(arrName.get(5), "drawable", mContext.getPackageName());
                randToast(idHinh);
                break;
            default:
                ImageView ivFace = (ImageView) v;
                showToast(ivFace.getDrawable());
                break;
        }
    }

    private void showToast(Drawable drawable) {
        Toast toast = new Toast(mContext);
        ImageView ivEmoij = new ImageView(mContext);
        ivEmoij.setImageDrawable(drawable);
        toast.setView(ivEmoij);
        toast.show();
    }

    private void randToast(int id) {
        Toast toast = new Toast(mContext);
        ImageView ivEmoji = new ImageView(mContext);
        ivEmoji.setImageResource(id);
        toast.setView(ivEmoji);
        toast.show();
    }
}
