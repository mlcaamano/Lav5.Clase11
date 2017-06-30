package com.example.alumno.sockets;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Handler.Callback, Ilanzar {



    TextView textoIngresado;
    EditText editMail;
    Button btnSend;

    public static String mensajeAEnviar;
    private MiHiloSocket hilo;

    public MiHiloSocket getHilo() {
        return hilo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Handler h = new Handler(this);
        hilo = new MiHiloSocket(h);
        Thread miHiloSocket = new Thread(hilo);

        miHiloSocket.start();



        btnSend = (Button) this.findViewById(R.id.buttonSend);
        editMail= (EditText)  this.findViewById(R.id.editText);
       textoIngresado=(TextView) this.findViewById(R.id.textView);


        ListenerBtn list = new ListenerBtn(this);
        btnSend.setOnClickListener(list);

    }

    @Override
    public boolean handleMessage(Message msg) {

        String respServer = (String) msg.obj;

        textoIngresado.setText(respServer);

        Log.d("ConnServer",respServer);
        return true;
    }

    @Override
    public void mandar() {
        Log.d("Listener", editMail.getText().toString() );
        hilo.escribo(editMail.getText().toString());
    }


    //Esto si o si va en un Hilo, pq es una conexion a un server
   //Socket clienteSocket= new Socket("192.168.2.163",4097);

    // En android hay que hacer un chat

}



