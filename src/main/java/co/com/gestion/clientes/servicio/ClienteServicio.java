package co.com.gestion.clientes.servicio;


import co.com.gestion.clientes.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServicio {

    Cliente saveCliente(Cliente cliente);

    void deleteCliente(Long id);

    List<Cliente> findAllClientes();

    Optional<Cliente> findAllClienteById(Long id);

    Cliente getClienteBySharedKey(String sharedKey);
}
