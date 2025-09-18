package org.moneytransfer.transfer;

import org.moneytransfer.transfer.valueobjects.TransactionEvent;
import org.moneytransfer.transfer.valueobjects.TransferId;

import java.time.LocalDateTime;

public record TransactionLog(
        TransferId transferId,
        TransactionEvent event,
        LocalDateTime timestamp
) {
}
