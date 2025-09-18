package org.moneytransfer.transfer;

import org.moneytransfer.account.Account;
import org.moneytransfer.account.AccountRepository;

public class TransferService {
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    public void executeTransfer(TransferRequest request) {
        Account fromAccount = accountRepository.findById(request.fromId());
        Account toAccount = accountRepository.findById(request.toId());

        Transfer transfer = new Transfer(fromAccount, toAccount, request.amount());
        transfer.execute();
        transferRepository.save(transfer);
    }
}
