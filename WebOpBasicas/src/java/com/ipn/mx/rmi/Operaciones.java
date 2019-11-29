package com.ipn.mx.rmi;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Operaciones extends Remote{
    public double sumar(double x, double y) throws RemoteException;
    public double restar(double x, double y) throws RemoteException;
    public double producto(double x, double y) throws RemoteException;
    public double cociente(double x, double y) throws RemoteException;
    
    double nFibonacci(double a) throws RemoteException;
    BigInteger factorial(double a) throws RemoteException;
    
    double promedio(ArrayList<Double> conjunto) throws RemoteException;
    double varianza(ArrayList<Double> conjunto) throws RemoteException;
    double desviacion(ArrayList<Double> conjunto) throws RemoteException;
    
}
