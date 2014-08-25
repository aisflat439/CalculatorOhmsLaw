package com.aisflat439gardens.calculatorohmslaw.app;

/**
 * Created by Devin on 3/6/14.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentResistorSelector extends Fragment implements View.OnClickListener {

    String firstBand, secondBand, thirdBand, multiplierBand, toleranceBand, tempCoBand;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6;

    TextView outputTextView;

    RadioButton fourBandButton, fiveBandButton, sixBandButton;

    public FragmentResistorSelector(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_resistor_selector, container, false);

        final Button evaluateButton = (Button) rootView.findViewById(R.id.evaluate_button);
        evaluateButton.setOnClickListener(FragmentResistorSelector.this);

        final TextView bandFourTextView = (TextView)rootView.findViewById(R.id.spinner4_text_view);
        final TextView bandFiveTextView = (TextView)rootView.findViewById(R.id.spinner5_text_view);

        outputTextView = (TextView)rootView.findViewById(R.id.output_resistor_text_view);

        final LinearLayout groupOne = (LinearLayout)rootView.findViewById(R.id.spinner1_layout);
        final LinearLayout groupTwo = (LinearLayout)rootView.findViewById(R.id.spinner2_layout);
        final LinearLayout groupThree = (LinearLayout)rootView.findViewById(R.id.spinner3_layout);
        final LinearLayout groupFour = (LinearLayout)rootView.findViewById(R.id.spinner4_layout);
        final LinearLayout groupFive = (LinearLayout)rootView.findViewById(R.id.spinner5_layout);
        final LinearLayout groupSix = (LinearLayout)rootView.findViewById(R.id.spinner6_layout);

        fourBandButton = (RadioButton)rootView.findViewById(R.id.four_band_radio);
        fiveBandButton = (RadioButton)rootView.findViewById(R.id.five_band_radio);
        sixBandButton = (RadioButton)rootView.findViewById(R.id.six_band_radio);

        spinner1 = (Spinner)rootView.findViewById(R.id.spinner1);
        spinner2 = (Spinner)rootView.findViewById(R.id.spinner2);
        spinner3 = (Spinner)rootView.findViewById(R.id.spinner3);
        spinner4 = (Spinner)rootView.findViewById(R.id.spinner4);
        spinner5 = (Spinner)rootView.findViewById(R.id.spinner5);
        spinner6 = (Spinner)rootView.findViewById(R.id.spinner6);

        ArrayAdapter<CharSequence> adapter_first = ArrayAdapter.createFromResource(rootView.getContext(), R.array.colors_array_first, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.colors_array, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> adapter_multiplier = ArrayAdapter.createFromResource(rootView.getContext(), R.array.multiplier_array, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> adapter_tolerance = ArrayAdapter.createFromResource(rootView.getContext(), R.array.tolerance_array, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> adapter_tempco = ArrayAdapter.createFromResource(rootView.getContext(), R.array.tempco_array, android.R.layout.simple_list_item_1);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

  //      spinner1.setAdapter(adapter_first);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter_multiplier);
        spinner5.setAdapter(adapter_tolerance);
        spinner6.setAdapter(adapter_tempco);

        RadioGroup radioBandGroup = (RadioGroup) rootView.findViewById(R.id.radio_band_group);

        groupOne.setVisibility(View.VISIBLE);
        groupTwo.setVisibility(View.VISIBLE);
        groupThree.setVisibility(View.GONE);
        groupFour.setVisibility(View.VISIBLE);
        groupFive.setVisibility(View.VISIBLE);
        groupSix.setVisibility(View.GONE);

        bandFourTextView.setText("3rd Band");
        bandFiveTextView.setText("4th Band");

        radioBandGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.four_band_radio:
                        //do work
                        groupOne.setVisibility(View.VISIBLE);
                        groupTwo.setVisibility(View.VISIBLE);
                        groupThree.setVisibility(View.GONE);
                        groupFour.setVisibility(View.VISIBLE);
                        groupFive.setVisibility(View.VISIBLE);
                        groupSix.setVisibility(View.GONE);

                        bandFourTextView.setText("3rd Band");
                        bandFiveTextView.setText("4th Band");

                        break;

                    case R.id.five_band_radio:
                        //do work
                        groupOne.setVisibility(View.VISIBLE);
                        groupTwo.setVisibility(View.VISIBLE);
                        groupThree.setVisibility(View.VISIBLE);
                        groupFour.setVisibility(View.VISIBLE);
                        groupFive.setVisibility(View.VISIBLE);
                        groupSix.setVisibility(View.GONE);

                        bandFourTextView.setText("4th Band");
                        bandFiveTextView.setText("5th Band");

                        break;

                    case R.id.six_band_radio:
                        //do work
                        groupOne.setVisibility(View.VISIBLE);
                        groupTwo.setVisibility(View.VISIBLE);
                        groupThree.setVisibility(View.VISIBLE);
                        groupFour.setVisibility(View.VISIBLE);
                        groupFive.setVisibility(View.VISIBLE);
                        groupSix.setVisibility(View.VISIBLE);

                        bandFourTextView.setText("4th Band");
                        bandFiveTextView.setText("5th Band");

                        break;
                }
            }
        });

        return rootView;

    }

    @Override
    public void onClick(View v){

        firstBand = spinner1.getSelectedItem().toString();
        secondBand = spinner2.getSelectedItem().toString();
        thirdBand = spinner3.getSelectedItem().toString();
        multiplierBand = spinner4.getSelectedItem().toString();
        toleranceBand = spinner5.getSelectedItem().toString();
        tempCoBand = spinner6.getSelectedItem().toString();

        switch (v.getId()) {
            case R.id.evaluate_button:
                IndividualResistor ir, ir1, ir2;
//-------------------------------------------------------------------------------------------------------------------------------
// TODO The problem with this is that I currently have built my object so that it builds 3 objects and only uses one based on user
// TODO selection. This is pretty silly. I should improve on this when I ask more experienced eyes to review this with me.
// TODO pretty sure I can solve this with better object design.
// -------------------------------------------------------------------------------------------------------------------------------
                ir = new IndividualResistor(firstBand, secondBand, multiplierBand, toleranceBand);
                ir1 = new IndividualResistor(firstBand, secondBand, thirdBand, multiplierBand, toleranceBand);
                ir2 = new IndividualResistor(firstBand, secondBand, thirdBand, multiplierBand, toleranceBand, tempCoBand);

                if (fourBandButton.isChecked()) {
                //    aliceView.setText(ir.analyzeBand(firstBand, secondBand, multiplierBand, toleranceBand));
                    outputTextView.setText(ir.getAboutThisBand());
                }else if(fiveBandButton.isChecked()){
                 //   aliceView.setText( ir1.analyzeBandWithFive(firstBand, secondBand, thirdBand, multiplierBand, toleranceBand));
                    outputTextView.setText(ir1.getAboutThisBand());
                }else {
                 //   aliceView.setText(ir2.analyzeBandWithSix(firstBand, secondBand, thirdBand, multiplierBand, toleranceBand, tempCoBand));
                    outputTextView.setText(ir2.getAboutThisBand());
                }

                break;
            default:
                break;
        }
    };
}

