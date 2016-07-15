/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorr.util;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author brouwerto
 */
@Named
public class FizzBuzz {

    @SuppressWarnings("FieldMayBeFinal")
    private static Map<String, Integer> counts = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(FizzBuzz.class.getName());

    public FizzBuzz() {
    }

    public String speak(String name) {
        int count = nextCountForName(name);
        String ret = (count % 3 == 0) ? "Fizz" : "";
        if (count % 5 == 0) {
            ret += "Buzz";
        }
        LOG.debug("Speak!: {}", ret);
        return ret.isEmpty() ? "Hello" : ret;
    }

    private int nextCountForName(String name) {
        Integer count = counts.get(name);
        count = count == null ? 0 : count + 1;
        counts.put(name, count);
        LOG.debug("Count! = {}", count);
        return count;
    }

}
