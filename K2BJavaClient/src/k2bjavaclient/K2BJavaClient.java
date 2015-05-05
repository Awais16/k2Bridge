/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k2bjavaclient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import k2Bridge.HandState;
import k2Bridge.Hands;
import k2bConnect.k2bClient;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author Awais Akhtar
 */
public class K2BJavaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        k2bClient client = new k2bClient();
         try {
         client.connect("localhost", 9090);
            
         while(true){
         Hands hands= client.getHands();
         System.out.println("leftX:"+hands.left.cameraSpaceX);
         System.out.println("leftY:"+hands.left.cameraSpaceY);
         System.out.println("state:"+hands.left.cameraSpaceZ);
         /*       
         System.out.println("rightX:"+hands.right.x);
         System.out.println("rightY:"+hands.right.y);
         System.out.println("state:"+hands.right.handState);
         */
         
              
           sleep(1000); //5sec
         }
            
         } catch (TTransportException ex) {
         Logger.getLogger(K2BJavaClient.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TException ex) {
         Logger.getLogger(K2BJavaClient.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InterruptedException ex) {
            Logger.getLogger(K2BJavaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       /* SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/

    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "
                + SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel pan= new MyPanel();
        f.add(pan);
        f.pack();
        f.setVisible(true);
        
    }
}

class HandFetcher extends Thread{
    
    public Hands hands;

    @Override
    public void run() {
        
        k2bClient client = new k2bClient();
         try {
         client.connect("localhost", 9090);
            
         while(true){
         client.getHands();
         System.out.println("leftX:"+hands.left.x);
         System.out.println("leftY:"+hands.left.y);
         System.out.println("state:"+hands.left.handState);
                
         System.out.println("rightX:"+hands.right.x);
         System.out.println("rightY:"+hands.right.y);
         System.out.println("state:"+hands.right.handState);
         
         
         sleep(5000); //5sec
         
         }
            
         } catch (TTransportException ex) {
         Logger.getLogger(K2BJavaClient.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TException ex) {
         Logger.getLogger(K2BJavaClient.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InterruptedException ex) {
         Logger.getLogger(K2BJavaClient.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
}


class MyPanel extends JPanel {
    
    private int squareW = 20;
    private int squareH = 20;
    Hands hands=null;

    public MyPanel() {
        //hands=new Hands();
        setBorder(BorderFactory.createLineBorder(Color.black));
        HandFetcher hf=new HandFetcher();
        hf.start();
        //hands=hf.hands;
        
    }

    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("This is my custom Panel!", 10, 20);
        g.setColor(Color.RED);
        
        //g.fillRect(Integer.parseInt(hands.left.x+""),Integer.parseInt(hands.left.y+""),squareW,squareH);
        //g.setColor(Color.BLACK);
        //g.drawRect(squareX,squareY,squareW,squareH);
    }
}
