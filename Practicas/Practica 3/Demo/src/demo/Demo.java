/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import entity.Alumno;

/**
 *
 * @author MAYRA
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Actions act = new Actions();
        Alumno a = new Alumno("2016630551","Mayra","Hernandez","Oseguera","mayrasho@hotmail.com");
        
        act.crear(a);
    }
    
}
