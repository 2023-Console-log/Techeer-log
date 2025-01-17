package com.techeerlog.project.enums;

import com.techeerlog.global.support.EnumModel;
import lombok.Getter;

@Getter
public enum PlatformEnum implements EnumModel {
    WEB("WEB"), APP("APP"), WEB_APP("WEB_APP");

    private final String name;

    PlatformEnum(String name) {
        this.name = name;
    }
}
