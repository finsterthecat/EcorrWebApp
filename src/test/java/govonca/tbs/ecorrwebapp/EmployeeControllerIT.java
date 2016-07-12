/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorrwebapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brouwerto
 */
public class EmployeeControllerIT {
    
    public EmployeeControllerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEmployeeInJSON method, of class EmployeeController.
     */
    @Test
    public void testGetEmployeeInJSON() {
        System.out.println("getEmployeeInJSON");
        String name = "";
        EmployeeController instance = new EmployeeController();
        Employee expResult = null;
        Employee result = instance.getEmployeeInJSON(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeInXML method, of class EmployeeController.
     */
    @Test
    public void testGetEmployeeInXML() {
        System.out.println("getEmployeeInXML");
        String name = "";
        EmployeeController instance = new EmployeeController();
        Employee expResult = null;
        Employee result = instance.getEmployeeInXML(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
