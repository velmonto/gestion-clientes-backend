package co.com.gestion.clientes.controlador;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.com.gestion.clientes.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.com.gestion.clientes.excepciones.ResourceNotFoundException;
import co.com.gestion.clientes.model.Cliente;
import co.com.gestion.clientes.repositorio.ClienteRepositorio;

@RestController
@RequestMapping("api/v1/clientes")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClienteControlador {
	
	@Autowired
	private ClienteServicio clienteServicio;
	
	/**Este metodo sirve para listar todos los clientes*/
	@GetMapping
	public List<Cliente> listarTodosLosClientes(){
		return clienteServicio.findAllClientes();
	}
	
	/**Este metodo sirve para guardar el empleado*/
	@PostMapping
	public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
		String[] letras = cliente.getBusinessId().split(" ");
		cliente.setSharedKey((letras[0].charAt(0))+(letras[1]));
		return new ResponseEntity<>(clienteServicio.saveCliente(cliente), HttpStatus.CREATED);
	}

	/**Este metodo sirve para buscar un empleado por id*/
	@GetMapping("{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id){
		Cliente cliente = clienteServicio.findAllClienteById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id: "+id));
		return ResponseEntity.ok(cliente);
	}

	/**Este metodo sirve para buscar un empleado por id*/
	@PutMapping("{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente detalleCliente){
		Cliente cliente = clienteServicio.findAllClienteById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id: "+id));
		cliente.setSharedKey(detalleCliente.getSharedKey());
		cliente.setBusinessId(detalleCliente.getBusinessId());
		cliente.setEmail(detalleCliente.getEmail());
		cliente.setPhone(detalleCliente.getPhone());
		cliente.setEndDate(new Date(System.currentTimeMillis()));
		Cliente clientActualize = clienteServicio.saveCliente(cliente);
		return ResponseEntity.ok(clientActualize);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id){
		clienteServicio.deleteCliente(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**Este metodo sirve para buscar un empleado por sharedKey*/
	@GetMapping("/shared/{shredKey}")
	public ResponseEntity<Cliente> getClientBySharedKey(@PathVariable String sharedKey){
		Cliente cliente = clienteServicio.getClienteBySharedKey(sharedKey);
		return ResponseEntity.ok(cliente);
	}
	
}
