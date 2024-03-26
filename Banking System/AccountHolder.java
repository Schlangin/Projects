import java.io.Serializable;
import java.util.Random;

public class AccountHolder {

    private String password;
    private String name;
    private String address;
    private int balance;
    String accountNumber;
    AccountHolder(String name,String address,int initialDeposit,String password){
        this.name = name;
        this.address = address;
        balance = initialDeposit;
        this.password = password;
        generateAccountNumber();
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int getBalance(){
        return balance;
    }
    public void addBalance(int deposit){
        balance = balance + deposit;
    }
    public void subtractBalance(int withdraw){
        if((balance - withdraw) < 0){
            throw new IllegalArgumentException("ERROR NOT ENOUGH FUNDS ");
        }else
        balance = balance - withdraw;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getPassword(){
        return password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    public boolean authenticate(String password){
        return this.password.equalsIgnoreCase(password);
    }
    // suppose to be private but temporarily public
    String generateAccountNumber(){
        Random rand = new Random();
        accountNumber = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 8; i++) {
            int temp = rand.nextInt(2);
            if(temp == 0){
                int number = rand.nextInt(10);
                accountNumber = accountNumber + number;
            }
            else {
                int temporary = rand.nextInt(26);
                accountNumber = accountNumber + alphabet.charAt(temporary);
            }
        }
        return accountNumber;
    }
    public String toString(){
        return name + address + balance + accountNumber;
    }
}
