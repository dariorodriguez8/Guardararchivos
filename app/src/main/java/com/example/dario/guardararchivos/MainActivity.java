package com.example.dario.guardararchivos;

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

    Button mShowButton;
    Button mAddButton;
    TextView mText;
    EditText mEditText;
    FileOutputStream fos;
    String texto;
    String file = "fichero.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mShowButton = (Button) findViewById(R.id.btnShow);
        mAddButton = (Button) findViewById(R.id.btnAdd);
        mText = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);


        try {
            fos = openFileOutput(file, Context.MODE_APPEND);
            texto = fos.toString();
            Toast.makeText(this, "He leido el fichero", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void añadir(View v){



        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_APPEND);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes(mEditText.getText().toString() + "\n");
            fos.close();
            dos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "el Texto ha sido guardado", Toast.LENGTH_LONG).show();

    }



    public void mostrar(View v) {
        try {
            FileInputStream fi =  openFileInput(file);
            DataInputStream dis = new DataInputStream(fi);
            byte [] bytes = new byte[10000];
            dis.read(bytes);
            mText.setText(new String(bytes));
            fi.close();
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

}

}
