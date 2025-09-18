package org.moneytransfer.transfer;

import org.moneytransfer.account.Account;
import org.moneytransfer.transfer.valueobjects.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transfer {
    private final List<LedgerEntry> entries;
    private final List<TransactionLog> transactionLogs;

    private final TransferId transferId;
    private final Account fromAccount;
    private final Account toAccount;
    private final Money amount;
    private TransferStatus  status;


    public Transfer(Account fromAccount, Account toAccount, Money amount) {
        this.transferId = new TransferId(UUID.randomUUID());
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = TransferStatus.PENDING;
        this.entries = new ArrayList<>();
        this.transactionLogs = new ArrayList<>();

        logEvent(TransactionEvent.INITIATED);
    }

    public void execute() {
        // Validates business rules
        if (status != TransferStatus.PENDING) {
            throw new IllegalStateException("Transfer is already processed");
        }

        // creates ledger entries
        entries.add(new LedgerEntry(fromAccount, amount, DebitCredit.DEBIT, transferId));
        entries.add(new LedgerEntry(toAccount, amount, DebitCredit.CREDIT, transferId));
        // updates transaction log
        this.status = TransferStatus.COMPLETED;
        logEvent(TransactionEvent.COMPLETED);
    }

    private void logEvent(TransactionEvent event) {
        transactionLogs.add(new TransactionLog(transferId, event, LocalDateTime.now()));
    }

    public List<LedgerEntry> getEntries() {
        return entries;
    }

    public List<TransactionLog> getTransactionLogs() {
        return transactionLogs;
    }

    public TransferId getTransferId() {
        return transferId;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public TransferStatus getStatus() {
        return status;
    }
}
