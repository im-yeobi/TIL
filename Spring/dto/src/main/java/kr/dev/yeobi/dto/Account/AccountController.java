package kr.dev.yeobi.dto.Account;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/account")
@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDto.Response> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountDto.Response findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping
    public AccountDto.Response create(@RequestBody @Valid AccountDto.CreateRequest createRequest) {
        return accountService.create(createRequest);
    }

    @PatchMapping("/{id}")
    public AccountDto.Response update(@PathVariable Long id, @RequestBody AccountDto.UpdateRequest updateRequest) {
        return accountService.update(id, updateRequest);
    }

}
