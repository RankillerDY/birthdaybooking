package com.swp.birthdaybooking.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    USER_READ("student:read"),
    USER_UPDATE("student:update"),
    USER_CREATE("student:create"),
    USER_DELETE("student:delete"),
    HOST_READ("leader:read"),
    HOST_UPDATE("leader:update"),
    HOST_CREATE("leader:create"),
    HOST_DELETE("leader:delete")
    ;

    @Getter
    private final String permission;


}
