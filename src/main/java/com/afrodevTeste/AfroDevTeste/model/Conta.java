package com.afrodevTeste.AfroDevTeste.model;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.*;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	private Random random = new Random();

	@Id
	@Column(name = "codigo_conta")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigoConta;

	@Column(name = "mes", nullable = false)
	private Integer mes;

	@Column(name = "consumoMesAtual", nullable = false)
	private Double consumoMesAtual;

	@Column(name = "consumoMesAnterior", nullable = false)
	private Double consumoMesAnterior;

	@Column(name = "diferencaConsumo", nullable = false)
	private Double diferencaConsumo;

	@Column(name = "valorConta", nullable = false)
	private Double valorConta;

	@ManyToOne
	@JoinColumn(name = "CPF", nullable = false)
	private Pessoa titular;

	private Conta() {}
	
	private Conta(Integer mes, Double consumoMesAtual, Pessoa titular) {
		this.mes = mes;
		this.consumoMesAtual = consumoMesAtual;
		setConsumoMesAnterior(consumoMesAtual);
		setDiferencaConsumo(consumoMesAtual);
		setValorConta(consumoMesAtual);
		this.titular = titular;
	}

	public Long getcodigoConta() {
		return this.codigoConta;
	}

	public Integer getMes() {
		return this.mes;
	}

	public Double getConsumoMesAtual() {
		return this.consumoMesAtual;
	}

	public void setConsumoMesAtual(Double consumoMesAtual) {
		this.consumoMesAtual = consumoMesAtual;
		setDiferencaConsumo(this.getConsumoMesAtual());
		setValorConta(this.getConsumoMesAtual());
	}

	public Double getConsumoMesAnterior() {
		return this.consumoMesAnterior;
	}

	private void setConsumoMesAnterior(Double consumoMesAtual) {
		// Usei números aleátorios simplesmente para simular os valores do mês anterior
		Double consumoMesAnterior = random.nextDouble() * consumoMesAtual;
		this.consumoMesAnterior = consumoMesAnterior;
	}

	public Double getDiferencaConsumo() {
		return this.diferencaConsumo;
	}

	private void setDiferencaConsumo(Double diferencaConsumo) {
		this.diferencaConsumo = diferencaConsumo - this.getConsumoMesAnterior();
	}

	public Double getValorConta() {
		return this.valorConta;
	}

	private void setValorConta(Double consumoMesAtual) {
		// REGRA DE NEGÓCIO: O valor da conta é igual ao Consumo do Mês vezes 0.36
		this.valorConta = consumoMesAtual * 0.36;
	}

	public Pessoa getTitular() {
		return this.titular;
	}

	private void setTitular(Pessoa titular) {
		this.titular = titular;
	}

	public static Conta criaConta(Integer mes, Double consumoMesAtual, Pessoa titular) {

		return new Conta(mes, consumoMesAtual, titular);
	}
}