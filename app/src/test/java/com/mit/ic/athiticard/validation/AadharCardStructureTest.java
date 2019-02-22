package com.mit.ic.athiticard.validation;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Test to check if the PANCardValidator function works as expected
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AadharCardStructureTest {

    static ArrayList<String> validAadharNumbers = new ArrayList<>(6);
    static ArrayList<String> invalidAadharNumbers = new ArrayList<>(12);

    @BeforeClass
    public static void init() {
        validAadharNumbers.add("GDWCU1962P");
        validAadharNumbers.add("STAYM0303R");
        validAadharNumbers.add("XATQC2232H");
        validAadharNumbers.add("VDNZF1479O");
        validAadharNumbers.add("ZWERC7356U");
        validAadharNumbers.add("IUYZZ5954S");

        //Invalid structure PAN Numbers
        invalidAadharNumbers.add("ZKBlC3773M");
        invalidAadharNumbers.add("02OkO6P8NL");
        invalidAadharNumbers.add("1XQ9HO6V8S");
        invalidAadharNumbers.add("ABAB12345Y");

        //Invalid characters PAN Numbers
        invalidAadharNumbers.add("avCDS1234Y");
        invalidAadharNumbers.add("I$YZZ5954S");
        invalidAadharNumbers.add("I$YZZ5954$");
        invalidAadharNumbers.add("I\nYZZ5954S");
        invalidAadharNumbers.add("I\nYZZ595••");
        invalidAadharNumbers.add("I$YZZ5--4$");

        //Invalid length PAN Numbers
        invalidAadharNumbers.add("I$YZZ54$");
        invalidAadharNumbers.add("I$Y••••ZZ5--4$");

    }

    @Test
    public void isTestContentsLoaded() {
        assertTrue(validAadharNumbers.size() > 5);
        assertTrue(invalidAadharNumbers.size() > 10);
    }

    @Test
    public void validation_isCorrect() {

    }
}

