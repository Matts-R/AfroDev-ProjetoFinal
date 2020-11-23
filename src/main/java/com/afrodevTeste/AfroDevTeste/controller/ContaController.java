package com.afrodevTeste.AfroDevTeste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afrodevTeste.AfroDevTeste.Repository.ContasRepository;
import com.afrodevTeste.AfroDevTeste.Repository.PessoasRepository;
import com.afrodevTeste.AfroDevTeste.model.Conta;
import com.afrodevTeste.AfroDevTeste.model.Pessoa;

@RestController
public class ContaController {

	@Autowired
	private PessoasRepository pessoaRepository;

	@Autowired
	private ContasRepository contasRepository;

	@PostMapping(path = "/copax/conta/cadastrarConta")
	public String adicionarNovaConta(@RequestParam Integer mes, @RequestParam Double consumoMesAtual,
			@RequestParam Integer cpf) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(cpf);
		if (!pessoa.isPresent()) {
			return "O titular não pôde ser encontrado\n";
		}

		Pessoa titular = pessoa.get();
		Conta novaConta = Conta.criaConta(mes, consumoMesAtual, titular);

		contasRepository.save(novaConta);
		return "Conta Cadastrada\n";
	}

	@GetMapping(path = "/copax/conta/todosRegistros")
	public List<Conta> retornaRegistrosContas() {
		return contasRepository.findAll();
	}

	@PutMapping(path = "/copax/conta/atualizaDados")
	public String updateOneBook(@RequestParam Long codigoConta, @RequestParam Double consumoMesAtual) {

		Optional<Conta> resultadoBuscaConta = contasRepository.findById(codigoConta);
		if (!resultadoBuscaConta.isPresent()) {
			return "Titular não encontrado\n";
		}

		Conta contaAtualzada = resultadoBuscaConta.get();
		contaAtualzada.setConsumoMesAtual(consumoMesAtual);

		contasRepository.save(contaAtualzada);
		return "Conta Atualizada\n";
	}

	@DeleteMapping(path = "/copax/conta/deletarConta")
	public String deletaUmaConta(@RequestParam Long codigoConta) {
		contasRepository.deleteById(codigoConta);
		return "Conta deletada\n";
	}
}