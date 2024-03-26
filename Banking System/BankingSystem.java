import java.io.*;
import java.util.HashMap;

public class BankingSystem {
    private HashMap<String,AccountHolder> accountHolderMap;
    private static final String ACCOUNTS_FILE = "accounts.txt";
    private int x;
    public BankingSystem()  {
        this.accountHolderMap = new HashMap<>();
        loadAccountHolderMap();
    }
    public void createAccount(AccountHolder holder){
        accountHolderMap.put(holder.getAccountNumber(),holder);
        saveAccountHolderMap();
    }

    public void deposit(String accountNumber,int amount){
        AccountHolder holder = accountHolderMap.get(accountNumber);
        if(holder != null){
            holder.addBalance(amount);
        }else{
            System.out.println("Error");
        }
        saveAccountHolderMap();
    }
    public void withdraw(String accountNumber, int amount){
        AccountHolder holder = accountHolderMap.get(accountNumber);
        if(holder!=null){
            holder.subtractBalance(amount);
        }
        saveAccountHolderMap();
    }
    public void transfer(String accountNumberOne,String accountNumberTwo, int amount){
        AccountHolder holder = accountHolderMap.get(accountNumberOne);
        AccountHolder holder1 = accountHolderMap.get(accountNumberTwo);

        if(holder.getBalance() - amount >= 0){
            holder.subtractBalance(amount);
            holder1.addBalance(amount);
        }else{
            throw new IllegalArgumentException("Balance is not enough to transfer");
        }
        saveAccountHolderMap();
    }
    public void updateAccountInfo(String accountNumber,String name,String address,String password){
        AccountHolder holder = accountHolderMap.get(accountNumber);
        holder.setName(name);
        holder.setAddress(address);
        holder.setPassword(password);
        saveAccountHolderMap();
    }
    public void closeAccount(String accountNumber){
        accountHolderMap.remove(accountNumber);
        saveAccountHolderMap();
    }
    public int checkBalance(String accountNumber){
        return accountHolderMap.get(accountNumber).getBalance();
    }
    public boolean login(String accountNumber, String password){
        AccountHolder holder = accountHolderMap.get(accountNumber);
        if(holder!=null){
            return holder.authenticate(password);
        }
        System.out.println("Account not found");
        return false;
    }
    public boolean doesAccountExist(String accountNumber){
        if(accountHolderMap.get(accountNumber).equals(null) ){
            return false;
        }
        return true;
    }
    public String toString(String accountNumber){
        return accountHolderMap.get(accountNumber).toString();
    }
    public void loadAccountHolderMap() {
        File file = new File(ACCOUNTS_FILE);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    continue; // skip if the line doesn't have enough parts
                }
                String name = parts[0];
                String address = parts[1];
                int balance = Integer.parseInt(parts[2]);
                String password = parts[3];
                String accountNumber = parts[4];
                AccountHolder holder = new AccountHolder(name, address, balance, password);
                holder.accountNumber = accountNumber; // directly set the account number
                accountHolderMap.put(accountNumber, holder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveAccountHolderMap() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNTS_FILE, false))) {
            for (AccountHolder holder : accountHolderMap.values()) {
                String line = holder.getName() + "," + holder.getAddress() + "," + holder.getBalance() + "," + holder.getPassword() + "," + holder.getAccountNumber();
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
