package com.afrodevTeste.AfroDevTeste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afrodevTeste.AfroDevTeste.Repository.ContasRepository;
import com.afrodevTeste.AfroDevTeste.Repository.PessoasRepository;
import com.afrodevTeste.AfroDevTeste.model.Conta;
import com.afrodevTeste.AfroDevTeste.model.Pessoa;

@RestController
public class PessoaController {

	@Autowired
	private PessoasRepository pessoaRepository;
	
	@Autowired
	private ContasRepository contasRepository;


	@PostMapping(path = "/copax/pessoa/cadastrarPessoa")
	public String adicionarNovaPessoa(@RequestParam Integer cpf, @RequestParam String nome,
			@RequestParam String sobrenome, @RequestParam String endereco) {

		Pessoa novaPessoa = new Pessoa();

		novaPessoa.setCPF(cpf);
		novaPessoa.setNome(nome);
		novaPessoa.setSobrenome(sobrenome);
		novaPessoa.setEndereco(endereco);

		pessoaRepository.save(novaPessoa);
		return "pessoaCadastrada\n";
	}

	@GetMapping(path = "/copax/pessoa/todosRegistros")
	public List<Pessoa> retornaRegistrosPessoas() {
		return pessoaRepository.findAll();
	}
	
	@PutMapping("/copax/pessoa/atualizaDados")
	public String atualizaPessoa(@RequestParam Integer cpf, @RequestParam String nome
			, @RequestParam String sobrenome, @RequestParam String endereco) {
		Optional<Pessoa> resultadoBuscaPessoa = pessoaRepository.findById(cpf);

		if (!resultadoBuscaPessoa.isPresent()) {
			return "Pessoa não registrada";
		}

		Pessoa pessoaAtualizada = resultadoBuscaPessoa.get();
		pessoaAtualizada.setNome(nome);
		pessoaAtualizada.setSobrenome(sobrenome);
		pessoaAtualizada.setEndereco(endereco);
		
		pessoaRepository.save(pessoaAtualizada);
		return "pessoaAtualizada\n";
	}

	@DeleteMapping(path = "/copax/pessoa/deletarPessoa")
	public String deletaUmaPessoa(@RequestParam Integer cpf) {
		pessoaRepository.deleteById(cpf);
		return "pessoaDeletada\n";
	}
}