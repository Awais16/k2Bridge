/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package k2bjavaclient;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
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
