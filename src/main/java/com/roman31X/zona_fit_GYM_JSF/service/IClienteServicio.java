package com.roman31X.zona_fit_GYM_JSF.service;

import com.roman31X.zona_fit_GYM_JSF.model.Cliente;
import java.util.List;

public interface IClienteServicio {

    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer idCliente);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);


}
