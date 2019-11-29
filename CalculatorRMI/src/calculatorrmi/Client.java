package calculatorrmi;

//package hello;
import java.awt.HeadlessException;
import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Client {

    private Client() {
    }

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            host = "localhost";
            Registry registry = LocateRegistry.getRegistry(host);
            double respuesta = 0;
            BigInteger respuestaDelFactorial;
            System.out.println("host: " + host);
//tambi�n puedes usar getRegistry(String host, int port)

            Operaciones stub = (Operaciones) registry.lookup("Calculator");
            int option = Integer.parseInt(JOptionPane.showInputDialog("1) Suma 2)Resta 3) Producto 4) Cociente 5) nFibonacci 6)Factorial \n 7) Media 8) Varianza 9) Desviación"));
            if (option < 7) {
                double x = Integer.parseInt(JOptionPane.showInputDialog("valor para x:"));
                double y = Integer.parseInt(JOptionPane.showInputDialog("valor para y:"));
                switch (option) {
                    case 1:
                        respuesta = stub.suma(x, y);
                        break;
                    case 2:
                        respuesta = stub.resta(x, y);
                        break;
                    case 3:
                        respuesta = stub.producto(x, y);
                        break;
                    case 4:
                        respuesta = stub.cociente(x, y);
                        break;
                    case 5:
                        respuesta = stub.nFibonacci(x);
                        break;
                    case 6:
                        respuestaDelFactorial = stub.factorial(x);
                        JOptionPane.showMessageDialog(null, "respuesta =" + respuestaDelFactorial);
                        System.out.println("respuesta =" + respuestaDelFactorial);
                        break;
                }

            } else {
                String cadenaA = JOptionPane.showInputDialog("Conjunto a:");
                String[] cjtoA = cadenaA.split(",");
                ArrayList<Double> conjuntoA = new ArrayList<>();
                for (int i = 0; i < cjtoA.length; i++) {
                    conjuntoA.add(Double.parseDouble(cjtoA[i]));
                }

                switch (option) {
                    case 7:
                        respuesta = stub.promedio(conjuntoA);
                        break;
                    case 8:
                        respuesta = stub.varianza(conjuntoA);
                        break;
                    case 9:
                        respuesta = stub.desviacion(conjuntoA);
                        break;

                }
            }
            JOptionPane.showMessageDialog(null, "respuesta =" + respuesta);
            System.out.println("respuesta = " + respuesta);
        } catch (HeadlessException | NumberFormatException | NotBoundException | RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
