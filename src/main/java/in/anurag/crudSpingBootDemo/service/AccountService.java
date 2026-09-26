package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.entity.Account;
import in.anurag.crudSpingBootDemo.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }
}
