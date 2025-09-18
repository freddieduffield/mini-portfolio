package org.moneytransfer.account;

import org.jooq.DSLContext;
import org.moneytransfer.config.Database;

import java.util.UUID;
import static org.moneytransfer.generated.Tables.ACCOUNTS;

public class AccountRepository {
    private final DSLContext dsl;

    public AccountRepository() {
        dsl = Database.getDsl();
    }

    public Account findById(UUID id) {
        return dsl
                .selectFrom(ACCOUNTS)
                .where(ACCOUNTS.ACCOUNT_ID.eq(id))
                .fetchOneInto(Account.class);
    }
}
