package co.com.gestion.clientes.servicio;

import co.com.gestion.clientes.model.Cliente;
import co.com.gestion.clientes.repositorio.ClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteServicio{

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServiceImpl(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Cliente saveCliente(Cliente cliente){
        return clienteRepositorio.save(cliente);
    }

    @Override
    public void deleteCliente(Long id){
        clienteRepositorio.deleteById(id);
    }

    @Override
    public List<Cliente> findAllClientes(){
        return clienteRepositorio.findAll();
    }

    @Override
    public Optional<Cliente> findAllClienteById(Long id){
        return clienteRepositorio.findById(id);
    }

    @Override
    public Cliente getClienteBySharedKey(String sharedKey){
        return clienteRepositorio.getClienteBySharedKey(sharedKey);
    }
}
