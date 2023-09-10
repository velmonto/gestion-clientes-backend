package co.com.gestion.clientes.controlador;

import co.com.gestion.clientes.model.Cliente;
import co.com.gestion.clientes.repositorio.ClienteRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClienteControladorTest {

    @Mock
    private ClienteRepositorio clienteRepositorio;

    @InjectMocks
    private ClienteControlador clienteControlador;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        cliente.setEmail("Davemon18@gmnail.com");
        cliente.setPhone("3219876543");
        cliente.setBusinessId("Daniel Velasquez");
        cliente.setSharedKey("DVelasquez");
        cliente.setId(Long.parseLong("1"));
    }

    @Test
    void listarTodosLosClientes() {
        when(clienteControlador.listarTodosLosClientes()).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteControlador.listarTodosLosClientes());
    }

    @Test
    void guardarCliente() {
        when(clienteControlador.guardarCliente(cliente)).thenReturn(cliente);
        assertNotNull(clienteControlador.guardarCliente(cliente));
    }

    @Test
    void obtenerClientePorId() {
        when(clienteControlador.obtenerClientePorId(cliente.getId())).thenReturn(ResponseEntity.ok(cliente));
        assertNotNull(clienteControlador.obtenerClientePorId(cliente.getId()));
    }

    @Test
    void actualizarCliente() {
        when(clienteControlador.actualizarCliente(cliente.getId(), cliente)).thenReturn(ResponseEntity.ok(cliente));
        assertNotNull(clienteControlador.actualizarCliente(cliente.getId(), cliente));
    }

    @Test
    void eliminarCliente() {
    }
}