import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import static javafx.application.Platform.exit;

public class Admins extends Users{
    public Admins(String email, AdminSpec userSpec)
    {
        super(email, userSpec);
    }


    public void priceUpdate() throws SQLException, IOException {
        float percentage;
        Scanner sc = new Scanner(System.in);
        SQLConnector sql = new SQLConnector();
        System.out.println("Enter percentage for price changing;use - for in case of decreasing");
        percentage = sc.nextFloat();
        Map<Integer, Float> prices;
        prices = sql.allPackages(ConnectionFactory.getCon());
        int priceSize;
        priceSize = prices.size();
        Float[] values;
        values = prices.values().toArray(new Float[priceSize]);
        for (int i = 0; i < prices.size(); i++) {

            float newPrice = values[i] + values[i] * (percentage / 100);
            sql.updatePackagePrice(ConnectionFactory.getCon(), newPrice, i + 1);
        }
    }

    public void channelNameUpdate(Channels channel) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        System.out.println("Enter new name");
        String newName =sc.next();
        channel.setName(newName);
        sql.updateChannelsName(ConnectionFactory.getCon(), channel.getName(), newName);
    }

    public void channelPriceUpdate(Channels channel) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        System.out.println("Enter new price");
        float newPrice=sc.nextFloat();
        channel.setPrice(newPrice);
        sql.updateChannelsPrice(ConnectionFactory.getCon(), channel.getName(),newPrice);
    }

    public void channelsUpdate(Channels channel) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        String command;
        while(true){
            System.out.println("To change name enter 1;To Change price enter 2;To end enter 3");
            command=sc.next();

            if(command.equals("1")){
                channelNameUpdate(channel);
            }
            if(command.equals("2")){
                    channelPriceUpdate(channel);
            }
            if(command.equals("3")){
                    dataUpdate();
                    break;
            }
        }

    }

    public void categoriesUpdate(Categories category) throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        System.out.println("Enter new price");
        float newPrice=sc.nextFloat();
        category.setPrice(newPrice);
        sql.updateCategoryPrice(ConnectionFactory.getCon(), category.getName(),newPrice);
    }

    public void deliveriesNameUpdate(Delivers deliver) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter new name");
        String newName=sc.nextLine();
        deliver.setName(newName);
        sql.updateDeliver(ConnectionFactory.getCon(), deliver.getName(),newName);
    }

    public void contractLengthUpdate(Contracts contract) throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter new length of contract");
        int newLength=sc.nextInt();
        contract.setLength(newLength);
        sql.updateContractLength(ConnectionFactory.getCon(),newLength,contract.getNumber());
    }

    public void channelDelete() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String name;
        System.out.println("Enter name of channel you want to delete");
        name=sc.nextLine();
        sql.deleteChannels(ConnectionFactory.getCon(),name);
    }

    public void categoryDelete() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String name;
        System.out.println("Enter name of category you want to delete");
        name=sc.nextLine();
        sql.deleteCategory(ConnectionFactory.getCon(),name);
    }
    public void deliverDelete() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String name;
        System.out.println("Enter name of deliver you want to delete");
        name=sc.nextLine();
        sql.deleteDelivers(ConnectionFactory.getCon(),name);
    }
    public void contractDelete() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String number;
        System.out.println("Enter name of contract you want to delete");
        number=sc.nextLine();
        sql.deleteContracts(ConnectionFactory.getCon(),number);
    }
    public void addChannel() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String name;
        float price;
        String categoryName;
        String num;
        System.out.println("Enter name of channel you want to add ");
        name=sc.nextLine();
        System.out.println("Enter the price of the channel");
        price=sc.nextFloat();
        System.out.println("Enter the category of the channel");
        categoryName=sc.next();

       Categories category=new Categories(categoryName,0);
        System.out.println("Enter the number  of contract channel is part of");
         num=sc.next();

       Contracts contract=new Contracts(num,null,0);
        Channels channel=new Channels(name,price,category,contract);
        if(sql.checkChannel(ConnectionFactory.getCon(),name)==0){
            sql.addChannel(ConnectionFactory.getCon(),channel.getName(),channel.getPrice(),sql.getContractIdByNum(ConnectionFactory.getCon(),num),sql.getCategoryIdByName(ConnectionFactory.getCon(),channel.getCategory().getName()));
        }
        else{
            System.out.println("Channel is already in list");
            dataAdd();
        }

    }
    public  void addCategory() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String name;
        float price;
        System.out.println("Enter name of category you want to add ");
        name=sc.nextLine();
        System.out.println("Enter the price of the category");
        price=sc.nextFloat();
        if(sql.checkCategory(ConnectionFactory.getCon(),name)==0){
            sql.addCategory(ConnectionFactory.getCon(),name,price);
        }
        else {
            System.out.println("Category is already in list");
            dataAdd();
        }

    }
    public  void addDelivers() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String name;
        System.out.println("Enter name of the deliver you want to add ");
        name=sc.nextLine();
        if(sql.checkDeliver(ConnectionFactory.getCon(),name)==0){
            sql.addDelivers(ConnectionFactory.getCon(),name);
        }
        else{
            System.out.println("Deliver is already in the list");
            dataAdd();
        }

    }
    public void addContracts() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        SQLConnector sql=new SQLConnector();
        String name;
        float value;
        System.out.println("Enter name of deliver you want to add ");
        name=sc.nextLine();
        System.out.println("Enter the value of the contract");
        value=sc.nextFloat();
        System.out.println("Enter number of contract you want to add ");
       String num=sc.next();
        System.out.println("Enter the length of contract in months");
        int length=sc.nextInt();
        System.out.println();
        if(sql.checkContract(ConnectionFactory.getCon(),num)==0){

            sql.addContracts(ConnectionFactory.getCon(),value,num,length,sql.getDeliverIdByName(ConnectionFactory.getCon(),name));
        }
        else{

            System.out.println("Contract is already in system");
            dataAdd();
        }
    }

public void dataAdd() throws SQLException, IOException {
    Scanner sc=new Scanner(System.in);
    System.out.println("To add channels enter 1,For categories 2;for Deliveries 3,For Contracts 4");
    String command;
    while (true){
        System.out.println("Enter command");
        command = sc.next();
        if (command.equals("1")) {
            addChannel();
        }
        if (command.equals("2")) {
            addCategory();
        }
        if (command.equals("3")) {
            addDelivers();
        }
        if (command.equals("4")) {
            addContracts();
        }
        if(command.equals("5")){
            menu();
            break;
        }
    }
}
    public void dataDelete() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("To delete channels enter 1,For categories 2;for Deliveries 3,For Contracts 4");
        String command;
        while(true){
            System.out.println("Enter command");
            command = sc.next();
            if (command.equals("1")) {

                channelDelete();
            }
            if (command.equals("2")) {

                categoryDelete();
            }
            if (command.equals("3")) {

                deliverDelete();
            }
            if (command.equals("4")) {

                contractDelete();
            }
            if(command.equals("5")){

                menu();
                break;
            }
        }
    }

    public void dataUpdate() throws SQLException, IOException {
        SQLConnector sql=new SQLConnector();
        Scanner sc=new Scanner(System.in);
        System.out.println("To change channels enter 1,For categories 2;for Deliveries 3,For Contracts 4");
        String command;
        while(true){

            System.out.println("Enter command");
          command=sc.next();
          if(command.equals("1")){

              System.out.println("Enter name of the channel ");
              String name=sc.next();
              Channels channel =new Channels(name,sql.dataForChannel(ConnectionFactory.getCon(),name),null,null);
              channelsUpdate(channel);
          }
          if(command.equals("2")){

              System.out.println("Enter name of category");
              String name=sc.next();
              Categories category =new Categories(name,sql.getPriceOfCategory(ConnectionFactory.getCon(),name));
              categoriesUpdate(category);
          }
          if(command.equals("3")){

              System.out.println("Enter name of deliver");
              String name=sc.next();
              Delivers deliver=new Delivers(name);
              deliveriesNameUpdate(deliver);
          }
          if(command.equals("4")){

              System.out.println("Enter number of contract");
              String number=sc.next();
              Contracts contracts=new Contracts(number,sql.getDeliverOfContract(ConnectionFactory.getCon(),number),sql.getLengthOfContract(ConnectionFactory.getCon(),number));
              contractLengthUpdate(contracts);
          }
          if(command.equals("5")){

              menu();
              break;
          }
        }
    }
//This method represents only functions that can be executed by admins
    public void menu() throws SQLException, IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Choose operation");
        System.out.println("Enter 1 to update price,2 to add Data;3 to update Data;4 to delete Data;5 to leave app");
        while(true){

            String command=sc.next();
            if(command.equals("1")){

                priceUpdate();
            }
            if(command.equals("2")){

                dataAdd();
            }
            if(command.equals("3")){

                dataUpdate();
            }
            if(command.equals("4")){

                dataDelete();
            }
            if(command.equals("5")){

                exit();
                break;
            }
        }
    }

}
