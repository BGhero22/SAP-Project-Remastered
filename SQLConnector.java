
import java.sql.*;
import java.util.*;

import static javafx.application.Platform.exit;

public class SQLConnector {
    public void addChannel(Connection con, String name, float price,int contract_id,int category_id)  {
        try{
            PreparedStatement pst = con.prepareStatement("insert into channels(name, price,contract_id,category_id) values(?,?,?,?);");
            pst.setString(1, name);
            pst.setFloat(2, price);
            pst.setInt(3,contract_id);
            pst.setInt(4,category_id);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }

        }
    }
public int getCategoryIdByName(Connection con,String name){
        int id = 0;
        ResultSet rs;
        try{
            PreparedStatement pst=con.prepareStatement("Select id from categories where name=?");
            pst.setString(1,name);
            rs= pst.executeQuery();
            while (rs.next()){
                id=rs.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
}
    public int getContractIdByNum(Connection con, String num){
        int id = 0;
        ResultSet rs;
        try{
            PreparedStatement pst=con.prepareStatement("Select id from contracts where numberOfContract=?;");
            pst.setString(1,num);
            rs= pst.executeQuery();
            while (rs.next()){
                id=rs.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public int getDeliverIdByName(Connection con,String name){
        int id = 0;
        ResultSet rs;
        try{
            PreparedStatement pst=con.prepareStatement("Select id from delivers where name=?");
            pst.setString(1,name);
            rs= pst.executeQuery();
            while (rs.next()){
                id=rs.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
public int checkChannel(Connection con,String name){
        int flag=0;
        ResultSet rs;
        String temp;
        try{
            PreparedStatement pst=con.prepareStatement("Select * from channels;");
          rs= pst.executeQuery();
            while (rs.next()){
                temp=rs.getString("name");
                if(name.equals(temp)){
                    flag++;
                }
            }
        }
        catch (SQLException | NullPointerException e ){
            System.out.println( e.getMessage());

        }
    return flag;
    }
    public int checkCategory(Connection con,String name){
        int flag=0;
        ResultSet rs ;
        String temp;
        try{
            PreparedStatement pst=con.prepareStatement("Select * from categories;");
          rs=pst.executeQuery();
            while (rs.next()){
                temp=rs.getString("name");
                if(name.equals(temp)){
                    flag++;
                }
            }
        }
        catch (SQLException | NullPointerException e){
            System.out.println( e.getMessage());

        }
        return flag;
    }

    public int checkDeliver(Connection con,String name){
        int flag=0;
        ResultSet rs;
        String temp;
        try{
            PreparedStatement pst=con.prepareStatement("Select * from delivers;");
         rs= pst.executeQuery();
            while (rs.next()){
                temp=rs.getString("name");
                if(name.equals(temp)){
                    flag++;
                }
            }
        }
        catch (SQLException | NullPointerException e){
            System.out.println( e.getMessage());

        }
        return flag;
    }
    public int checkContract(Connection con,String num){
        int flag=0;
        ResultSet rs;
        String temp;
        try{
            PreparedStatement pst=con.prepareStatement("Select * from contracts;");
          rs=pst.executeQuery();
            while (rs.next()){
                temp=rs.getString("numberOfContract");
                if(num.equals(temp)){
                    flag++;
                }
            }
        }
        catch (SQLException | NullPointerException e){
            System.out.println( e.getMessage());

        }
        return flag;
    }

    public void addCategory(Connection con, String name, float price) {
        try{
            PreparedStatement pst = con.prepareStatement("insert into categories(name,price) values(?,?);");
            pst.setString(1, name);
            pst.setFloat(2, price);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void addContracts(Connection con, float value, String numberOfContract, int length, int deliver_id)  {
        try{
            PreparedStatement pst = con.prepareStatement("insert into contracts(price,numberOfContract,length_in_months,deliver_id) values(?,?,?,?);");
            pst.setFloat(1,value);
            pst.setString(2, numberOfContract);
            pst.setInt(3, length);
            pst.setInt(4, deliver_id);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void addClients(Connection con, String name, String password, String email, int package_id)
    {
        try{
            PreparedStatement pst = con.prepareStatement("insert into TV_Operator.clients(username,pass,email,package_id) values(?,?,?,?);");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setInt(4, package_id);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }
    public void addAdmins(Connection con, String name, String password, String email, String adminId)
    {
        try{
            PreparedStatement pst = con.prepareStatement("insert into TV_Operator.clients(username,pass,email,adminId) values(?,?,?,?);");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setString(4,adminId);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }


    public void addDelivers(Connection con, String name)  {
        try{
            PreparedStatement pst = con.prepareStatement("insert into delivers (name) values(?)");
            pst.setString(1, name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void addPackages(Connection con)  {

        try {
            PreparedStatement pst = con.prepareStatement("insert into packages(price) values(?); ");
            pst.setFloat(1, 0.00F);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
    }

    public int getKey(Connection con)  {
        ResultSet rs;
        int id = 0;
        try{
            PreparedStatement pst=con.prepareStatement("Select count(id) as rowscount from packages;");
            rs=pst.executeQuery();
            while(rs.next()){
                id=rs.getInt("rowscount");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return id;
    }

    public void updateChannelsName(Connection con, String old_name,String newName)  {

        try{
            PreparedStatement pst = con.prepareStatement("update channels  set name=? where name=?;");
            pst.setString(1, newName);
            pst.setString(2,old_name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void updateChannelsPrice(Connection con,String name, float price)  {

        try {
            PreparedStatement pst = con.prepareStatement("update channels  set price=? where name=?;");
            pst.setFloat(1, price);
            pst.setString(2,name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
    }

    public void updateCategoryPrice(Connection con,String name, float price)  {

        try{
            PreparedStatement pst = con.prepareStatement("update categories  set price=? where name=?;");
            pst.setFloat(1, price);
            pst.setString(2,name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void updateContractLength(Connection con, int length, String number)  {
        try{
            PreparedStatement pst = con.prepareStatement("update contracts  set length_in_months=? where numberOfContract=?;");
            pst.setInt(1, length);
            pst.setString(2,number);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }
public List<String> allUsernames(Connection con){
        ResultSet rs;
        List<String>names = new ArrayList<>();
        try{
            PreparedStatement pst=con.prepareStatement("Select username from clients;");
            rs=pst.executeQuery();
            while (rs.next()){
                String name=rs.getString("username");
                names.add(name);
            }
        }
        catch (SQLException | NullPointerException e){
            System.out.println( e.getMessage());

        }
        return  names;
}
    public void updateClientName(Connection con,String username,String newUsername )
    {

        try{
            PreparedStatement pst = con.prepareStatement("update clients  set username=? where username=?;");
            pst.setString(1, newUsername);
            pst.setString(2,username);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void updateClientPass(Connection con,String password, String username)  {

        try{

            PreparedStatement pst = con.prepareStatement("update clients  set pass=? where username=?;");
            pst.setString(1, password);
            pst.setString(2,username);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void updateClientMail(Connection con, String username,String newMail)  {

        try{
            PreparedStatement pst = con.prepareStatement("update clients  set email=? where username=?;");
            pst.setString(1, newMail);
            pst.setString(2,username);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void updatePackagePrice(Connection con, float price, int id)  {
        try{
            PreparedStatement pst = con.prepareStatement("update packages  set price=? where id=?;");
            pst.setFloat(1, price);
            pst.setInt(2,id);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }


    public void updateDeliver(Connection con,String oldName,String newName)  {
        try{
            PreparedStatement pst = con.prepareStatement("update delivers set name=? where name=?;");
            pst.setString(1,newName);
            pst.setString(2,oldName);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void deleteChannels(Connection con,String name) {
        try{
            PreparedStatement pst = con.prepareStatement("Delete from channels where name=?");
            pst.setString(1,name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void deleteCategory(Connection con,String name)  {
        try{
            PreparedStatement pst = con.prepareStatement("Delete  from categories where name=?");
            pst.setString(1,name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
    }

    public void deleteContracts(Connection con,String number)  {
        try {
            PreparedStatement pst = con.prepareStatement("Delete from contracts where numberOfContract=?;");
            pst.setString(1,number);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void deleteClients(Connection con,String name) {
        try{
            PreparedStatement pst = con.prepareStatement("Delete from clients where username=?;");
            pst.setString(1,name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
    }

    public void deleteDelivers(Connection con,String name)  {
        try{
            PreparedStatement pst = con.prepareStatement("Delete from delivers where name=?;");
            pst.setString(1,name);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }

    public void deletePackages(Connection con,int id) {
        try{
            PreparedStatement pst = con.prepareStatement("Delete from packages where id=?;");
            pst.setInt(1,id);
            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }
    
    public void  allChannels(Connection con)  {
        ResultSet rs;
        try{
            PreparedStatement pst=con.prepareStatement("Select * from channels;");
            rs=pst.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String channel =rs.getString("name");
                System.out.print(id);
                System.out.println(" "+ channel);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }
    
    public String getChannelById(Connection con,int id){
        ResultSet rs;
        String channel = "";
        try{
            PreparedStatement pst=con.prepareStatement("Select name from channels where id=?;");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                channel =rs.getString("name");

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return channel;

    }
    
    public String getCategoryById(Connection con,int id){

        ResultSet rs;

        String category = "";
        try{
            PreparedStatement pst=con.prepareStatement("Select name from categories where id=?;");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                category =rs.getString("name");

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return category;

    }

    public float getPriceOfCategory(Connection con, String name) {
        ResultSet rs;
        float price = 0;

        try{
            PreparedStatement pst=con.prepareStatement("Select categories.price\n" +
                    "from categories\n" +
                    "where categories.name=?;");
            pst.setString(1,name);
            rs= pst.executeQuery();
            while(rs.next()){
                price=rs.getFloat("price");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

        return price;
    }

    public float getPriceOfChan(Connection con,String name)  {
        float price = 0;
        try{
            PreparedStatement pst= con.prepareStatement("Select channels.price\n" +
                    "from channels\n" +
                    "where channels.name=?;");
            pst.setString(1,name);
            ResultSet rs=pst.executeQuery();

            while(rs.next()){
                price=rs.getFloat("price");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

        return price;
    }

    public Categories getCategoryOfChan(Connection con,String name)  {
        Categories category=new Categories(null,0);
        try{
            PreparedStatement pst= con.prepareStatement("Select categories.name from categories \n" +
                    "join channels on channels.category_id = categories.id \n" +
                    "where channels.name=?;");
            pst.setString(1,name);
            ResultSet rs=pst.executeQuery();

            while(rs.next()){
                    category.setName(rs.getString("name"));
                    category.setPrice(0.00F);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

        return category;
    }
    public Map<Integer, Float> allPackages(Connection con)  {
        Map<Integer,Float> prices= new HashMap<>();
        try{
            ResultSet rs;
            PreparedStatement pst=con.prepareStatement("Select * from packages;");
            rs=pst.executeQuery();

            while(rs.next()){
                Float price=rs.getFloat("price");
                int id =rs.getInt("id");
                prices.put(id,price);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

        return prices;
    }

    public void allCategoriesForShowing(Connection con)  {

        try{
            ResultSet rs;
            PreparedStatement pst=con.prepareStatement("Select * from categories;");
            rs=pst.executeQuery();

            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                System.out.print(id);
                System.out.println(" "+name);

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

    }


    public void insertChanelIntoPackage(Connection con, int idOfChannelWantToAdd, int idOfPackageYouWantToAdd)  {
        int idOfPackage = 0;
        int idOfChannel = 0;
        try
        {
            con.setAutoCommit(false);
            Statement pst = con.createStatement();
            ResultSet rs= pst.getGeneratedKeys() ;
            while (rs.next()){
                idOfChannel =rs.getInt("id");
                if(idOfChannelWantToAdd == idOfChannel){
                    break;
                }
            }
            Statement pst1 = con.createStatement();
            ResultSet rs1= pst1.getGeneratedKeys();
            while (rs1.next()){
                idOfPackage =rs.getInt("id");
                if(idOfPackageYouWantToAdd == idOfPackage){
                    break;
                }
            }
            PreparedStatement pst2=con.prepareStatement("Insert into channel_package(channel_id,package_id) values(?,?);");
            pst2.setInt(1, idOfChannel);
            pst2.setInt(2, idOfPackage);
            pst2.executeQuery();
            con .commit();
        }
        catch(Exception e)
        {
            try{
                con.rollback();
            }
            catch (SQLException p){
                System.out.println(p.getMessage());
                try {
                    con.close();
                    exit();
                }
                catch (SQLException e1){
                    exit();
                }
            }
        }
        finally
        {
            try{
                con.setAutoCommit(true);
            }
            catch (SQLException p){
                System.out.println(p.getMessage());
                try {
                    con.close();
                    exit();
                }
                catch (SQLException e1){
                    exit();
                }
            }

        }

    }

    public void insertCategoryIntoPackage(Connection con, int idOfWantedPackage, int idOfWantedCategory)  {
        int idOfPackage = 0;
        int idOfCategory = 0;
        try
        {
            con.setAutoCommit(false);
            Statement pst = con.createStatement();
            ResultSet rs= pst.getGeneratedKeys();
            while (rs.next()){
                idOfCategory =rs.getInt("id");
                if(idOfWantedCategory == idOfCategory){
                    break;
                }
            }
            Statement pst1 = con.createStatement();
            ResultSet rs1= pst1.getGeneratedKeys();
            while (rs1.next()){
                idOfPackage =rs.getInt("id");
                if(idOfWantedPackage == idOfPackage){
                    break;
                }
            }
            PreparedStatement pst2=con.prepareStatement("Insert into category_package(category_id,package_id) values(?,?);");
            pst2.setInt(1, idOfCategory);
            pst2.setInt(2, idOfPackage);
            pst2.executeQuery();
            con .commit();
        }
        catch(Exception e)
        {
            try{
                con.rollback();
            }
            catch (SQLException p){
                System.out.println(p.getMessage());
                try {
                    con.close();
                    exit();
                }
                catch (SQLException e1){
                    exit();
                }
            }
        }
        finally
        {
            try{
                con.setAutoCommit(true);
            }
            catch (SQLException p){
                System.out.println(p.getMessage());
                try {
                    con.close();
                    exit();
                }
                catch (SQLException e1){
                    exit();
                }
            }

        }
    }

    public Map<String, String> nameAndPass(Connection con)  {
        Map<String,String> passwords = new HashMap<>();
        try{
            ResultSet rs;
            PreparedStatement pst=con.prepareStatement("Select username,pass from TV_Operator.clients;");
            rs=pst.executeQuery();

            while(rs.next()){
                String password=rs.getString("pass");
                String name=rs.getString("username");
                passwords.put(name,password);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

        return passwords;
    }
    public Map<String, String> nameAndPassAdmins(Connection con)  {
        Map<String,String> passwords = new HashMap<>();
        try{
            ResultSet rs;
            PreparedStatement pst=con.prepareStatement("Select username,pass from TV_Operator.admins ;");
            rs=pst.executeQuery();

            while(rs.next()){
                String password=rs.getString("pass");
                String name=rs.getString("username");
                passwords.put(name,password);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }

        return passwords;
    }
    
    public String getIdOfAdmin(Connection con,String username) {
        ResultSet rs;
        String adminID = " ";
        try{
            PreparedStatement pst=con.prepareStatement("Select adminId from admins where username=?;");
            pst.setString(1,username);
            rs=pst.executeQuery();
            while(rs.next()){
                adminID=rs.getString("adminid");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return adminID;
    }

    public String getEmailOfAdmin(Connection con,String username) {
        ResultSet rs;
        String email = " ";
        try{
            PreparedStatement pst=con.prepareStatement("Select email from admins where username=?;");
            pst.setString(1,username);
            rs=pst.executeQuery();
            while(rs.next()){
                email=rs.getString("email");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return email;
    }

    public String getEmailOfClient(Connection con,String username) {
        ResultSet rs;
        String email = " ";
        try{
            PreparedStatement pst=con.prepareStatement("Select email from clients where username=?;");
            pst.setString(1,username);
            rs=pst.executeQuery();
            while(rs.next()){
                email=rs.getString("email");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return email;
    }

public List<String> channelsOfUser(Connection con, String username){
        ResultSet rs;
        List<String>channels = new ArrayList<>();
        String name;
    try{
        PreparedStatement pst=con.prepareStatement("Select name from channels join channel_package on channels.id=channel_package.channel_id join clients on channel_package.package_id=clients.id where username=?;");
        pst.setString(1,username);
        rs=pst.executeQuery();

        while(rs.next()){
             name=rs.getString("name");
            channels.add(name);
        }
    }
    catch (SQLException e){
        System.out.println(e.getMessage());
        try {
            con.close();
            exit();
        }
        catch (SQLException e1){
            exit();
        }
    }

    return channels;
    }
    public List<String> categoriesOfUser(Connection con, String username){
        ResultSet rs;
        List<String> categories = new ArrayList<>();
        try{
            PreparedStatement pst=con.prepareStatement("Select name from categories join package_category on categories.id=package_category.category_id join clients on package_category.package_id=clients.id where username=?;");
            pst.setString(1,username);
            rs=pst.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                categories.add(name);

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return categories;
    }
    
    public int getIdOfPackage(Connection con, String username)
    { ResultSet rs;
        int ID = 0;
        try{
            PreparedStatement pst=con.prepareStatement("Select package_id from clients where username=?;");
            pst.setString(1,username);
            rs=pst.executeQuery();
            while(rs.next()){
               
                 ID=rs.getInt("package_id");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return ID;   
    }

    public float dataForChannel(Connection con,String name ){
        ResultSet rs;
        float price = 0;
        try{
            PreparedStatement pst=con.prepareStatement("Select * from channels where name=?;");
            pst.setString(1,name);
            rs=pst.executeQuery();
            while(rs.next()){
                 price=rs.getFloat("price");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.close();
                exit();
            }
            catch (SQLException e1){
                exit();
            }
        }
        return price;
    }
    public Delivers getDeliverOfContract(Connection con, String num){
        ResultSet rs;
        Delivers deliver=new Delivers(null);
        try {
            PreparedStatement pst= con.prepareStatement("Select delivers.name from delivers\n" +
                    "join contracts on contracts.deliver_id=delivers.id where  numberOfContract=?;");
            pst.setString(1,num);
            rs= pst.executeQuery();
            while (rs.next()){
                 deliver.setName(rs.getString("name"));
                
            }
        }
        catch (SQLException | NullPointerException e ){
            System.out.println( e.getMessage());
        }
        return deliver;
    }


    public int getLengthOfContract(Connection con, String num){
        ResultSet rs;
        int lengthInMonths=0;
        try{
            PreparedStatement pst= con.prepareStatement("Select length_in_months from contracts where numberOfContract=?");
           pst.setString(1,num);
           rs= pst.executeQuery();
            while ((rs.next())){
                lengthInMonths=rs.getInt("length_in_months");
            }
        }
        catch (SQLException e){
            System.out.println( e.getMessage());
        }
        return lengthInMonths;
    }
    


}







