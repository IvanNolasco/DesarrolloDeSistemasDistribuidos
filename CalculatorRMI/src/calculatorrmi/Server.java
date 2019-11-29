package calculatorrmi;

//package hello;
import java.math.BigInteger;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements Operaciones {

    public Server() {
    }

    @Override
    public double suma(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double resta(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double producto(double a, double b) throws RemoteException {
        return a * b;
    }

    @Override
    public double cociente(double a, double b) throws RemoteException {
        return a / b;
    }

    @Override
    public Mensajes obtenerMensaje() {
        Mensajes men = new Mensajes("nada de mensajes vía RMI", 1);
        return men;
    }

    @Override
    public double nFibonacci(double n) throws RemoteException {
        if (n > 1) {
            return nFibonacci(n - 1) + nFibonacci(n - 2);
        } else if (n == 1) {
            return 1;
        } else if (n == 0) {
            return 0;
        } else { //error
            System.err.print("Debes ingresar un tamaño mayor o igual a 1");
            return -1;
        }
    }

    @Override
    public double promedio(ArrayList<Double> conjunto) throws RemoteException {
        double suma = 0;
        for (int i = 0; i < conjunto.size(); i++) {
            suma += conjunto.get(i);
        }

        suma /= conjunto.size();
        return suma;
    }

    @Override
    public double varianza(ArrayList<Double> conjunto) throws RemoteException {
        double m = promedio(conjunto);
        double sum = 0;
        for (int i = 0; i < conjunto.size(); i++) {
            sum += Math.pow(conjunto.get(i), 2.0);
        }

        return sum / conjunto.size() - Math.pow(m, 2.0);
    }

    @Override
    public double desviacion(ArrayList<Double> conjunto) throws RemoteException {
        double prom, sum = 0;
        int i, n = conjunto.size();
        prom = promedio(conjunto);

        for (i = 0; i < n; i++) {
            sum += Math.pow(conjunto.get(i) - prom, 2);
        }

        return Math.sqrt(sum / (double) n);
    }

    @Override
    public BigInteger factorial(double a) throws RemoteException {
        BigInteger f = (BigInteger.ONE);
        for (int i = 1; i <= a; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        System.out.println(f);
        return f;
    }

    public static void main(String args[]) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099); //puerto default del rmiregistry
            System.out.println("RMI registry ready.");
        } catch (Exception e) {
            System.out.println("Exception starting RMI registry:");
            e.printStackTrace();
        }//catch

        try {//esto es lo del classpath
            System.setProperty("java.rmi.server.codebase", "http://127.0.0.1/clases/"); ///file:///f:\\redes2\\RMI\\RMI2
            Server obj = new Server();
            Operaciones stub = (Operaciones) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Calculator", stub);


            System.err.println("Servidor listo...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
