package org.paidy.utility;

public class OrdinalNumberSuffix {

    // Constant Suffix //
    final static String[] suffixDefined={"st","nd","rd","th"};

    // Avoid Define Integer Class Because Of Performance In Pointer Class //
    public static String ordinalNumberSuffix(int inputNumber)
    {
        // Zero //
        if(inputNumber==0) return "zeroth";

        // Point To The Default //
        short ptrToSuffix=3;

        // Recognize Number From Math Order //
        int j = inputNumber % 10;
        int k = inputNumber % 100;

        if (j == 1 && k != 11) {
            ptrToSuffix=0;
        }
        else if (j == 2 && k != 12) {
            ptrToSuffix=1;
        }
        else if (j == 3 && k != 13) {
            ptrToSuffix=2;
        }

        return String.valueOf(inputNumber)+suffixDefined[ptrToSuffix];
    }
}
