package br.com.dio.desafio.dominio;

public class Produto extends Conteudo {

    private double preco;

    public Produto(String nome, double preco) {
        setTitulo(nome);
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public double calcularXp() {
        // aqui o XP é só ilustrativo
        return XP_PADRAO + preco / 10;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + getTitulo() + '\'' +
                ", preco=" + preco +
                '}';
    }
}
