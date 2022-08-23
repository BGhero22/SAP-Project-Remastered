import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        Controller control=new Controller();
        SQLConnector sql=new SQLConnector();
        MailValidator mailValidator=new MailValidator();
        System.out.println("Welcome");
        System.out.println("Enter username");
        String password ;
        String username=sc.nextLine();
        if(control.isAlreadyRegistered(username)){
            System.out.println("Enter password");
             password=sc.nextLine();
            control.logUsers(username,password);
        }
        else{
            System.out.println("Enter email");
            String email=sc.nextLine();
            System.out.println("Enter password");
            password=sc.nextLine();
            UserSpec spec;
            if(mailValidator.validateMail(email)){
                System.out.println("Enter your Admin ID");
                String adminID=sc.nextLine();
                spec=new AdminSpec(username,password,adminID);
            }
            else
            {
                sql.addPackages(ConnectionFactory.getCon());
                int packageId=sql.getKey(ConnectionFactory.getCon());
              spec=new ClientSpec(username,password,packageId);
            }

            control.addUsers(email,spec);
        }
    }
}
