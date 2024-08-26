package Source.View.Paineis;

import Source.Control.EstoqueFicheiro;
import Source.Control.UsuarioFicheiro;
import Source.View.MenuAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Source.Control.UsuarioFicheiro.desktopPath;

public class GerirStock extends JPanel implements ActionListener{
    private String Nome;
    private String UserType;
    private JButton btVoltarSTOCK;
    private JButton btAdicionarStock;
    private JButton btRemoverStock;
    private JLabel lbGestaoDeStock;
    private JLabel lbQuantidade;
    private JTextField txQuantidade;
    private JLabel lbProdID;
    private JTextField txProdID;
    private JScrollPane ScrollPaneStock;
    private JTable tabelaStock;
    private JLabel FormPaneImage_2;
    ImageIcon TopBarImageIcon= new ImageIcon(desktopPath + "/Interface/Assets/Images/topbar.png");
   private ImageIcon resizedTopBarImage_2;
    private Image imageTB_2;
private Font myFont;

    public GerirStock( String Nome, String UserType) throws IOException, ClassNotFoundException {
        setLayout(null);
        imageTB_2 = TopBarImageIcon.getImage().getScaledInstance(300, 450, Image.SCALE_DEFAULT);
        resizedTopBarImage_2 =  new ImageIcon(imageTB_2);
        this.Nome = Nome;
        this.UserType = UserType;
        // Initialize components
        btVoltarSTOCK = new JButton("Voltar");
        btAdicionarStock = new JButton("Aumentar stock");
        btRemoverStock = new JButton("Descontinuar produto");
        lbGestaoDeStock = new JLabel("Stock");
        lbQuantidade = new JLabel("Quantidade");
        txQuantidade = new JTextField();
        lbProdID = new JLabel("ID de Produto");
        txProdID = new JTextField();
        tabelaStock = new JTable();
        ScrollPaneStock = new JScrollPane(tabelaStock);
        FormPaneImage_2 = new JLabel();
        try{
            myFont = Font.createFont(Font.TRUETYPE_FONT,new File
                    (desktopPath + "/Interface/Assets/Fonts/eudoxus-sans/EudoxusSans-Bold.ttf")).deriveFont(20);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(myFont);
        }
        catch(IOException|FontFormatException e){
            e.printStackTrace();
        }

        Font EudoxusSansVersion=myFont.deriveFont(Font.PLAIN,19);
        Font EudoxusSansButton=myFont.deriveFont(Font.PLAIN,15);
        Font EudoxusSansIntro = myFont.deriveFont(Font.PLAIN,45);
        EstoqueFicheiro ef = new EstoqueFicheiro();
        tabelaStock.setModel(ef.modeloTabelaEstoque(ef.pegarFileEstoque()));
        txProdID.setEditable(false);
        FormPaneImage_2.setIcon(resizedTopBarImage_2);

        // Set bounds
        btVoltarSTOCK.setBounds(1015, 620, 150, 20);
        btAdicionarStock.setBounds(390, 620, 200, 25);
        btRemoverStock.setBounds(590, 620, 200, 25);
        lbGestaoDeStock.setBounds(410, 30, 700, 60);
        lbQuantidade.setBounds(800, 160, 200, 50);
        txQuantidade.setBounds(800, 200, 200, 25);
        lbProdID.setBounds(800, 240, 200, 50);
        txProdID.setBounds(800, 280, 200, 25);
        ScrollPaneStock.setBounds(155,150,600,450);
        FormPaneImage_2.setBounds(780, 105, 450, 540);

        // Set fonts
        btVoltarSTOCK.setFont(EudoxusSansButton);
        btAdicionarStock.setFont(EudoxusSansButton);
        btRemoverStock.setFont(EudoxusSansButton);
        lbGestaoDeStock.setFont(EudoxusSansIntro);
        lbQuantidade.setFont(EudoxusSansVersion);
        txQuantidade.setFont(EudoxusSansVersion);
        lbProdID.setFont(EudoxusSansVersion);
        txProdID.setFont(EudoxusSansVersion);

        // Add components to panel
        add(btVoltarSTOCK);
        add(btAdicionarStock);
        add(btRemoverStock);
        add(lbGestaoDeStock);
        add(lbQuantidade);
        add(txQuantidade);
        add(lbProdID);
        add(txProdID);
        add(ScrollPaneStock);
        add(FormPaneImage_2);

        // Add listeners
        btVoltarSTOCK.addActionListener(this);
        btAdicionarStock.addActionListener(this);
        btRemoverStock.addActionListener(this);
    }
    public void btAdicionarStockActionPerformed(ActionEvent evt) {
        if(txQuantidade.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Preencha os campos!");
        } else {
            try {
                int quantidade = Integer.parseInt(txQuantidade.getText());

                EstoqueFicheiro ef = new EstoqueFicheiro();
                ef.adicionarEstoque(quantidade, Nome);

                carregarDadosTabela();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Insira números válidos para a quantidade e o ID do produto");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar estoque: " + ex.getMessage());
            }
        }
    }
    public void carregarDadosTabela() throws IOException, FileNotFoundException, ClassNotFoundException {
                EstoqueFicheiro estoqueFile = new EstoqueFicheiro();
                tabelaStock.setModel(estoqueFile.modeloTabelaEstoque(estoqueFile.pegarFileEstoque()));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAdicionarStock) {
            if(txQuantidade.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Preencha os campos!");
            } else {
                try {
                    int quantidade = Integer.parseInt(txQuantidade.getText());

                    EstoqueFicheiro ef = new EstoqueFicheiro();
                    ef.adicionarEstoque(quantidade, Nome);

                    carregarDadosTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Insira números válidos para a quantidade e o ID do produto");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar estoque: " + ex.getMessage());
                }
            }
        }
        if (e.getSource() ==  btRemoverStock ){     if (tabelaStock.getSelectedRow()<0)
            JOptionPane.showMessageDialog(null, "Porfavor selecione um Produto da tabela");
        else{
            int opt = JOptionPane.showConfirmDialog(
                    null,
                    "Voce tem certeza que deseja eliminar este Produto?",
                    "Confirmacao",
                    JOptionPane.YES_NO_OPTION);
            if(opt==JOptionPane.YES_OPTION) {
                EstoqueFicheiro file = new EstoqueFicheiro();
                int ID = Integer.parseInt(txProdID.getText());
                try {
                    file.removerEstoque(file.pegarFileEstoque(), ID );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            try {
                carregarDadosTabela();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }}
        if(e.getSource() == btVoltarSTOCK){
            // Criar uma nova instância de MenuAdmin, assumindo que MenuAdmin é uma JFrame
            MenuAdmin menuAdmin = null;
            try {
                menuAdmin = new MenuAdmin(Nome, UserType);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            menuAdmin.setVisible(true);
            SwingUtilities.windowForComponent(this).dispose();

            // Remover a chamada para dispose(), pois a classe atual não é um JFrame
        }





    }

    public static void main(String[] args) {
        // Criar um JFrame para conter o painel HistoricoDeVendas
        JFrame frame = new JFrame("Gestao de Stock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700); // Tamanho personalizável
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criar uma instância do painel HistoricoDeVendas
        GerirStock GerirStock = null;
        try {
            GerirStock = new GerirStock("Nome", "Tipo de usuário");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace(); // Ou trate o erro de outra forma, se necessário
        }

        // Adicionar o painel ao JFrame
        frame.add(GerirStock);

        // Definir o JFrame como visível
        frame.setVisible(true);
    }




}
