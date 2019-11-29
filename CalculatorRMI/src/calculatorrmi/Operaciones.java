package calculatorrmi;

//package hello;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Operaciones extends Remote {
    double suma(double a,double b) throws RemoteException;
    double resta(double a,double b) throws RemoteException;
    double producto(double a,double b) throws RemoteException;
    double cociente(double a,double b) throws RemoteException;
    
    double nFibonacci(double a) throws RemoteException;
    BigInteger factorial(double a) throws RemoteException;
    
    double promedio(ArrayList<Double> conjunto) throws RemoteException;
    double varianza(ArrayList<Double> conjunto) throws RemoteException;
    double desviacion(ArrayList<Double> conjunto) throws RemoteException;
    
    
    Mensajes obtenerMensaje() throws RemoteException;
}
