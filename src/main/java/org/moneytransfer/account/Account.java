package org.moneytransfer.account;

import java.util.UUID;

public class Account {
    private final UUID accountId;

    public Account(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getAccountId() {
        return accountId;
    }
}
