package com.sampaio.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sampaio.cursomc.domain.Categoria;
import com.sampaio.cursomc.domain.Cidade;
import com.sampaio.cursomc.domain.Cliente;
import com.sampaio.cursomc.domain.Endereco;
import com.sampaio.cursomc.domain.Estado;
import com.sampaio.cursomc.domain.Produto;
import com.sampaio.cursomc.domain.enums.TipoCliente;
import com.sampaio.cursomc.repositories.CategoriaRepository;
import com.sampaio.cursomc.repositories.CidadeRepository;
import com.sampaio.cursomc.repositories.ClienteRepository;
import com.sampaio.cursomc.repositories.EnderecoRepository;
import com.sampaio.cursomc.repositories.EstadoRepository;
import com.sampaio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");
		
		Produto p1 = new Produto("Computador", 2000);
		Produto p2 = new Produto("Impressora", 800);
		Produto p3 = new Produto("Mouse", 80);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");
		
		Cidade c1 = new Cidade("Uberlândia", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com","12332112332", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("998899989", "35354543"));
		
		Endereco e1 = new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "84200000", cli1, c1);
		Endereco e2 = new Endereco("Av Matos", "460", "casa", "Centro", "84200150", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
