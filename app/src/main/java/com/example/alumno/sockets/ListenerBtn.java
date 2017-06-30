package com.example.alumno.sockets;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by alumno on 29/06/2017.
 */

public class ListenerBtn implements View.OnClickListener  {

    Ilanzar l;

    public ListenerBtn(Ilanzar l) {
        this.l = l;
    }

    @Override
    public void onClick(View v) {
        l.mandar();
    }

    //public String TextoAEnviar()
  //  {
    //  return   editText.getText().toString();
    //}
}
