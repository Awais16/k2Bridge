/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package k2bjavaclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import k2Bridge.HandService;
import k2Bridge.Hands;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

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

        try {
            TTransport transport;
            if (true) {
                transport = new TSocket("localhost", 9090);
                transport.open();
            } else {
                /*
                 * Similar to the server, you can use the parameters to setup client parameters or
                 * use the default settings. On the client side, you will need a TrustStore which
                 * contains the trusted certificate along with the public key. 
                 * For this example it's a self-signed cert. 
                 */
                TSSLTransportParameters params = new TSSLTransportParameters();
                params.setTrustStore("../../lib/java/test/.truststore", "thrift", "SunX509", "JKS");
                /*
                 * Get a client transport instead of a server transport. The connection is opened on
                 * invocation of the factory method, no need to specifically call open()
                 */
                transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
            }

            TProtocol protocol = new TBinaryProtocol(transport);
            HandService.Client client = new HandService.Client(protocol);

            perform(client);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(HandService.Client client) throws TException {

        try {
            while (true) {
                Hands hand = client.getHands();
                System.out.println(hand.left.x);
                System.out.println(hand.left.y);
                System.out.println(hand.left.trackingState);
                Thread.sleep(10000);
            }

        } catch (TException ex) {
            System.out.println("Invalid operation: " + ex.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(K2BJavaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
