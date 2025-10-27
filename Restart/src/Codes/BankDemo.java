package Codes;

abstract class BankAccount {
    abstract void deposit(double amount);
    abstract void withdraw(double amount);
}

class SavingAccount extends BankAccount {
    double balance = 0;
    
    void deposit(double amount) {
        balance += amount;
        System.out.println("SavingAccount Balance: " + balance);
    }
    
    void withdraw(double amount) {
        if (balance >= amount) balance -= amount;
        System.out.println("SavingAccount Balance: " + balance);
    }
}

class CurrentAccount extends BankAccount {
    double balance = 0;
    
    void deposit(double amount) {
        balance += amount;
        System.out.println("CurrentAccount Balance: " + balance);
    }
    
    void withdraw(double amount) {
        if (balance >= amount) balance -= amount;
        System.out.println("CurrentAccount Balance: " + balance);
    }
}

public class BankDemo {
    public static void main(String[] args) {
        BankAccount s = new SavingAccount();
        s.deposit(20000);
        s.withdraw(10000);
        
        BankAccount c = new CurrentAccount();
        c.deposit(40000);
        c.withdraw(30000);
    }
}
