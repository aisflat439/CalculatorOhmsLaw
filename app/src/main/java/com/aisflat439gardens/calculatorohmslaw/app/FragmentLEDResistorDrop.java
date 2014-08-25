package com.aisflat439gardens.calculatorohmslaw.app;

/**
 * Created by Devin on 3/6/14.
 */

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class FragmentLEDResistorDrop extends Fragment implements View.OnClickListener{

    // declare variables you think you need
    double dVoltageSourceES = 0.00, dVoltageDropELED = 0.00, dCurrentILED = 0.00, dResistance = 0.00,
            dPower = 0.00, milli = 100, kilo = 1000, mega = Math.pow(10,-6), amps = 10, ohms = 10;


    String ampsSpinnerValueString, ohmsSpinnerValueString;

    DecimalFormat df = new DecimalFormat("####.####");

    EditText voltageSourceEditText, voltageDropEditText, currentThroughEditText, resistanceEditText,
            powerEditText;
    Spinner ampsSpinner, ohmsSpinner;

    Button calculateButton, resetButton;

    public FragmentLEDResistorDrop(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_led_resistor_drop_calculator, container, false);

        voltageSourceEditText = (EditText) rootView.findViewById(R.id.voltage_source);
        voltageDropEditText = (EditText) rootView.findViewById(R.id.voltage_drop);
        currentThroughEditText = (EditText) rootView.findViewById(R.id.current_through_led);
        resistanceEditText = (EditText) rootView.findViewById(R.id.resistance);
        powerEditText =  (EditText) rootView.findViewById(R.id.power);

        resetButton = (Button) rootView.findViewById(R.id.reset);
        calculateButton = (Button) rootView.findViewById(R.id.calculate);

        resetButton.setOnClickListener(FragmentLEDResistorDrop.this);
        calculateButton.setOnClickListener(FragmentLEDResistorDrop.this);

        ampsSpinner = (Spinner)rootView.findViewById(R.id.amps_spinner);
        ohmsSpinner = (Spinner)rootView.findViewById(R.id.ohms_spinner);

        ArrayAdapter<CharSequence> adapterAmps = ArrayAdapter.createFromResource(rootView.getContext(), R.array.amps_array, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> adapterOhms = ArrayAdapter.createFromResource(rootView.getContext(), R.array.ohms_array, android.R.layout.simple_list_item_1);

        ampsSpinner.setAdapter(adapterAmps);
        ohmsSpinner.setAdapter(adapterOhms);

        adapterAmps.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapterOhms.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        ampsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ampsSpinnerValueString = ampsSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    // doesn't run because something I've set defalut to amps selected

            }
        });

        ohmsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ohmsSpinnerValueString = ohmsSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // doesn't run because something I've set defalut to amps selected

            }
        });

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

    public void calculateButtonPressed(){

        // set double values equal to editTexts
        dVoltageSourceES = retrieveValueAndCheck(voltageSourceEditText.getText().toString());
        dVoltageDropELED = retrieveValueAndCheck(voltageDropEditText.getText().toString());
        dCurrentILED = retrieveValueAndCheck(currentThroughEditText.getText().toString());
        dResistance = retrieveValueAndCheck(resistanceEditText.getText().toString());
        dPower = retrieveValueAndCheck(powerEditText.getText().toString());

        // handle amps spinner
        if (ampsSpinnerValueString.contentEquals("milliamps")){
            dVoltageSourceES /= milli; dVoltageDropELED /= milli; dCurrentILED /= milli; dResistance /= milli; dPower /= milli; }
        //else { dCurrentILED = dCurrentILED * amps; dResistance = dResistance * ohms; }

        // handle ohms spinner
        if (ohmsSpinnerValueString.contentEquals("kilohms")){
            dVoltageDropELED *= kilo; dVoltageDropELED *= kilo; dCurrentILED *= kilo; dResistance *= kilo; dPower *= kilo;}
        else if (ohmsSpinnerValueString.contentEquals("megaohms")){
            dVoltageDropELED *= mega; dVoltageDropELED *= mega; dCurrentILED *= mega; dResistance *= mega; dPower *= mega;}
        //else { dCurrentILED = dCurrentILED * amps; dResistance = dResistance * ohms; }

        calculateOhmsLaw(dVoltageSourceES, dVoltageDropELED, dCurrentILED, dResistance, dPower);

        voltageSourceEditText.setTextColor(Color.BLUE);
        voltageDropEditText.setTextColor(Color.BLUE);
        currentThroughEditText.setTextColor(Color.BLUE);
        resistanceEditText.setTextColor(Color.BLUE);
        powerEditText.setTextColor(Color.BLUE);

        voltageSourceEditText.setText(String.valueOf(df.format(dVoltageSourceES)) + " " + getString(R.string.voltage_source_hint));
        voltageDropEditText.setText(String.valueOf(df.format(dVoltageDropELED)) + " " + getString(R.string.voltage_drop_hint));
        currentThroughEditText.setText(String.valueOf(df.format(dCurrentILED)) + " " + getString(R.string.current_through_led_hint));
        resistanceEditText.setText(String.valueOf(df.format(dResistance)) + " " + getString(R.string.resistance_hint));
        powerEditText.setText(String.valueOf(df.format(dPower)) + " " + getString(R.string.power__hint));

        calculateButton.setClickable(false);
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

    public void resetButtonClickedActions(){

        dVoltageSourceES = 0.00;
        dVoltageDropELED = 0.00;
        dCurrentILED = 0.00;
        dResistance = 0.00;
        dPower = 0.00;

        voltageSourceEditText.setText("");
        voltageDropEditText.setText("");
        currentThroughEditText.setText("");
        resistanceEditText.setText("");
        powerEditText.setText("");

        voltageSourceEditText.setTextColor(Color.BLACK);
        voltageDropEditText.setTextColor(Color.BLACK);
        currentThroughEditText.setTextColor(Color.BLACK);
        resistanceEditText.setTextColor(Color.BLACK);
        powerEditText.setTextColor(Color.BLACK);

        calculateButton.setClickable(true);
    }

    public void calculateOhmsLaw(double dVES, double dVLED, double dAmps, double dOhms, double dWatts){
        double volts = 0;
        // uk Ohms and Watts
        if (dVES > 0 && dVLED > 0 && dAmps > 0){
            dCurrentILED = (dVES - dVLED) / dAmps;
            dPower = (dVES - dVLED) * dAmps;
        } else if (dVES > 0 && dVLED > 0 && dOhms > 0){// uk Amps and Watts
            dResistance = (dVES - dVLED) / dOhms;
            dPower = (dVES - dVLED) * dAmps;
        } else if (dVES > 0 && dVLED > 0 && dWatts > 0){ // uk Ohms and Amps
            dResistance = dWatts / (dVES - dVLED);
            dPower = (dVES - dVLED) / dAmps;
        } else if (dVES > 0 && dAmps > 0 && dOhms > 0){// uk VoltsLED adn Watts
            volts = dAmps * dOhms;
            dVoltageDropELED = dVES - volts;
            dPower = dAmps * dAmps * dOhms;
        } else if (dVES > 0 && dAmps > 0 & dWatts > 0){// uk VoltsLED and Ohms
            volts = dWatts / dAmps;
            dVoltageDropELED = dVES - volts;
            dCurrentILED = volts / dAmps;
        } else if (dVES > 0 && dOhms > 0 && dWatts > 0){// uk voltsLED and Amps
            dResistance = Math.sqrt((dWatts / dOhms));
            volts = dAmps * dOhms;
            dVoltageDropELED = dVES - volts;
        } else if (dVLED > 0 && dAmps > 0 && dOhms > 0){// uk voltsES anbd watts
            volts = dAmps * dOhms;
            dVoltageSourceES = volts + dVLED;
            dPower = dAmps * dAmps * dOhms;
        } else if (dVLED > 0 && dAmps > 0 && dWatts > 0){// uk voltsES and ohms
            volts = dWatts - dAmps;
            dVoltageSourceES = volts + dVLED;
            dCurrentILED = volts / dAmps;
        } else if (dVLED >0 && dOhms > 0 && dWatts > 0){// uk voltseS and AMPS
            dResistance = Math.sqrt(dWatts/dOhms);
            volts = dAmps * dOhms;
            dVoltageSourceES = volts + dVLED;
        } else if (dOhms > 0 && dAmps > 0 && dWatts < 0){// volts ES and VoltsLED
            dResistance = Math.sqrt(dWatts/dOhms);
            volts = dAmps * dOhms;
            dVoltageSourceES = volts;
            dVoltageDropELED = 0;
        }
    }
}