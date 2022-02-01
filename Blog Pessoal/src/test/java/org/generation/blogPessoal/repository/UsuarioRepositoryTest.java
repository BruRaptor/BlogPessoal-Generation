package org.generation.blogPessoal.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L,"Gil Brother Ribeiro", "gilbrother@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L,"Conceição Ribeiro", "conceicao@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L,"Gabriel Ribeiro", "gabriel@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L,"Thiago", "thiago@email.com.br", "13456789"));
	}
	
	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("gabriel@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("gabriel@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuario() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Ribeiro");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Gil Brother Ribeiro"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Conceição Ribeiro"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Gabriel Ribeiro"));
	}
}
