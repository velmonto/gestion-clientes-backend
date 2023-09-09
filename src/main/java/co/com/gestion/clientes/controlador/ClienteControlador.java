package co.com.gestion.clientes.controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.gestion.clientes.excepciones.ResourceNotFoundException;
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
	
	/*Este metodo sirve para guardar el empleado*/
	@PostMapping("/clientes")
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		String[] letras = cliente.getBusinessId().split(" ");
		cliente.setSharedKey((letras[0].charAt(0))+(letras[1]));
		return clienteRepositorio.save(cliente);
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id){
		Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id: "+id));
		return ResponseEntity.ok(cliente);
	}
	
}
