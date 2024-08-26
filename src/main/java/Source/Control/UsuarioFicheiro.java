package Source.Control;

import Source.Model.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
 import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import javax.swing.JTextArea;
 
 public class UsuarioFicheiro{
    
    public static final String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
     public UsuarioFicheiro(){}
   
      public void salvarTextoEmArquivo(JTextArea textArea) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registro.txt"))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
           
        }
    }
     
      public void carregarTextoDeArquivo(JTextArea textArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader("registro.txt"))) {
            String linha;
            StringBuilder conteudo = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
            textArea.setText(conteudo.toString());
        } catch (IOException e) {
        }
    }
     
     
     
     
     
     public boolean verificaLogin(String username, String password) throws IOException, ClassNotFoundException {
        boolean loggedIn = false;

            for (Usuario user : pegarFileUser()) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    loggedIn = true;
                    break; // No need to continue if login credentials match
                }
            }
        return loggedIn;
    }
     
      public void escreverFileUser(List<Usuario> userList) throws FileNotFoundException, IOException{
                try (ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream("DATA_BASE_USER.cc"))) {
                objectOutput.writeObject(userList);
            }
      }
      
      public List<Usuario> pegarFileUser() throws FileNotFoundException, IOException, ClassNotFoundException{
        File file = new File("DATA_BASE_USER.cc");
          List<Usuario> userList = new ArrayList<>();
        if(file.exists()){
         try (FileInputStream fileInput = new FileInputStream("DATA_BASE_USER.cc");
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {


            userList = (List<Usuario>) objectInput.readObject();
            }
        }
        return  userList;
     }
      
      public void adcionarUser(String Nome, String BI, String localizacao, String Telefone, String username, String password, String TipoUsuario) throws IOException, ClassNotFoundException {     
         List<Usuario> userList;
        File file = new File("DATA_BASE_USER.cc");
        if (file.exists()) {
             userList = pegarFileUser();
        } else {
                userList = new ArrayList<>();
        }
     //verifica se existe tal username
        boolean usernameExists = false;
        for (Usuario user : userList) {
            if (user.getUsername().equals(username)) {
                usernameExists = true;
                break;
            }
        }
        if (!usernameExists) {
            Random random = new Random();
            int ID;
            boolean uniqueID = false;

            //gera um ID unico
            do {
                ID = random.nextInt(9000) + 100;
                uniqueID = true;
                for (Usuario user : userList) {
                    if (user.getID() == ID) {
                        uniqueID = false;
                        break;
                    }
                }
            } while (!uniqueID);

            userList.add(new Usuario(ID, BI, Nome, localizacao, Telefone, username, password, TipoUsuario));
            escreverFileUser(userList);
            JOptionPane.showMessageDialog(null, "usuário criado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "O nome de usuário já existe. Por favor, escolha outro.");
        }
    }  
   
      public String getUserFullNome( String username) throws IOException, ClassNotFoundException {
           String FullNome = "ERRO";
           for(Usuario user : pegarFileUser()){
               if(user.getUsername().equals(username)){
               FullNome= user.getNome();
               break;}
           }
            return FullNome;
       }
           
      public String getUserType( String username) throws IOException, ClassNotFoundException {
           String UserType = "ERRO";
           for(Usuario user : pegarFileUser()){
               if(user.getUsername().equals(username)){
               UserType= user.getTipoUsuario();
               break;}
           }
           return UserType;} 

      public void removerUser(List <Usuario> usuarios, String username) throws IOException, ClassNotFoundException {
               List<Usuario> userList;
        Iterator<Usuario> iterator = usuarios.iterator();
        while(iterator.hasNext()){
        Usuario usuario = iterator.next();
        if(usuario.getUsername().equals(username)){
        iterator.remove();
        break;
        }
        }
         escreverFileUser(usuarios);
    }
 
      //Metodo necessario para poder ler dados dos ficheiros e carregar nas tabelas.
      public DefaultTableModel buildTableModel(List<Usuario> userList) {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Nome");
        columnNames.add("Username");
        columnNames.add("Telefone");
        columnNames.add("BI");
        columnNames.add("Senha");
        columnNames.add("Local");
        columnNames.add("Utilizador");

        Vector<Vector<Object>> data = new Vector<>();
            for (Usuario user : userList) {
            Vector<Object> vector = new Vector<>();
            vector.add(user.getNome());
            vector.add(user.getUsername());
            vector.add(user.getTelefone());
            vector.add(user.getBI());
            vector.add(user.getPassword());
            vector.add(user.getLocalizacao());
            vector.add(user.getTipoUsuario());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

 

}

   

