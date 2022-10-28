package com.trabpoouninter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App // Casse principal
{
    public static void main( String[] args )
    {
        String textMainMenu = "ESCOLHA UMA OPÇÃO\n" // Texto do menu principal
        + "1 - Adicionar Moedas\n"
        + "2 - Remover Moedas\n"
        + "3 - Listar Moedas\n"
        + "4 - Total\n"
        + "5 - Sair";

        String textMenuAdd = "ESCOLHA O TIPO DE MOEDA\n" // Texto do menu de escolha do tipo de moedas.
        + "1 - Real\n"
        + "2 - Dolar\n"
        + "3 - Euro\n"
        + "4 - <-";


        ArrayList<Moeda> moedas = new ArrayList<>(); // Banco de moedas
        Integer op = 0; // Variável de escolha de opções de menu.
        Scanner scan = new Scanner(System.in);

        while (op != 5) { // Loop principal

            System.out.println(textMainMenu);

            try {
                op = scan.nextInt();

                if (op > 5 || op < 1) {
                    System.out.println("Opção Inválida!\n");
                    scan.nextLine();
                    continue;
                }

                switch (op) { // Switch do menu principal.
                    case 1: // Bloco da opção incluir moedas.
                        System.out.println(textMenuAdd);
                        op = scan.nextInt();

                        if (op > 3 || op < 1) {
                            System.out.println("Opção Inválida!\n");
                            scan.nextLine();
                            continue;
                        }
        
                        switch (op) { // Switch da escolha de moedas.
                            case 1:
                                System.out.print("Digite o valor com '.' no lugar de ',': ");
                                moedas.add(new Real(scan.nextDouble()));
                                break;
                            case 2:
                                System.out.print("Digite o valor com '.' no lugar de ',': ");
                                moedas.add(new Dolar(scan.nextDouble()));
                                break;
                            case 3:
                                System.out.print("Digite o valor com '.' no lugar de ',': ");
                                moedas.add(new Euro(scan.nextDouble()));
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                        break;
                    case 2: // Bloco da opção excluir moedas.
                        Integer moedasCount = moedas.size();
                        App.listaMoedas(moedas);
                        System.out.print("Ditite a linha do valor a ser excluído: ");
                        op = scan.nextInt();

                        if (op > moedasCount || op < 1) {
                            System.out.println("Moeda inexistente!\n");
                        } else {
                            System.out.println("Moeda " + moedas.get(op - 1).getValor() + " Removida!");
                            moedas.remove(op - 1);
                        }
                        break;
                    case 3: // Bloco da opção listar moedas.
                        App.listaMoedas(moedas);
                        break;
                    case 4: // Bloco da opção calcular total.
                        Double dolarPrice = 0.0, euroPrice = 0.0, total = 0.0;

                        // Cria onjeto responsável por consultar as cotaes em uma WEB API publica
                        CotacaoService cs = new CotacaoService(new Dolar(0.0), new Real(0.0));

                        dolarPrice = cs.getCotacaMoeda().getValor(); // Obtem a cotação do dolar no dia.
                        cs.setOrigeMoeda(new Euro(0.0));
                        euroPrice = cs.getCotacaMoeda().getValor(); // Obtem cotação do euro no dia.
                        System.out.println("\nCotação do Dolar: " + dolarPrice);
                        System.out.println("Cotação do Euro: " + euroPrice);
                        System.out.println("\nMOEDAS:");
                        App.listaMoedas(moedas);

                        /*Loop para conversão e soma das moedas no cofre */
                        for (Moeda moeda : moedas) {
                            if (moeda.getSigla().equals("EUR")) {
                                total += moeda.getValor() * euroPrice;
                            } else if (moeda.getSigla().equals("USD")) {
                                total += moeda.getValor() * dolarPrice;
                            } else {
                                total += moeda.getValor();
                            }
                        }
                        
                        System.out.println("Total em R$: " + total + "\n");
                        break;
                    case 5: // Sair
                        scan.close();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida\n");
                scan.nextLine();
                continue;
            }
        }
    }

    private static void listaMoedas(ArrayList<Moeda> moedas) { // Função que lista as moedas na tela.
        if (moedas.isEmpty()) {
            System.out.println("\nVocê não possui moedas\n");
        }

        System.out.println();

        for (Moeda moeda : moedas) {
            System.out.println("[" + (moedas.indexOf(moeda) + 1) 
            + "] - " + moeda.getSigla() + " " + moeda.getValor()
            + "  " + moeda.Info());
        }
        System.out.println();
    }
}
