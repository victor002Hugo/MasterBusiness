package controller;

public class Cliente extends PessoaFisica{


    public Cliente(String cpf, int pk, String nome) {
        super(cpf, pk, nome);
    }

    public Cliente(int pk_cliente, String cpf, String nome) {
        super(cpf, nome);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "enderecos=" + enderecos +
                '}';
    }

    public void print(){
        super.print();
    }
}
