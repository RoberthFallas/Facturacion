package org.una.tienda.facturacion.services;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;

@SpringBootTest
class ProductoPrecioServiceImplementationTest {

    @Autowired
    private IProductoPrecioService productoPrecioService;

    ProductoPrecioDTO productoPrecioEjemplo;

    @BeforeEach
    public void setup() {
        productoPrecioEjemplo = new ProductoPrecioDTO() {
            {
                setDescuentoMaximo(0.2);
                setDescuentoPromocional(0.5);
                setEstado(true);
                setPrecioColones(5000.0);

            }
        };
    }

    @Test
    public void sePuedeCrearUnPrecioDeUnProductoCorrectamente() {

        productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);

        Optional<ProductoPrecioDTO> productoEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());

        if (productoEncontrado.isPresent()) {
            ProductoPrecioDTO producto = productoEncontrado.get();
            assertEquals(productoPrecioEjemplo.getId(), producto.getId());

        } else {
            fail("No se encontro la información en la BD");
        }
    }

    @Test
    public void sePuedeModificarUnProductoCorrectamente() {

        productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);
        productoPrecioEjemplo.setPrecioColones(4000.0);
        productoPrecioService.update(productoPrecioEjemplo, productoPrecioEjemplo.getId());
        Optional<ProductoPrecioDTO> productoEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());

        if (productoEncontrado.isPresent()) {
            ProductoPrecioDTO producto = productoEncontrado.get();
            Assertions.assertEquals(productoPrecioEjemplo.getId(), producto.getId());
            Assertions.assertEquals(productoPrecioEjemplo.getPrecioColones(), producto.getPrecioColones());
            Assertions.assertEquals(productoPrecioEjemplo.getDescuentoMaximo(), producto.getDescuentoMaximo());
            Assertions.assertEquals(productoPrecioEjemplo.getDescuentoPromocional(), producto.getDescuentoPromocional());
        } else {
            fail("No se encontro la información en la BD");
        }
    }

    @Test
    public void sePuedeEliminarUnProductoCorrectamente() {
        productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);
        productoPrecioService.delete(productoPrecioEjemplo.getId());
        Optional<ProductoPrecioDTO> productoEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());

        if (productoEncontrado != null) {
            fail("El objeto no se ha eliminado de la BD");
        } else {
            productoPrecioEjemplo = null;
            Assertions.assertTrue(true);
        }
    }

    @AfterEach
    public void tearDown() {
        if (productoPrecioEjemplo != null) {
            productoPrecioService.delete(productoPrecioEjemplo.getId());
            productoPrecioEjemplo = null;
        }

    }
}
