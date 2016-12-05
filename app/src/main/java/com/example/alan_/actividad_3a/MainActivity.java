package com.example.alan_.actividad_3a;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etEntrada;
    Button btAnyadir, btMostrar;
    TextView tvContenido;
    String FILE_NAME = "fichero.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEntrada = (EditText) this.findViewById(R.id.et_entrada);
        btAnyadir = (Button) this.findViewById(R.id.bt_anyadir);
        btMostrar = (Button) this.findViewById(R.id.bt_mostrar);
        tvContenido = (TextView) this.findViewById(R.id.tv_contenido);

        btAnyadir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                    DataOutputStream dos = new DataOutputStream(fos);
                    dos.writeBytes(etEntrada.getText().toString()+"\n");
                    fos.close();
                    etEntrada.setText("");
                    Toast.makeText(getApplicationContext(),"Guardado", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btMostrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = openFileInput(FILE_NAME);
                    DataInputStream dis = new DataInputStream(fis);
                    byte[] buff = new byte[1000];
                    dis.read(buff);
                    tvContenido.setText(new String(buff));
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
