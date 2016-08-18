/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yahoo.ycsb.db;

import com.yahoo.ycsb.ByteIterator;
import com.yahoo.ycsb.DB;
import com.yahoo.ycsb.DBException;
import com.yahoo.ycsb.Status;
import com.yahoo.ycsb.StringByteIterator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emele_000
 */
public class Client extends DB {

    private ClienteKeyValueStore cli;

    public static final String HOST_PROPERTY = "KeyValueStore.host";
    public static final String PORT_PROPERTY = "KeyValueStore.port";

    public void init() throws DBException {
        try {
            Properties props = getProperties();
            String port;

            String portString = props.getProperty(PORT_PROPERTY);
            if (portString != null) {
                port = portString;
            } else {
                port = "1000";
            }
            String host = props.getProperty(HOST_PROPERTY);
            if (host != null) {
                host = "localhost";
            }
            cli = new ClienteKeyValueStore(host, port);
            cli.startClient();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cleanup() throws DBException {
        try {
            cli.closedClient();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Calculate a hash for a key to store it in an index. The actual return value
     * of this function is not interesting -- it primarily needs to be fast and
     * scattered along the whole space of doubles. In a real world scenario one
     * would probably use the ASCII values of the keys.
     */
    private double hash(String key) {
        return key.hashCode();
    }

    @Override
    public Status read(String table, String key, Set<String> fields, HashMap<String, ByteIterator> result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Status scan(String table, String startkey, int recordcount, Set<String> fields, Vector<HashMap<String, ByteIterator>> result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Status update(String table, String key, HashMap<String, ByteIterator> values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Status insert(String table, String key, HashMap<String, ByteIterator> values) {
        try {
            String comando="[put, " + key + ", " + StringByteIterator.getStringMap(values) + "]";
            cli.out.println(comando);
            String respuesta;
            while (((respuesta = cli.in.readLine()) != null)) {
                System.out.println(comando);
                System.out.println(respuesta);

                return Status.OK;
            }
            return Status.ERROR;
        } catch (IOException ex) {

            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return Status.ERROR;
        }

    }

    @Override
    public Status delete(String table, String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
