package org.una.tienda.facturacion.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FacturaDetalleServiceImplementationTest {

    @Autowired
    private IFacturaDetalleService facturaDetalleService;

    FacturaDetalleDTO facturaDetalleEjemplo;

    @BeforeEach
    public void setup() {
        facturaDetalleEjemplo = new FacturaDetalleDTO() {
            {
              setCantidad(2);
              setDescuento_final(0.10);
            }
        };
    }

    @Test
    public void sePuedeCrearUnFacturaDetalleCorrectamente() {

        facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);

        Optional<FacturaDetalleDTO> FacturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());

        if (FacturaDetalleEncontrado.isPresent()) {
            FacturaDetalleDTO FacturaDetalle = FacturaDetalleEncontrado.get();
            assertEquals(facturaDetalleEjemplo.getId(), FacturaDetalle.getId());

        } else {
            fail("No se encontro la información en la BD");
        }
    }

    @AfterEach
    public void tearDown() {
        if (facturaDetalleEjemplo != null) {
            facturaDetalleService.delete(facturaDetalleEjemplo.getId());
            facturaDetalleEjemplo = null;
        }

    }

    @Test
    public void sePuedeModificarUnFacturaDetalleCorrectamente() {
        facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
        facturaDetalleEjemplo.setCantidad(10);

        facturaDetalleService.update(facturaDetalleEjemplo);
        Optional<FacturaDetalleDTO> facturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());

        if (facturaDetalleEncontrado.isPresent()) {
            FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrado.get();
            Assertions.assertEquals(facturaDetalleEjemplo.getId(), facturaDetalle.getId());
            Assertions.assertEquals(facturaDetalleEjemplo.getCantidad(), facturaDetalle.getCantidad());
        } else {
            fail("No se encontro la información en la BD");
        }
    }

    @Test
    public void sePuedeEliminarUnFacturaDetalleCorrectamente() {

        facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);

        facturaDetalleService.delete(facturaDetalleEjemplo.getId());

        Optional<FacturaDetalleDTO> facturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());

        if (facturaDetalleEncontrado != null) {
            fail("El objeto no se ha eliminado de la BD");
        }else{
            facturaDetalleEjemplo = null;
            Assertions.assertTrue(true);
        }


    }


}