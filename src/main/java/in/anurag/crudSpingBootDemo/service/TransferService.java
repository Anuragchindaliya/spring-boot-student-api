package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.entity.Account;
import in.anurag.crudSpingBootDemo.entity.TransferRecord;
import in.anurag.crudSpingBootDemo.repository.AccountRepository;
import in.anurag.crudSpingBootDemo.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransferService {
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository,TransferRepository transferRepository){
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount){
        
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(()-> new RuntimeException("User not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(()-> new RuntimeException("User not found"));

        fromAccount.debitAccount(amount);
        toAccount.creditAccount(amount);
        
        transferRepository.save(new TransferRecord(fromAccountId, toAccountId, amount, LocalDate.now()));

    }
}
