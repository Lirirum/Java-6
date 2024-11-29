/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class UserCounterListener implements HttpSessionListener {

    private static final AtomicInteger activeUsers = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeUsers.incrementAndGet();
        System.out.println("Сесія створена. Активні користувачі: " + activeUsers.get());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeUsers.decrementAndGet();
        System.out.println("Сесія завершена. Активні користувачі: " + activeUsers.get());
    }

    public static int getActiveUsers() {
        return activeUsers.get();
    }
}



