package com.example.alumno.sockets;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


/**
 * Created by alumno on 29/06/2017.
 */

public class MiHiloSocket  implements Runnable {

    private String Url;
    private Handler miHandler;
    private String miMensaje;
    BufferedWriter bw;

    public void setMiMensaje(String miMensaje) {
        this.miMensaje = miMensaje;
    }

    public String getMiMensaje() {
        return miMensaje;
    }

    public MiHiloSocket(Handler h) {
        miHandler = h;
    }



    @Override
    public void run() {


        Message msg = new Message();



        try {
            Socket clienteSocket= new Socket("192.168.2.163",4097);

            OutputStream os = clienteSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);



                InputStream is = clienteSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);


            while(true)
            {
                String mensjStr = br.readLine();
                if(mensjStr == null)
                     break;

                msg.obj = mensjStr;
                miHandler.sendMessage(msg);
            }




        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void escribo(String mensaje)
    {
        try {
            bw.write(mensaje);
            Log.d("Hilo", mensaje);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}