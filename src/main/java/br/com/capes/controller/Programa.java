package br.com.capes.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe responsável pelas regras de negócio.
 *
 * @author Bruno E. Silveira
 * @version 1.0
 */
public class Programa {

    /**
     * Método para buscar o primeiro bolsista do ano informado.
     *
     * @param ano Parâmetro que passa o ano em que vai ser filtrado a busca na
     *            lista.
     * @return Retorna o primeiro bolsista do ano informado. Retorna nulo caso não
     * haja dados.
     */
    public Pessoa buscarBolsaZero(int ano) {

        List<Pessoa> lista = popularLista();
        Pessoa pessoa;
        int i = 0;
        int y = 0;
        while (i < lista.size()) {
            if (Integer.parseInt(lista.get(i).getAno()) == ano) {
                y = i;
            }
            i++;
        }
        if (y > 0) {
            pessoa = lista.get(y);
        } else
            pessoa = null;

        return pessoa;
    }

    /**
     * Método que faz uma lista baseado no que o usuário digita.
     *
     * @param nome Texto que o usuário escreveu.
     * @return Retorna uma lista com os dados dos bolsistas.
     */
    public List<Pessoa> pesquisarNome(String nome) {
        if(nome == null || nome.length() < 4)
            return null;
        List<Pessoa> lista = popularLista();
        List<Pessoa> listaPesquisa = new ArrayList<>();
        int i = 0;

        while (i < lista.size()) {
            if (lista.get(i).getNome().contains(nome.toUpperCase())) {
                    listaPesquisa.add(lista.get(i));
            }
            i++;
        }

        if (listaPesquisa.size() < 1)
            return null;
        else
            return listaPesquisa;
    }

    /**
     * Método para codificar nomes.
     *
     * @param nome Nome a ser codificado.
     * @return retorna o nome codificado.
     */
    public String codificarNome(String nome) {

        if(nome == null || nome.length() < 1)
            return null;
        nome = nome.toUpperCase();
        String[] nomeDividido = nome.split(" ");
        StringBuilder finalCodificado = new StringBuilder();

        for (String palavras : nomeDividido) {
            String[] letras = palavras.split("");

            String s;
            for (int i = (letras.length - 1) / 2; i > 0; i--) {
                s = letras[i];
                letras[i] = letras[letras.length - i - 1];
                letras[letras.length - i - 1] = s;
            }
            char c;
            int y;
            for (int i = letras.length - 1; i >= 0; i--) {
                y = letras[i].charAt(0);
                if (y >= 90)
                    y = 65;
                else
                    y += 1;
                c = (char) y;
                letras[i] = Character.toString(c);
            }
            StringBuilder codificado = new StringBuilder();
            for (String letra : letras)
                codificado.append(letra);

            finalCodificado.append(" ").append(codificado);
        }
        return finalCodificado.toString().trim();
    }

    /**
     * Método para checagem de nome/cpf repetido, usado nos Métodos
     * <b>rankingValoresAltos</b> e <b>rankingValoresBaixos</b> para não repetir
     * nomes no (classificação).
     *
     * @param bolsista Bolsista que está no laço atual.
     * @param lista  Lista com o ranking atual.
     * @return retorna um booleando, false se existe o mesmo nome/cpf, e verdadeiro
     * se não existe.
     */
    boolean checagemBolsistaRepetido(Pessoa bolsista, List<Pessoa> lista) {
        for (Pessoa pessoa : lista) {
            if (bolsista.equals(pessoa)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que faz a classificação dos três bolsistas com maior valor da bolsa.
     *
     * @return Retorna uma lista com os três bolsistas com maior valor da bolsa.
     */
    public List<Pessoa> rankingValoresAltos() {
        List<Pessoa> lista = popularLista();
        List<Pessoa> ranking = new ArrayList<>();

        lista.sort(Comparator.comparing(Pessoa::getValor));
        for(int i = 0; i < 3; i++){
            if(checagemBolsistaRepetido(lista.get(i), ranking))
                ranking.add(lista.get(lista.size() - i - 1));
        }
        return ranking;
    }

    /**
     * Método que faz a classificação dos três bolsistas com menor valor da bolsa.
     *
     * @return Retorna uma lista com os três bolsistas com menor valor da bolsa.
     */
    public List<Pessoa> rankingValoresBaixos() {
        List<Pessoa> lista = popularLista();
        List<Pessoa> ranking = new ArrayList<>();

        lista.sort(Comparator.comparing(Pessoa::getValor));
        for(int i = 0; i < 3; i++){
            if(checagemBolsistaRepetido(lista.get(i), ranking))
                ranking.add(lista.get(i));
        }
        return ranking;
    }

    /**
     * Método que faz o cálculo para trazer a média do valor das bolsas do ano
     * informado.
     *
     * @param ano Parâmetro que passa o ano em que vai ser filtrado a busca na
     *            lista.
     * @return Retorna a média do valor de todos bolsista do ano informado. Retorna
     * 0 caso não haja dados.
     */
    public int mediaAnual(int ano) {
        List<Pessoa> lista = popularLista();
        int total = 0;
        int i = 0;
        int y = 0;

        while (i < lista.size()) {
            if (Integer.parseInt(lista.get(i).getAno()) == ano) {
                total += lista.get(i).getValor();
                y++;
            }
            i++;
        }
        if (y > 0)
            total = total / y;
        return total;
    }

    /**
     * Método responsável por popular a lista para manipulação de dados dos
     * bolsistas.
     *
     * @return Retorna uma lista com todos os bolsistas e os seus respetivos dados. Em
     * caso de exceção, retorna uma lista vazia.
     */
    List<Pessoa> popularLista() {
        String path = "files\\br-capes-bolsistas-uab.csv";

        List<Pessoa> lista = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] vet = line.split(";");
                Pessoa pessoa = new Pessoa(vet[0], vet[1], vet[2], vet[3], vet[4], vet[5], vet[6], vet[7],
                vet[8], vet[9], Integer.parseInt(vet[10]));
                lista.add(pessoa);

                line = reader.readLine();
            }
            return lista;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return lista;
        }
    }
}
