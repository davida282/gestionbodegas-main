package com.c3.gestionbodegas.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.c3.gestionbodegas.entities.Bodega;
import com.c3.gestionbodegas.entities.MovimientoInventario;
import com.c3.gestionbodegas.entities.MovimientoInventario.TipoMovimiento;
import com.c3.gestionbodegas.entities.Usuario;
import com.c3.gestionbodegas.services.MovimientoInventarioService;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin("*")
public class ReporteControllerTest {
    @Autowired
    private MovimientoInventarioService movimientoService;

    // Filtrar movimientos por tipo
    @GetMapping("/tipo/{tipo}")
    public List<MovimientoInventario> buscarPorTipo(@PathVariable TipoMovimiento tipo) {
        return movimientoService.buscarPorTipo(tipo);
    }
}
