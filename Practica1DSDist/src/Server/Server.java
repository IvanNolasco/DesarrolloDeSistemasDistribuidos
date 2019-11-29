package Server;

import Objetos.Cliente;
import Objetos.Cuenta;
import Objetos.OperacionesCuenta;
import ObjetosDAO.ClienteDAO;
import ObjetosDAO.CuentaDAO;
import ObjetosDAO.OperacioneaCuentaDAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server {

    public static void main(String[] args) {
        int port = 8001;
        try {
            ServerSocket serverSocket = new ServerSocket(port); //se crear un socketServer en el puerto 8000
            System.out.println("Listo para recibir peticiones d d d");

            Socket clienteSocket = serverSocket.accept();   //se acepta un sokcet de un cliente            
            System.out.println("ya acepté uno");

            ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream());//se instancia unflujo deObjeto de salida (para enviar objetos serializados a través de el)            

            Cliente cte = new Cliente();                      //estos objetos son "auxiliares"
            OperacionesCuenta opCta = new OperacionesCuenta();                   //o genericos, en el sentido que serán los objetos que envie el cliente
            Cuenta cta = new Cuenta();
            Object obtToSend = new Object();

            Double cantidad, cant2;
            Cuenta cuentaAuxiliar;
            System.out.println("walk around");
            while (true) {                                      //Siempre o for(ever)

                System.out.println("estoy esperando la opción");

                int opcion = (int) (ois.readObject());      //se lee el entero enviado por el cliente mas uno (porque alla es un comboBox)
                System.out.println("yaya");

                //oos.writeObject(ClienteDAO.leerCliente(cte.getIdcliente()));
                //ps.println("ya");                               //se le avisa al cliente para que ya envie lo que sigue
                Object objBase = ois.readObject();              //se lee un objeto, (generico) desde el socket o el cliente
                if (objBase instanceof Cuenta) {              //si el objeto es una instancia de Producto
                    cta = (Cuenta) objBase;                         //tal objeto se castea a Producto para dejarlo en p 
                
                }
                if (objBase instanceof OperacionesCuenta) {             //si el objeto es instancia de Categoria
                    opCta = (OperacionesCuenta) objBase;                      //se deja en cat
                }

                if (objBase instanceof Cliente) {
                    cte = (Cliente) objBase;
                    System.out.println(cte.toString());
                }

                switch (opcion) {                           //segun la opcion elegida desde el cliente
                    case 1:
                        obtToSend = ClienteDAO.leerCliente(cte.getIdcliente());//se inserta a la BD p
                        System.out.println(obtToSend.toString());
                        oos.writeObject(obtToSend);
                        System.out.println("opt1");
                                                
                        OperacioneaCuentaDAO.insertarOperacionCta(new OperacionesCuenta(Long.parseLong("0"), CuentaDAO.leerCuentasByCliente(cte.getIdcliente()).get(0), new Date(), 'C', 0));
                        break;
                    case 2:
                        obtToSend = CuentaDAO.leerCuentasByCliente(cte.getIdcliente());
                        oos.writeObject(obtToSend);
                        break;
                    case 3:
                        System.out.println("opt3");
                        cantidad=(Double)ois.readObject();
                        double ct2=cantidad;
                        cuentaAuxiliar=CuentaDAO.leerCuenta(cta.getIdcuenta());
                        cantidad+=cuentaAuxiliar.getBalance();
                        cta.setBalance(cantidad);

                        obtToSend = CuentaDAO.actualizarCuenta(cta);//se actualiza en la BD usando p 
                        oos.writeObject(obtToSend);                      //se envia respuesta al cliente usando el flujo de impresión, via socket
                        
                        OperacioneaCuentaDAO.insertarOperacionCta(new OperacionesCuenta(Long.parseLong("0"), CuentaDAO.leerCuentasByCliente(cte.getIdcliente()).get(0), new Date(), 'R', ct2));
                        break;
                    case 4:
                        System.out.println("opt3");
                        cantidad=(Double)ois.readObject();
                        Double ctt=cantidad;
                        cuentaAuxiliar=CuentaDAO.leerCuenta(cta.getIdcuenta());
                        cantidad=cuentaAuxiliar.getBalance()-cantidad;
                        System.out.println("Retirando: "+cantidad);
                        System.out.println(cta.toString());
                        System.out.println("Cuenta aux: "+cuentaAuxiliar.toString());
                        if(cantidad<=0 || cuentaAuxiliar.getCliente().getIdcliente()!=cta.getCliente().getIdcliente())
                        {
                            System.out.println("No puede hacerse la transacción");
                            JOptionPane.showMessageDialog(null, "No puede retirarse. \n Saldo inferior o cuenta no le pertenece");
                            oos.writeObject(false);
                            break;
                        }
                        cta.setBalance(cantidad);

                        obtToSend = CuentaDAO.actualizarCuenta(cta);//se actualiza en la BD usando p 
                        oos.writeObject(obtToSend);                      //se envia respuesta al cliente usando el flujo de impresión, via socket
                        
                        OperacioneaCuentaDAO.insertarOperacionCta(new OperacionesCuenta(Long.parseLong("0"), CuentaDAO.leerCuentasByCliente(cte.getIdcliente()).get(0), new Date(), 'T', ctt));
                        
                        break;
                    case 5:
                        System.out.println("opt3");
                        
                        Cuenta ctaADep=(Cuenta)ois.readObject();
                        
                        cantidad=(Double)ois.readObject();
                        
                        cant2=cantidad;
                        
                        
                        cuentaAuxiliar=CuentaDAO.leerCuenta(cta.getIdcuenta());
                        cantidad=cuentaAuxiliar.getBalance()-cantidad;
                        System.out.println("Retirando: "+cantidad);
                        System.out.println(cta.toString());
                        System.out.println("Cuenta aux: "+cuentaAuxiliar.toString());
                        if(cantidad<=0 || cuentaAuxiliar.getCliente().getIdcliente()!=cta.getCliente().getIdcliente())
                        {
                            System.out.println("No puede hacerse la transacción");
                            JOptionPane.showMessageDialog(null, "No puede retirarse. \n Saldo inferior o cuenta no le pertenece");
                            oos.writeObject(false);
                            break;
                        }
                        cta.setBalance(cantidad);

                        
                        
                        obtToSend = CuentaDAO.actualizarCuenta(cta);//se actualiza en la BD usando p 
                        
                        ctaADep=CuentaDAO.leerCuenta(ctaADep.getIdcuenta());
                        ctaADep.setBalance(cant2+ctaADep.getBalance());
                        CuentaDAO.actualizarCuenta(ctaADep);
                        
                        oos.writeObject(obtToSend);   
                        
                        OperacioneaCuentaDAO.insertarOperacionCta(new OperacionesCuenta(Long.parseLong("0"), CuentaDAO.leerCuentasByCliente(cte.getIdcliente()).get(0), new Date(), 'R', cant2));
                        OperacioneaCuentaDAO.insertarOperacionCta(new OperacionesCuenta(Long.parseLong("0"), ctaADep, new Date(), 'D', cant2)); 
                        break;                    
                    default:
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
