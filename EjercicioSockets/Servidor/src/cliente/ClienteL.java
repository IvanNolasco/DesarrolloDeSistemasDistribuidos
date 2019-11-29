package cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import servidor.Tupla;

public class ClienteL extends JFrame {

    public static JTextField jtxt;
    public static JFrame myFrame;
    public static JTextArea jtxtArea;
    public static DefaultTableModel modeloTable;
    public static JTable table;

    public static void main(String args[]) throws UnknownHostException, IOException {
        myFrame = new JFrame();
        myFrame.setTitle("--");
        jtxtArea = new JTextArea();
        JButton btnUno = new JButton("Enviar request");

        jtxt = new JTextField("Consulta", 30);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new FlowLayout());
        myFrame.add(btnUno);
        myFrame.add(jtxt);
        jtxtArea.setText("--");
        myFrame.add(jtxtArea);

        String[] columns = new String[]{};

        Object[][] data = new Object[][]{};
        //create table with data
        modeloTable = new DefaultTableModel(data, columns);
        table = new JTable(modeloTable);

        //add the table to the frame
        JScrollPane panelTabla= new JScrollPane(table);
        panelTabla.setSize(800, 450);
        myFrame.add(panelTabla);

        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setSize(900, 650);

        btnUno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirSocket();
            }
        });

    }

    private static final int PUERTOESCUCHA = 6666;

    private void btnUnoAction(ActionEvent evt) {
        abrirSocket();
    }

    private static void abrirSocket() {
        Socket socket = null;
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            dtm.removeRow(0);
        }

        try {
            socket = new Socket("localhost", PUERTOESCUCHA);
            PrintStream salida = new PrintStream(socket.getOutputStream());
            salida.println(jtxt.getText()); // Se envía

            // Recibo de información
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            int k = 0;
            while (true) {

                Object objetoBase = (Object) ois.readObject();
                if (objetoBase instanceof ArrayList & k == 0) {

                    ArrayList<String> cabeceras = (ArrayList<String>) objetoBase;
                    dtm.setColumnIdentifiers(cabeceras.toArray());

                } else if (objetoBase instanceof ArrayList) {
                    ArrayList<String> atributos = (ArrayList<String>) objetoBase;
                    dtm = (DefaultTableModel) table.getModel();
                    dtm.addRow(atributos.toArray());
                }

                k++;
                if (objetoBase instanceof Integer) {
                    break;
                }
                salida.println("ya");
            }

            Object datos = ois.readObject();
            Tupla claseDatos = new Tupla();
            if (datos.getClass() == claseDatos.getClass()) {
                // Se carga la tabla
                //llenaTabla((Tupla) datos);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ee) {
            }
        }
    }


}
