package com.aisflat439gardens.calculatorohmslaw.app;

import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Devin on 4/29/2014.
 */
public class FragmentSingleLEDCalculator extends Fragment implements View.OnTouchListener {

//--------------------------------------------------------------------------------------------------
// These are for the animation
//
    int duration = 120;
    Rect rect;
    DecelerateInterpolator sDecelerator = new DecelerateInterpolator();
    OvershootInterpolator mOvershooter = new OvershootInterpolator(7f);
//
//--------------------------------------------------------------------------------------------------

    double dSourceVoltage = 0.0, dDiodeForwardVoltage = 0.0, dDiodeForwardCurrent = 0.0;
    EditText sourceVoltageET, diodeVoltageET, diodeCurrentET;
    Button resetButton, calculateButton;
    TextView resultsTextView;
    DecimalFormat df = new DecimalFormat("####.####");

    public FragmentSingleLEDCalculator(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_led_caclulator, container, false);

        sourceVoltageET = (EditText) rootView.findViewById(R.id.source_voltage_edit_text);
        diodeVoltageET = (EditText) rootView.findViewById(R.id.diode_forward_voltage_edit_text);
        diodeCurrentET = (EditText) rootView.findViewById(R.id.diode_forward_voltage_edit_text);

        resultsTextView = (TextView) rootView.findViewById(R.id.results);

        resetButton = (Button) rootView.findViewById(R.id.reset);
        calculateButton = (Button) rootView.findViewById(R.id.calculate);

        resetButton.animate().setDuration(duration);
        calculateButton.animate().setDuration(duration);

        // set listeners to this view
        calculateButton.setOnTouchListener(FragmentSingleLEDCalculator.this);
        resetButton.setOnTouchListener(FragmentSingleLEDCalculator.this);

        return rootView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        switch (v.getId()){
            case R.id.calculate:
                feedbackAnimation(v, action, calculateButton, event);
                resultsTextView.setText("Oh my golly");
                break;
            case R.id.reset:
                feedbackAnimation(v, action, resetButton, event);
                resultsTextView.setText("Holy woot yea!");
                break;
            default:
                return true;
        }

        return true;
    }

    private boolean feedbackAnimation(View v, int action, Button btn, MotionEvent event) {
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                // Here we are setting the scale we want our button
                // to grow to in the animation. it will get bigger,
                // hence teh 1.5f
                btn.animate().setInterpolator(sDecelerator)
                        .scaleX(1.5f)
                        .scaleY(1.5f);
                return true;
            case (MotionEvent.ACTION_UP):
                if (!rect.contains(v.getLeft() + (int) event.getX(),
                        v.getTop() + (int) event.getY())) {
                    // if finger outside don't do anything
                } else {

                    btn.animate()
                            .setInterpolator(mOvershooter)
                            .scaleX(1f)
                            .scaleY(1f);
                    // Note the scale variables here!

                    // you may want to delay your action from happening
                    // in order to enjoy your animation and
                    // avoid jarring your users
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // your intent here
                            Log.w("Bouncy Button", "Hello there");
                        }
                    }, 150);
                    // I'm delaying this 150 note out animation
                    // not animation total is 120
                }
                return true;

            case (MotionEvent.ACTION_MOVE):
                if (!rect.contains(v.getLeft() + (int) event.getX(),
                        v.getTop() + (int) event.getY())) {
                    btn.animate()
                            .setInterpolator(mOvershooter)
                            .scaleX(1f)
                            .scaleY(1f);
                }
                return true;
            default:
        }
        return true;
    }
}

