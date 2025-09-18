package org.moneytransfer.transfer;

import org.moneytransfer.account.Account;
import org.moneytransfer.transfer.valueobjects.DebitCredit;
import org.moneytransfer.transfer.valueobjects.Money;
import org.moneytransfer.transfer.valueobjects.TransferId;

public record LedgerEntry(
        Account accountId,
        Money amount,
        DebitCredit type,
        TransferId transferId
) {
}
