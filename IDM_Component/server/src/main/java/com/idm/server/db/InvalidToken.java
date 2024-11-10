package com.idm.server.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidToken {
    private int id;
    private String token;
    private String reason;

    public InvalidToken(String token, String reason) {
        this.token = token;
        this.reason = reason;
    }
}
