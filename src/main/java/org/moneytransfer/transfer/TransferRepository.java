package org.moneytransfer.transfer;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.moneytransfer.config.Database;
import org.moneytransfer.generated.enums.DebitCreditEnum;
import org.moneytransfer.generated.enums.EventType;

import static org.moneytransfer.generated.Tables.*;

public class TransferRepository {
    private final DSLContext dsl;
    public TransferRepository() {
        this.dsl = Database.getDsl();
    }
    public Transfer save(Transfer transfer) {
        dsl.transaction((configuration) -> {
            DSLContext ctx = DSL.using(configuration);

            ctx.insertInto(TRANSFERS)
                    .set(TRANSFERS.TRANSFER_ID, transfer.getTransferId().value())
                    .set(TRANSFERS.FROM_ACCOUNT, transfer.getFromAccount().getAccountId())
                    .set(TRANSFERS.TO_ACCOUNT, transfer.getToAccount().getAccountId())
                    .set(TRANSFERS.AMOUNT, transfer.getAmount().amount().longValue())
                    .execute();

            for (TransactionLog transactionLog : transfer.getTransactionLogs()) {
                ctx.insertInto(TRANSACTION_LOG)
                        .set(TRANSACTION_LOG.TRANSACTION_ID, transactionLog.transferId().value())
                        .set(TRANSACTION_LOG.TRANSFER_ID, transactionLog.transferId().value())
                        .set(TRANSACTION_LOG.EVENT, EventType.valueOf(transactionLog.event().name()))
                        .set(TRANSACTION_LOG.TIMESTAMP, transactionLog.timestamp())
                        .execute();
            }

            for (LedgerEntry le :  transfer.getEntries()) {
                ctx.insertInto(LEDGER_ENTRIES)
                        .set(LEDGER_ENTRIES.TRANSFER_ID, le.transferId().value())
                        .set(LEDGER_ENTRIES.AMOUNT, le.amount().amount().longValue())
                        .set(LEDGER_ENTRIES.ACCOUNT_ID, le.accountId().getAccountId())
                        .set(LEDGER_ENTRIES.DEBIT_CREDIT, DebitCreditEnum.valueOf(le.type().name()))
                        .execute();
            }
        });

        return transfer;
    }
}
