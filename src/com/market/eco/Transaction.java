package com.market.eco;

import com.market.account.Account;

enum TransactionState{
    Created, // Transaction got created no action taken yet
    Withdrawn, // Money/Commodity got withdrawn from source
    Confirmed, // Trade got confirmed / authorised
    Deposited, // Money/commodity get deposited to destination
    Done, // Transaction is complete
    Canceling, // Transaction is being canceled, Money/Commodity is not yet returned to source
    Canceled, // Transaction got canceled, Money/Commodity has been returned to its source
    Failed, // Transaction failed, Money/Commodity is still in the Transaction
}

public class Transaction {
    public void setSourceAccount(Account sourceAccount) {
        if(this.state == TransactionState.Created || this.state == TransactionState.Failed) {
            this.sourceAccount = sourceAccount;
        }
    }

    public void setDestinationAccount(Account destinationAccount) {
        if (this.state == TransactionState.Created || this.state == TransactionState.Failed || this.state == TransactionState.Withdrawn) {
            this.destinationAccount = destinationAccount;
        }
    }

    private Account sourceAccount;
    private Account destinationAccount;
    private double amount = 0.0;
    private Commodity commodity = null;
    private TransactionState state;

    public Transaction(Account source, Account destination, double amount) {
        this.sourceAccount = source;
        this.destinationAccount = destination;
        this.amount = source.getCurrency().getWorth() * amount;
        this.state = TransactionState.Created;
        update();
    }

    public Transaction(Account source, Account destination, Commodity commodity) {
        this.sourceAccount = source;
        this.destinationAccount = destination;
        this.commodity = commodity;
        this.state = TransactionState.Created;
        update();
    }

    public TransactionState update() {
        double sourceAmount = this.amount * this.sourceAccount.getCurrency().getWorth();
        double destinationAmount = this.amount * this.destinationAccount.getCurrency().getWorth();

        transactionSwitch:{
            switch(this.state) {
                case Created:
                    // withdraw from source
                    if(this.sourceAccount.withdraw(sourceAmount)) {
                        this.state = TransactionState.Withdrawn;
                    } else {
                        this.state = TransactionState.Canceled;
                        break;
                    }
                case Withdrawn:
                    // In case of trade wait for confirmation
                    this.state = TransactionState.Confirmed;

                case Confirmed:
                    // deposit to destination
                    if(this.destinationAccount.deposit(destinationAmount)) {
                        this.state = TransactionState.Deposited;
                    } else {
                        // TODO: Have another look at this for automated trading (so that trades dont get canceled if buyer failed)
                        this.state = TransactionState.Canceling;
                        break transactionSwitch;
                    }

                case Deposited:
                    // mark as done (maybe callback)
                    this.state = TransactionState.Done;
                    break;

                case Canceling:
                    // Transaction got canceled return money/commodity to source
                    if(this.sourceAccount.deposit(sourceAmount)) {
                        this.state = TransactionState.Canceled;
                    } else {
                        this.state = TransactionState.Failed;
                    }

            }

        }

        return this.state;
    }

    public boolean cancel() {
        if(this.state == TransactionState.Withdrawn || this.state == TransactionState.Failed) {
            this.state = TransactionState.Canceling;
            return true;
        }
        return false;
    }

    public boolean confirm() {
        if(this.state == TransactionState.Withdrawn) {
            this.state = TransactionState.Confirmed;
            return true;
        }
        return false;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public TransactionState getState() {
        return state;
    }

}
