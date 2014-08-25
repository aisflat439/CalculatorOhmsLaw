package com.aisflat439gardens.calculatorohmslaw.app;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

/**
 * Created by Devin on 3/7/14.
 */
public class FragmentMCDLumensCalculator extends Fragment implements View.OnClickListener {

    double dLuminousIntensity = 0, dBeamAngle = 0, dLuminousFlux = 0;

    EditText luminousIntensityET, beamAngleET, luminousFluxET;

    DecimalFormat df = new DecimalFormat("####.####");

    Button resetButton, calculateButton;

    public FragmentMCDLumensCalculator(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mcd_lumens_calc, container, false);

        luminousIntensityET = (EditText) rootView.findViewById(R.id.luminous_intensity_edit_text);
        beamAngleET = (EditText) rootView.findViewById(R.id.beam_angle_edit_text);
        luminousFluxET = (EditText) rootView.findViewById(R.id.luminous_flux_edit_text);

        resetButton = (Button) rootView.findViewById(R.id.reset);
        calculateButton = (Button) rootView.findViewById(R.id.calculate);

        // set listeners to this view
        calculateButton.setOnClickListener(FragmentMCDLumensCalculator.this);
        resetButton.setOnClickListener(FragmentMCDLumensCalculator.this);

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
                resetButtonPressed();

                break;
        }
    }

    public void calculateButtonPressed(){

        dLuminousIntensity = retrieveValueAndCheck(luminousIntensityET.getText().toString());
        dBeamAngle = retrieveValueAndCheck(beamAngleET.getText().toString());
        dLuminousFlux = retrieveValueAndCheck(luminousFluxET.getText().toString());

        // method for calc
        calculateLumens(dLuminousIntensity, dBeamAngle, dLuminousFlux);

        // output results
        luminousIntensityET.setText(String.valueOf(df.format(dLuminousIntensity)) + " " + getString(R.string.millicandelas));
        luminousIntensityET.setTextColor(Color.BLUE);

        beamAngleET.setText(String.valueOf(df.format(dBeamAngle)) + " " + getString(R.string.degrees));
        beamAngleET.setTextColor(Color.BLUE);

        luminousFluxET.setText(String.valueOf(df.format(dLuminousFlux)) + " " + getString(R.string.lumens));
        luminousFluxET.setTextColor(Color.BLUE);

    }

    public void resetButtonPressed(){

        dLuminousIntensity = 0;
        dBeamAngle = 0;
        dLuminousFlux = 0;

        luminousIntensityET.setText("");
        luminousIntensityET.setTextColor(Color.BLACK);

        beamAngleET.setText("");
        beamAngleET.setTextColor(Color.BLACK);

        luminousFluxET.setText("");
        luminousFluxET.setTextColor(Color.BLACK);

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

    // dLuminousIntensity = intensity, dBeamAngle = angle, dLuminousFlux = flux
    public void calculateLumens(double intensity, double angle, double flux){

        if (intensity > 0 && angle > 0){
            double sr = (1-Math.cos(angle * Math.PI/360)) * 2 * Math.PI;
            dLuminousFlux = intensity * sr;
        } else if (intensity > 0 && flux > 0){
            double sr = flux / intensity;
            dBeamAngle = Math.acos(1-sr/2/Math.PI) * 360 / Math.PI;
        } else if (flux > 0 && angle > 0){ //intensity
            double sr = (1-Math.cos(angle * Math.PI/360)) * 2 * Math.PI;
            dLuminousIntensity = flux / sr;
        }

        dLuminousFlux = Math.round(dLuminousFlux * 100000);
        dLuminousFlux /= 100000;
        dBeamAngle = Math.round(dBeamAngle * 100000);
        dBeamAngle /= 100000;
        dLuminousIntensity = Math.round(dLuminousIntensity * 100000);
        dLuminousIntensity /= 100000;
        dLuminousIntensity *= 1000;

    }
}
