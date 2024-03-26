import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class AccountHolderTest {
    @Test
    void testGetName() {
        String expectedName = "John Doe";
        AccountHolder holder = new AccountHolder(expectedName,"13802 acoro st",1000,"dad");

        String actualName = holder.getName();

        assertEquals(expectedName,actualName);
    }
    @Test
    void testGetAddress() {
        String expectedAddress = "13802 Acoro St";
        AccountHolder holder = new AccountHolder(expectedAddress,"13802 Acoro St",1000,"dad");

        String actualAddress = holder.getAddress();

        assertEquals(expectedAddress,actualAddress);
    }
    @Test
    void testGetBalance(){
        int expectedBalance = 10;
        AccountHolder holder = new AccountHolder("John Doe","13802 acoro st",expectedBalance,"dad");

        int actualBalance = holder.getBalance();

        assertEquals(expectedBalance,actualBalance);
    }
    @Test
    void testAddBalance(){
        int expectedDeposit = 10;
        AccountHolder holder = new AccountHolder("John Doe","13802 Acoro St",0,"dad");
        holder.addBalance(expectedDeposit);
        assertEquals(expectedDeposit,holder.getBalance());
    }
    @Test
    void testSubtractBalance(){
        int expectedAmount = 50;
        AccountHolder holder = new AccountHolder("John Doe","13802 Acoro St",100,"dad");
        holder.subtractBalance(50);
        assertEquals(expectedAmount,holder.getBalance());
    }
    @Test
    void testGenerateAccountNumberLength(){
        int expectedLength = 8;
        AccountHolder holder = new AccountHolder("John Doe","13802 Acoro St",0,"dad");
        String temp = holder.generateAccountNumber();
        assertEquals(expectedLength,temp.length());
    }
    @Test
    void testGenerateAccountNumberUniqueness(){
        AccountHolder holder = new AccountHolder("John Doe","13802 Acoro St",0,"dad");
        Set<String> generatedNumbers = new HashSet<>();
        int iterations = 1000;
        for (int i = 0; i < iterations; i++) {
            String accountNumber = holder.generateAccountNumber();
            assertFalse(generatedNumbers.contains(accountNumber));
            generatedNumbers.add(accountNumber);
        }
    }
    @Test
    void testGenerateAccountNumberInput(){
        AccountHolder holder = new AccountHolder("John Doe","13802 Acoro St",0,"dad");
        String accountNumber = holder.generateAccountNumber();

        for (int i = 0; i < accountNumber.length(); i++) {
            int temp = accountNumber.charAt(i);
            boolean isDigit = Character.isDigit(temp);
            boolean isUppercaseLetter = Character.isUpperCase(temp);
            assertTrue(isDigit || isUppercaseLetter);
        }
    }
    @Test
    void testToString(){
        AccountHolder holder = new AccountHolder("John Doe","13802 Acoro St",0,"dad");
        String expected = holder.getName() + holder.getAddress()+holder.getBalance()+holder.getAccountNumber();
        assertEquals(expected,holder.toString());
    }
}