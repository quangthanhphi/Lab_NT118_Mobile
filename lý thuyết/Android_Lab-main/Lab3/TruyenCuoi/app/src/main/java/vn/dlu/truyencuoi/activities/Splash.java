package vn.dlu.truyencuoi.activities;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import vn.dlu.truyencuoi.R;

public class Splash extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        return inflater.inflate(R.layout.splash, container, false);
    }

    private void initViews() {
        new Handler().postDelayed(this::gotoNextScreen, 2000);
    }

    private void gotoNextScreen() {
        ((MainActivity) getActivity()).hideFragment();
    }
}