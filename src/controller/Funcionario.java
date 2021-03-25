package controller;

import java.util.Objects;

public class Funcionario extends PessoaFisica{

    private Cargo cargo;

    public Funcionario(Cargo cargo, String cpf, int pk, String nome) {
        super(cpf, pk, nome);
        this.cargo = cargo;
    }

    public Funcionario(Cargo cargo, String cpf, String nome) {
        super(cpf, nome);
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.cargo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "cargo=" + cargo + '}';
    }


    public void print(){
        super.print();
    }
}
