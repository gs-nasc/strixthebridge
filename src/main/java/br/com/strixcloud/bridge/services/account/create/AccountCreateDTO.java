package br.com.strixcloud.bridge.services.account.create;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AccountCreateDTO {

    private final String uuid;
    private final String player;

}
