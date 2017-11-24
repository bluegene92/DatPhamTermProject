/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import model.Observer;

/**
 *
 * @author Dat
 */
public class FinishLine {
    
    private List<Observer> observerList = new ArrayList<>();
    int state = 0;
    public void render(Graphics2D g) {
        
    }
    
    public void changeState() {
        notifyAllObserver();
    }
    
    public void notifyAllObserver() {
        for (Observer o : observerList) {
            o.change();
        }
    }
    
}
