package com.geektrust.backend.entities;

import com.geektrust.backend.exceptions.InValidAmountException;

public class MetroCard extends BaseEntity{
    
    private final String cardNumber;
    private int balance;
    private static final int MIN_AMOUNT = 0;

    //constructors,id,cardNumber,balance
    public MetroCard(String id, String cardNumber, int balance) {
       this(cardNumber, balance);
       this.id = id;
    }

    //constructor,cardNumber,balance
    public MetroCard(String cardNumber, int balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
     }

     //getters 
    public String getCardNumber() {
        return cardNumber;
    }

    public int getBalance() {
        return balance;
    } 
    
    //credit amount to card
    public void addAmount(int amount) {
        validateForCredit(amount);
        balance += amount;
    }

    public void validateForCredit(int amount) {
        if(amount <= MIN_AMOUNT){
            throw new InValidAmountException();
        }
    }

    //debit amount from card
    public void deductAmount(int amount) {
        validateForDebit(amount);
        balance -= amount;
    }

    public void validateForDebit(int amount) {
        if(amount <= MIN_AMOUNT || amount > balance){
            throw new InValidAmountException();
        }
    }

    public boolean hasSufficientBalance(int amount) {
        if (amount <= this.balance)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + balance;
        result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()) {
            return false;
        }
    
        MetroCard other = (MetroCard) obj;
        if((this.cardNumber.equals(other.cardNumber)) && (this.balance == other.balance))
            return true;
    
        return false;
    }
}
