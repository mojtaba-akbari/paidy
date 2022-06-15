package org.paidy.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskPersonalInformation {
    // Any Param could have Getter Setter For Config Shared Class //
    final static String EMAIL_REGX = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}"; // copy from Github following by question :)
    final static Pattern VALID_EMAIL_PATTERN = Pattern.compile(EMAIL_REGX, Pattern.CASE_INSENSITIVE);
    final static String EMAIL_MASK_STAR = "*****";
    final static short EMAIL_PART_MASKED = 0;
    final static short EMAIL_PART_UNMASKED = 1;

    final static String PHONE_REGX = "\\+?([\\d][\\s]?){9,}"; // wrote by myself following question requirement :)
    final static Pattern VALID_PHONE_PATTERN = Pattern.compile(PHONE_REGX, Pattern.CASE_INSENSITIVE);

    final static short COUNT_OF_UNMASK_PHONE_FROM_LAST = 3;

    public static String maskPersonalInformation(String inputPersonalData) {
        // Call Filter Step By Step So We Can Add More Filter... //
        return userEmailMaskedWithStart(
                userPhoneNumberMaskedWithStart(inputPersonalData)
        );
    }

    // Encapsulation Public -For Individual Test And Maybe Another Programmer On Another Team Want Just Use One Filter//
    public static String userEmailMaskedWithStart(String inputPersonalData) {

        Matcher matcher = VALID_EMAIL_PATTERN.matcher(inputPersonalData);

        // Make Replacement Part //
        // Check If Any Group Find (Can Throw Exception ) But I prefer Check //
        if (matcher.find()) {
            String userEmailFound = matcher.group(0).split("@")[EMAIL_PART_MASKED];
            if (userEmailFound.length() > 1) {
                String resultEmailMasked = String.valueOf(userEmailFound.charAt(0)) +
                        EMAIL_MASK_STAR +
                        String.valueOf(userEmailFound.charAt(userEmailFound.length() - 1)) + "@" +
                        matcher.group(0).split("@")[EMAIL_PART_UNMASKED];

                return matcher.replaceAll(resultEmailMasked);
            }
        }

        return inputPersonalData; // I hate Empty "" Or Null //
    }

    // Encapsulation Public -For Individual Test And Maybe Another Programmer On Another Team Want Just Use One Filter//
    public static String userPhoneNumberMaskedWithStart(String inputPersonalData) {

        Matcher matcher = VALID_PHONE_PATTERN.matcher(inputPersonalData);

        if (matcher.find()) {
            // Make Replacement Part //
            System.out.println(matcher.group(0));

            String userPhoneFound = matcher.group(0);
            // First + Remain
            // Last 4 Char Remain
            String userPhoneMaskedResult = "";
            char[] userPhoneConvertedToCharArray = userPhoneFound.toCharArray();


            // Loop From End To Start //
            // Result Reversed //
            int lastFourDigitUnMasked = 0;
            for (int i = userPhoneConvertedToCharArray.length - 1; i > 0; i--) {
                if (userPhoneConvertedToCharArray[i] == ' ') {
                    userPhoneMaskedResult += "-";
                    continue;
                } else if (lastFourDigitUnMasked <= COUNT_OF_UNMASK_PHONE_FROM_LAST) {
                    lastFourDigitUnMasked++;
                    userPhoneMaskedResult += userPhoneConvertedToCharArray[i];
                    continue;
                }

                userPhoneMaskedResult += "*";
            }

            if (userPhoneConvertedToCharArray[0] == '+') userPhoneMaskedResult += "+";

            return matcher.replaceAll(new StringBuffer(userPhoneMaskedResult).reverse().toString());
        }

        return inputPersonalData; // I hate Empty "" Or Null //
    }
}
