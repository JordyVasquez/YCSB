package com.yahoo.ycsb.db;

import java.io.IOException;



//Clase principal que hará uso del cliente
public class MainCliente2
{
    public static void main(String[] args) throws IOException
    {  
        ClienteKeyValueStore cli = new ClienteKeyValueStore(args[0],args[1]); 
        
        System.out.println("Iniciando cliente\n");
        cli.startClient(); //Se inicia el cliente
    }
}