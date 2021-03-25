package controller;

import java.util.Date;
import java.util.Objects;

public class Venda {

    private int pkVenda;
    private Cliente fkCliente;
    private Funcionario fkVendedor;
    private int numero;
    private Date data;


    public Venda(int pkVenda, Cliente fkCliente, Funcionario fkVendedor, int numero, Date data) {
        this.pkVenda = pkVenda;
        this.fkCliente = fkCliente;
        this.fkVendedor = fkVendedor;
        this.numero = numero;
        this.data = data;
    }

    public Venda(Cliente fkCliente, Funcionario fkVendedor, int numero, Date data) {
        this.fkCliente = fkCliente;
        this.fkVendedor = fkVendedor;
        this.numero = numero;
        this.data = data;
    }

    public int getPkVenda() {
        return pkVenda;
    }

    public void setPkVenda(int pkVenda) {
        this.pkVenda = pkVenda;
    }

    public Cliente getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Cliente fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Funcionario getFkVendedor() {
        return fkVendedor;
    }

    public void setFkVendedor(Funcionario fkVendedor) {
        this.fkVendedor = fkVendedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "pkVenda=" + pkVenda +
                ", fkCliente=" + fkCliente +
                ", fkVendedor=" + fkVendedor +
                ", numero=" + numero +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return pkVenda == venda.pkVenda && numero == venda.numero && Objects.equals(fkCliente, venda.fkCliente) && Objects.equals(fkVendedor, venda.fkVendedor) && Objects.equals(data, venda.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkVenda, fkCliente, fkVendedor, numero, data);
    }

    public void print(){
        System.out.println(this);
    }
}
