package Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Producto {

        @NonNull
        @PrimaryKey
        private String IDproducto;
        @NonNull
        @ColumnInfo(name = "Nombre")
        private String nombre;
        @NonNull
        @ColumnInfo(name = "Tipo_Producto")
        private String Tipo_Producto;
        @NonNull
        @ColumnInfo(name = "Cantidad")
        private int cantidad;
        @NonNull
        @ColumnInfo(name = "Precio")
        private String precio;


        public Producto() {

        }

        public Producto(@NonNull String IDproducto, @NonNull String nombre, @NonNull String Tipo_Producto, @NonNull int cantidad, @NonNull String precio) {
                this.IDproducto = IDproducto;
                this.nombre = nombre;
                this.Tipo_Producto = Tipo_Producto;
                this.cantidad = cantidad;
                this.precio = precio;
        }

        @NonNull
        public String getIDproducto() {
                return IDproducto;
        }

        public void setIDproducto(@NonNull String IDproducto) {
                this.IDproducto = IDproducto;
        }

        @NonNull
        public String getNombre() {
                return nombre;
        }

        public void setNombre(@NonNull String nombre) {
                this.nombre = nombre;
        }

        @NonNull
        public String getTipo_Producto() {
                return Tipo_Producto;
        }

        public void setTipo_Producto(@NonNull String tipo_Producto) {
                Tipo_Producto = tipo_Producto;
        }

        public int getCantidad() {
                return cantidad;
        }

        public void setCantidad(int cantidad) {
                this.cantidad = cantidad;
        }

        @NonNull
        public String getPrecio() {
                return precio;
        }

        public void setPrecio(@NonNull String precio) {
                this.precio = precio;
        }

        @Override
        public String toString() {
                return "=======/= P R O D U C T O =/=======\n" +
                        "ID Producto: "+ IDproducto +"\n"+
                        "Nombre: "+nombre+"\n"+
                        "Tipo: "+ Tipo_Producto +"\n"+
                        "Cantidad: "+cantidad+"\n"+
                        "Precio: "+ precio +"\n";
        }
}
