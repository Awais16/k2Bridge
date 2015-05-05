/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k2bConnect;

import k2Bridge.HandService;
import k2Bridge.Hands;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author Awais Akhtar
 */
public class k2bClient {

    private TTransport transport;
    private TProtocol protocol;
    private HandService.Client client;

    public void connect(String host, int port) throws TTransportException {
        //simple connection, ignore ssl for now
        if (true) {
            transport = new TSocket(host, port);
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
            transport = TSSLTransportFactory.getClientSocket(host, port, 0, params);
        }

        TProtocol protocol = new TBinaryProtocol(transport);
        client = new HandService.Client(protocol);
    }

    public Hands getHands() throws TException {
        return client.getHands();
    }

    public void disconnect() { //
        if (transport != null) {
            transport.close();
        }
    }

}
