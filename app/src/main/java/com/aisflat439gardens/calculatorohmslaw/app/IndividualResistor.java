package com.aisflat439gardens.calculatorohmslaw.app;

/**
 * Created by Devin on 3/9/14.
 */
public class IndividualResistor {

    String bandOne, bandTwo, bandThree;
    String bandMultiplier;
    String bandTolerance;
    String bandTempCo;
    String aboutThisBand;

    IndividualResistor(String b1, String b2, String bM, String bT){

        bandOne = b1;
        bandTwo = b2;
        bandMultiplier = bM;
        bandTolerance = bT;
        aboutThisBand = analyzeBand(bandOne, bandTwo, bandMultiplier, bandTolerance);

    }

    IndividualResistor(String b1, String b2, String b3, String bM, String bT){

        bandOne = b1;
        bandTwo = b2;
        bandThree = b3;
        bandMultiplier = bM;
        bandTolerance = bT;
        aboutThisBand = analyzeBandWithFive(bandOne, bandTwo, bandThree, bandMultiplier, bandTolerance);

    }

    IndividualResistor(String b1, String b2, String b3, String bM, String bT, String bTC){

        bandOne = b1;
        bandTwo = b2;
        bandThree = b3;
        bandMultiplier = bM;
        bandTolerance = bT;
        bandTempCo = bTC;
        aboutThisBand = analyzeBandWithSix(bandOne, bandTwo, bandThree, bandMultiplier, bandTolerance, bandTempCo);
    }

    public String getBandOne() {
        return bandOne;
    }

    public String getBandTwo() {
        return bandTwo;
    }

    public String getBandThree() {
        return bandThree;
    }

    public String getBandMultiplier() {
        return bandMultiplier;
    }

    public String getBandTolerance() {
        return bandTolerance;
    }

    public String getBandTempCo() {
        return bandTempCo;
    }

    public void setBandOne(String bandOne) {
        this.bandOne = bandOne;
    }

    public void setBandTwo(String bandTwo) {
        this.bandTwo = bandTwo;
    }

    public void setBandThree(String bandThree) {
        this.bandThree = bandThree;
    }

    public void setBandMultiplier(String bandMultiplier) {
        this.bandMultiplier = bandMultiplier;
    }

    public void setBandTolerance(String bandTolerance) {
        this.bandTolerance = bandTolerance;
    }

    public void setBandTempCo(String bandTempCo) {
        this.bandTempCo = bandTempCo;
    }

    public String getAboutThisBand() {
        return aboutThisBand;
    }

    @Override
    public String toString() {
        return "IndividualResistor{" +
                "bandOne='" + bandOne + '\'' +
                ", bandTwo='" + bandTwo + '\'' +
                ", bandThree='" + bandThree + '\'' +
                ", bandMultiplier='" + bandMultiplier + '\'' +
                ", bandTolerance='" + bandTolerance + '\'' +
                ", bandTempCo='" + bandTempCo + '\'' +
                '}';
    }

    public String analyzeBand(String b1, String b2, String b3, String b4){
        String result = "";
        String stringOhmValue = "";
        double dOhmValue = 0.00;

        String black = "Black", brown = "Brown", red = "Red", orange = "Orange", yellow = "Yellow", green = "Green", blue = "Blue", violet = "Violet", grey = "Grey", white = "White", gold = "Gold", silver = "Silver";
        double digitOne = 0, digitTwo = 0, digitMultiplier = 0, digitTolerance = 0;

        String firstBand = b1;
        String secondBand = b2;
        String multiplierBand = b3;
        String toleranceBand = b4;

        // run each band through to set its value, we will calculate afterwords
// TODO figure a better way to handle the if statements... map the string objects???
        // series of if statements for first band
        if (firstBand.contentEquals(black)){digitOne = 0;}
        else if (firstBand.contentEquals(brown)){digitOne = 1;}
        else if (firstBand.contentEquals(red)){digitOne = 2;}
        else if (firstBand.contentEquals(orange)){digitOne = 3;}
        else if (firstBand.contentEquals(yellow)){digitOne = 4;}
        else if (firstBand.contentEquals(green)){digitOne = 5;}
        else if (firstBand.contentEquals(blue)){digitOne = 6;}
        else if (firstBand.contentEquals(violet)){digitOne = 7;}
        else if (firstBand.contentEquals(grey)){digitOne = 8;}
        else if (firstBand.contentEquals(white)){digitOne = 9;}
        else {
            //    Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //    toast.show();
        }

        // series of if statements for second band
        if (secondBand.contentEquals(black)){digitTwo = 0;}
        else if (secondBand.contentEquals(brown)){digitTwo = 1;}
        else if (secondBand.contentEquals(red)){digitTwo = 2;}
        else if (secondBand.contentEquals(orange)){digitTwo = 3;}
        else if (secondBand.contentEquals(yellow)){digitTwo = 4;}
        else if (secondBand.contentEquals(green)){digitTwo = 5;}
        else if (secondBand.contentEquals(blue)){digitTwo = 6;}
        else if (secondBand.contentEquals(violet)){digitTwo = 7;}
        else if (secondBand.contentEquals(grey)){digitTwo = 8;}
        else if (secondBand.contentEquals(white)){digitTwo = 9;}
        else {
            //     Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        // series of if statements for multiplier band
        if (multiplierBand.contentEquals(black)){digitMultiplier = 0;}
        else if (multiplierBand.contentEquals(brown)){digitMultiplier = 1;}
        else if (multiplierBand.contentEquals(red)){digitMultiplier = 2;}
        else if (multiplierBand.contentEquals(orange)){digitMultiplier = 3;}
        else if (multiplierBand.contentEquals(yellow)){digitMultiplier = 4;}
        else if (multiplierBand.contentEquals(green)){digitMultiplier = 5;}
        else if (multiplierBand.contentEquals(blue)){digitMultiplier = 6;}
        else if (multiplierBand.contentEquals(violet)){digitMultiplier = 7;}
        else if (multiplierBand.contentEquals(gold)){digitMultiplier = .1;}
        else if (multiplierBand.contentEquals(silver)){digitMultiplier = .01;}
        else {
            //      Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        //series of if statements for toleranceBand
        if (toleranceBand.contentEquals(brown)){digitTolerance = 1;}
        else if (toleranceBand.contentEquals(red)){digitTolerance= 2;}
        else if (toleranceBand.contentEquals(green)){digitTolerance = .5;}
        else if (toleranceBand.contentEquals(blue)){digitTolerance = .25;}
        else if (toleranceBand.contentEquals(violet)){digitTolerance = .1;}
        else if (toleranceBand.contentEquals(grey)){digitTolerance = .05;}
        else if (toleranceBand.contentEquals(gold)){digitTolerance = 5;}
        else if (toleranceBand.contentEquals(silver)){digitTolerance = 10;}
        else {
            digitTolerance = 20;
        }

        dOhmValue = ((digitOne * 10) + digitTwo) * (Math.pow(10, digitMultiplier));

        if (dOhmValue >= 1000000){
            dOhmValue = dOhmValue * .000001;
            stringOhmValue = String.valueOf(dOhmValue);
            StringBuilder sb = new StringBuilder();
            sb.append(stringOhmValue + " M");
            result = sb.toString() + " ohms, with a tolerance of " + digitTolerance + "%";
        }else if (dOhmValue >= 1000){
            dOhmValue = dOhmValue * .001;
            stringOhmValue = String.valueOf(dOhmValue);
            StringBuilder sb = new StringBuilder();
            sb.append(stringOhmValue + " k");
            result = sb.toString() + " ohms, with a tolerance of " + digitTolerance + "%";
        } else {
            result = dOhmValue + " ohms, with a tolerance of " + digitTolerance + "%";
        }

        return result;
    }

    public String analyzeBandWithFive(String b1, String b2, String b3, String b4, String b5){
        String result = "";
        String stringOhmValue = "";
        double dOhmValue = 0.00;

        String black = "Black", brown = "Brown", red = "Red", orange = "Orange", yellow = "Yellow", green = "Green", blue = "Blue", violet = "Violet", grey = "Grey", white = "White", gold = "Gold", silver = "Silver";
        double digitOne = 0, digitTwo = 0, digitThree = 0, digitMultiplier = 0, digitTolerance = 0;

        String firstBand = b1;
        String secondBand = b2;
        String thirdBand = b3;
        String multiplierBand = b4;
        String toleranceBand = b5;

        if (firstBand.contentEquals(black)){digitOne = 0;}
        else if (firstBand.contentEquals(brown)){digitOne = 1;}
        else if (firstBand.contentEquals(red)){digitOne = 2;}
        else if (firstBand.contentEquals(orange)){digitOne = 3;}
        else if (firstBand.contentEquals(yellow)){digitOne = 4;}
        else if (firstBand.contentEquals(green)){digitOne = 5;}
        else if (firstBand.contentEquals(blue)){digitOne = 6;}
        else if (firstBand.contentEquals(violet)){digitOne = 7;}
        else if (firstBand.contentEquals(grey)){digitOne = 8;}
        else if (firstBand.contentEquals(white)){digitOne = 9;}
        else {
            //    Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //    toast.show();
        }

        // series of if statements for second band
        if (secondBand.contentEquals(black)){digitTwo = 0;}
        else if (secondBand.contentEquals(brown)){digitTwo = 1;}
        else if (secondBand.contentEquals(red)){digitTwo = 2;}
        else if (secondBand.contentEquals(orange)){digitTwo = 3;}
        else if (secondBand.contentEquals(yellow)){digitTwo = 4;}
        else if (secondBand.contentEquals(green)){digitTwo = 5;}
        else if (secondBand.contentEquals(blue)){digitTwo = 6;}
        else if (secondBand.contentEquals(violet)){digitTwo = 7;}
        else if (secondBand.contentEquals(grey)){digitTwo = 8;}
        else if (secondBand.contentEquals(white)){digitTwo = 9;}
        else {
            //     Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        if (thirdBand.contentEquals(black)){digitThree = 0;}
        else if (thirdBand.contentEquals(brown)){digitThree = 1;}
        else if (thirdBand.contentEquals(red)){digitThree = 2;}
        else if (thirdBand.contentEquals(orange)){digitThree = 3;}
        else if (thirdBand.contentEquals(yellow)){digitThree = 4;}
        else if (thirdBand.contentEquals(green)){digitThree = 5;}
        else if (thirdBand.contentEquals(blue)){digitThree = 6;}
        else if (thirdBand.contentEquals(violet)){digitThree = 7;}
        else if (thirdBand.contentEquals(grey)){digitThree = 8;}
        else if (thirdBand.contentEquals(white)){digitThree = 9;}
        else {
            //     Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        // series of if statements for multiplier band
        if (multiplierBand.contentEquals(black)){digitMultiplier = 0;}
        else if (multiplierBand.contentEquals(brown)){digitMultiplier = 1;}
        else if (multiplierBand.contentEquals(red)){digitMultiplier = 2;}
        else if (multiplierBand.contentEquals(orange)){digitMultiplier = 3;}
        else if (multiplierBand.contentEquals(yellow)){digitMultiplier = 4;}
        else if (multiplierBand.contentEquals(green)){digitMultiplier = 5;}
        else if (multiplierBand.contentEquals(blue)){digitMultiplier = 6;}
        else if (multiplierBand.contentEquals(violet)){digitMultiplier = 7;}
        else if (multiplierBand.contentEquals(gold)){digitMultiplier = .1;}
        else if (multiplierBand.contentEquals(silver)){digitMultiplier = .01;}
        else {
            //      Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        //series of if statements for toleranceBand
        if (toleranceBand.contentEquals(brown)){digitTolerance = 1;}
        else if (toleranceBand.contentEquals(red)){digitTolerance= 2;}
        else if (toleranceBand.contentEquals(green)){digitTolerance = .5;}
        else if (toleranceBand.contentEquals(blue)){digitTolerance = .25;}
        else if (toleranceBand.contentEquals(violet)){digitTolerance = .1;}
        else if (toleranceBand.contentEquals(grey)){digitTolerance = .05;}
        else if (toleranceBand.contentEquals(gold)){digitTolerance = 5;}
        else if (toleranceBand.contentEquals(silver)){digitTolerance = 10;}
        else {
            digitTolerance = 20;
        }

        dOhmValue = ((digitOne * 100) + (digitTwo * 10) + digitThree) * (Math.pow(10, digitMultiplier));

        if (dOhmValue >= 1000000){
            dOhmValue = dOhmValue * .000001;
            stringOhmValue = String.valueOf(dOhmValue);
            StringBuilder sb = new StringBuilder();
            sb.append(stringOhmValue + " M");
            result = sb.toString() + " ohms, with a tolerance of " + digitTolerance + "%";
        }else if (dOhmValue >= 1000){
            dOhmValue = dOhmValue * .001;
            stringOhmValue = String.valueOf(dOhmValue);
            StringBuilder sb = new StringBuilder();
            sb.append(stringOhmValue + " k");
            result = sb.toString() + " ohms, with a tolerance of " + digitTolerance + "%";
        } else {
            result = dOhmValue + " ohms, with a tolerance of " + digitTolerance + "%";
        }
        return result;
    }

    public String analyzeBandWithSix(String b1, String b2, String b3, String b4, String b5, String b6){
        String result = "";
        String stringOhmValue = "";
        double dOhmValue = 0.00;

        String black = "Black", brown = "Brown", red = "Red", orange = "Orange", yellow = "Yellow", green = "Green", blue = "Blue", violet = "Violet", grey = "Grey", white = "White", gold = "Gold", silver = "Silver";
        double digitOne = 0, digitTwo = 0, digitThree = 0, digitMultiplier = 0, digitTolerance = 0, digitTempCoefficient = 0;

        String firstBand = b1;
        String secondBand = b2;
        String thirdBand = b3;
        String multiplierBand = b4;
        String toleranceBand = b5;
        String tempCoBand = b6;

        if (firstBand.contentEquals(black)){digitOne = 0;}
        else if (firstBand.contentEquals(brown)){digitOne = 1;}
        else if (firstBand.contentEquals(red)){digitOne = 2;}
        else if (firstBand.contentEquals(orange)){digitOne = 3;}
        else if (firstBand.contentEquals(yellow)){digitOne = 4;}
        else if (firstBand.contentEquals(green)){digitOne = 5;}
        else if (firstBand.contentEquals(blue)){digitOne = 6;}
        else if (firstBand.contentEquals(violet)){digitOne = 7;}
        else if (firstBand.contentEquals(grey)){digitOne = 8;}
        else if (firstBand.contentEquals(white)){digitOne = 9;}
        else {
            //    Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //    toast.show();
        }

        // series of if statements for second band
        if (secondBand.contentEquals(black)){digitTwo = 0;}
        else if (secondBand.contentEquals(brown)){digitTwo = 1;}
        else if (secondBand.contentEquals(red)){digitTwo = 2;}
        else if (secondBand.contentEquals(orange)){digitTwo = 3;}
        else if (secondBand.contentEquals(yellow)){digitTwo = 4;}
        else if (secondBand.contentEquals(green)){digitTwo = 5;}
        else if (secondBand.contentEquals(blue)){digitTwo = 6;}
        else if (secondBand.contentEquals(violet)){digitTwo = 7;}
        else if (secondBand.contentEquals(grey)){digitTwo = 8;}
        else if (secondBand.contentEquals(white)){digitTwo = 9;}
        else {
            //     Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        if (thirdBand.contentEquals(black)){digitThree = 0;}
        else if (thirdBand.contentEquals(brown)){digitThree = 1;}
        else if (thirdBand.contentEquals(red)){digitThree = 2;}
        else if (thirdBand.contentEquals(orange)){digitThree = 3;}
        else if (thirdBand.contentEquals(yellow)){digitThree = 4;}
        else if (thirdBand.contentEquals(green)){digitThree = 5;}
        else if (thirdBand.contentEquals(blue)){digitThree = 6;}
        else if (thirdBand.contentEquals(violet)){digitThree = 7;}
        else if (thirdBand.contentEquals(grey)){digitThree = 8;}
        else if (thirdBand.contentEquals(white)){digitThree = 9;}
        else {
            //     Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        // series of if statements for multiplier band
        if (multiplierBand.contentEquals(black)){digitMultiplier = 0;}
        else if (multiplierBand.contentEquals(brown)){digitMultiplier = 1;}
        else if (multiplierBand.contentEquals(red)){digitMultiplier = 2;}
        else if (multiplierBand.contentEquals(orange)){digitMultiplier = 3;}
        else if (multiplierBand.contentEquals(yellow)){digitMultiplier = 4;}
        else if (multiplierBand.contentEquals(green)){digitMultiplier = 5;}
        else if (multiplierBand.contentEquals(blue)){digitMultiplier = 6;}
        else if (multiplierBand.contentEquals(violet)){digitMultiplier = 7;}
        else if (multiplierBand.contentEquals(gold)){digitMultiplier = .1;}
        else if (multiplierBand.contentEquals(silver)){digitMultiplier = .01;}
        else {
            //      Toast toast = Toast.makeText(getApplicationContext(), "Please contact Developer if this message appears, error: band selection", Toast.LENGTH_LONG);
            //      toast.show();
        }

        //series of if statements for toleranceBand
        if (toleranceBand.contentEquals(brown)){digitTolerance = 1;}
        else if (toleranceBand.contentEquals(red)){digitTolerance= 2;}
        else if (toleranceBand.contentEquals(green)){digitTolerance = .5;}
        else if (toleranceBand.contentEquals(blue)){digitTolerance = .25;}
        else if (toleranceBand.contentEquals(violet)){digitTolerance = .1;}
        else if (toleranceBand.contentEquals(grey)){digitTolerance = .05;}
        else if (toleranceBand.contentEquals(gold)){digitTolerance = 5;}
        else if (toleranceBand.contentEquals(silver)){digitTolerance = 10;}
        else {
            digitTolerance = 20;
        }

        if (tempCoBand.contentEquals(brown)){digitTempCoefficient = 100;}
        else if (tempCoBand.contentEquals(red)){digitTempCoefficient= 50;}
        else if (tempCoBand.contentEquals(orange)){digitTempCoefficient = 15;}
        else if (tempCoBand.contentEquals(yellow)){digitTempCoefficient = 25;}
        else if (tempCoBand.contentEquals(blue)){digitTempCoefficient = 10;}
        else if (tempCoBand.contentEquals(violet)){digitTempCoefficient = 5;}

        dOhmValue = ((digitOne * 100) + (digitTwo * 10) + (digitThree)) * (Math.pow(10, digitMultiplier));

        if (dOhmValue >= 1000000){
            dOhmValue = dOhmValue * .000001;
            stringOhmValue = String.valueOf(dOhmValue);
            StringBuilder sb = new StringBuilder();
            sb.append(stringOhmValue + " M");
            result = sb.toString() + " ohms, with a tolerance of " + digitTolerance + "%";
        }else if (dOhmValue >= 1000){
            dOhmValue = dOhmValue * .001;
            stringOhmValue = String.valueOf(dOhmValue);
            StringBuilder sb = new StringBuilder();
            sb.append(stringOhmValue + " k");
            result = sb.toString() + " ohms, with a tolerance of " + digitTolerance + "%";
        } else {
            result = dOhmValue + " ohms, with a tolerance of " + digitTolerance + "%";
        }

        result = result + " " + digitTempCoefficient + "ppm";

        return result;
    }
}


