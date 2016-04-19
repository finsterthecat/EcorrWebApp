/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorrwebapp;
 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping("employees")
public class EmployeeController {
 
    Employee employee = new Employee();
 
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public Employee getEmployeeInJSON(@PathVariable String name) {
 
   	 employee.setName(name);
   	 employee.setEmail("employee1@govonca.com");
 
   	 return employee;
 
    }
 
    @RequestMapping(value = "/{name}.xml", method = RequestMethod.GET, produces = "application/xml")
    public Employee getEmployeeInXML(@PathVariable String name) {
 
   	 employee.setName(name);
   	 employee.setEmail("employee1@govonca.com");
 
   	 return employee;
 
    }
 
}
