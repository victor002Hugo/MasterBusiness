package controller;

import java.util.Objects;

public class VendaItem {

    private int pkItem;
    private Venda fkVenda;
    private Produto fkProduto;
    private int qtd;
    private double valorUnitario;




    public VendaItem(int pkItem, Venda fkVenda, Produto fkProduto, int qtd, double valorUnitario) {
        this.pkItem = pkItem;
        this.fkVenda = fkVenda;
        this.fkProduto = fkProduto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }

    public VendaItem(Venda fkVenda, Produto fkProduto, int qtd, double valorUnitario) {
        this.fkVenda = fkVenda;
        this.fkProduto = fkProduto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }

    public int getPkItem() {
        return pkItem;
    }

    public void setPkItem(int pkItem) {
        this.pkItem = pkItem;
    }

    public Venda getFkVenda() {
        return fkVenda;
    }

    public void setFkVenda(Venda fkVenda) {
        this.fkVenda = fkVenda;
    }

    public Produto getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Produto fkProduto) {
        this.fkProduto = fkProduto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaItem vendaItem = (VendaItem) o;
        return pkItem == vendaItem.pkItem && qtd == vendaItem.qtd && Double.compare(vendaItem.valorUnitario, valorUnitario) == 0 && Objects.equals(fkVenda, vendaItem.fkVenda) && Objects.equals(fkProduto, vendaItem.fkProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkItem, fkVenda, fkProduto, qtd, valorUnitario);
    }

    @Override
    public String toString() {
        return "VendaItem{" +
                "pkItem=" + pkItem +
                ", fkVenda=" + fkVenda +
                ", fkProduto=" + fkProduto +
                ", qtd=" + qtd +
                ", valorUnitario=" + valorUnitario +
                '}';
    }

    public void print(){
        System.out.println(this);
    }
}
