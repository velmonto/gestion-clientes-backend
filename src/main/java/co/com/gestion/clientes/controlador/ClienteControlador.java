package co.com.gestion.clientes.controlador;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	/*Este metodo sirve para buscar un empleado por id*/
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id){
		Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id: "+id));
		return ResponseEntity.ok(cliente);
	}

	/*Este metodo sirve para buscar un empleado por id*/
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente detalleCliente){
		Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id: "+id));
		cliente.setSharedKey(detalleCliente.getSharedKey());
		cliente.setBusinessId(detalleCliente.getBusinessId());
		cliente.setEmail(detalleCliente.getEmail());
		cliente.setPhone(detalleCliente.getPhone());
		cliente.setEndDate(new Date(System.currentTimeMillis()));

		Cliente clientActualize = clienteRepositorio.save(cliente);
		return ResponseEntity.ok(clientActualize);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarCliente(@PathVariable Long id){
		Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id: "+id));
		clienteRepositorio.delete(cliente);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
}
