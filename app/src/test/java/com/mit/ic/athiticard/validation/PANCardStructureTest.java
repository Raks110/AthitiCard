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
public class PANCardStructureTest {

    static ArrayList<String> validPANNumbers = new ArrayList<>(6);
    static ArrayList<String> invalidPANNumbers = new ArrayList<>(12);

    @BeforeClass
    public static void init() {
        validPANNumbers.add("GDWCU1962P");
        validPANNumbers.add("STAYM0303R");
        validPANNumbers.add("XATQC2232H");
        validPANNumbers.add("VDNZF1479O");
        validPANNumbers.add("ZWERC7356U");
        validPANNumbers.add("IUYZZ5954S");

        //Invalid structure PAN Numbers
        invalidPANNumbers.add("ZKBlC3773M");
        invalidPANNumbers.add("02OkO6P8NL");
        invalidPANNumbers.add("1XQ9HO6V8S");
        invalidPANNumbers.add("ABAB12345Y");

        //Invalid characters PAN Numbers
        invalidPANNumbers.add("avCDS1234Y");
        invalidPANNumbers.add("I$YZZ5954S");
        invalidPANNumbers.add("I$YZZ5954$");
        invalidPANNumbers.add("I\nYZZ5954S");
        invalidPANNumbers.add("I\nYZZ595••");
        invalidPANNumbers.add("I$YZZ5--4$");

        //Invalid length PAN Numbers
        invalidPANNumbers.add("I$YZZ54$");
        invalidPANNumbers.add("I$Y••••ZZ5--4$");

    }

    @Test
    public void isTestContentsLoaded() {
        assertTrue(validPANNumbers.size() > 5);
        assertTrue(invalidPANNumbers.size() > 10);
    }

    @Test
    public void validation_isCorrect() {

    }
}