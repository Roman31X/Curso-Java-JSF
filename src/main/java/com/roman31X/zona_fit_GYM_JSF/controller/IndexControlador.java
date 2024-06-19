package com.roman31X.zona_fit_GYM_JSF.controller;

import com.roman31X.zona_fit_GYM_JSF.model.Cliente;
import com.roman31X.zona_fit_GYM_JSF.service.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component      // Para incluir en la construcción de Spring
@Data           // Getter y Setter como constructores
@ViewScoped     // Indicando solo disponible para una vista en una sola página
public class IndexControlador {

    @Autowired
    IClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @PostConstruct  // Terminar de inicializar características después del constructor
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.clientes = this.clienteServicio.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

}
