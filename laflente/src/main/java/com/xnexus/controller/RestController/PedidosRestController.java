package com.xnexus.controller.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xnexus.model.ItemCarrinho;
import com.xnexus.model.Pedido;
import com.xnexus.model.Produto;
import com.xnexus.model.UsuarioECommerce;
import com.xnexus.repository.PedidoRepository;
import com.xnexus.repository.ProdutoRepository;
import com.xnexus.repository.UsuarioRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidosRestController {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Pedido> getPedidos(){
		return repository.findAll();
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> cadastraPedido(@RequestBody Pedido pedido){
		
		double valorTotal = 0;
		for (ItemCarrinho item : pedido.getCarrinho().getItens()) {
			
			Optional<Produto> optional = produtoRepository.findById(item.getCodigoProduto());
			
			if(optional.isPresent()) {
				Produto produto = optional.get();
				valorTotal += item.getQuantidade() * produto.getPreco();
			}
			
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String email;

		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}
		
		UsuarioECommerce usuario= usuarioRepository.findByEmail(email).get();
		
		pedido.setIdUsuario(usuario.getId());
		pedido.setDataVenda(new Date());
		pedido.setValorTotal(valorTotal);
		pedido.setStatus("Aguardando pagamento");
		repository.save(pedido);
		
		return ResponseEntity.created(null).build();
	}
}
