import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminSpec extends UserSpec{
    private String AdminId;
    public AdminSpec(String username, String password, String adminId) {
        super(username, password);
        AdminId = adminId;
    }

    public String getAdminId() {
        return AdminId;
    }

    @Override
    public AdminSpec logIn() throws SQLException, IOException {
        super.logIn();
        Scanner sc=new Scanner(System.in);
        this.AdminId=sc.nextLine();
        return  new AdminSpec(getUsername(),getPassword(),getAdminId());

    }
    @Override
    public AdminSpec signUp(){
        super.signUp();
        Scanner sc=new Scanner(System.in);
        this.AdminId=sc.nextLine();
        return  new AdminSpec(getUsername(),getPassword(),getAdminId());
    }


}
