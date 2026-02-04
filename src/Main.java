import br.com.dio.desafio.dominio.Conteudo;
import br.com.dio.desafio.dominio.Dono_do_Cartão;
import br.com.dio.desafio.dominio.Fatura_do_cartão;
import br.com.dio.desafio.dominio.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Dono_do_Cartão dev = new Dono_do_Cartão("Usuario", 0.0, 2000.0);
        Fatura_do_cartão fatura = new Fatura_do_cartão();

        Produto bolsa = new Produto("Bolsa", 150.0);
        Produto celular = new Produto("Celular", 1200.0);
        Produto notebook = new Produto("Notebook", 3500.0);

        int opcao;
        do {
            System.out.println("\n=== MENU CARTÃO ===");
            System.out.println("1 - Ver saldo/limite/fatura");
            System.out.println("2 - Depositar");
            System.out.println("3 - Comprar (débito)");
            System.out.println("4 - Comprar (crédito parcelado)");
            System.out.println("5 - Ver compras (conteúdos)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Saldo: R$ " + dev.getSaldo());
                    System.out.println("Limite crédito: R$ " + dev.getLimiteCredito());
                    System.out.println("Crédito usado: R$ " + dev.getCreditoUsado());
                    System.out.println("Crédito disponível: R$ " + dev.getCreditoDisponivel());
                    System.out.println("Total fatura: R$ " + fatura.calcularTotalFatura());
                    break;
                case 2:
                    System.out.print("Valor depósito: ");
                    double v = sc.nextDouble();
                    dev.depositar(v);
                    break;
                case 3:
                    Produto pDeb = escolherProduto(sc, bolsa, celular, notebook);
                    if (pDeb != null) dev.comprarDebito(pDeb, fatura);
                    break;
                case 4:
                    Produto pCred = escolherProduto(sc, bolsa, celular, notebook);
                    if (pCred != null) {
                        System.out.print("Número de parcelas: ");
                        int parcelas = sc.nextInt();
                        dev.comprarCreditoParcelado(pCred, parcelas, fatura);
                    }
                    break;
                case 5:
                    System.out.println("=== CONTEÚDOS (COMPRAS) ===");
                    for (Conteudo c : dev.getConteudosInscritos()) {
                        System.out.println(c);
                    }
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    private static Produto escolherProduto(Scanner sc, Produto bolsa, Produto celular, Produto notebook) {
        System.out.println("\nEscolha o produto:");
        System.out.println("1 - " + bolsa);
        System.out.println("2 - " + celular);
        System.out.println("3 - " + notebook);
        System.out.print("Opção: ");
        int op = sc.nextInt();
        switch (op) {
            case 1: return bolsa;
            case 2: return celular;
            case 3: return notebook;
            default:
                System.out.println("Produto inválido.");
                return null;
        }
    }
}
