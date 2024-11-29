/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;
import jakarta.ejb.Singleton;
import jakarta.ejb.Schedule;
import jakarta.ejb.Timer;
import java.time.LocalDateTime;

@Singleton
public class TimerBean {

    @Schedule(hour = "*", minute = "*", second = "*/10", persistent = false) 
    public void executeTimer(Timer timer) {
        System.out.println("Таймер спрацював! Поточний час: " + LocalDateTime.now());
    }
}



