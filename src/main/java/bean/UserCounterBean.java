/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("userCounterBean")
@SessionScoped 
public class UserCounterBean  implements Serializable{

    public int getActiveUsers() {
        return UserCounterListener.getActiveUsers();
    }
}




