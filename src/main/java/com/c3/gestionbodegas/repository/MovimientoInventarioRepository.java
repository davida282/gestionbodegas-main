package com.c3.gestionbodegas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.c3.gestionbodegas.entities.Bodega;
import com.c3.gestionbodegas.entities.MovimientoInventario;
import com.c3.gestionbodegas.entities.MovimientoInventario.TipoMovimiento;
import com.c3.gestionbodegas.entities.Usuario;


@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer>{

    
    // Buscar por tipo (ENTRADA, SALIDA, TRANSFERENCIA)
    List<MovimientoInventario> findByTipo(TipoMovimiento tipo);
    
    // Buscar por usuario responsable
    List<MovimientoInventario> findByUsuario(Usuario usuario);

    // Buscar movimientos entre un rango de fechas
    List<MovimientoInventario> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar movimientos por bodega origen o destino
    List<MovimientoInventario> findByBodegaOrigenOrBodegaDestino(Bodega bodegaOrigen, Bodega bodegaDestino);


    // Consulta para obtener los productos más movidos (para reportes)
    @Query("""
            SELECT dm.producto.id, COUNT(dm.id) AS cantidadMovimientos
            FROM DetalleMovimiento dm
            GROUP BY dm.producto.id
            ORDER BY cantidadMovimientos DESC
            """)
    List<Object[]> obtenerProductosMasMovidos();

    // Consulta para obtener los ultimos 10 movimientos registrados
    @Query("""
            SELECT mi.id 
            FROM MovimientoInventario mi
            WHERE mi.id BETWEEN 1 AND 10
            ORDER BY mi.id DESC;
        """)
    List<Object[]> obtenerUltimosRegistros();
    
}
