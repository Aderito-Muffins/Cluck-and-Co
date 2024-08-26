package Source.View;

import Source.Control.EstoqueFicheiro;
import Source.Control.UsuarioFicheiro;
import Source.Control.VendaFicheiro;
import Source.View.Paineis.GerirStock;
import Source.View.Paineis.HistoricoDeVendas;
import static Source.Control.UsuarioFicheiro.desktopPath;
import Source.View.Paineis.Fornecedores;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuAdmin extends JFrame implements ActionListener {
    
    public static int precoovo = 8;
    private String Nome;
    private String UserType;
    private float lucroDia;
    int ScreenHeight= 700;
    int ScreenWidth = 1200;
 
    
    
    JTable tabelaFuncionarios = new JTable();
    JTable tabelaStock = new JTable();
    JTable tabelaVendas= new JTable();
    JScrollPane ScrollPaneFuncionario= new JScrollPane(tabelaFuncionarios);
    JButton btLogOut= new JButton("Logout");
    JButton btHistorico=new JButton("Histórico de vendas");
    JButton btStock=new JButton("Gerir Stock");
    JButton btFuncionario = new  JButton("Gerir funcionários");
    JButton btFornecedor = new JButton("Gerir Fornecedores");
    JButton btVoltarHISTORICO= new JButton("Voltar");
    JButton btVoltarFUNCIONARIO= new JButton("Voltar");     
    
    JButton btAdicionarFunc= new JButton("Adicionar funcionário");
    JButton btRemoverFunc= new JButton("Remover funcionário");
    JButton btActualizarFunc= new JButton("REFRESCAR");
    JLabel lbFuncionariosCC= new JLabel(" Funcionários da Cluck & Co");
    JLabel lbGestaoDeStock = new JLabel("Stock");

    JLabel lbEstatisticas = new JLabel("Estatísticas");
    JLabel lbLucroDoDia_txt= new JLabel("Lucro do dia:");
    VendaFicheiro vf = new VendaFicheiro();
    JLabel lbLucroDoDia_num= new JLabel(Integer.toString(vf.getLucroDoDia())+ " MTn");
    JLabel lbLucroDaSemana_txt= new JLabel("Lucro semanal:");
    JLabel lbLucroDaSemana_num= new JLabel(Integer.toString(vf.getLucroSemanal())+ " MTn");
    JLabel lbLucroDoMes_txt= new JLabel("Lucro do mês:");
    JLabel lbLucroDoMes_num= new JLabel(Integer.toString(vf.getLucroMensal())+ " MTn");
//    JLabel lbTaxaDeRotatividade_txt= new JLabel("Taxa de Rotatividade:");
 //   JLabel lbTaxaDeRotatividade_num= new JLabel(Integer.toString(0));
    JLabel lbModificarPreco=new JLabel("Modificar o preço por ovo");
 //   JLabel lbAplicarDesconto = new JLabel("Aplicar Desconto a um produto");
    JLabel lbRelatorio_do_Dia= new JLabel("Relatório");
    JButton bt_Relatorio = new JButton("SALVAR");
    JTextArea TXA_Relatorio= new JTextArea();
   
   
    
  //      String[]produtos={"Ovo",};
   // JComboBox<String>ListaDeProdutos_1= new JComboBox<>(produtos);
   // JComboBox<String>ListaDeProdutos_2= new JComboBox<>(produtos);
    JTextField txModificarPreco = new JTextField();
    JTextField txAplicarDesconto= new JTextField();

    JLabel lbnomeDofuncionario = new JLabel ("Nome");
    JTextField txnomeDoFuncionario= new JTextField();
    JLabel lbBI = new JLabel ("BI");
    JTextField txBI= new JTextField();
    JLabel lbPass = new JLabel ("Senha");
    JPasswordField txPass = new JPasswordField();
    JLabel lbTelefone = new JLabel ("nº de Telefone");
    JTextField txTelefone = new JTextField();
    JLabel lbUsername = new JLabel ("Username");
    JTextField txUsername = new JTextField();
    JRadioButton PerfilAdmin = new JRadioButton("ADMINISTRADOR");
    JRadioButton PerfilFunc = new JRadioButton("VENDEDOR");

    ButtonGroup group = new ButtonGroup();
    String[]provincias= {"Maputo","Gaza","Inhambane","Manica","Sofala","Tete","Zambézia","Niassa","Nampula","Cabo Delgado"};
    JComboBox<String>Provincia = new JComboBox<>(provincias);

    JLabel lbQuantidade = new JLabel("Quantidade");
    JTextField txQuantidade = new JTextField();
    
    JLabel lbProdID   = new JLabel("ID de Produto");
    JTextField txProdID = new JTextField();
    

    //Tratamento de imagens
    JLabel LogoImage= new JLabel();
    ImageIcon LogoImageIcon = new ImageIcon(desktopPath + "/Interface/Assets/Images/logo.png");
    Image imageL=LogoImageIcon.getImage().getScaledInstance(160,100,Image.SCALE_DEFAULT);
    ImageIcon resizedLogoImage = new ImageIcon(imageL);
    
    JLabel BGImage=new JLabel();
    ImageIcon BGImageIcon = new ImageIcon(desktopPath + "/Interface/Assets/Images/Background.jpg");
    Image imageB=BGImageIcon.getImage().getScaledInstance(200,800,Image.SCALE_DEFAULT);
    ImageIcon resizedBGImage = new ImageIcon(imageB);
    
    JLabel TopBarImage=new JLabel();
    JLabel FormPaneImage_1=new JLabel();
    JLabel FormPaneImage_2=new JLabel();
    ImageIcon TopBarImageIcon= new ImageIcon(desktopPath + "/Interface/Assets/Images/topbar.png");
    Image imageTB_1 = TopBarImageIcon.getImage().getScaledInstance(950, 120, Image.SCALE_DEFAULT);
    Image imageTB_2 = TopBarImageIcon.getImage().getScaledInstance(300, 450, Image.SCALE_DEFAULT);

    ImageIcon resizedTopBarImage_1 = new ImageIcon(imageTB_1);
    ImageIcon resizedTopBarImage_2 = new ImageIcon(imageTB_2);

    JLabel profileImage=new JLabel();
    ImageIcon profileImageIcon= new ImageIcon(desktopPath + "/Interface/Assets/Images/profile.png");
    Image imagePR = profileImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    ImageIcon resizedProfileImage = new ImageIcon(imagePR);

    JPanel mainPanel; 
    JPanel HistoricoDeVendas;
    JPanel GerirStock;
    JPanel GerirFuncionario;
    JPanel Fornecedores;
    Font myFont;
    
    public MenuAdmin(String nome, String usertype) throws ClassNotFoundException, IOException{
        UsuarioFicheiro uf = new UsuarioFicheiro();
        uf.carregarTextoDeArquivo(TXA_Relatorio);
        Nome = nome;
        UserType = usertype;
        JLabel lbNomeUsuario=new JLabel(Nome);
        setTitle("Cluck&Co.");
        setSize(ScreenWidth,ScreenHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        HistoricoDeVendas = new HistoricoDeVendas(nome, usertype);
  
        //Tratamento de fonte
         try{
            myFont = Font.createFont(Font.TRUETYPE_FONT,new File
            (desktopPath + "/Interface/Assets/Fonts/eudoxus-sans/EudoxusSans-Bold.ttf")).deriveFont(20);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(myFont);
         }
         catch(IOException|FontFormatException e){
            e.printStackTrace();
            }
      //  Font EudoxusSans=myFont.deriveFont(Font.PLAIN,23); 
        Font EudoxusSansVersion=myFont.deriveFont(Font.PLAIN,19);
        Font EudoxusSansButton=myFont.deriveFont(Font.PLAIN,15);
        Font EudoxusSansIntro = myFont.deriveFont(Font.PLAIN,45);
        //Algumas inicializações
       
       //Listeners
       btLogOut.addActionListener(this);
       btFuncionario.addActionListener(this);
       btHistorico.addActionListener(this);
       btStock.addActionListener(this);
       btFornecedor.addActionListener(this);
       btVoltarFUNCIONARIO.addActionListener(this);
       btVoltarHISTORICO.addActionListener(this);
       UsuarioFicheiro fm = new UsuarioFicheiro();
       EstoqueFicheiro ef = new EstoqueFicheiro();
       //setIcon(Inicializaão das imagens)
        LogoImage.setIcon(resizedLogoImage);
        BGImage.setIcon(BGImageIcon);
        profileImage.setIcon(resizedProfileImage);
        TopBarImage.setIcon(resizedTopBarImage_1);
        FormPaneImage_1.setIcon(resizedTopBarImage_2);
        FormPaneImage_2.setIcon(resizedTopBarImage_2);
        tabelaFuncionarios.setModel(fm.buildTableModel(fm.pegarFileUser()));
        tabelaStock.setModel(ef.modeloTabelaEstoque(ef.pegarFileEstoque()));
        tabelaStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaStockMouse(evt);
            }
        });
    
        VendaFicheiro vendaFile = new VendaFicheiro();
        tabelaVendas.setModel(vendaFile.modeloTabelaEstoque(vendaFile.carregarHistoricoVendas()));
        group.add(PerfilAdmin);
        group.add(PerfilFunc);
        tabelaFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFuncMouse(evt);
            }
        });
    
      
        //setBounds
        LogoImage.setBounds(50,5,200,130);
        BGImage.setBounds(0,0,250,800);
        btLogOut.setBounds(55,620,150,25);
        btHistorico.setBounds(25,150,200,25);
        btStock.setBounds(25,200,200,25);
        btVoltarFUNCIONARIO.setBounds(1015,620,150,25);
        btFuncionario.setBounds(25,250,200,25);
        btFornecedor.setBounds(25,300,200,25);
        btActualizarFunc.setBounds(690,620,200,25);
        btActualizarFunc.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btActualizarFuncActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }});
        btRemoverFunc.setBounds(490,620,200,25);
        btRemoverFunc.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {             
                try {
                    btRemoverFuncActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
}});
        btAdicionarFunc.setBounds(290,620,200,25);
        btAdicionarFunc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btAdicionarFuncActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        lbNomeUsuario.setBounds(385, 50, 300, 25);
        lbFuncionariosCC.setBounds(290,30,700,60);
        lbGestaoDeStock.setBounds(410,30,700,60);
        lbEstatisticas.setBounds(300,130, 300, 60);
        lbRelatorio_do_Dia.setBounds(800,400,300, 60);
        bt_Relatorio.setBounds(1030,420,100, 40);
        bt_Relatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btRelatorioActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }});
        lbLucroDoDia_txt.setBounds(300,200, 300, 25);
        lbLucroDoDia_num.setBounds(420,200, 300, 25);
        lbLucroDaSemana_txt.setBounds(300,250, 300, 25);
        lbLucroDaSemana_num.setBounds(470,250, 300, 25);
        lbLucroDoMes_txt.setBounds(300,300, 300, 25);
        lbLucroDoMes_num.setBounds(440,300, 300, 25);
        lbModificarPreco.setBounds(300,550, 300, 25);
        txModificarPreco.setBounds(300,580, 120, 25);
        TXA_Relatorio.setBounds(660,480,500,200);
        ScrollPaneFuncionario.setBounds(155,150,ScreenWidth/2,450);
        FormPaneImage_1.setBounds(780,105,450,540);
        FormPaneImage_2.setBounds(780,105,450,540);
        TopBarImage.setBounds(250,0,950,120);
        profileImage.setBounds(265,10,100,100);
        lbnomeDofuncionario.setBounds(800,160,150,25);
        txnomeDoFuncionario.setBounds(950,160,100,25);
        lbUsername.setBounds(800,200,150,25);
        txUsername.setBounds(950,200,100,25);
        lbTelefone.setBounds(800,240,150,25);
        txTelefone.setBounds(950,240,100,25);
        addPhoneNumberKeyListener(txTelefone);
        lbBI.setBounds(800,280,150,25);
        txBI.setBounds(950,280,100,25);
        addIdentityCardKeyListener(txBI);
        lbPass.setBounds(800,320,150,25);
        txPass.setBounds(950,320,100,25);
        Provincia.setBounds(800,360,150,25);
        PerfilAdmin.setBounds(800,400,150,25);
        PerfilFunc.setBounds(800,440,150,25);
        lbQuantidade.setBounds(800,160,200,50);
        txQuantidade.setBounds(800,200,200,25);
        lbProdID.setBounds(800,240,200,50);
        txProdID.setBounds(800,280,200,25);
        txProdID.setEditable(false);
        //setFont
        btLogOut.setFont(EudoxusSansButton);
        btHistorico.setFont(EudoxusSansButton);
        btStock.setFont(EudoxusSansButton);
        btFuncionario.setFont(EudoxusSansButton);
        btVoltarFUNCIONARIO.setFont(EudoxusSansButton);
        btAdicionarFunc.setFont(EudoxusSansButton);
        btRemoverFunc.setFont(EudoxusSansButton);
        btActualizarFunc.setFont(EudoxusSansButton);
        btFornecedor.setFont(EudoxusSansButton);
        lbNomeUsuario.setFont(EudoxusSansVersion);
        lbFuncionariosCC.setFont(EudoxusSansIntro);
        lbGestaoDeStock.setFont(EudoxusSansIntro);
        lbEstatisticas.setFont(EudoxusSansIntro);
        lbLucroDoDia_txt.setFont(EudoxusSansVersion);
        lbLucroDoDia_num.setFont(EudoxusSansVersion);
        lbLucroDaSemana_txt.setFont(EudoxusSansVersion);
        lbLucroDaSemana_num.setFont(EudoxusSansVersion);
        lbLucroDoMes_txt.setFont(EudoxusSansVersion);
        lbLucroDoMes_num.setFont(EudoxusSansVersion);
        lbRelatorio_do_Dia.setFont(EudoxusSansIntro);
        lbModificarPreco.setFont(EudoxusSansVersion);
        txModificarPreco.setFont(EudoxusSansButton);
        addIntegerKeyListener(txModificarPreco);
        TXA_Relatorio.setFont(EudoxusSansButton);
        bt_Relatorio.setFont(EudoxusSansButton);
        lbnomeDofuncionario.setFont(EudoxusSansButton);
        txnomeDoFuncionario.setFont(EudoxusSansButton);
        addFullNameKeyListener(txnomeDoFuncionario);
        lbUsername.setFont(EudoxusSansButton);
        txUsername.setFont(EudoxusSansButton);
        addUsernameKeyListener(txUsername);
        lbTelefone.setFont(EudoxusSansButton);
        txTelefone.setFont(EudoxusSansButton);
        lbBI.setFont(EudoxusSansButton);
        txBI.setFont(EudoxusSansButton);
        lbPass.setFont(EudoxusSansButton);
        txPass.setFont(EudoxusSansButton);
        addPasswordKeyListener(txPass);
        Provincia.setFont(EudoxusSansButton);
        lbQuantidade.setFont(EudoxusSansVersion);
        txQuantidade.setFont(EudoxusSansVersion); 
        addIntegerKeyListener(txQuantidade);
        lbProdID.setFont(EudoxusSansVersion);
        txProdID.setFont(EudoxusSansVersion);
        addIntegerKeyListener(txProdID);
    
        //Adiciona os componentes ao painel
        mainPanel=new JPanel();
        mainPanel.setLayout(null);        
        HistoricoDeVendas= new HistoricoDeVendas(nome, UserType);
        GerirStock = new GerirStock(nome, UserType);
        Fornecedores = new Fornecedores();
        GerirFuncionario=new JPanel();
        GerirFuncionario.setLayout(null); 
        mainPanel.add(btLogOut);
        mainPanel.add(btHistorico);
        mainPanel.add(btStock);
        mainPanel.add(btFuncionario);
        mainPanel.add(btFornecedor);
        mainPanel.add(LogoImage); 
        mainPanel.add(BGImage); 
        mainPanel.add(lbNomeUsuario);   
        mainPanel.add(profileImage);
        mainPanel.add(TopBarImage); 
        mainPanel.add (lbEstatisticas);
        mainPanel.add(lbRelatorio_do_Dia);
        mainPanel.add(lbLucroDoDia_txt);
        mainPanel.add(lbLucroDoDia_num);
        mainPanel.add (lbLucroDaSemana_txt);
        mainPanel.add( lbLucroDaSemana_num);
        mainPanel.add (lbLucroDoMes_txt);
        mainPanel.add (lbLucroDoMes_num);
        mainPanel.add(lbModificarPreco);
        mainPanel.add(txModificarPreco);
        mainPanel.add(TXA_Relatorio);
        mainPanel.add(bt_Relatorio);
        
        
        GerirFuncionario.add(lbnomeDofuncionario);
        GerirFuncionario.add(txnomeDoFuncionario);
        GerirFuncionario.add (lbUsername);
        GerirFuncionario.add (txUsername);
        GerirFuncionario.add(lbTelefone);
        GerirFuncionario.add(txTelefone);
        GerirFuncionario.add(lbBI);
        GerirFuncionario.add(txBI);
        GerirFuncionario.add(lbPass);
        GerirFuncionario.add(txPass);
        GerirFuncionario.add (Provincia);
        GerirFuncionario.add(PerfilAdmin);
        GerirFuncionario.add(PerfilFunc);
        GerirFuncionario.add(btVoltarFUNCIONARIO);
        GerirFuncionario.add(btAdicionarFunc);
        GerirFuncionario.add(btRemoverFunc);
        GerirFuncionario.add(btActualizarFunc);
        GerirFuncionario.add(lbFuncionariosCC);
        GerirFuncionario.add(ScrollPaneFuncionario);
        GerirFuncionario.add(FormPaneImage_1);


        add(mainPanel);
    }     
    // Eventos
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btLogOut){
           
            Login a = new Login();
            a.setVisible(true);
            a.setDefaultCloseOperation(EXIT_ON_CLOSE); 
             dispose();
        }

        if(e.getSource()==btVoltarHISTORICO ||e.getSource()==btVoltarFUNCIONARIO){
            try {
                
                MenuAdmin c= new MenuAdmin(Nome, UserType);
                c.setVisible(true);
                c.setDefaultCloseOperation(EXIT_ON_CLOSE);
                dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if( e.getSource()==btHistorico){
            remove(mainPanel);
            add(HistoricoDeVendas);
            revalidate();
        }
        if( e.getSource()==btStock){
            remove(mainPanel);
            add(GerirStock);
            revalidate();
        }
        if( e.getSource()==btFuncionario){
            remove(mainPanel);
            add(GerirFuncionario);
            revalidate();
        }
        if(e.getSource() == btFornecedor){
            remove(mainPanel);
            add(Fornecedores);
            revalidate();}
           if( e.getSource()==bt_Relatorio){
           UsuarioFicheiro uf = new UsuarioFicheiro();
           uf.salvarTextoEmArquivo(TXA_Relatorio);
           uf.carregarTextoDeArquivo(TXA_Relatorio);
        }

    }
    
     public void addIntegerKeyListener(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char inputChar = e.getKeyChar();
                if (!Character.isDigit(inputChar) && inputChar != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                } if (inputChar == KeyEvent.VK_ENTER) {  
                    int MP = Integer.parseInt(txModificarPreco.getText());
                    if(MP<=0) {JOptionPane.showMessageDialog(null, "Preco Impossivel");}
                    else{precoovo = MP;}
                    JOptionPane.showMessageDialog(null, "MODIFICACAO FEITA");}
                
            }
        });
}
     public void addIdentityCardKeyListener(JTextField textField) {
    textField.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            String idCard = textField.getText();
            if (isValidIDCard(idCard)) {
                textField.setBackground(Color.GREEN);
            } else {
                textField.setBackground(Color.RED);
            }
        }

        private boolean isValidIDCard(String idCard) {
            // O bilhete de identidade deve ter 13 caracteres, 12 são quaisquer números, o último caractere é uma letra maiúscula.
            String idCardPattern = "^[0-9]{12}[A-Z]$";
            Pattern pattern = Pattern.compile(idCardPattern);
            Matcher matcher = pattern.matcher(idCard);

            return matcher.matches();
        }
    });
}

     
     public void addFullNameKeyListener(JTextField textField) {
    textField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            String text = textField.getText();
            int length = text.length();

            // Verifica se o caractere é uma letra ou espaço
            if (!Character.isLetter(e.getKeyChar()) && !Character.isSpaceChar(e.getKeyChar())) {
                e.consume(); // Ignora o caractere inválido
            }

            // Verifica se a primeira letra de cada palavra é maiúscula
            else if (length > 0 && Character.isSpaceChar(text.charAt(length - 1)) && !Character.isUpperCase(e.getKeyChar())) {
                e.consume(); // Ignora o caractere inválido
            }

            // Verifica se a primeira letra do texto é maiúscula
            else if (length == 0 && !Character.isUpperCase(e.getKeyChar())) {
                e.consume(); // Ignora o caractere inválido
            }

            // Verifica se o comprimento total excede 25 caracteres
            else if (length >= 25) {
                e.consume(); // Ignora o caractere inválido
            }
        }
    });
}
public void addPasswordKeyListener(JPasswordField passwordField) {
    passwordField.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            String password = new String(passwordField.getPassword());
            if (isValidPassword(password)) {
                passwordField.setBackground(Color.GREEN);
            } else {
                passwordField.setBackground(Color.RED);
            }
        }

        private boolean isValidPassword(String password) {
            // A senha deve ter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula e um número.
            String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
            Pattern pattern = Pattern.compile(passwordPattern);
            Matcher matcher = pattern.matcher(password);

            return matcher.matches();
        }
    });
}

public void addPhoneNumberKeyListener(JTextField textField) {
    textField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            String text = textField.getText();
            int length = text.length();

            // Validação do prefixo "+2588"
            if (length < 5 && !"+2588".startsWith(text + e.getKeyChar())) {
                e.consume(); // Ignora o caractere inválido
            }

            // Validação do sexto caractere (entre 2 e 7)
            else if (length == 5 && !(e.getKeyChar() >= '2' && e.getKeyChar() <= '7')) {
                e.consume(); // Ignora o caractere inválido
            }

            // Validação dos demais caracteres (dígitos 0 a 9)
            else if (length >= 6 && length < 13 && !Character.isDigit(e.getKeyChar())) {
                e.consume(); // Ignora o caractere inválido
            }

            // Validação do comprimento total (13 caracteres)
            else if (length >= 13) {
                e.consume(); // Ignora o caractere inválido
            }
        }
    });
}




public void addUsernameKeyListener(JTextField textField) {
    textField.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
            char inputChar = e.getKeyChar();
            // Verifica se o caractere é válido para um nome de usuário
            if (!Character.isLetterOrDigit(inputChar) && inputChar != '_' && inputChar != KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
            if (textField.getText().length() >= 10 && inputChar != KeyEvent.VK_BACK_SPACE) {
                e.consume();}
        }
    });

    // Adiciona uma ação de verificação de comprimento mínimo e máximo do nome de usuário
    textField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Verifica se o nome de usuário tem pelo menos 5 caracteres
            if (textField.getText().length() < 5) {
                // Exibe uma mensagem de aviso se o nome de usuário for muito curto
                JOptionPane.showMessageDialog(null, "O nome de usuário deve ter pelo menos 5 caracteres.");
           textField.setText("");
            }
        }
    });
}   
    public void btAdicionarStockActionPerformed(java.awt.event.ActionEvent evt) {
    if(txQuantidade.getText().isEmpty() ){
        JOptionPane.showMessageDialog(null, "Preencha os campos!");
    } else {
        try {
            int quantidade = Integer.parseInt(txQuantidade.getText());

            EstoqueFicheiro ef = new EstoqueFicheiro();
            ef.adicionarEstoque(quantidade, Nome);

            carregarDadosTabela("Stock");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Insira números válidos para a quantidade e o ID do produto");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar estoque: " + ex.getMessage());
        }
    }
}
    public void btAdicionarFuncActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException{
        if (txnomeDoFuncionario.getText().equals("") || txUsername.getText().equals("") || txTelefone.getText().equals("") || txBI.getText().equals("") || txPass.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Porfavor preencha tudo!.");
        else{
            UsuarioFicheiro file = new UsuarioFicheiro();
        String Provincias = (String) Provincia.getSelectedItem();
        if(PerfilAdmin.isSelected()){
        file.adcionarUser(txnomeDoFuncionario.getText(), txBI.getText(), Provincias, txTelefone.getText(), txUsername.getText(), txPass.getText(), PerfilAdmin.getActionCommand());
        }
        if(PerfilFunc.isSelected()){
         file.adcionarUser(txnomeDoFuncionario.getText(), txBI.getText(), Provincias, txTelefone.getText(), txUsername.getText(), txPass.getText(), PerfilFunc.getActionCommand());
        }
        else{JOptionPane.showMessageDialog(null, "Porfavor preencha tudo!.");}
        carregarDadosTabela("Funcionario");
        
        }
    }
    
    public void btRelatorioActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException{
   UsuarioFicheiro uf = new UsuarioFicheiro();
           JOptionPane.showMessageDialog(null, "TEXTO GUARDADO!");
           uf.salvarTextoEmArquivo(TXA_Relatorio);
           uf.carregarTextoDeArquivo(TXA_Relatorio);
    }
    public void btRemoverFuncActionPerformed(java.awt.event.ActionEvent evt) throws IOException, FileNotFoundException, ClassNotFoundException{
    if (tabelaFuncionarios.getSelectedRow()<0)
            JOptionPane.showMessageDialog(null, "Porfavor selecione um usuario da tabela");
        else{
            int opt = JOptionPane.showConfirmDialog(
                    null,
                    "Voce tem certeza que deseja eliminar este usuario?",
                    "Confirmacao",
                    JOptionPane.YES_NO_OPTION);
            if(opt==JOptionPane.YES_OPTION) {
    UsuarioFicheiro file = new UsuarioFicheiro();
    file.removerUser(file.pegarFileUser(), txUsername.getText());
    }
             carregarDadosTabela("Funcionario");
    }}
   public void btRemoverStockActionPerformed(java.awt.event.ActionEvent evt) throws IOException, FileNotFoundException, ClassNotFoundException{
     if (tabelaStock.getSelectedRow()<0)
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
    file.removerEstoque(file.pegarFileEstoque(), ID );
    }
             carregarDadosTabela("Stock");
    }
    }
    
    public void btActualizarFuncActionPerformed(java.awt.event.ActionEvent evt) throws IOException, FileNotFoundException, ClassNotFoundException{
    carregarDadosTabela("Funcionario");
   
   }
  

 public void carregarDadosTabela(String nomeTabela) throws IOException, FileNotFoundException, ClassNotFoundException {
    switch (nomeTabela) {
        case "Funcionario":
            UsuarioFicheiro usuarioFile = new UsuarioFicheiro();
            tabelaFuncionarios.setModel(usuarioFile.buildTableModel(usuarioFile.pegarFileUser()));
            break;
        case "Stock":
            EstoqueFicheiro estoqueFile = new EstoqueFicheiro();
            tabelaStock.setModel(estoqueFile.modeloTabelaEstoque(estoqueFile.pegarFileEstoque()));
            break;
        default:
            throw new IllegalArgumentException("Nome da tabela não reconhecido: " + nomeTabela);
    }
}

   
     private void tabelaFuncMouse(java.awt.event.MouseEvent evt) {                                       
        int row = tabelaFuncionarios.getSelectedRow();
        int col = tabelaFuncionarios.getColumnCount();
        Object[] val = new Object[col];

        for(int i=0; i<col; i++) {
            val[i] = tabelaFuncionarios.getValueAt(row, i);
        }
        txnomeDoFuncionario.setText(val[0].toString());
        txUsername.setText(val[1].toString());
        txTelefone.setText(val[2].toString());
        txBI.setText(val[3].toString());
        txPass.setText(val[4].toString());
        Provincia.setSelectedItem(val[5].toString());
    }
     private void tabelaStockMouse(java.awt.event.MouseEvent evt) {                                       
        int row = tabelaStock.getSelectedRow();
        int col = tabelaStock.getColumnCount();
        Object[] val = new Object[col];

        for(int i=0; i<col; i++) {
            val[i] = tabelaStock.getValueAt(row, i);
        }
        txQuantidade.setText(val[0].toString());
        txProdID.setText(val[1].toString());
    }
    
    public static void main(String[]args) throws ClassNotFoundException, IOException{
       // EstoqueFicheiro EF = new EstoqueFicheiro();
      //  EF.adcionarEstoque(155, "EU");
        MenuAdmin c= new MenuAdmin("","");
        c.setVisible(true); 
        c.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
