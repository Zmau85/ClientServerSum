package clijentserversum;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Nemanja Koprivica
 */
public class ClientClass {

    public static void main(String[] args) throws InterruptedException {

        Frame frame = new Frame("Sabiranje dva broja");
        //LayoutManager lm = new FlowLayout();
        Label l1 = new Label("Unesite prvi broj");
        Label l2 = new Label("Unesite drugi broj");
        Label l3 = new Label("Zbir brojeva je:");
        Label l4 = new Label();
        Label l5 = new Label("Možete sabirati samo cele, ili decimalne brojeve!!!");
        TextField tx1 = new TextField();
        TextField tx2 = new TextField();
        Button btn = new Button("Saberi");

        l1.setBounds(30, 50, 180, 30);
        l2.setBounds(220, 50, 180, 30);
        l3.setBounds(230, 160, 180, 30);
        l4.setBounds(230, 200, 180, 50);
        l5.setBounds(50, 140, 300, 20);
        tx1.setBounds(30, 100, 180, 30);
        tx2.setBounds(220, 100, 180, 30);
        btn.setBounds(30, 190, 150, 30);

        l1.setAlignment(1);
        l2.setAlignment(1);
        l3.setAlignment(1);
        l4.setAlignment(1);
        l4.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setForeground(Color.RED);
        l5.setVisible(false);
        
        

        btn.addActionListener((ActionEvent ae) -> {
            try (Socket client = new Socket("localhost", 1100);) {

                BufferedReader bis = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());

                String br1 = tx1.getText();
                String br2 = tx2.getText();
                
                bos.write((br1 + " ").getBytes());
                bos.write((br2 + "\n").getBytes());
                bos.flush();
                String zbir = (String) bis.readLine();
                l3.setVisible(true);
                l4.setVisible(true);
                l4.setText(zbir);

                /*BufferedReader bisR = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String zbir = (String) bisR.readLine();
                System.out.println("Pristigli zbir: " + zbir);*/
            } catch (IOException ex) {
                System.out.println("Greška u konekciji sa serverom " + ex.getMessage());
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        frame.setLayout(null);
        frame.add(l1);
        frame.add(l2);
        frame.add(tx1);
        frame.add(tx2);
        frame.add(btn);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);

        frame.setSize(500, 300);
        frame.setVisible(true);
    }

}
