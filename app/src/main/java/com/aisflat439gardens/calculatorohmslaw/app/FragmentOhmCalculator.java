package com.aisflat439gardens.calculatorohmslaw.app;

/**
 * Created by Devin on 3/6/14.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;

public class FragmentOhmCalculator extends Fragment implements View.OnClickListener{

    // declare variables as doubles for maths
    double  dCurrent = 0.00, dVoltage = 0.00, dResistance = 0.00,
            dPower = 0.00, milli = 100, kilo = 1000, mega = Math.pow(10,-6);

    DecimalFormat df = new DecimalFormat("####.####");

    // instantiate the edit texts, textViews, radiobuttons and buttons
    EditText voltageInputEditText, currentInputEditText,
            resistanceInputEditText, powerInputEditText;

    //instantiate radio buttons
    RadioButton ampsRadioButton, milliAmpsRadioButton, ohmsRadioButton,
            kilohmsRadioButton, megaOhmsRadioButton;

    Button resetButton, calculateButton;

    public FragmentOhmCalculator(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ohm_calculator, container, false);

        voltageInputEditText = (EditText) rootView.findViewById(R.id.voltage);
        currentInputEditText = (EditText) rootView.findViewById(R.id.current);
        resistanceInputEditText = (EditText) rootView.findViewById(R.id.resistance);
        powerInputEditText = (EditText) rootView.findViewById(R.id.power);

        // associate buttons and radio buttons
        ampsRadioButton = (RadioButton) rootView.findViewById(R.id.amps_radio_button);
        milliAmpsRadioButton = (RadioButton) rootView.findViewById(R.id.milliamps_radio_button);
        ohmsRadioButton = (RadioButton) rootView.findViewById(R.id.ohms_radio_button);
        kilohmsRadioButton = (RadioButton) rootView.findViewById(R.id.kilohms_radio_button);
        megaOhmsRadioButton = (RadioButton) rootView.findViewById(R.id.megaohms_radio_button);

        resetButton = (Button) rootView.findViewById(R.id.reset);
        calculateButton = (Button) rootView.findViewById(R.id.calculate);

        // set listeners to this view
        calculateButton.setOnClickListener(FragmentOhmCalculator.this);
        resetButton.setOnClickListener(FragmentOhmCalculator.this);

//-------------------------------------------------------------------------------------------------------------------
//TODO    Do some reading to clarify what this is and how it works. I don't think this is implemeneted correctly
//-------------------------------------------------------------------------------------------------------------------

        InputMethodManager mgrV = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgrV.hideSoftInputFromWindow(voltageInputEditText.getWindowToken(), 0);

        InputMethodManager mgrC = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgrC.hideSoftInputFromWindow(currentInputEditText.getWindowToken(), 0);

        InputMethodManager mgrR = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgrR.hideSoftInputFromWindow(resistanceInputEditText.getWindowToken(), 0);

        InputMethodManager mgrP = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgrP.hideSoftInputFromWindow(powerInputEditText.getWindowToken(), 0);

        return rootView;
    }
    @Override
    public void onClick(View v){

        switch (v.getId()){
            // calculate button listener
            case R.id.calculate:
                // do calc, update results, deactivate button
                calculateButtonPressed();

                break;

            case R.id.reset:
                //set values and text and radioButtons back to default
                resetButtonClickedActions();

                break;

        }
    }

    public double retrieveValueAndCheck (String stringValue){
        // This function checks the value held in the edit texts
        double dParsedValue = 0.00;

        if (stringValue.contentEquals("")){
            return dParsedValue;
        }
        dParsedValue = Double.parseDouble(stringValue);
        return dParsedValue;
    }

    //dCurrent (I), dVoltage (E), dPower (P), dResistance (R)
    public void calculateOhmsLaw (double I, double E, double P, double R){

        if (E != 0.00 && I != 0.00){
            P = E * I;
            R = E / I;
            dPower = P;
            dResistance = R;
        }else if (E != 0.00 && R != 0.00) {
            P = (E * E)/R;
            I = (E / R);
            dPower = P;
            dCurrent = I;
        }else if (E != 0.00 && P != 0.00) {
            R = (E * E) / P;
            I = P / E;
            dResistance = R;
            dCurrent = I;
        }else if (R != 0.00 && I != 0.00) {
            E = R * I;
            P = R * (I * I);
            dVoltage = E;
            dPower = P;
        }else if (R != 0.00 && P != 0.00) {
            E = Math.sqrt(P * R);
            I = Math.sqrt(P / R);
            dVoltage = E;
            dCurrent = I;
        }else if (I != 0.00 && P != 0.00) {
            R = P / (I * I );
            E = P / I;
            dResistance = R;
            dVoltage = E;
        }
    }

    public void resetButtonClickedActions(){

        dCurrent        = 0.00;
        dVoltage        = 0.00;
        dPower          = 0.00;
        dResistance     = 0.00;

        ampsRadioButton.setChecked(true);
        ohmsRadioButton.setChecked(true);

        currentInputEditText.setText("");
        currentInputEditText.setTextColor(Color.BLACK);

        voltageInputEditText.setText("");
        voltageInputEditText.setTextColor(Color.BLACK);

        powerInputEditText.setText("");
        powerInputEditText.setTextColor(Color.BLACK);

        resistanceInputEditText.setText("");
        resistanceInputEditText.setTextColor(Color.BLACK);

        calculateButton.setClickable(true);
    }

    public void calculateButtonPressed(){

        // set double values equal to editTexts
        dCurrent    =   retrieveValueAndCheck(currentInputEditText.getText().toString());
        dVoltage    =   retrieveValueAndCheck(voltageInputEditText.getText().toString());
        dPower      =   retrieveValueAndCheck(powerInputEditText.getText().toString());
        dResistance =   retrieveValueAndCheck(resistanceInputEditText.getText().toString());

        // handle amps button
        if (milliAmpsRadioButton.isChecked()){calculateOhmsLaw(dCurrent/milli, dVoltage/milli, dPower/milli, dResistance/milli);};

        //handle ohms buttons
        if (megaOhmsRadioButton.isChecked()){calculateOhmsLaw(dCurrent * mega, dVoltage * mega, dPower * mega, dResistance * mega);}
        else if (kilohmsRadioButton.isChecked()){calculateOhmsLaw(dCurrent * kilo, dVoltage * kilo, dPower * kilo, dResistance * kilo);}
        else calculateOhmsLaw(dCurrent, dVoltage, dPower, dResistance);

        currentInputEditText.setText(String.valueOf(df.format(dCurrent)) + " " + getString(R.string.current_hint));
        currentInputEditText.setTextColor(Color.BLUE);

        voltageInputEditText.setText(String.valueOf(df.format(dVoltage)) + " " + getString(R.string.voltage_hint));
        voltageInputEditText.setTextColor(Color.BLUE);

        powerInputEditText.setText(String.valueOf(df.format(dPower)) + " " + getString(R.string.power__hint));
        powerInputEditText.setTextColor(Color.BLUE);

        resistanceInputEditText.setText(String.valueOf(df.format(dResistance)) + " " + getString(R.string.resistance_hint));
        resistanceInputEditText.setTextColor(Color.BLUE);

        calculateButton.setClickable(false);
    }

}