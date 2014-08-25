package com.aisflat439gardens.calculatorohmslaw.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Devin on 4/29/2014.
 */
public class FragmentLEDSeriesResistorCalculator extends Fragment implements View.OnClickListener {

    public FragmentLEDSeriesResistorCalculator(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_led_series_calculator, container, false);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        
    }
}
