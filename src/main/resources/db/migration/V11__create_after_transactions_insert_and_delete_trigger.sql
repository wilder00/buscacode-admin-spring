-- WHEN INSERT A TRANSACTION
DELIMITER //

CREATE TRIGGER after_transaction_insert
AFTER INSERT ON transactions
FOR EACH ROW
BEGIN
    IF NEW.type = 'INFLOW' THEN
        UPDATE accounts
        SET current_balance = current_balance + NEW.amount
        WHERE id = NEW.account_id;
    ELSEIF NEW.type = 'OUTFLOW' THEN
        UPDATE accounts
        SET current_balance = current_balance - NEW.amount
        WHERE id = NEW.account_id;
    END IF;
END;

//

DELIMITER ;

-- WHEN DELETE A TRANSACTION
DELIMITER //

CREATE TRIGGER after_transaction_delete
AFTER DELETE ON transactions
FOR EACH ROW
BEGIN
    -- Reverse the transaction effect on account balance
    IF OLD.type = 'inflow' THEN
        UPDATE accounts
        SET current_balance = current_balance - OLD.amount
        WHERE id = OLD.account_id;
    ELSEIF OLD.type = 'outflow' THEN
        UPDATE accounts
        SET current_balance = current_balance + OLD.amount
        WHERE id = OLD.account_id;
    END IF;
END;

//

DELIMITER ;
