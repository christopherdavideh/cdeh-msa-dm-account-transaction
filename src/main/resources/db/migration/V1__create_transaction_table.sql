-- Drop tables if they exist for clean migration
DROP TABLE IF EXISTS transactions;

-- Create transactions table
CREATE TABLE transactions (
    transaction_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_id UUID NOT NULL,
    source_account VARCHAR(50) NOT NULL,
    initial_balance DECIMAL(19,2) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    available_balance DECIMAL(19,2) NOT NULL,
    transaction_status BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX idx_transactions_customer_id ON transactions(customer_id);
CREATE INDEX idx_transactions_source_account ON transactions(source_account);
CREATE INDEX idx_transactions_created_at ON transactions(created_at);
CREATE INDEX idx_transactions_status ON transactions(transaction_status);
