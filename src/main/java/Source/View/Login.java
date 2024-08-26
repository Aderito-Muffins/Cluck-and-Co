package Source.View;


import Source.Control.EstoqueFicheiro;
import Source.Control.UsuarioFicheiro;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Login extends JFrame implements ActionListener{
    String username;

    int ScreenHeight=700;
    int ScreenWidth=500;
    Color smoothBlue=new Color(63,187,250,255);//Cor usada no loading 
    JLabel lbLoginUser = new JLabel("Nome de usuário");//Label usuario
    JTextField txLoginUser = new JTextField(20);//Campo para digitar o username
    JLabel lbLoginSenha = new JLabel("Senha");//Label senha
    JPasswordField psLoginSenha = new JPasswordField(20);
    JLabel lbVersion = new JLabel ("v 2.0");//label da versão
    JLabel LogoImage= new JLabel();//Label do logtipo
    JLabel BGImage=new JLabel();//label da imagem de fundo
    JLabel LoadingImage= new JLabel();//Label do gif
    JLabel lbBemVindo=new JLabel("Bem Vindo");
    JLabel lbCluckCo=new JLabel("á Cluck & Co.");
    JLabel lbUserFail=new JLabel("Usuário não encontrado");
    JLabel lbVerify=new JLabel("Verifique as credenciais");
    JLabel lbIncorrectCred= new JLabel("introduza as credenciais correctamente");
 

    //TRATAMENTO DE IMAGEM (LOGOTIPO)

    ImageIcon LogoImageIcon = new ImageIcon(UsuarioFicheiro.desktopPath + "/Interface/Assets/Images/logo.png");
    Image imageL = LogoImageIcon.getImage().getScaledInstance((int)(ScreenWidth*0.53),(int)(ScreenHeight*0.25),Image.SCALE_DEFAULT);
    ImageIcon resizedLogoImage = new ImageIcon(imageL);
   
    //TRATAMENTO DE IMAGEM (FUNDO)
    ImageIcon BGImageIcon = new ImageIcon(UsuarioFicheiro.desktopPath + "/Interface/Assets/Images/Background.jpg");
    Image imageB=BGImageIcon.getImage().getScaledInstance(600,800,Image.SCALE_DEFAULT);
    ImageIcon resizedBGImage = new ImageIcon(imageB);        
    
    //TRATAMENTO DE IMAGEM (LOGIN EFECTUADO COM SUCESS0)
    ImageIcon SucessImageIcon = new ImageIcon(UsuarioFicheiro.desktopPath + "/Interface/Assets/Images/MAIN_EGG.png");
    Image imageS=SucessImageIcon.getImage().getScaledInstance(350,350,Image.SCALE_DEFAULT);
    ImageIcon resizedSucessImage = new ImageIcon(imageS);
    
    //TRATAMENTO DE IMAGEM (LOGIN SEM SUCESS0)
    ImageIcon FailImageIcon = new ImageIcon(UsuarioFicheiro.desktopPath + "/Interface/Assets/Images/SAD_EGG.png");
    Image imageF=FailImageIcon.getImage().getScaledInstance(320,320,Image.SCALE_DEFAULT);
    ImageIcon resizedFailImage = new ImageIcon(imageF);

    //TRATAMENTO DE IMAGEM (GIF DE CARREGAMENTO)
     ImageIcon LoadingImageIcon = new ImageIcon(UsuarioFicheiro.desktopPath + "/Interface/Assets/Images/loading.gif");
     Image imageLo=LoadingImageIcon.getImage().getScaledInstance(280,243,Image.SCALE_DEFAULT);
     ImageIcon resizedLoadingImage = new ImageIcon(imageLo);

    JButton btEntrar = new JButton("Entrar");
    JButton btOKlogin= new JButton("Ok");
    Font myFont;
    JPanel panel;    
    JPanel sucessPanel;
    JPanel FailPanel;
    JLabel SucessImage= new JLabel();
    JLabel FailImage=new JLabel();
 
    public Login(){
        setTitle("Cluck&Co.");
        setSize(ScreenWidth,ScreenHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        //PAINEIS


        panel = new JPanel();
        sucessPanel= new JPanel();
        FailPanel= new JPanel();
        panel.setLayout(null);
        sucessPanel.setLayout(null);
        sucessPanel.setBackground(smoothBlue);
        FailPanel.setLayout(null);
        
        //Tratamento da fonte
        try{
            myFont = Font.createFont(Font.TRUETYPE_FONT,new File
            (UsuarioFicheiro.desktopPath + "/Interface/Assets/Fonts/eudoxus-sans/EudoxusSans-Bold.ttf")).deriveFont(20);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(myFont);
         }
         catch(IOException|FontFormatException e){
            e.printStackTrace();
            }
            LogoImage.setIcon(resizedLogoImage);
            BGImage.setIcon(resizedBGImage); 
            SucessImage.setIcon(resizedSucessImage);//Imagem de sucesso
            FailImage.setIcon(resizedFailImage);
            LoadingImage.setIcon(resizedLoadingImage);
            Font EudoxusSans=myFont.deriveFont(Font.PLAIN,(int)(ScreenWidth*0.04));
            Font EudoxusSansVersion=myFont.deriveFont(Font.PLAIN,(int)(ScreenWidth*0.0316));
            Font EudoxusSansButton=myFont.deriveFont(Font.PLAIN,(int)(ScreenWidth*0.025));
            Font EudoxusSansIntro = myFont.deriveFont(Font.PLAIN,(int)(ScreenWidth*0.075));
       int WidthX = (int)(ScreenWidth * 0.14 );
       int HeightY = (int)(ScreenHeight * 0.45 );


        // set bounds dos componetes
        lbLoginUser.setBounds(WidthX,HeightY,(int)(ScreenWidth*0.33),(int)(ScreenHeight*0.025));
        lbVersion.setBounds((int)(ScreenWidth*0.83),(int)(ScreenHeight*0.875),(int)(ScreenWidth * 0.1 ),(int)(ScreenHeight * 0.0625 ));
        lbLoginSenha.setBounds(WidthX,(int)(ScreenHeight*0.475),(int)(ScreenWidth*0.13),(int)(ScreenHeight*0.125));
        txLoginUser.setBounds(WidthX,(int)(ScreenHeight*0.4875),(int)(ScreenWidth*0.33),(int)(ScreenHeight*0.025));
        psLoginSenha.setBounds(WidthX,(int)(ScreenHeight*0.5625),(int)(ScreenWidth*0.33),(int)(ScreenHeight*0.025));
        btEntrar.setBounds((int)(ScreenWidth*0.66),(int)(ScreenHeight*0.75),(int)(ScreenWidth*0.16),(int)(ScreenHeight*0.03125));
        btOKlogin.setBounds((int)(ScreenWidth*0.40),(int)(ScreenHeight*0.75),(int)(ScreenWidth*0.16),(int)(ScreenHeight*0.03125));
        LogoImage.setBounds((int)(ScreenWidth*0.25),(int)(ScreenHeight*0.075),(int)(ScreenWidth*0.53),(int)(ScreenHeight*0.375));
        BGImage.setBounds(0,0,ScreenWidth*1,ScreenHeight*1);
        SucessImage.setBounds((int)(ScreenWidth*0.20),(int)(ScreenHeight*0.075),(int)(ScreenWidth*0.53),(int)(ScreenHeight*0.53));
        FailImage.setBounds((int)(ScreenWidth*0.23),(int)(ScreenHeight*0.1),(int)(ScreenWidth*0.53),(int)(ScreenHeight*0.53));
        LoadingImage.setBounds((int)(ScreenWidth*0.25),(int)(ScreenHeight*0.5625),(int)(ScreenWidth*0.53),(int)(ScreenHeight*0.53));
        lbBemVindo.setBounds((int)(ScreenWidth*0.3),(int)(ScreenHeight*0.3),(int)(ScreenWidth*0.53),(int)(ScreenHeight*0.53));
        lbCluckCo.setBounds((int)(ScreenWidth*0.26),(int)(ScreenHeight*0.3625),(int)(ScreenWidth*0.66),(int)(ScreenHeight*0.53));
        lbUserFail.setBounds((int)(ScreenWidth*0.083),(int)(ScreenHeight*0.3),600,(int)(ScreenHeight*0.53));
        lbVerify.setBounds((int)(ScreenWidth*0.308),(int)(ScreenHeight*0.3625),(int)(ScreenWidth*0.66),(int)(ScreenHeight*0.53));
        lbIncorrectCred.setBounds((int)(ScreenWidth*0.141),(int)(ScreenHeight*0.625),(int)(ScreenWidth*0.66),(int)(ScreenHeight*0.125));
    
        //Set fonts
        lbLoginUser.setFont(EudoxusSans);
        lbLoginSenha.setFont(EudoxusSans);
        txLoginUser.setFont(EudoxusSansButton);
        btEntrar.setFont(EudoxusSansButton); 
        btOKlogin.setFont(EudoxusSansButton); 
        lbVersion.setFont(EudoxusSansVersion);
        lbBemVindo.setFont(EudoxusSansIntro);
        lbCluckCo.setFont(EudoxusSansIntro);
        lbUserFail.setFont(EudoxusSansIntro);
        lbVerify.setFont(EudoxusSansVersion);
        lbIncorrectCred.setFont(EudoxusSansVersion);
        
        //Add listeners
        btEntrar.addActionListener(this);
        btOKlogin.addActionListener(this);

       //Adiciona componentes ao painel principal de login
        panel.add(lbLoginUser);//adiciona a label username
        panel.add(txLoginUser);//adiciona o campo para username
        panel.add(lbLoginSenha);//adiciona a label senha
        panel.add(psLoginSenha);//adiciona o campo para a senha
        panel.add(lbVersion);//adiciona a label uda versão 
        panel.add(btEntrar);
        panel.add(LogoImage);   
        panel.add(BGImage); 
        add(panel); 
        //Adiciona componenentes ao painel de login realizado com sucesso 
        sucessPanel.add(lbBemVindo);
        sucessPanel.add(lbCluckCo);
        sucessPanel.add(SucessImage);
        sucessPanel.add(LoadingImage);
        //Adiciona componentes ao painel de login sem sucesso 
        FailPanel.add(btOKlogin);
        FailPanel.add(lbUserFail);
        FailPanel.add(lbVerify);
        FailPanel.add(FailImage);
    }

    //Eventos 
    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btEntrar){
            UsuarioFicheiro flmngr=new UsuarioFicheiro(); 
            String nome=txLoginUser.getText();
         
    
            
            if (txLoginUser.getText().equals("")|| psLoginSenha.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Introduza as credenciais correctamente");
            }// Abre um JOptioPane caso não sejam introduzidas as credênciais
            else try {
                if (flmngr.verificaLogin(txLoginUser.getText(),psLoginSenha.getText()) ){//É aqui onde o usuário deve ser verificado
                  username = txLoginUser.getText(); 
                    remove(panel);
                    sucessPanel.setBackground(smoothBlue);
                    add(sucessPanel);
                    revalidate();
                    
                    SwingWorker<Void,Void>worker= new SwingWorker<Void,Void>(){
                        @Override
                        protected Void doInBackground() throws Exception {
                            Thread.sleep(3500);
                            return null;
                        }
                        protected void done(){
                          
                            try {
                              
                                if(flmngr.getUserType(username).equals("VENDEDOR") || flmngr.getUserType(username).equals("FUNCIONARIO") ){
                                    MenuFuncionario b = new MenuFuncionario(flmngr.getUserFullNome(username),flmngr.getUserType(username));
                                    b.setVisible(true);
                                    b.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                      dispose();
                                }
                                else{
                                    MenuAdmin b = new MenuAdmin(flmngr.getUserFullNome(username),flmngr.getUserType(username));
                                    b.setVisible(true);
                                    b.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                    dispose();
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                            }
                          
                               
                           
                       
                        }
                    };
                    worker.execute();
                }

                    
                    else{//Mostra a tela de login fracassado
                        remove(panel);
                        FailPanel.add(BGImage);
                        add(FailPanel);
                        revalidate();
                    }
                } catch (ClassNotFoundException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        }

        if (e.getSource()==btOKlogin){
           
           Login a = new Login();
           a.setVisible(true);
           a.setDefaultCloseOperation(EXIT_ON_CLOSE);
           dispose();
        }
    }
    public double div(int valorinicial, int proporcao){
        double s ;
        s = valorinicial/proporcao;
       return s;
    }
    public static void main(String[]args) throws HeadlessException, ClassNotFoundException, IOException{
    //

          EstoqueFicheiro EF = new EstoqueFicheiro();
//        EF.adcionarEstoque(155, "EU");
        EF.criarFicheiro();
        UsuarioFicheiro s = new UsuarioFicheiro();
     //s.adcionarUser("Aderito Muffins", "123566B", "MAPUTO","258858782674", "aderito_muffins", "0412", "ADMINISTRADOR");
        Login a = new Login();
        a.setVisible(true);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}