package controller;

public class Fornecedor extends PessoaJuridica{

    public Fornecedor(int pk, String nome, String cnpj) {
        super(pk, nome, cnpj);
    }

    public Fornecedor(String nome, String cnpj) {
        super(nome, cnpj);
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "enderecos=" + enderecos +
                '}';
    }

    public void print(){
        super.print();
    }
}
