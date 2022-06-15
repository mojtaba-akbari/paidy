package org.paidy;

import org.junit.Test;

// Class Tested Import //
import org.paidy.utility.MaskPersonalInformation;
import org.paidy.utility.OrdinalNumberSuffix;
import org.paidy.utility.SundayCounterBetweenDates;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Paidy App.
 */
public class AppTest 
{
    @Test
    public void ordinalNumberSuffixTest()
    {
        int[] inputNumberTest={0,1,2,3,7,9,10,11,12,13,16,20,21,25,100,101,111,166,200,202,2001,2002};
        String[] resultNumberSuffix={"zeroth","1st","2nd","3rd","7th","9th","10th","11th","12th","13th","16th","20th","21st","25th",
                "100th","101st","111th","166th","200th","202nd","2001st","2002nd"};
        for (int i=0;i< inputNumberTest.length;i++) {
            assertEquals(resultNumberSuffix[i],OrdinalNumberSuffix.ordinalNumberSuffix(inputNumberTest[i]));
        }
    }

    @Test
    public void sundayCounterBetweenDatesTest(){

        assertEquals(0,SundayCounterBetweenDates.sundayCounterBetweenDates("01-05-2021","30-05-2020"));

        assertEquals(1,SundayCounterBetweenDates.sundayCounterBetweenDates("30-05-2021","30-05-2021"));

        assertEquals(1,SundayCounterBetweenDates.sundayCounterBetweenDates("01-05-2021","07-05-2021"));

        assertEquals(5,SundayCounterBetweenDates.sundayCounterBetweenDates("01-05-2021","30-05-2021"));

        assertEquals(7,SundayCounterBetweenDates.sundayCounterBetweenDates("10-02-2021","29-03-2021"));
    }

    @Test
    public void maskPersonalInformationEmailPartTest(){
        // Question Requirement Did not Any explain about this test but, I handle that :) //
        assertEquals("l@l.com",MaskPersonalInformation.userEmailMaskedWithStart("l@l.com"));
        assertEquals("l*****l@l.com",MaskPersonalInformation.userEmailMaskedWithStart("lil@l.com"));
        assertEquals("My Email Is m*****c@gmail.com",MaskPersonalInformation.userEmailMaskedWithStart("My Email Is mojtaba.akbari.sec@gmail.com"));
    }

    @Test
    public void maskPersonalInformationPhoneNumberPartTest(){
        assertEquals("my number is +**-***-**6-789",MaskPersonalInformation.userPhoneNumberMaskedWithStart("my number is +44 123 456 789"));
        assertEquals("+**-***-*2-5-79",MaskPersonalInformation.userPhoneNumberMaskedWithStart("+44 123 42 5 79"));
        assertEquals("+*-**-**56-89",MaskPersonalInformation.userPhoneNumberMaskedWithStart("+1 12 3456 89"));
    }
    @Test
    public void maskPersonalInformation(){
        assertEquals("Email: l*****l@lil.com and Phone Number is: +*-**-**56-89",MaskPersonalInformation.maskPersonalInformation("Email: lil@lil.com and Phone Number is: +1 12 3456 89"));
    }
}
