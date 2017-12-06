package com.example.ignacio.neib;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Button btnBuscar, btnBorrar, btnGuardar, btnActualizar;
    EditText etId, etNombreN, etCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBorrar = (Button)findViewById(R.id.btnBorrar);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);

        etId = (EditText)findViewById(R.id.etId);
        etNombreN = (EditText)findViewById(R.id.etNombreN);
        etCiudad = (EditText)findViewById(R.id.etCiudad);

        final HelpBD AyudaBD = new HelpBD(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = AyudaBD.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(HelpBD.DATA.Columna_Id,etId.getText().toString());
                valores.put(HelpBD.DATA.Columna_Nombres,etNombreN.getText().toString());
                valores.put(HelpBD.DATA.Columna_Ciudad,etCiudad.getText().toString());

                Long IdGuardado = db.insert(HelpBD.DATA.TableName, HelpBD.DATA.Columna_Id, valores);
                Toast.makeText(getApplicationContext(), "Se Guardo el Dato: "+IdGuardado, Toast.LENGTH_LONG).show();

            }

        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = AyudaBD.getReadableDatabase();
                String[] argsel= {etId.getText().toString()};
                String[] projection = {
                        HelpBD.DATA.Columna_Nombres, HelpBD.DATA.Columna_Ciudad};
                        Cursor c = db.query(HelpBD.DATA.TableName, projection, HelpBD.DATA.Columna_Id+"=?",argsel,null,null,null);

                        c.moveToFirst();
                        etNombreN.setText(c.getString(0));
                        etCiudad.setText(c.getString(1));
            }

            })

        ;
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        SQLiteDatabase db = AyudaBD.getWritableDatabase();
                        String Selection = HelpBD.DATA.Columna_Id+"=?";
                        String[] argsel= {etId.getText().toString()};

                        db.delete(HelpBD.DATA.TableName,Selection,argsel);
            }

        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = AyudaBD.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(HelpBD.DATA.Columna_Nombres, etNombreN.getText().toString());
                valores.put(HelpBD.DATA.Columna_Ciudad, etCiudad.getText().toString());
                String[] argsel= {etId.getText().toString()};
                String Selection = HelpBD.DATA.Columna_Id+"=?";

                int count = db.update(HelpBD.DATA.TableName,valores,Selection,argsel);

            }

        });
    }
}
