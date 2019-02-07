package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Peer {
    /**
     * Constructor. Creates and connects the socket to the
     * specified server.
     *
     * @param address Server address.
     * @param port Server port.
     * @throws IOException when bad things happen to the streams
     */
    public Client (String address, int port) throws IOException {

        sock = new Socket(address, port);

        out = new DataOutputStream(sock.getOutputStream());
        input = new DataInputStream(sock.getInputStream());

    }
}
