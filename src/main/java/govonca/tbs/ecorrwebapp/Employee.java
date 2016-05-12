/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorrwebapp;
 
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Scope;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
@Named
public class Employee {
    @Inject
    FizzBuzz fizzBuzz;

    String name;
    String email;
 
    public String getName() {
   	 return fizzBuzz.speak() + " " + name;
    }
 
    public void setName(String name) {
   	 this.name = name;
    }
 
    public String getEmail() {
   	 return email;
    }
 
    public void setEmail(String email) {
   	 this.email = email;
    }
 
    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }
 
    public Employee() {
        
    }
}
