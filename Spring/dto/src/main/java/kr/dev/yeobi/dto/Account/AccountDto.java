package kr.dev.yeobi.dto.Account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class AccountDto {

    @Getter
    public static class CreateRequest {
        @NotNull
        @JsonProperty
        private String email;

        @NotNull
        @JsonProperty
        private String password;

        @NotNull
        @JsonProperty
        private String name;

        public Account toEntity() {
            return Account.of(email, password, name);
        }
    }

    @Getter
    public static class UpdateRequest {
        @NotNull
        @JsonProperty
        private String password;

        @NotNull
        @JsonProperty
        private String name;

        public Account apply(Account account) {
            return account.update(password, name);
        }
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        @JsonProperty
        private Long id;

        @JsonProperty
        private String email;

        @JsonProperty
        private String name;

        @Builder
        private Response(Long id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        public static Response of(Account account) {
            return Response.builder()
                .id(account.getId())
                .email(account.getEmail())
                .name(account.getName())
                .build();
        }
    }

}
