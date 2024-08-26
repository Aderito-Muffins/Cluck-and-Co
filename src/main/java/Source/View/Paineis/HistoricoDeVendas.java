package Source.View.Paineis;

import Source.Control.VendaFicheiro;
import Source.View.MenuAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Source.Control.UsuarioFicheiro.desktopPath;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class HistoricoDeVendas extends JPanel  implements ActionListener{
    private JTable tabelaVendas= new JTable();
    private JScrollPane ScrollPaneVendas= new JScrollPane(tabelaVendas);
    private JLabel lbHitoricoDeVendas = new JLabel("Histórico de vendas");
    private JButton btVoltarHISTORICO= new JButton("Voltar");
    private Font myFont;

    private String name,UserType;

    public HistoricoDeVendas(String Name, String UserType) throws IOException, ClassNotFoundException {
        this.name = Name;
        this.UserType = UserType;

        setLayout(null);

        try{
            myFont = Font.createFont(Font.TRUETYPE_FONT,new File
                    (desktopPath + "/Interface/Assets/Fonts/eudoxus-sans/EudoxusSans-Bold.ttf")).deriveFont(20);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(myFont);
        }
        catch(IOException|FontFormatException e){
            e.printStackTrace();
        }

        Font EudoxusSansIntro = myFont.deriveFont(Font.PLAIN,45);
        VendaFicheiro vendaFile = new VendaFicheiro();
        tabelaVendas.setModel(vendaFile.modeloTabelaEstoque(vendaFile.carregarHistoricoVendas()));

        btVoltarHISTORICO.setBounds(1015,620,150,25);
        btVoltarHISTORICO.addActionListener(this);
        ScrollPaneVendas.setBounds(155,150,900,450);
        lbHitoricoDeVendas.setBounds(370,30,700,60);

        lbHitoricoDeVendas.setFont(EudoxusSansIntro);


       add(btVoltarHISTORICO);
       add(lbHitoricoDeVendas);
       add(ScrollPaneVendas);
   }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btVoltarHISTORICO) {
            // Criar uma nova instância de MenuAdmin, assumindo que MenuAdmin é uma JFrame
            MenuAdmin menuAdmin = null;
            try {
                menuAdmin = new MenuAdmin(name, UserType);
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
        JFrame frame = new JFrame("Histórico de Vendas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700); // Tamanho personalizável
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criar uma instância do painel HistoricoDeVendas
        HistoricoDeVendas historicoDeVendas = null;
        try {
            historicoDeVendas = new HistoricoDeVendas("Nome", "Tipo de usuário");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace(); // Ou trate o erro de outra forma, se necessário
        }

        // Adicionar o painel ao JFrame
        frame.add(historicoDeVendas);

        // Definir o JFrame como visível
        frame.setVisible(true);
    }

}
