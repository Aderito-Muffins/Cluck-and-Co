package Source.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Estoque implements Serializable {
    
    private int quantidade;
    private int id;
    private int moneyInvestido;
    private LocalDate dataCompra;
    private LocalDate dataExpira;
    private String funcionario;
    private boolean vendido;

    public Estoque(int quantidade, int id, LocalDate dataCompra, String funcionario) {
        this.id = id;
        this.moneyInvestido = quantidade * 6; // Considerando que cada ovo custa 5 unidades monetárias
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
        this.dataExpira = dataCompra.plusMonths(2); // Considerando que o prazo de validade é de 2 meses
        this.funcionario = funcionario;
        this.vendido = false; // Inicialmente, o ovo não está vendido
    }
public boolean isExpirado() {
    return !dataCompra.isBefore(dataExpira);
}

    
    
    public int getMoneyInvestido() {
        return moneyInvestido;
    }

    public void setMoneyInvestido(int moneyInvestido) {
        this.moneyInvestido = moneyInvestido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalDate getDataExpira() {
        return dataExpira;
    }

    public void setDataExpira(LocalDate dataExpira) {
        this.dataExpira = dataExpira;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
    
    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
    
    public boolean estaNoPrazo() {
        return LocalDate.now().isBefore(dataExpira);
    }
}
