/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorrwebapp;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;

/**
 *
 * @author brouwerto
 */
@Named
public class FizzBuzz {
    @SuppressWarnings("FieldMayBeFinal")
    private static Map<String, Integer> counts = new HashMap<>();
    
    public FizzBuzz() {}
    
    public String speak(String name) {
        int count = nextCountForName(name);
        String ret = "";
        if (count % 3 == 0) {
            ret += "Fizz";
        }
        if (count % 5 == 0) {
            ret += "Buzz";
        }
        return ret.isEmpty() ? "Hello" : ret;
    }

    private int nextCountForName(String name) {
        Integer count = counts.get(name);
        count = count == null ? 0 : count +1;
        counts.put(name, count);
        return count;
    }
    
}
