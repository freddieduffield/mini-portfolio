CREATE TABLE accounts (
                          account_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          balance BIGINT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE transfer_status AS ENUM ('complete', 'cancelled', 'requested', 'pending');

CREATE TABLE transfers (
                           transfer_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           from_account UUID NOT NULL,
                           to_account UUID NOT NULL,
                           amount BIGINT NOT NULL,
                           status transfer_status DEFAULT 'pending',
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT fk_from_account_id FOREIGN KEY (from_account) REFERENCES accounts(account_id),
                           CONSTRAINT fk_to_account_id FOREIGN KEY (to_account) REFERENCES accounts(account_id)
);

CREATE TYPE debit_credit_enum AS ENUM ('debit', 'credit');

CREATE TABLE ledger_entries (
                                entry_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                account_id UUID NOT NULL,
                                amount BIGINT NOT NULL,
                                debit_credit debit_credit_enum NOT NULL,
                                transfer_id UUID NOT NULL,
                                CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES accounts(account_id),
                                CONSTRAINT fk_transfer_id FOREIGN KEY (transfer_id) REFERENCES transfers(transfer_id)
);

CREATE TYPE event_type AS ENUM ('initiated', 'completed', 'failed');

CREATE TABLE transaction_log (
                                 transaction_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                 transfer_id UUID NOT NULL,
                                 event event_type NOT NULL,
                                 timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 CONSTRAINT fk_transfer_id FOREIGN KEY (transfer_id) REFERENCES transfers(transfer_id)
);