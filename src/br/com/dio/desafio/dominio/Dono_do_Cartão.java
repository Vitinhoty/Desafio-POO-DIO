package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dono_do_Cartão {

    private String nome;
    private double saldo;
    private double limiteCredito;
    private double creditoUsado;

    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();

    public Dono_do_Cartão(String nome, double saldoInicial, double limiteCredito) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.limiteCredito = limiteCredito;
        this.creditoUsado = 0;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido.");
            return;
        }
        this.saldo += valor;
        System.out.println("Depósito realizado. Saldo atual: R$ " + saldo);
    }

    public void comprarDebito(Produto produto, Fatura_do_cartão fatura) {
        if (produto.getPreco() > saldo) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        saldo -= produto.getPreco();
        fatura.adicionarCompra(produto);
        conteudosInscritos.add(produto);
        System.out.println("Compra no débito aprovada: " + produto);
    }

    public void comprarCreditoParcelado(Produto produto, int parcelas, Fatura_do_cartão fatura) {
        double valor = produto.getPreco();
        if (valor > getCreditoDisponivel()) {
            System.out.println("Limite de crédito insuficiente.");
            return;
        }
        if (parcelas < 1) {
            System.out.println("Número de parcelas inválido.");
            return;
        }
        creditoUsado += valor;

        double valorParcela = valor / parcelas;
        for (int i = 0; i < parcelas; i++) {
            Parcela parcela = new Parcela(valorParcela, java.time.LocalDate.now().plusMonths(i + 1));
            fatura.adicionarCompra(parcela);
            conteudosInscritos.add(parcela);
        }
        System.out.println("Compra no crédito aprovada: " + produto +
                " em " + parcelas + "x de R$ " + String.format("%.2f", valorParcela));
    }

    public double getCreditoDisponivel() {
        return limiteCredito - creditoUsado;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public double getCreditoUsado() {
        return creditoUsado;
    }

    public String getNome() {
        return nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }
}
