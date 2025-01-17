package com.techeerlog.project.enums;

import com.techeerlog.global.support.EnumModel;
import lombok.Getter;

@Getter
public enum SearchFieldEnum implements EnumModel {
    TITLE("TITLE"),
    WRITER("WRITER"),
    TEAM_MEMBER("TEAM_MEMBER"),
    CONTENT("CONTENT"),
    ALL("ALL"),
    ;

    private final String name;

    SearchFieldEnum(String name) {
        this.name = name;
    }
}
