import java.util.Scanner;

public class Main {
    private static final BankingSystem system = new BankingSystem();
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        boolean validInput = false;


        while(!validInput){
            System.out.println("Do you (1) have an account or (2) wish to create an account (3) exit ");
            String in = scan.next();
            if(in.equals("1")){
                String accNumber = authenticateLogin();
                while(true){
                    System.out.println("Choose the option you would like to do: ");
                    System.out.println("(1) Deposit\n(2) Withdraw\n(3) Transfer\n(4) View Account Details\n(5) Update Account Info\n(6) Close Account \n(7) Exit \n(8) Check Balance");
                    String input = scan.next();
                    switch(input){
                        case "1":
                           int deposit = 0;
                           boolean valid = false;
                           while(!valid){
                               System.out.println("Enter the amount you would like to deposit");
                               if(scan.hasNextInt()){
                                   deposit = scan.nextInt();
                                   if(deposit < 0){
                                       System.out.println("Negative deposit is invalid");
                                       continue;
                                   }
                                   else {
                                       valid = true;
                                   }
                               }
                               else{
                                   System.out.println("Invalid Input");
                                   scan.next();
                               }
                           }
                           system.deposit(accNumber,deposit);
                            System.out.println("Deposit success");
                           break;
                        case "2":
                            withdrawFunds(accNumber);
                            System.out.println("Withdrawal Success");
                            break;

                        case "3":
                            valid = false;
                            while(!valid){
                                System.out.println("What is the account number of the user you want to transfer money to? Enter (2) to exit");
                                String userInput = scan.next();
                                if(system.doesAccountExist(userInput) == false){
                                    System.out.println("Account not found");
                                    continue;
                                }
                                System.out.println("How much would you like to send to that user ");
                                int amount = withdrawFunds(accNumber);
                                system.deposit(accNumber,amount);
                                System.out.println("Transaction successful");
                            }
                            break;
                        case "4":
                            System.out.println(system.toString(accNumber));
                            break;
                        case "5":
                            System.out.println("Enter your name: ");
                            String name = scan.next();
                            System.out.println("Enter your address: ");
                            String address = scan.nextLine();
                            System.out.println("Enter new password: ");
                            String password = scan.next();
                            system.updateAccountInfo(accNumber,name,address,password);
                            System.out.println("Account updates completed");
                            break;
                        case "6":
                            system.closeAccount(accNumber);
                            System.out.println("Funds have been withdrawn to you and account is closed! ");
                            break;
                        case "7":
                            System.exit(0);
                        case "8":
                            System.out.println(system.checkBalance(accNumber));
                            break;
                        default:
                            System.out.println("invalid input");
                            break;
                    }
                }
            }
            else if (in.equals("2")) {
                createAccount();
            }
            else if (in.equals("3")) {
                validInput = true;
                break;
            }
            else{
                System.out.println("invalid input try again");
            }
        }
    }
    private static void createAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Whats your name? ");
        String name = scanner.nextLine();

        System.out.println("Whats your address? ");
        String address = scanner.nextLine();

        System.out.println("How much would you like to initially deposit? ");
        int deposit = 0;
        boolean validDeposit = false;
        while(!validDeposit){
            if(scanner.hasNextInt()){
                deposit = scanner.nextInt();
                if(deposit < 0){
                    System.out.println("cannot deposit negative value: ");
                }
                else {
                    validDeposit = true;
                }
            }
            else {
                System.out.println("Invalid input: ");
                scanner.next();
            }
        }

        System.out.println("Enter a password to be used for your account");
        String password = scanner.next();

        AccountHolder holder = new AccountHolder(name,address,deposit,password);

        system.createAccount(holder);
        System.out.println("To login you must use your account number. Your account number is: " + holder.getAccountNumber() + "\nDon't Lose this its important! ");
    }
    private static String authenticateLogin(){
        String accNumber = "";
        while(true){
            System.out.println("Enter your account number: ");
            accNumber = scan.next();
            System.out.println("Enter your password");
            String pass = scan.next();
            boolean isAuthenticated = system.login(accNumber,pass);
            if(!isAuthenticated){
                System.out.println("Login failed");
                continue;
            }
            System.out.println("login success");
            break;
        }
        return accNumber;
    }
    private static int withdrawFunds(String accNumber){
        boolean valid = false;
        int withdraw = 0;
        int currentBalance = system.checkBalance(accNumber);
        while(!valid){
            if(system.checkBalance(accNumber) <= 0){
                System.out.println("You have no money to withdraw ");
                break;
            }
            System.out.println("How much would you like to withdraw? ");
            if(scan.hasNextInt()){
                withdraw = scan.nextInt();
                if(withdraw > currentBalance){
                    System.out.println("Insufficient funds your current balance is " + currentBalance);
                }
                else if (withdraw <= 0) {
                    System.out.println("Withdrawal amount must be positive.");
                }
                else {
                    valid = true;
                }
            }
            else {
                System.out.println("Insufficient input please enter a number");
                scan.next();
            }
        }
        system.withdraw(accNumber,withdraw);
        return withdraw;
    }
}
