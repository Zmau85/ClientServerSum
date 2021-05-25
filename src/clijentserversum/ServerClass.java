package clijentserversum;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nemanja Koprivica
 */
public class ServerClass {

    public static void main(String[] args) throws InterruptedException {

        try (ServerSocket server = new ServerSocket(1100);) {
            
          
            Socket cltSocket = server.accept();
            System.out.println("Proba porta...");

            BufferedReader bis = new BufferedReader(new InputStreamReader(cltSocket.getInputStream()));
            String brojevi = bis.readLine();
            System.out.println("Pristigli brojevi: " + brojevi);
           
            
            String[] spliter = brojevi.split(" ");
            double zbir = 0;
            for (String s : spliter) {
                double temp = Double.parseDouble(s);
                zbir += temp;
            }
            System.out.println("Provera zbira: " + zbir);
            String zbirS = zbir + "";

            BufferedOutputStream bos = new BufferedOutputStream(cltSocket.getOutputStream());
            bos.write((zbirS + "\n").getBytes());
            bos.flush();
            
           

        } catch (IOException ex) {
            System.out.println("Greška u konekciji servera " + ex.getMessage());
        }

    }

}

/*            BufferedReader primanje = new BufferedReader(new InputStreamReader(sct.getInputStream()));
            
            String brojevi = primanje.readLine();
            String[] razdvajanje = brojevi.split(" ");
            int zbir = 0;
            for (int i = 0; i < razdvajanje.length; i++) {
            int temp = Integer.parseInt(razdvajanje[i]);
            zbir = +temp;
            }
            while (brojevi != null && !brojevi.equals("")) {
            System.out.println(brojevi);
            brojevi=primanje.readLine();
            }
            DataOutputStream slanje = new DataOutputStream(sct.getOutputStream());
            
            slanje.writeByte(zbir);
 */
