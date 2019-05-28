package kr.dev.yeobi.dto.Account;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // 다건 조회
    public List<AccountDto.Response> findAll() {
        return accountRepository.findAll().stream()
                .map(AccountDto.Response::of)
                .collect(Collectors.toList());
    }

    // 단건 조회
    public AccountDto.Response findById(Long id) {
        Account createdAccount = accountRepository.findById(id).orElseThrow(IllegalStateException::new);

        return AccountDto.Response.of(createdAccount);
    }

    // 생성
    public AccountDto.Response create(AccountDto.CreateRequest createRequest) {
        Account account = accountRepository.save(createRequest.toEntity());

        return AccountDto.Response.of(account);
    }

    // 수정
    public AccountDto.Response update(Long id, AccountDto.UpdateRequest updateRequest) {
        Account account = accountRepository.findById(id)
                .map(updateRequest::apply)
                .orElseThrow(IllegalStateException::new);

        return AccountDto.Response.of(accountRepository.save(account));
    }

}
