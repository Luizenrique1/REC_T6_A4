package Controlador;

import java.util.List;

import Entidades.Producto;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DAO {

    @Query("SELECT * FROM Producto")
    List<Producto> getAll();

    @Query("SELECT * FROM Producto WHERE IDproducto = :nc")
    Producto buscarProducto(String nc);

    @Query("SELECT * FROM Producto WHERE IDproducto = :nc")
    List<Producto>  getAllbuscarAlumno(String nc);

    @Query("DELETE FROM Producto WHERE IDproducto = :nc")
    int eliminarAlumno(String nc);

    /*
    @Query("SELECT * FROM Producto WHERE uid IN (:userIds)")
    List<Producto> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Producto WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Producto findByName(String first, String last); */

    @Insert
    void insertAll(Producto... productos);

    @Delete
    void delete(Producto producto);

    @Update
    void update(Producto producto);



}
