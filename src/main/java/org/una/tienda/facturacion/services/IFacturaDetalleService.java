package org.una.tienda.facturacion.services;

import org.una.tienda.facturacion.dto.FacturaDetalleDTO;

import java.util.Optional;

public interface IFacturaDetalleService {
    public FacturaDetalleDTO create(FacturaDetalleDTO facturaDetalleDTO);

    public Optional<FacturaDetalleDTO> update(FacturaDetalleDTO facturaDetalleDTO);

    public void delete(Long id);

    public Optional<FacturaDetalleDTO> findById(Long id);
}
