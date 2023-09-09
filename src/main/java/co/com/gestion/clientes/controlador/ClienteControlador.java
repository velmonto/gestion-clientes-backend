package co.com.gestion.clientes.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.gestion.clientes.model.Cliente;
import co.com.gestion.clientes.repositorio.ClienteRepositorio;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClienteControlador {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	/**Este metodo sirve para listar todos los clientes*/
	@GetMapping("/clientes")
	public List<Cliente> listarTodosLosClientes(){
		return clienteRepositorio.findAll();
	}
	
	@PostMapping("/clientes")
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
}
