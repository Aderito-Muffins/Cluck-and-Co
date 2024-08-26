/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Source.Model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author mufum
 */
public class Venda implements Serializable {
    private int ID;
    private int quantidade;
    private String nomeComprador;
    private String tipoPagamento;
    private LocalDate dataVenda;
    private float dinheiro; 

    public Venda(int idProduto, int quantidade, String nomeComprador, String tipoPagamento, LocalDate dataVenda, float dinheiro) {
        this.ID = idProduto;
        this.quantidade = quantidade;
        this.nomeComprador = nomeComprador;
        this.tipoPagamento = tipoPagamento;
        this.dataVenda = dataVenda;
        this.dinheiro = dinheiro;
    }

    public float getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(float dinheiro) {
        this.dinheiro = dinheiro;
    }

    // Getters e setters

    public int getID() {
        return ID;
    }

    public void setID(int idProduto) {
        this.ID = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}