import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class Controller {

    public boolean isAlreadyRegistered(String username) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Map<String,String> pairs= sql.nameAndPass(ConnectionFactory.getCon());
        Map<String,String> adminPairs=sql.nameAndPassAdmins(ConnectionFactory.getCon());
        return pairs.containsKey(username) || adminPairs.containsKey(username);
    }

    public boolean checkAdminOrClient(String username) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Map<String,String> adminPairs=sql.nameAndPassAdmins(ConnectionFactory.getCon());
        return adminPairs.containsKey(username);
    }

    public void wrongPasswordActivity(String username) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Wrong password");
        String password = sc.nextLine();
        logUsers(username, password);
    }

    public  void adminPasswordCheck(String username, String password) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Map<String,String> adminPairs=sql.nameAndPassAdmins(ConnectionFactory.getCon());//returning username/password pairs of admins
        if(password.equals(adminPairs.get(username))){
            AdminSpec adminSpec=new AdminSpec(username,password,sql.getIdOfAdmin(ConnectionFactory.getCon(),username));
            Admins admin=new Admins(sql.getEmailOfAdmin(ConnectionFactory.getCon(),username),adminSpec);
            admin.menu();
        }
        else{
            wrongPasswordActivity(username);
        }
    }

    public  void clientPasswordCheck(String username,String password) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Map<String,String> pairs=sql.nameAndPassAdmins(ConnectionFactory.getCon());//returning username/password pairs of clients
        if(password.equals(pairs.get(username))){
            ClientSpec clientSpec=new ClientSpec(username,password,sql.getIdOfPackage(ConnectionFactory.getCon(),username));
            Clients client=new Clients(sql.getEmailOfClient(ConnectionFactory.getCon(),username),clientSpec,sql.channelsOfUser(ConnectionFactory.getCon(),username),sql.categoriesOfUser(ConnectionFactory.getCon(),username));
            client.menu(client);
        }
        else{
            wrongPasswordActivity(username);
        }
    }

    public void logUsers(String username,String password) throws SQLException, IOException {
       if(checkAdminOrClient(username)){
                     adminPasswordCheck(username, password);
        }
        else {
                clientPasswordCheck(username, password);
        }
    }

    public void addAdmin(String email,UserSpec spec) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Admins admin =new Admins(email, (AdminSpec) spec);
        sql.addAdmins(ConnectionFactory.getCon(),spec.getUsername(),spec.getPassword(),email,((AdminSpec) spec).getAdminId());
        admin.menu();
    }

    public void addClient(String email,UserSpec spec) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Clients client =new Clients(email,(ClientSpec) spec,null,null);
        sql.addClients(ConnectionFactory.getCon(),spec.getUsername(),spec.getPassword(),email,((ClientSpec) spec).getPackageID());
        client.menu(client);
    }

    public void addUsers(String email, UserSpec spec) throws SQLException, IOException {

        if(spec instanceof AdminSpec){

           addAdmin(email,spec);
        }
        if(spec instanceof  ClientSpec){

            addClient(email,spec);
        }
    }
}
