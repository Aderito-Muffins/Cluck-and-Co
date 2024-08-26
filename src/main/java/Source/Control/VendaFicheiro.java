package Source.Control;

import Source.View.MenuAdmin;
import Source.Model.Estoque;
import static Source.Control.UsuarioFicheiro.desktopPath;
import Source.Model.Venda;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VendaFicheiro {

    public List<Venda> carregarHistoricoVendas() throws IOException, ClassNotFoundException {
        File file = new File("HISTORICO_VENDAS.cc");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (List<Venda>) ois.readObject();
            }
        }
        return new ArrayList<>();
    }

  /*  public void fazerCompra(int quantidade, String nomeComprador, String tipoPagamento) {
      EstoqueFicheiro fs = new EstoqueFicheiro();
        try {
            
            List<Estoque> estoqueList = fs.pegarFileEstoque(); // Alteração aqui
            List<Estoque> produtosFiltrados = filtrarProdutos(estoqueList);

            if (!produtosFiltrados.isEmpty()) {
                Estoque produtoMaisAntigo = produtosFiltrados.get(0);

                if (produtoMaisAntigo.getDataExpira().isAfter(LocalDate.now())) {
                    System.out.println("Erro: Produto fora do prazo de validade!");
                    return;
                }

                adicionarVendaAoHistorico(quantidade, nomeComprador, tipoPagamento);
                atualizarEstoque(estoqueList, produtoMaisAntigo.getId(), quantidade);
            } else {
                System.out.println("Erro: Produto não encontrado!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
 
    
    public int getLucroDoDia() throws IOException, ClassNotFoundException{
    int vendaS = 0 ;
 //   int estoqueS = 0 ;
    EstoqueFicheiro ef = new EstoqueFicheiro();
    

    for(Venda vendas : carregarHistoricoVendas()){
    if(vendas.getDataVenda().equals(LocalDate.now()))
    vendaS = vendaS + vendas.getQuantidade();
    }
   // for(Estoque estoque : ef.pegarFileEstoque()){
   // estoqueS = estoqueS + estoque.getQuantidade();
   // }
    int vRv = vendaS * MenuAdmin.precoovo; 
    int vRe = vendaS * 6;
    int lucro =  vRv - vRe;
    return lucro;
    }
      public int getLucroSemanal() throws IOException, ClassNotFoundException{
    int vendaS = 0 ;
    int estoqueS = 0 ;
    long diferencaDias;
    EstoqueFicheiro ef = new EstoqueFicheiro();
    

    for(Venda vendas : carregarHistoricoVendas()){
        
    diferencaDias = ChronoUnit.DAYS.between(vendas.getDataVenda(), LocalDate.now());
        
        // Verifica se a diferença é maior ou igual a 7 dias
    if(diferencaDias<=7)
    vendaS = vendaS + vendas.getQuantidade();
    }
   // for(Estoque estoque : ef.pegarFileEstoque()){
   // estoqueS = estoqueS + estoque.getQuantidade();
   // }
    int vRv = vendaS * MenuAdmin.precoovo; 
    int vRe = vendaS * 6;
    int lucro = vRv - vRe;
    return lucro;
    }
      
      public int getLucroMensal() throws IOException, ClassNotFoundException{
    int vendaS = 0 ;
    int estoqueS = 0 ;
    long diferencaDias;
    EstoqueFicheiro ef = new EstoqueFicheiro();
    

    for(Venda vendas : carregarHistoricoVendas()){
        
    diferencaDias = ChronoUnit.DAYS.between(vendas.getDataVenda(), LocalDate.now());
        
        // Verifica se a diferença é maior ou igual a 7 dias
    if(diferencaDias<=30)
    vendaS = vendaS + vendas.getQuantidade();
    }
   // for(Estoque estoque : ef.pegarFileEstoque()){
   // estoqueS = estoqueS + estoque.getQuantidade();
   // }
    int vRv = vendaS * MenuAdmin.precoovo; 
    int vRe = vendaS * 6;
    int lucro = vRv - vRe;
    return lucro;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 public boolean realizarVenda( int Quant) throws IOException, ClassNotFoundException {
    EstoqueFicheiro ef = new EstoqueFicheiro();
    List<Estoque> estoqueAtualizado = new ArrayList<>();
    // Encontre o produto no estoque
    for (Estoque p : ef.pegarFileEstoque()) {
        if (p.isExpirado()) {
            JOptionPane.showMessageDialog(null, "IMPOSSIVEL FAZER A VENDA, LOTE OVOS EXPIRADO!");
            estoqueAtualizado.add(p);
            continue;
        }
        if (Quant <= p.getQuantidade()) {
            p.setQuantidade(p.getQuantidade() - Quant);
            estoqueAtualizado.add(p);
            // Atualize o estoque no arquivo aqui
            ef.escreverFileEstoque(estoqueAtualizado);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "IMPOSSIVEL FAZER A COMPRA, O VALOR ULTRAPASSA O ESTOQUE EXISTENTE!");
            estoqueAtualizado.add(p);
        }
    }
    ef.escreverFileEstoque(estoqueAtualizado);
    return false;
}          
      
    
    
    
    
    
    
    
    
    
    
    
    
    
    public DefaultTableModel modeloTabelaEstoque(List<Venda> vendaList) {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID da Venda");
        columnNames.add("Quantidade Vendida");
        columnNames.add("Nome do Comprador");
        columnNames.add("Pagamento");
        columnNames.add("Data de Venda");
        columnNames.add("Valor de venda");

        Vector<Vector<Object>> data = new Vector<>();
        for (Venda venda : vendaList) {
            Vector<Object> vector = new Vector<>();
            vector.add(venda.getID());
            vector.add(venda.getQuantidade() + " Ovos");
            vector.add(venda.getNomeComprador());
            vector.add(venda.getTipoPagamento());
            vector.add(venda.getDataVenda());
            vector.add(venda.getDinheiro() + " MTn");

            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
    private List<Estoque> filtrarProdutos(List<Estoque> estoqueList) {
        return estoqueList.stream()
                .filter(Estoque::estaNoPrazo) // Alteração aqui
                .sorted(Comparator.comparing(Estoque::getDataExpira))
                .toList();
    }

   /* private void atualizarEstoque(List<Estoque> estoqueList, int idProduto, int quantidade) throws IOException {
        Iterator<Estoque> iterator = estoqueList.iterator();
        int quantidadeRestante = quantidade;

        while (iterator.hasNext() && quantidadeRestante > 0) {
            Estoque produto = iterator.next();
            if (produto.getId() == idProduto) {
                int quantidadeDisponivel = produto.getQuantidade();
                int quantidadeRetirada = Math.min(quantidadeDisponivel, quantidadeRestante);
                produto.setQuantidade(quantidadeDisponivel - quantidadeRetirada);
                quantidadeRestante -= quantidadeRetirada;

                if (produto.getQuantidade() == 0) {
                    iterator.remove();
                }
            }
        }

        if (quantidadeRestante == 0) {
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Erro: Quantidade insuficiente em estoque!");
        }
        EstoqueFicheiro fs = new EstoqueFicheiro();
        fs.escreverFileEstoque(estoqueList); // Alteração aqui
    }*/

    public void adicionarVenda( int quantidade, String nomeComprador, String tipoPagamento, LocalDate dataVenda, float Dinh) throws IOException, ClassNotFoundException {
        List<Venda> vendaList = carregarHistoricoVendas();

        // Gera um ID único
        int pid = gerarIdUnico(vendaList);

        // Adiciona o novo produto ao estoque
        vendaList.add(new Venda(pid, quantidade, nomeComprador, tipoPagamento, dataVenda, Dinh ));
        salvarHistoricoVendas(vendaList);

        JOptionPane.showMessageDialog(null, "Venda feita com sucesso!");
    }

    private int gerarIdUnico(List<Venda> vendaList) {
        Random random = new Random();
        int ID;
        boolean uniqueID = false;

        // Gera um ID único
        do {
            ID = random.nextInt(9000) + 100;
            uniqueID = true;
            for (Venda venda : vendaList) {
                if (venda.getID() == ID) {
                    uniqueID = false;
                    break;
                }
            }
        } while (!uniqueID);

        return ID;
    }



    public void criarFicheiroVenda() throws IOException {
        File file = new File("HISTORICO_VENDAS.cc");
        if (!file.exists()) {
            List<Venda> vendaList = new ArrayList<>();
            salvarHistoricoVendas(vendaList); // Alteração aqui
        }
    }

    public void salvarHistoricoVendas(List<Venda> historicoVendas) throws IOException {
        File file = new File("HISTORICO_VENDAS.cc");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(historicoVendas);
        }
    }
}
