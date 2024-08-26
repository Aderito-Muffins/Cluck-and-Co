package Source.Control;

import Source.Model.Estoque;
import static Source.Control.UsuarioFicheiro.desktopPath;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EstoqueFicheiro {

    public void escreverFileEstoque(List<Estoque> estoqueList) throws IOException {
        try (ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream("DATA_BASE_ESTOQUE.cc"))) {
            objectOutput.writeObject(estoqueList);
        }
    }

    public List<Estoque> pegarFileEstoque() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInput = new FileInputStream("DATA_BASE_ESTOQUE.cc");
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {

            @SuppressWarnings("unchecked")
            List<Estoque> estoqueList = (List<Estoque>) objectInput.readObject();
            return estoqueList;
        }
    }

    public void adicionarEstoque(int quantidade, String funcionario) throws IOException, ClassNotFoundException {
        List<Estoque> estoqueList = pegarFileEstoque();

        // Gera um ID único
        int pid = gerarIdUnico(estoqueList);

        // Adiciona o novo produto ao estoque
        estoqueList.add(new Estoque(quantidade, pid, LocalDate.now(), funcionario));
        escreverFileEstoque(estoqueList);

        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
    }

    private int gerarIdUnico(List<Estoque> estoqueList) {
        Random random = new Random();
        int ID;
        boolean uniqueID = false;

        // Gera um ID único
        do {
            ID = random.nextInt(9000) + 100;
            uniqueID = true;
            for (Estoque estoque : estoqueList) {
                if (estoque.getId() == ID) {
                    uniqueID = false;
                    break;
                }
            }
        } while (!uniqueID);

        return ID;
    }

    public void criarFicheiro() throws IOException {
        File file = new File("DATA_BASE_ESTOQUE.cc");
        if (!file.exists()) {
            List<Estoque> estoqueList = new ArrayList<>();
            escreverFileEstoque(estoqueList);
        }
    }

    public void removerEstoque(List<Estoque> estoques, int ID) throws IOException, ClassNotFoundException {
        Iterator<Estoque> iterator = estoques.iterator();
        while (iterator.hasNext()) {
            Estoque estoque = iterator.next();
            if (estoque.getId() == ID) {
                iterator.remove();
                break;
            }
        }
        escreverFileEstoque(estoques);
    }

    public DefaultTableModel modeloTabelaEstoque(List<Estoque> estoqueList) {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Quantidade de Ovos");
        columnNames.add("ID do Produto");
        columnNames.add("Funcionario");
        columnNames.add("Data de Compra");
        columnNames.add("Data de expiracao");
        columnNames.add("Valor investido");
        columnNames.add("Estado Expirado");

        Vector<Vector<Object>> data = new Vector<>();
        for (Estoque estoque : estoqueList) {
            Vector<Object> vector = new Vector<>();
            vector.add( estoque.getQuantidade() + " Ovos");
            vector.add(estoque.getId());
            vector.add(estoque.getFuncionario());
            vector.add(estoque.getDataCompra());
            vector.add(estoque.getDataExpira());
            vector.add(estoque.getMoneyInvestido() + " MTn");
            vector.add(estoque.isExpirado());
            if(estoque.getQuantidade() == 0){data.remove(data);}
            else{ data.add(vector);}
            
        }

        return new DefaultTableModel(data, columnNames);
    }
}
