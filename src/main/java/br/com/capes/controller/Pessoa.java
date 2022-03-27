package br.com.capes.controller;

/**
 * Classe que representa cada bolsista
 *
 * @author Bruno E. Silveira
 * @version 1.0
 */
public class Pessoa {

    /**
     * O atributo é utilizado para referenciar o nome do bolsista.
     */
    private String nome;
    /**
     * O atributo é utilizado para referenciar o cpf do bolsista.
     */
    private String cpf;
    /**
     * O atributo é utilizado para referenciar a entidade de ensino superior do bolsista.
     */
    private String entidade;
    /**
     * O atributo é utilizado para referenciar o Mês de referência.
     */
    private String mes;
    /**
     * O atributo é utilizado para referenciar o ano de referência.
     */
    private String ano;
    /**
     * O atributo é utilizado para referenciar a sigla da diretoria.
     */
    private String diretoria;
    /**
     * O atributo é utilizado para referenciar sigla do sistema de origem.
     */
    private String sistemaOrigem;
    /**
     * O atributo é utilizado para referenciar Código de modalidade SGB.
     */
    private String codModalidadeSgb;
    /**
     * O atributo é utilizado para referenciar modalidade de pagamento.
     */
    private String modalidadePagamento;
    /**
     * O atributo é utilizado para referenciar o Código da moeda.
     */
    private String codMoeda;
    /**
     * O atributo é utilizado para referenciar o valor da bolsa.
     */
    private int valor;

    /**
     * Construtor padrão da classe Pessoa.
     */
    public Pessoa() {

    }

    /**
     * Construtor sobrecarregado da classe Pessoa.
     *
     * @param nome                Nome do bolsista.
     * @param cpf                 CPF do bolsista.
     * @param entidade            Entidade de ensino superior do bolsista.
     * @param mes                 Mês referência.
     * @param ano                 Ano referência.
     * @param diretoria           Sigla da diretoria.
     * @param sistemaOrigem       Sigla do Sistema de Origem.
     * @param codModalidadeSgb    Código da Modalidade SGB.
     * @param modalidadePagamento Descrição da Modalidade de Pagamento.
     * @param codMoeda            Código da moeda
     * @param valor               Valor da bolsa.
     */
    public Pessoa(String nome, String cpf, String entidade, String mes, String ano, String diretoria, String sistemaOrigem, String codModalidadeSgb, String modalidadePagamento, String codMoeda, int valor) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.entidade = entidade;
        this.mes = mes;
        this.ano = ano;
        this.diretoria = diretoria;
        this.sistemaOrigem = sistemaOrigem;
        this.codModalidadeSgb = codModalidadeSgb;
        this.modalidadePagamento = modalidadePagamento;
        this.codMoeda = codMoeda;
        this.valor = valor;
    }

    /**
     * get do nome
     *
     * @return Retorna nome do bolsista;
     */
    public String getNome() {
        return nome;
    }

    /**
     * set do nome
     *
     * @param nome Parâmetro para inserir nome do bolsista.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * get do CPF.
     *
     * @return Retorna o CPF do bolsista;
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * set do CPF.
     *
     * @param cpf Parâmetro para inserir CPF do bolsista.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * get da entidade de ensino superior.
     *
     * @return Retorna Entidade de Ensino Superior do bolsista;
     */
    public String getEntidade() {
        return entidade;
    }

    /**
     * set da entidade de ensino superior.
     *
     * @param entidade Parâmetro para inserir Entidade de Ensino Superior do bolsista;
     */
    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    /**
     * get do Mês.
     *
     * @return Retorna Mês de referência;
     */
    public String getMes() {
        return mes;
    }

    /**
     * set do Mês.
     *
     * @param mes Parâmetro para inserir Mês de referência.
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * get do ano.
     *
     * @return Retorna Ano de referência;
     */
    public String getAno() {
        return ano;
    }

    /**
     * set do ano.
     *
     * @param ano Parâmetro para inserir Ano de referência.
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * get da Diretoria.
     *
     * @return Retorna a sigla da Diretoria;
     */
    public String getDiretoria() {
        return diretoria;
    }

    /**
     * set do Diretoria.
     *
     * @param diretoria Parâmetro para inserir a sigla da Diretoria.
     */
    public void setDiretoria(String diretoria) {
        this.diretoria = diretoria;
    }


    /**
     * get do Sistema de Origem.
     *
     * @return Retorna a sigla do sistema de origem;
     */
    public String getSistema() {
        return sistemaOrigem;
    }

    /**
     * set do Sistema de Origem.
     *
     * @param sistemaOrigem Parâmetro para inserir a sigla do sistema de origem;
     */
    public void setSistema(String sistemaOrigem) {
        this.sistemaOrigem = sistemaOrigem;
    }

    /**
     * get do Código da moeda
     *
     * @return Retorna o Código da moeda;
     */
    public String codMoeda() {
        return codMoeda;
    }

    /**
     * set do Código da moeda
     *
     * @param codMoeda Parâmetro para inserir o Código da moeda;
     */
    public void codMoeda(String codMoeda) {
        this.codMoeda = codMoeda;
    }

    /**
     * get do Código de modalidade SGB
     *
     * @return Retorna o Código de modalidade SGB;
     */
    public String getcodModalidadeSgb() {
        return codModalidadeSgb;
    }

    /**
     * set do Código de modalidade SGB
     *
     * @param codModalidadeSgb Parâmetro para inserir o Código de modalidade SGB;
     */
    public void setcodModalidadeSgb(String codModalidadeSgb) {
        this.codModalidadeSgb = codModalidadeSgb;
    }

    /**
     * get da modalidade de pagamento
     *
     * @return Retorna modalidade de pagamento;
     */
    public String getmodalidadePagamento() {
        return modalidadePagamento;
    }

    /**
     * set da modalidade de pagamento.
     *
     * @param modalidadePagamento Parâmetro para inserir a modalidade de pagamento;
     */
    public void setmodalidadePagamento(String modalidadePagamento) {
        this.modalidadePagamento = modalidadePagamento;
    }

    /**
     * get do valor da bolsa.
     *
     * @return Retorna valor da bolsa;
     */
    public int getValor() {
        return valor;
    }

    /**
     * set do valor da bolsa.
     *
     * @param valor Parâmetro para inserir o valor da bolsa;
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return " Nome: " + nome + "\n CPF: " + cpf + "\n Entidade de Ensino Superior: " + entidade + "\n Mes/Ano: " + mes + "/" + ano + "\n Diretoria: " + diretoria + "\n Sistema de Origem: " + sistemaOrigem + "\n Cód. Modalidade: " + codModalidadeSgb + "\n Modalidade Pagamento: " + modalidadePagamento + "\n Cód. Moeda: " + codMoeda + "\n Valor da Bolsa: " + valor;
    }


}
