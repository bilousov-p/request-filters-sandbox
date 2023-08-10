package me.bilousov.requestfilterssandbox.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestModel {
    private String testModelName;
    private int testModelVersion;
}
