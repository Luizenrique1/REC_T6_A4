package itsj.e.bd_sqlite_room;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Entidades.Producto;
import androidx.room.Room;

import bd_room.BodegaBD;

public class ActivityCambios extends Activity {

    Spinner spinnerTipo;
    EditText txtIdProducto, txtNombre, txtPrecio, txtcantidad, txtBuscar;
    Button botonModificar, botonCancelar, botonBuscar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        spinnerTipo = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Tipo_de_Producto, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapterEdad);


        txtIdProducto = findViewById(R.id.txt_numControlC);
        txtNombre = findViewById(R.id.txt_nombreC);
        txtPrecio = findViewById(R.id.txt_precioCam);
        txtcantidad = findViewById(R.id.txtcantidadca);
        txtBuscar = findViewById(R.id.txt_buscarC);

        botonModificar = findViewById(R.id.btn_modificar2);
        botonCancelar = findViewById(R.id.btn_cancelarC);
        botonBuscar = findViewById(R.id.btn_searchC);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BodegaBD bd = Room.databaseBuilder(getApplicationContext(), BodegaBD.class, "Bodega").allowMainThreadQueries().build();

                String buscar = txtBuscar.getText().toString();
                Producto al = bd.DAO().buscarProducto(buscar);

                if( al != null ) {
                    Log.i("Activity Cambios", "Se están llenando");
                    txtIdProducto.setText(al.getIDproducto());
                    txtNombre.setText(al.getNombre());
                    String p=al.getPrecio();
                    txtPrecio.setText(p,TextView.BufferType.EDITABLE);
                    p=al.getCantidad()+"";
                    txtcantidad.setText(p, TextView.BufferType.EDITABLE);
                    int posicion = 0;
                    for (int i = 0; i < spinnerTipo.getCount(); i++) {
                        if (spinnerTipo.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(al.getTipo_Producto())) ) {
                            posicion = i;
                        }
                    }
                    spinnerTipo.setSelection(posicion);
                    Toast.makeText(ActivityCambios.this, "Datos cargados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityCambios.this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    Log.i("Activity Bajas", "No se encontró resultado");
                }

            }
        });
    }

    public void Modificar(View v) {
        BodegaBD bd = Room.databaseBuilder(getApplicationContext(), BodegaBD.class, "Bodega").allowMainThreadQueries().build();
        Producto a = new Producto(txtIdProducto.getText().toString(), txtNombre.getText().toString(),spinnerTipo.getSelectedItem().toString(), Integer.parseInt(txtcantidad.getText().toString()), txtPrecio.getText().toString());

        bd.DAO().update(a);
        Toast.makeText(ActivityCambios.this, "Registro Modificado", Toast.LENGTH_SHORT).show();
        txtIdProducto.setText("");
        txtBuscar.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtcantidad.setText("");
        spinnerTipo.setSelection(0);

    }

}
