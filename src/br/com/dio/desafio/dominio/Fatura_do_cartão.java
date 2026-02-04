package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class Fatura_do_cartão {

    private String nome;
    private String descricao;
    private final LocalDate dataInicial = LocalDate.now();
    private final LocalDate dataFinal = dataInicial.plusMonths(1);

    private Set<Dono_do_Cartão> devsInscritos = new HashSet<>();
    private Set<Conteudo> conteudos = new LinkedHashSet<>();

    public void adicionarCompra(Conteudo conteudo) {
        this.conteudos.add(conteudo);
    }

    public void removerConteudo(Conteudo conteudo) {
        this.conteudos.remove(conteudo);
    }

    public double calcularTotalFatura() {
        double total = 0;
        for (Conteudo c : conteudos) {
            if (c instanceof Produto) {
                total += ((Produto) c).getPreco();
            } else if (c instanceof Parcela) {
                total += ((Parcela) c).getValorParcela();
            }
        }
        return total;
    }

    public Set<Conteudo> getConteudos() {
        return conteudos;
    }

    public Set<Dono_do_Cartão> getDevsInscritos() {
        return devsInscritos;
    }

    // getters e setters de nome/descricao (opcional)

}
