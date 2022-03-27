package br.com.capes.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe responsável pelos testes unitários automatizados utilizando a biblioteca JUnit.
 *
 * @author Bruno E. Silveira
 * @version 1.0
 */
class ProgramaTest {

    private static Programa programa;

    @BeforeAll
    static void setup() {
        programa = new Programa();
    }

    @Test
    void buscarBolsistaZeroComBolsistaZero() {
        Pessoa bolsistaZeroEsperado = new Pessoa("ROSILENE DE LIMA", "***.474.229-**", "UNIVERSIDADE ESTADUAL DE MARINGA", "1", "2013", "DED", "SGB", "6", "TUTOR", "R$", 765);
        Pessoa bolsistaZeroRetornado = programa.buscarBolsaZero(2013);

        Assertions.assertEquals(bolsistaZeroEsperado.getNome(), bolsistaZeroRetornado.getNome());
        Assertions.assertEquals(bolsistaZeroEsperado.getCpf(), bolsistaZeroRetornado.getCpf());
        Assertions.assertEquals(bolsistaZeroEsperado.getEntidade(), bolsistaZeroRetornado.getEntidade());
        Assertions.assertEquals(bolsistaZeroEsperado.getValor(), bolsistaZeroRetornado.getValor());
    }

    @Test
    void buscarBolsistaZeroNulo() {
        Pessoa bolsistaZeroEsperado = null;
        Pessoa bolsistaZeroRetornado = programa.buscarBolsaZero(2012);

        Assertions.assertEquals(bolsistaZeroEsperado, bolsistaZeroRetornado);
    }

    @Test
    void pesquisarNomeComRetorno() {
        String nome = "adriana";
        List<Pessoa> lista = programa.popularLista();
        List<Pessoa> listaPessoasEsperado = new ArrayList<>();
        List<Pessoa> listaPessoasRetorno = programa.pesquisarNome(nome);

        int i = 0;
        while (i < lista.size()) {
            if (lista.get(i).getNome().contains(nome.toUpperCase())) {
                listaPessoasEsperado.add(lista.get(i));
            }
            i++;
        }

        Assertions.assertEquals(listaPessoasEsperado.size(), listaPessoasRetorno.size());
    }

    @Test
    void pesquisarNomeNaoExiste() {
        String nome = "grsdghsdhsdhds";
        List<Pessoa> listaPessoasEsperado = null;
        List<Pessoa> listaPessoasRetorno = programa.pesquisarNome(nome);

        Assertions.assertEquals(listaPessoasEsperado, listaPessoasRetorno);
    }

    @Test
    void pesquisarNomeVazio() {
        String nome = "";
        List<Pessoa> listaPessoasEsperado = null;
        List<Pessoa> listaPessoasRetorno = programa.pesquisarNome(nome);

        Assertions.assertEquals(listaPessoasEsperado, listaPessoasRetorno);
    }

    @Test
    void pesquisarNomeNulo() {
        String nome = null;
        List<Pessoa> listaPessoasEsperado = null;
        List<Pessoa> listaPessoasRetorno = programa.pesquisarNome(nome);

        Assertions.assertEquals(listaPessoasEsperado, listaPessoasRetorno);
    }

    @Test
    void codificarNomeMultiplasPalavras() {
        String nome = "antonio da silva neto";
        // ANTONIO DA SILVA NETO -> ONTONIA AD AILVS OETN -> AINOTNO DA SVLIA NTEO -> BJOPUOP EB TWMJB OUFP
        String nomeCodificadoEsperado = "BJOPUOP EB TWMJB OUFP".toUpperCase();
        String nomeCodificadoRetorno = programa.codificarNome(nome);

        Assertions.assertEquals(nomeCodificadoEsperado, nomeCodificadoRetorno);
    }

    @Test
    void codificarNomeUmaPalavra() {
        String nome = "antonio";
        // ANTONIO -> ONTONIA -> AINOTNO -> BJOPUOP
        String nomeCodificadoEsperado = "BJOPUOP".toUpperCase();
        String nomeCodificadoRetorno = programa.codificarNome(nome);

        Assertions.assertEquals(nomeCodificadoEsperado, nomeCodificadoRetorno);
    }

    @Test
    void codificarNomeUmaLetra() {
        String nome = "a";
        String nomeCodificadoEsperado = "b".toUpperCase();
        String nomeCodificadoRetorno = programa.codificarNome(nome);

        Assertions.assertEquals(nomeCodificadoEsperado, nomeCodificadoRetorno);
    }
    @Test
    void codificarNomeDuasLetra() {
        String nome = "aB";
        String nomeCodificadoEsperado = "bc".toUpperCase();
        String nomeCodificadoRetorno = programa.codificarNome(nome);

        Assertions.assertEquals(nomeCodificadoEsperado, nomeCodificadoRetorno);
    }
    @Test
    void codificarNomeTresLetra() {
        String nome = "ATz";
        String nomeCodificadoEsperado = "bua".toUpperCase();
        String nomeCodificadoRetorno = programa.codificarNome(nome);

        Assertions.assertEquals(nomeCodificadoEsperado, nomeCodificadoRetorno);
    }

    @Test
    void codificarNomeVazio() {
        String nome = "";
        String nomeCodificadoEsperado = null;
        String nomeCodificadoRetorno = programa.codificarNome(nome);

        Assertions.assertEquals(nomeCodificadoEsperado, nomeCodificadoRetorno);
    }

    @Test
    void codificarNomeNulo() {
        String nome = null;
        String nomeCodificadoEsperado = null;
        String nomeCodificadoRetorno = programa.codificarNome(nome);

        Assertions.assertEquals(nomeCodificadoEsperado, nomeCodificadoRetorno);
    }

    @Test
    void consultarMedAnualAnoValido() {
        int ano = 2015;
        int valorEsperado = 938;
        int valorRetorno = programa.mediaAnual(ano);

        Assertions.assertEquals(valorEsperado, valorRetorno);
    }

    @Test
    void consultarMedAnualAnoZero() {
        int ano = 0;
        int valorEsperado = 0;
        int valorRetorno = programa.mediaAnual(ano);

        Assertions.assertEquals(valorEsperado, valorRetorno);
    }

    @Test
    void consultarMedAnualAnoInvalido() {
        int ano = 2035;
        int valorEsperado = 0;
        int valorRetorno = programa.mediaAnual(ano);

        Assertions.assertEquals(valorEsperado, valorRetorno);
    }

    @Test
    void rankingValoresAltosValido(){
        List<String> listaEsperado = new ArrayList<>();
        List<Pessoa> listaRetorno = programa.rankingValoresAltos();

        List<Pessoa> lista = programa.popularLista();
        lista.sort(Comparator.comparing(Pessoa::getValor));
        for(int i = 0; i < 3; i++){
            listaEsperado.add(lista.get(lista.size() - i - 1).getNome());
        }

        for(int i = 0; i < 3; i++){
            Assertions.assertEquals(listaEsperado.get(i), listaRetorno.get(i).getNome());
        }
    }

    @Test
    void rankingValoresBaixosValido(){
        List<String> listaEsperado = new ArrayList<>();
        List<Pessoa> listaRetorno = programa.rankingValoresBaixos();

        List<Pessoa> lista = programa.popularLista();
        lista.sort(Comparator.comparing(Pessoa::getValor));
        for(int i = 0; i < 3; i++){
            listaEsperado.add(lista.get(i).getNome());
        }

        for(int i = 0; i < 3; i++){
            Assertions.assertEquals(listaEsperado.get(i), listaRetorno.get(i).getNome());
        }
    }


}
