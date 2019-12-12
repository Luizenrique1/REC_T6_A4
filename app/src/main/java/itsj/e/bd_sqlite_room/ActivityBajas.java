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

public class ActivityBajas extends Activity {

    Spinner SpinnerTipo;
    EditText txtIdProducto, txtNombre, txtPrecio, txtcantidad, txtBuscar;
    Button botonEliminar, botonCancelar, botonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        SpinnerTipo = findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Tipo_de_Producto, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerTipo.setAdapter(adapterEdad);


        txtIdProducto = findViewById(R.id.txt_IdProdutoBajas);
        txtNombre = findViewById(R.id.txt_nombreB);
        txtPrecio = findViewById(R.id.txt_Precio);
        txtcantidad = findViewById(R.id.txt_cantidad);
        txtBuscar = findViewById(R.id.txt_buscarC);

        botonEliminar = findViewById(R.id.btn_eliminarB);
        botonCancelar = findViewById(R.id.btn_cancelarB);
        botonBuscar = findViewById(R.id.btn_searchC);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BodegaBD bd = Room.databaseBuilder(getApplicationContext(), BodegaBD.class, "Bodega").allowMainThreadQueries().build();

                String buscar = txtBuscar.getText().toString();
                Producto al = bd.DAO().buscarProducto(buscar);

                if( al != null ) {
                    Log.i("Activity Bajas", "Se están llenando");
                    txtIdProducto.setText(al.getIDproducto());
                    txtNombre.setText(al.getNombre());
                    String p=al.getPrecio();
                    txtPrecio.setText(p, TextView.BufferType.EDITABLE);
                    p=al.getCantidad()+"";
                    txtcantidad.setText(p, TextView.BufferType.EDITABLE);

                    int posicion = 0;
                    for (int i = 0; i < SpinnerTipo.getCount(); i++) {
                        if (SpinnerTipo.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(al.getTipo_Producto())) ) {
                            posicion = i;
                        }
                    }
                    SpinnerTipo.setSelection(posicion);
                    Toast.makeText(ActivityBajas.this, "Datos cargados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityBajas.this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    Log.i("Activity Bajas", "No se encontró resultado");
                }

            }
        });
        
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BodegaBD bd = Room.databaseBuilder(getApplicationContext(), BodegaBD.class, "Bodega").allowMainThreadQueries().build();

                if(bd.DAO().eliminarAlumno(txtIdProducto.getText().toString()) != 0 ) {
                    Log.i("ActivityBajas", "Si se pudo");
                    txtIdProducto.setText("");
                    txtBuscar.setText("");
                    txtNombre.setText("");
                    txtPrecio.setText("");
                    txtcantidad.setText("");
                    SpinnerTipo.setSelection(0);
                    Toast.makeText(ActivityBajas.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Activity Bajas", "No se pudo");
                    Toast.makeText(ActivityBajas.this, "No se pudo eliminar el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        
    }

}
