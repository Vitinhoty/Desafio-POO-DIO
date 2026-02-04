package br.com.dio.desafio.dominio;

import java.time.LocalDate;

public class Parcela extends Conteudo {

    private double valorParcela;
    private LocalDate dataVencimento;

    public Parcela(double valorParcela, LocalDate dataVencimento) {
        this.valorParcela = valorParcela;
        this.dataVencimento = dataVencimento;
        setTitulo("Parcela com vencimento em " + dataVencimento);
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    @Override
    public double calcularXp() {
        return XP_PADRAO;
    }

    @Override
    public String toString() {
        return "Parcela{" +
                "valorParcela=" + valorParcela +
                ", dataVencimento=" + dataVencimento +
                '}';
    }
}
