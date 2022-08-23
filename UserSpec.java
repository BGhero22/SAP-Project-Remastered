import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class UserSpec {
    private String username;
    private String password;

    public UserSpec(String username, String password) {
        this.username = username;
        this.password = password;
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
    public  UserSpec logIn() throws SQLException, IOException {
        SQLConnector sql = new SQLConnector();
        Map<String,String> pairs= sql.nameAndPass(ConnectionFactory.getCon());
        Scanner sc = new Scanner(System.in);
        UsernameValidator usv = new UsernameValidator();
        PasswordValidator psv=new PasswordValidator();
        do {
            this. username = sc.nextLine();
        } while (!usv.validateUserName(this.username));
        String passcheck=pairs.get(username);
        do {
            System.out.println("Enter password");
            this.password = sc.nextLine();
        }while(!psv.validatePassword(password) ||!password.equals(passcheck));


        return new UserSpec(username,password);
    }

    public UserSpec signUp(){
        Scanner sc = new Scanner(System.in);
        UsernameValidator usv = new UsernameValidator();
        PasswordValidator psv=new PasswordValidator();
        do {
            this. username = sc.nextLine();
        } while (!usv.validateUserName(this.username));
        do {
            System.out.println("Enter password");
            this.password = sc.nextLine();
        }while(!psv.validatePassword(password));
        return new UserSpec(username, password);

    }


}
