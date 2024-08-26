package Source.View;

import Source.Control.EstoqueFicheiro;
import Source.Control.UsuarioFicheiro;
import Source.Control.VendaFicheiro;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuFuncionario extends JFrame implements ActionListener {
private String Nome;
private String UserType;
    
    int ScreenHeight= 800;
    int ScreenWidth = 1200;
    int UnityEggCounter=0;//Uma variável apenas para contar quantos ovos serão comprados
    int X6EggCounter=0;
    int X12EggCounter=0;
    int X18EggCounter=0;
    int QuntidadeTotal=0;
    
    float price;

    JTable tabelaStock = new JTable();
    JTable tabelaVendas= new JTable();

    Object[][] dados = {};
    Object[]colunasStock ={"ID","Quantidade","Data de entrada","Data de validade"};
    Object[]colunasVendas ={"Nome do cliente","Método de pagamento", "Valor pago", "Data da Compra"};
    
    DefaultTableModel modeloTabelaStock = new DefaultTableModel(dados,colunasStock);
    DefaultTableModel modeloTabelaVendas = new DefaultTableModel(dados,colunasVendas);

    JScrollPane ScrollPaneStock= new JScrollPane(tabelaStock);
    JScrollPane ScrollPaneVendas= new JScrollPane(tabelaVendas);
   
    Color smoothBlue=new Color(63,187,250,255);
    JButton btLogOut= new JButton("Logout");
    JButton btCompra = new JButton("Efectuar venda");
    JButton btCancelar = new JButton("Cancelar venda");
    JButton btHistorico=new JButton("Histórico de vendas");
    JButton btStock=new JButton("Visualizar Stock");
    JButton btVoltarHISTORICO= new JButton("Voltar"); 
    JButton btVoltarSTOCK= new JButton("Voltar"); 
   
  
    JLabel lbx1=new  JLabel("Unidades");
    JLabel lbx6=new  JLabel("Meia dúzia");
    JLabel lbx12=new  JLabel("Dúzia");
    JLabel lbx18=new  JLabel("Pacote especial x18");
    JLabel lbNomeDoCliente= new JLabel("Nome do cliente");
    JLabel lbMetodoDePagamento= new JLabel("Método de pagamento");
    JLabel lbQuantidadeDeOvos=new JLabel("Quantidade de ovos");
    JLabel lbDescontoAplicado=new JLabel("Desconto aplicado á compra");
    JLabel lbValorApagarText=new JLabel(" Valor a pagar");
    JLabel lbValorApagarNum = new JLabel(Float.toString(0)); 
    JLabel lbHitoricoDeVendas = new JLabel("Histórico de vendas");
    JLabel lbStock = new JLabel("Stock");
    
    JTextField txtNomeDoCliente = new JTextField();
    
    String[]metodos={"Cartão de crédito", "Cartão de débito", "Numerário"};
    JComboBox<String>cbMetodosDePagamento = new JComboBox<>(metodos);
    JTextField txtQuantidadeDeOvos = new JTextField();
    JTextField txtDescontoAplicado = new JTextField();
    UsuarioFicheiro s = new UsuarioFicheiro();
    //Tratamento de imagens
    JLabel LogoImage= new JLabel();
    ImageIcon LogoImageIcon = new ImageIcon(s.desktopPath + "/Interface/Assets/Images/logo.png");
    Image imageL=LogoImageIcon.getImage().getScaledInstance(160,100,Image.SCALE_DEFAULT);
    ImageIcon resizedLogoImage = new ImageIcon(imageL);
    
    JLabel BGImage=new JLabel();
    ImageIcon BGImageIcon = new ImageIcon(s.desktopPath + "/Interface/Assets/Images/Background.jpg");
    Image imageB=BGImageIcon.getImage().getScaledInstance(200,800,Image.SCALE_DEFAULT);
    ImageIcon resizedBGImage = new ImageIcon(imageB);

    JLabel x1Image=new JLabel();
    ImageIcon x1ImageIcon= new ImageIcon(s.desktopPath + "/Interface/Assets/Images/x1.png");
    Image imageQ1 = x1ImageIcon.getImage().getScaledInstance(200, 189, Image.SCALE_DEFAULT);
    ImageIcon resizedX1Image = new ImageIcon(imageQ1);

    
    JLabel x6Image=new JLabel();
    ImageIcon x6ImageIcon= new ImageIcon(s.desktopPath + "/Interface/Assets/Images/x6.png");
    Image imageQ6 = x6ImageIcon.getImage().getScaledInstance(200, 182, Image.SCALE_DEFAULT);
    ImageIcon resizedX6Image = new ImageIcon(imageQ6);

    
    JLabel x12Image=new JLabel();
    ImageIcon x12ImageIcon= new ImageIcon(s.desktopPath + "/Interface/Assets/Images/x12.png");
    Image imageQ12 = x12ImageIcon.getImage().getScaledInstance(200, 189, Image.SCALE_DEFAULT);
    ImageIcon resizedX12Image = new ImageIcon(imageQ12);

    
    JLabel x18Image=new JLabel();
    ImageIcon x18ImageIcon= new ImageIcon(s.desktopPath + "/Interface/Assets/Images/x18.png");
    Image imageQ18 = x18ImageIcon.getImage().getScaledInstance(200, 204, Image.SCALE_DEFAULT);
    ImageIcon resizedX18Image = new ImageIcon(imageQ18);

    JLabel profileImage=new JLabel();
    ImageIcon profileImageIcon= new ImageIcon(s.desktopPath + "/Interface/Assets/Images/profile.png");
    Image imagePR = profileImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    ImageIcon resizedProfileImage = new ImageIcon(imagePR);

    JLabel TopBarImage=new JLabel();
    JLabel DownBarImage=new JLabel();
    JLabel SquareX1=new JLabel();
    JLabel SquareX6=new JLabel();
    JLabel SquareX12=new JLabel();
    JLabel SquareX18=new JLabel();
    
    ImageIcon TopBarImageIcon= new ImageIcon(s.desktopPath + "/Interface/Assets/Images/topbar.png");
    Image imageTB_1 = TopBarImageIcon.getImage().getScaledInstance(950, 120, Image.SCALE_DEFAULT);
    Image imageTB_2 = TopBarImageIcon.getImage().getScaledInstance(460, 160, Image.SCALE_DEFAULT);
    Image imageTB_3 = TopBarImageIcon.getImage().getScaledInstance(940, 200, Image.SCALE_DEFAULT);
    ImageIcon resizedTopBarImage_1 = new ImageIcon(imageTB_1);
    ImageIcon resizedTopBarImage_2 = new ImageIcon(imageTB_2);
    ImageIcon resizedTopBarImage_3 = new ImageIcon(imageTB_3);
    
    JPanel mainPanel; 
    JPanel HistoricoDeVendas;
    JPanel VerStock;
    Font myFont;
    JButton btPlusX1=new JButton("+");
    JButton btMinusX1=new JButton("-");
    JTextField txtQuantidadeDeProdutoX1=new JTextField();

    JButton btPlusX6=new JButton("+");
    JButton btMinusX6=new JButton("-");
    JTextField txtQuantidadeDeProdutoX6=new JTextField();

    JButton btPlusX12=new JButton("+");
    JButton btMinusX12=new JButton("-");
    JTextField txtQuantidadeDeProdutoX12=new JTextField();

    JButton btPlusX18=new JButton("+");
    JButton btMinusX18=new JButton("-");
    JTextField txtQuantidadeDeProdutoX18=new JTextField();

    

    public MenuFuncionario(String nome, String usertype) throws IOException, FileNotFoundException, ClassNotFoundException{
         
        Nome = nome;
        UserType = usertype;
        JLabel lbNomeUsuario=new JLabel(Nome);

        setTitle("Cluck&Co.");
        setSize(ScreenWidth,ScreenHeight);
        setResizable(false);
        setLocationRelativeTo(null);  
        price=(float) MenuAdmin.precoovo;
        //Tratamento de fonte
         try{
            myFont = Font.createFont(Font.TRUETYPE_FONT,new File
            (s.desktopPath + "/Interface/Assets/Fonts/eudoxus-sans/EudoxusSans-Bold.ttf")).deriveFont(20);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(myFont);
         }
         catch(IOException|FontFormatException e){
            e.printStackTrace();
            }
       // Font EudoxusSans=myFont.deriveFont(Font.PLAIN,23); 
        Font EudoxusSansVersion=myFont.deriveFont(Font.PLAIN,19);
        Font EudoxusSansButton=myFont.deriveFont(Font.PLAIN,15);
        Font EudoxusSansIntro = myFont.deriveFont(Font.PLAIN,45);
        //Algumas inicializações
        txtQuantidadeDeOvos.setEditable(false);
        txtQuantidadeDeOvos.setText(Integer.toString(0));
        txtDescontoAplicado.setEditable(false);
        txtDescontoAplicado.setText("Nenhum");
        txtQuantidadeDeProdutoX1.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        txtQuantidadeDeProdutoX6.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        txtQuantidadeDeProdutoX12.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        txtQuantidadeDeProdutoX18.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        btCompra.setBackground(Color.GREEN);
        btCompra.setForeground(Color.BLACK);
        btCancelar.setBackground(Color.RED);
        btCancelar.setForeground(Color.WHITE);
        EstoqueFicheiro ef = new EstoqueFicheiro();
        VendaFicheiro vf = new VendaFicheiro();
        tabelaStock.setModel(ef.modeloTabelaEstoque(ef.pegarFileEstoque()));
        tabelaVendas.setModel(vf.modeloTabelaEstoque(vf.carregarHistoricoVendas()));
       
       //Listeners
       btLogOut.addActionListener(this);
       btCancelar.addActionListener(this);
       btPlusX1.addActionListener(this);
       btMinusX1.addActionListener(this);
       btPlusX6.addActionListener(this);
       btMinusX6.addActionListener(this);
       btPlusX12.addActionListener(this);
       btMinusX12.addActionListener(this);
       btPlusX18.addActionListener(this);
       btMinusX18.addActionListener(this);
       btHistorico.addActionListener(this);
       btStock.addActionListener(this);
       btVoltarSTOCK.addActionListener(this);
       btVoltarHISTORICO.addActionListener(this);
       //setIcon(Inicializaão das imagens)
        LogoImage.setIcon(resizedLogoImage);
        BGImage.setIcon(BGImageIcon);
        x1Image.setIcon(resizedX1Image);
        x6Image.setIcon(resizedX6Image);
        x12Image.setIcon(resizedX12Image);
        x18Image.setIcon(resizedX18Image);
        SquareX1.setIcon(resizedTopBarImage_2);
        SquareX6.setIcon(resizedTopBarImage_2);
        SquareX12.setIcon(resizedTopBarImage_2);
        SquareX18.setIcon(resizedTopBarImage_2);
        profileImage.setIcon(resizedProfileImage);
        TopBarImage.setIcon(resizedTopBarImage_1);
        DownBarImage.setIcon(resizedTopBarImage_3);

        //setBounds
        LogoImage.setBounds(50,5,200,130);
        BGImage.setBounds(0,0,250,800);
        btLogOut.setBounds(55,675,150,25);
        btHistorico.setBounds(25,150,200,25);
        btStock.setBounds(25,200,200,25);
        btVoltarHISTORICO.setBounds(1015,710,150,25);
        btVoltarSTOCK.setBounds(1015,710,150,20);
        btCompra.setBounds(850,710,150,20);
        btCompra.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            try {
                btCompraActionPerformed(evt);
            } catch (IOException ex) {
                Logger.getLogger(MenuFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
            }});
        btCancelar.setBounds(1015,710,150,20);
        x1Image.setBounds(250,95,455,230);
        x6Image.setBounds(720,95,455,230);
        x12Image.setBounds(250,270,455,230);
        x18Image.setBounds(720,270,455,230);
        TopBarImage.setBounds(250,0,950,120);
        profileImage.setBounds(265,10,100,100);
        lbNomeUsuario.setBounds(385, 50, 300, 25);
        lbx1.setBounds(460, 100, 100, 100);
        lbx6.setBounds(900, 100, 100, 100);
        lbx12.setBounds(460, 275, 100, 100);
        lbx18.setBounds(900, 275, 200, 100);
        SquareX1.setBounds(265,100,445,230);
        SquareX6.setBounds(725,100,455,230);
        SquareX12.setBounds(265,275,445,230);
        SquareX18.setBounds(725,275,455,230);
        lbNomeDoCliente.setBounds(280,520,200,100);
        lbMetodoDePagamento.setBounds(280,570,250,100);
        lbQuantidadeDeOvos.setBounds(280,620,300,100);
        lbDescontoAplicado.setBounds(280,670,300,100);
        txtNomeDoCliente.setBounds(600,560,200,20);
        cbMetodosDePagamento.setBounds(600,610,200,20);
        txtQuantidadeDeOvos.setBounds(600,660,200,20);
        txtDescontoAplicado.setBounds(600,710,200,20);
        DownBarImage.setBounds(265,540,910,200);
        lbValorApagarText.setBounds(840,475,500,200);
        lbValorApagarNum.setBounds(850,540,500,200);
        lbStock.setBounds(530,30,700,60);
        lbHitoricoDeVendas.setBounds(370,30,700,60);

        ScrollPaneStock.setBounds(155,150,900,450);
        ScrollPaneVendas.setBounds(155,150,900,450);

        btMinusX1.setBounds(463,180,45,25);
        txtQuantidadeDeProdutoX1.setBounds(510,180,100,25);
        btPlusX1.setBounds(610,180,45,25);

        btMinusX6.setBounds(900,180,45,25);
        txtQuantidadeDeProdutoX6.setBounds(947,180,100,25);
        btPlusX6.setBounds(1047,180,45,25);
        
        btMinusX12.setBounds(463,355,45,25);
        txtQuantidadeDeProdutoX12.setBounds(510,355,100,25);
        btPlusX12.setBounds(610,355,45,25);

        btMinusX18.setBounds(900,355,45,25);
        txtQuantidadeDeProdutoX18.setBounds(947,355,100,25);
        btPlusX18.setBounds(1047,355,45,25);
       
        //setFont
        btLogOut.setFont(EudoxusSansButton);
        btCompra.setFont(EudoxusSansButton);
        btCancelar.setFont(EudoxusSansButton);
        btHistorico.setFont(EudoxusSansButton);
        btVoltarHISTORICO.setFont(EudoxusSansButton);
        btStock.setFont(EudoxusSansButton);
        btVoltarSTOCK.setFont(EudoxusSansButton);
        lbNomeUsuario.setFont(EudoxusSansVersion);
        lbx1.setFont(EudoxusSansVersion);
        lbx6.setFont(EudoxusSansVersion);
        lbx12.setFont(EudoxusSansVersion);
        lbx18.setFont(EudoxusSansVersion);
        lbNomeDoCliente.setFont(EudoxusSansVersion);
        lbMetodoDePagamento.setFont(EudoxusSansVersion);
        lbQuantidadeDeOvos.setFont(EudoxusSansVersion);
        lbDescontoAplicado.setFont(EudoxusSansVersion);
        cbMetodosDePagamento.setFont(EudoxusSansButton);
        txtQuantidadeDeOvos.setFont(EudoxusSansButton);
        txtDescontoAplicado.setFont(EudoxusSansButton);
        txtNomeDoCliente.setFont(EudoxusSansButton);
        lbValorApagarText.setFont(EudoxusSansIntro);
        lbValorApagarNum.setFont(EudoxusSansIntro);
        lbStock.setFont(EudoxusSansIntro);
        lbHitoricoDeVendas.setFont(EudoxusSansIntro);
       
        btPlusX1.setFont(EudoxusSansButton);
        btMinusX1.setFont(EudoxusSansButton);
        txtQuantidadeDeProdutoX1.setFont(EudoxusSansButton);
       
        btPlusX6.setFont(EudoxusSansButton);
        btMinusX6.setFont(EudoxusSansButton);
        txtQuantidadeDeProdutoX6.setFont(EudoxusSansButton);
        
        btPlusX12.setFont(EudoxusSansButton);
        btMinusX12.setFont(EudoxusSansButton);
        txtQuantidadeDeProdutoX12.setFont(EudoxusSansButton);
        
        btPlusX18.setFont(EudoxusSansButton);
        btMinusX18.setFont(EudoxusSansButton);
        txtQuantidadeDeProdutoX18.setFont(EudoxusSansButton);
       
        //Adiciona os componentes ao painel
        mainPanel=new JPanel();
        VerStock = new JPanel();
        HistoricoDeVendas= new JPanel();
        mainPanel.setLayout(null);
        VerStock.setLayout(null);
        HistoricoDeVendas.setLayout(null);
        mainPanel.add(LogoImage);     
        mainPanel.add(btLogOut);
        mainPanel.add(btHistorico);
        mainPanel.add(btStock);
        mainPanel.add(btCompra);  
        mainPanel.add(btCancelar);

        mainPanel.add(btPlusX1);
        mainPanel.add(btMinusX1);
        mainPanel.add(txtQuantidadeDeProdutoX1);

        mainPanel.add(btPlusX6);
        mainPanel.add(btMinusX6);
        mainPanel.add(txtQuantidadeDeProdutoX6);

        mainPanel.add(btPlusX12);
        mainPanel.add(btMinusX12);
        mainPanel.add(txtQuantidadeDeProdutoX12);

        mainPanel.add(btPlusX18);
        mainPanel.add(btMinusX18);
        mainPanel.add(txtQuantidadeDeProdutoX18);
        
        mainPanel.add(lbx1);   
        mainPanel.add(lbx6); 
        mainPanel.add(lbx12); 
        mainPanel.add(lbx18); 
        mainPanel.add(BGImage);
        mainPanel.add(x1Image);
        mainPanel.add(x6Image);
        mainPanel.add(x12Image);
        mainPanel.add(x18Image); 
        mainPanel.add(lbValorApagarText);
        mainPanel.add(lbValorApagarNum);
        mainPanel.add(lbMetodoDePagamento);
        mainPanel.add(lbNomeDoCliente);
        mainPanel.add(lbDescontoAplicado);
        mainPanel.add(lbQuantidadeDeOvos);
        mainPanel.add(cbMetodosDePagamento);
        mainPanel.add(txtNomeDoCliente);
        mainPanel.add(txtQuantidadeDeOvos);
        mainPanel.add(txtDescontoAplicado);    
        mainPanel.add(SquareX1);
        mainPanel.add(SquareX6);
        mainPanel.add(SquareX12);
        mainPanel.add(SquareX18);
        mainPanel.add(profileImage);
        mainPanel.add(lbNomeUsuario);
        mainPanel.add(TopBarImage);
        mainPanel.add(DownBarImage);

        HistoricoDeVendas.add(btVoltarHISTORICO);
        HistoricoDeVendas.add(lbHitoricoDeVendas);
        HistoricoDeVendas.add(ScrollPaneVendas);

        VerStock.add(btVoltarSTOCK);
        VerStock.add(lbStock);
        VerStock.add(ScrollPaneStock);

        add(mainPanel);
    }     
 
    // Eventos
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btLogOut){
            dispose();
            Login a = new Login();
            a.setVisible(true);
            a.setDefaultCloseOperation(EXIT_ON_CLOSE);  
        }

        if (e.getSource()==btPlusX1){
            UnityEggCounter++;
            txtQuantidadeDeProdutoX1.setText(Integer.toString(UnityEggCounter));
            QuntidadeTotal+=1;
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal));
        }
        else if (e.getSource()==btMinusX1){
            if(UnityEggCounter>0){
             UnityEggCounter--;
            txtQuantidadeDeProdutoX1.setText(Integer.toString(UnityEggCounter));
            QuntidadeTotal-=1;
            if(QuntidadeTotal<=0){
                lbValorApagarNum.setText(Integer.toString(0));
                txtQuantidadeDeOvos.setText(Integer.toString(0));
            }
            else{
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal)); 
                }
            } 
        }

        if (e.getSource()==btPlusX6){
            X6EggCounter++;
            txtQuantidadeDeProdutoX6.setText(Integer.toString(X6EggCounter));
            QuntidadeTotal+=6;
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal));
        }
        else if (e.getSource()==btMinusX6){
            if(X6EggCounter>0){
             X6EggCounter--;
            txtQuantidadeDeProdutoX6.setText(Integer.toString(X6EggCounter));
            QuntidadeTotal-=6;
            if(QuntidadeTotal<=0){
                lbValorApagarNum.setText(Integer.toString(0));
                txtQuantidadeDeOvos.setText(Integer.toString(0));
            }
            else{
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal)); 
                }
            } 
        }

        if (e.getSource()==btPlusX12){
            X12EggCounter++;
            txtQuantidadeDeProdutoX12.setText(Integer.toString(X12EggCounter));
            QuntidadeTotal+=12;
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal));
        }
        else if (e.getSource()==btMinusX12){
            if(X12EggCounter>0){
             X12EggCounter--;
            txtQuantidadeDeProdutoX12.setText(Integer.toString(X12EggCounter));
            QuntidadeTotal-=12;
            if(QuntidadeTotal<=0){
                lbValorApagarNum.setText(Integer.toString(0));
                txtQuantidadeDeOvos.setText(Integer.toString(0));
            }
            else{
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal)); 
                }
            } 
        }

        if (e.getSource()==btPlusX18){
            X18EggCounter++;
            txtQuantidadeDeProdutoX18.setText(Integer.toString(X18EggCounter));
            QuntidadeTotal+=18;
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal));
        }
        else if (e.getSource()==btMinusX18){
            if(X18EggCounter>0){
             X18EggCounter--;
            txtQuantidadeDeProdutoX18.setText(Integer.toString(X18EggCounter));
            QuntidadeTotal-=18;
            if(QuntidadeTotal<=0){
                lbValorApagarNum.setText(Integer.toString(0));
                txtQuantidadeDeOvos.setText(Integer.toString(0));
            }
            else{
            txtQuantidadeDeOvos.setText(Integer.toString(QuntidadeTotal));
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal)); 
                }
 
            } 
        }

        if(e.getSource()==btCancelar){
            txtNomeDoCliente.setText(null);
            txtQuantidadeDeOvos.setText(null);
            txtDescontoAplicado.setText(null);
            X6EggCounter=0;
            X12EggCounter=0;
            X18EggCounter=0;
            QuntidadeTotal=0;
            QuntidadeTotal=0;  
            txtQuantidadeDeProdutoX1.setText(null);
            txtQuantidadeDeProdutoX6.setText(null);
            txtQuantidadeDeProdutoX12.setText(null);
            txtQuantidadeDeProdutoX18.setText(null);
            lbValorApagarNum.setText(Float.toString(price*QuntidadeTotal)); 
            
        }

        if(e.getSource()==btVoltarHISTORICO ||e.getSource()==btVoltarSTOCK){
            try {
                 MenuFuncionario b= new MenuFuncionario(Nome, UserType);
                b.setVisible(true);
                b.setDefaultCloseOperation(EXIT_ON_CLOSE);
                dispose();
               
            } catch (IOException ex) {
                Logger.getLogger(MenuFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if( e.getSource()==btHistorico){
         
                
          
                add(HistoricoDeVendas);
                remove(mainPanel);
                revalidate();
          
        }
        if( e.getSource()==btStock){
            try {
                add(VerStock);
                remove(mainPanel);
                carregarDadosTabela("Stock");
               
                revalidate();
            } catch (IOException ex) {
                Logger.getLogger(MenuFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void btCompraActionPerformed(ActionEvent e) throws IOException, IOException, ClassNotFoundException {
     
       if(lbQuantidadeDeOvos.getText().equals("") || lbNomeDoCliente.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Preencha os espacos!");
       }
       else {
       int QuantidOvos = Integer.parseInt(txtQuantidadeDeOvos.getText());
       String Nome = txtNomeDoCliente.getText();
       String Payment = (String) cbMetodosDePagamento.getSelectedItem();
       LocalDate Data = LocalDate.now();
       float Dinheiro =Float.parseFloat( lbValorApagarNum.getText());
       VendaFicheiro vf = new VendaFicheiro();
 
       if(vf.realizarVenda(QuantidOvos)){
       vf.adicionarVenda(QuantidOvos, Nome, Payment, Data, Dinheiro);
       carregarDadosTabela("Venda");
       }//else{JOptionPane.showMessageDialog(null, "Impossivel fazer a venda, porque o valor ultrapassa o estoque");}
    }}
    public void carregarDadosTabela(String nomeTabela) throws IOException, FileNotFoundException, ClassNotFoundException {
    switch (nomeTabela) {
        case "Stock":
            EstoqueFicheiro estoqueFile = new EstoqueFicheiro();
            tabelaStock.setModel(estoqueFile.modeloTabelaEstoque(estoqueFile.pegarFileEstoque()));
            break;
        case "Venda":
            VendaFicheiro vendaFile = new VendaFicheiro();
            tabelaVendas.setModel(vendaFile.modeloTabelaEstoque(vendaFile.carregarHistoricoVendas()));
        default:
            throw new IllegalArgumentException("Nome da tabela não reconhecido: " + nomeTabela);
    }
}
    
    
    
    public static void main(String[]args) throws IOException, FileNotFoundException, ClassNotFoundException{
       
        VendaFicheiro vf = new VendaFicheiro();
        vf.criarFicheiroVenda();
        MenuFuncionario b= new MenuFuncionario("","");
        b.setVisible(true); 
        b.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
