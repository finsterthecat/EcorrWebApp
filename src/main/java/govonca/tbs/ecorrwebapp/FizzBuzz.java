/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorrwebapp;

import javax.inject.Named;

/**
 *
 * @author brouwerto
 */
@Named
public class FizzBuzz {
    private static int count = 0;
    
    public FizzBuzz() {}
    
    public String speak() {
        count +=1;
        String ret = "";
        if (count % 3 == 0) {
            ret += "Fizz";
        }
        if (count % 5 == 0) {
            ret += "Buzz";
        }
        if (ret.isEmpty()) {
            ret = "Hello";
        }
        return ret;
    }
    
}
