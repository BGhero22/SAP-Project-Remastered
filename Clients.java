import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public  class Clients extends Users  {
    List<String> channels;
    List<String> categories;

    public Clients(String email, ClientSpec userSpec, List<String> channels, List<String> categories) {
        super(email, userSpec);
        this.channels = channels;
        this.categories = categories;
    }

    public List<String> getChannels() {
        return channels;
    }



    public List<String> getCategories() {
        return categories;
    }

public void  usernameUpdate(Users user) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
    UserSpec userspec = user.getUserSpec();
    System.out.println("Enter new username");
    String newUserName = sc.next();
    userspec.setUsername(newUserName);
    // verification that username is unique
    if (!sql.allUsernames(ConnectionFactory.getCon()).contains(newUserName)){
        sql.updateClientName(ConnectionFactory.getCon(), userspec.getUsername(), newUserName);
    } else {
        System.out.println("This username is already used,please enter new one");
        profileUpdate(user);

    }
}

public void passwordUpdate(Users user) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
    UserSpec userspec = user.getUserSpec();
    System.out.println("Enter new password");
    String newPassword = sc.next();
    userspec.setPassword(newPassword);
    sql.updateClientPass(ConnectionFactory.getCon(),newPassword, userspec.getUsername());
}

public void emailUpdate(Users user) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
    System.out.println("Enter new email");
    String newEmail = sc.next();
    user.setEmail(newEmail);
    sql.updateClientMail(ConnectionFactory.getCon(),user.getUserSpec().getUsername(),newEmail);
}

    public void profileUpdate(Users user) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose what you want to change");
        String command;
        while (true){
            System.out.println("For username enter 1;For Password enter 2,For email enter 3;To end enter 4");
            command = sc.next();
            if (command.equals("1")) {
                usernameUpdate(user);

            }
            if (command.equals("2")) {
                passwordUpdate(user);
            }
            if (command.equals("3")) {
                emailUpdate(user);
            }
           if (command.equals("4")){
               menu(user);
               break;
           }
        }
    }
    public void accountDelete(Users user) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        sql.deleteClients(ConnectionFactory.getCon(),user.getUserSpec().getUsername());
        sql.deletePackages(ConnectionFactory.getCon(),sql.getIdOfPackage(ConnectionFactory.getCon(),getUserSpec().getUsername()));
        exit();
    }
    public void updatePackageWithChannel(Users user) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        float price = 0;
        sql.allChannels(ConnectionFactory.getCon());
        System.out.println("Enter number of channel");
        int idOfChannel =sc.nextInt();
        String channelName =sql.getChannelById(ConnectionFactory.getCon(), idOfChannel);

        if(channelName.equals(" ")){
            System.out.println("This channel is not in the list");
            planUpdate(user);
        }
        else{
            Channels channel =new Channels(channelName,sql.getPriceOfChan(ConnectionFactory.getCon(), channelName),sql.getCategoryOfChan(ConnectionFactory.getCon(), channelName),null);
            if (!getChannels().contains(channelName)||!getCategories().contains(channel.getCategory().getName()) ){
                sql.insertChanelIntoPackage(ConnectionFactory.getCon(), idOfChannel,sql.getIdOfPackage(ConnectionFactory.getCon(), user.getUserSpec().getUsername()));
                price+= channel.getPrice();
                sql.updatePackagePrice(ConnectionFactory.getCon(),price, idOfChannel);
            }
            else{
                System.out.println("Channel is already in your package");
                planUpdate(user);
            }
        }
    }

    public void updatePackageWithCategory(Users user) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        float price=0;

        sql.allCategoriesForShowing(ConnectionFactory.getCon());
        System.out.println("Enter number of category");
        int idOfCategory=sc.nextInt();
        String category = sql.getCategoryById(ConnectionFactory.getCon(),idOfCategory);
        if(getCategories().contains(category)|| category.equals(" ")){

            System.out.println("Category is already in your package or it isn`t in list");
            planUpdate(user);
        }
        else{
            sql.insertCategoryIntoPackage(ConnectionFactory.getCon(),sql.getIdOfPackage(ConnectionFactory.getCon(), getUserSpec().getUsername()),idOfCategory);
            price+= sql.getPriceOfCategory(ConnectionFactory.getCon(),category);
            sql.updatePackagePrice(ConnectionFactory.getCon(),price,sql.getIdOfPackage(ConnectionFactory.getCon(), getUserSpec().getUsername()));
        }
    }
// This method is used to change content of client package
    public void planUpdate(Users user) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose what you want to add");
        String command;

        label:
        while(true){
            System.out.println("For channel enter 1; for category enter 2; to end enter 3");
            command=sc.next();
            switch (command) {
                case "1":
                    updatePackageWithChannel(user);
                    break;
                case "2":
                    updatePackageWithCategory(user);
                    break;
                case "3":
                    menu(user);
                    break label;
            }
        }
    }
    //This method represents only functions that can be executed by clients
    public void menu(Users user) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("For profile update enter 1;For plan update enter 2;To delete your account enter 3;to return back enter 4");
        while (true) {
            System.out.println("Enter command");
            String command = sc.next();
            if(command.equals("1")){
                profileUpdate(user);
                }
            if(command.equals("2")){
                planUpdate(user);
                }
            if(command.equals("3")){
                accountDelete(user);
                }
            if (command.equals("4")){
                exit();
                break;
                }
            }
        }

}
