import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientSpec extends UserSpec  {
    private int packageID;

    public ClientSpec(String username, String password, int packageID) {
        super(username, password);
        this.packageID = packageID;
    }

    public int getPackageID() {
        return packageID;
    }


    @Override
    public ClientSpec logIn() throws SQLException, IOException {
         super.logIn();
        Scanner sc=new Scanner(System.in);
        this.packageID =sc.nextInt();
        return new ClientSpec(getUsername(),getPassword(), getPackageID());
    }
    @Override
    public ClientSpec signUp(){
        super.signUp();
        Scanner sc=new Scanner(System.in);
        this.packageID =sc.nextInt();
        return new ClientSpec(getUsername(),getPassword(), getPackageID());
    }
}
