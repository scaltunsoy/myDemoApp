package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class AppTest {
    @Test
    public void computeAbsolute() {
        Integer[] array = {new Integer(2), new Integer(7)}; //locations
        ArrayList list = new ArrayList(Arrays.asList(17, 6, 27)); //values
        int result = App.compute(array, list, "sametcan", "altunsoy");
        assertEquals(406, result);
    }
    
    @Test
    public void biggerLocation() {
        Integer[] array = {new Integer(2), new Integer(7)}; //locations
        ArrayList list = new ArrayList(Arrays.asList(1, 2, 3)); //values
        int result = App.compute(array, list, "samet", "can");
        assertEquals(-1, result);
    }
    
    @Test
    public void negativeValues() {
        Integer[] array = {new Integer(2), new Integer(7)}; //locations
        ArrayList list = new ArrayList(Arrays.asList(1, 2, -5)); //values
        int result = App.compute(array, list, "sametcan", "altunsoy");
        assertEquals(-1, result);
    }
    
    @Test
    public void emptyParams() {
        int result = App.compute(new Integer[0], new ArrayList<Integer>(), "", "");
        assertEquals(-1, result);
    }
    
    @Test
    public void nullParams() {
        int result = App.compute(null, null, null, null);
        assertEquals(-1, result);
    }
}
