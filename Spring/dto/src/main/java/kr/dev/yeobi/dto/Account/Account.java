package kr.dev.yeobi.dto.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "entity")
public class Account {

    @Id // @Column 포함되어 있다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Builder
    private Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static Account of(String email, String password, String name) {
        return Account.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

    public Account update(String password, String name) {
        this.password = password;
        this.name = name;

        return this;
    }

}
