package Source.Model;


import java.io.Serializable;
import java.time.LocalDate;


public class Usuario implements Serializable {
    private int ID;
    private String nome,username, BI,localizacao,nTelefone,tipoUsuario, password;
    public Usuario(String username, String userType, String password){
        this.username=username;
        this.tipoUsuario=userType;
        this.password=password;
    }
      public Usuario(int ID,String BI, String fullName, String location, String phone, String username, String password, String userType) {
        this.ID = ID;
        this.BI = BI;
        this.nome = fullName;
        this.localizacao = location;
        this.nTelefone = phone;
        this.username = username;
        this.password = password;
        this.tipoUsuario = userType;
    }
     public int getID() {
        return ID;
    }
      
      public void setID(int ID) {
        this.ID = ID;
    }
           public String getBI() {
        return BI;
    }
      
      public void setBI(String ID) {
        this.BI = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setFullNome(String fullName) {
        this.nome = fullName;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String location) {
        this.localizacao = location;
    }

    public String getTelefone() {
        return nTelefone;
    }

    public void setTelefone(String phone) {
        this.nTelefone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String userType) {
        this.tipoUsuario = userType;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "ID=" + ID +"BI=" + BI + ", fullNome=" + nome + ", Localizacao=" + localizacao + ", phone=" + nTelefone + ", username=" + username + ", password=" + password + ", userType=" + tipoUsuario + '}';
    }
}
