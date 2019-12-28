package com.selfspring.gimi.poi;

import lombok.Data;

/**
 * Created by ckyang on 2019/12/27.
 */
@Data
public class FieldDeclare {
    private String clair;
    private String name;

    public FieldDeclare(String clair, String name) {
        this.clair = clair;
        this.name = name;
    }

    public FieldDeclare(String name) {
        this.clair = "java.lang.String";
        this.name = name;
    }
}
