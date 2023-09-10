package co.com.gestion.clientes.controlador;

import co.com.gestion.clientes.model.Cliente;
import co.com.gestion.clientes.repositorio.ClienteRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        cliente.setId();
    }

    @Test
    void listarTodosLosClientes() {
        when(clienteControlador.listarTodosLosClientes()).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteControlador.listarTodosLosClientes());
    }
}