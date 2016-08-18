package com.yahoo.ycsb.db;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

public class ClienteKeyValueStore extends Conexion {

    PrintWriter out = null;
    BufferedReader in = null;

    public ClienteKeyValueStore(String args1, String args2) throws IOException {
        super("cliente", args1, args2);
    }

    public void closedClient() throws IOException {
        out.close();
        in.close();
        cs.close();//Fin de la conexión
    }

    public void startClient() 
    {

        try {
            out = new PrintWriter(cs.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            String fromServer;
            if ((fromServer = in.readLine()) != null) {
            }

            System.out.println("se ha conectado");
                           // out.println(comandoscl);

        } catch (Exception e) {
            System.out.println("se termino la ejecucion");
        }

    }
}
