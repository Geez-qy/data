package com.qy.hnbt.data.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AreaEnum {
    NAPO("hnbt_451026"),LINGYUN("hnbt_451027"),TIANDONG("hnbt_451022");

    private String key;
}
