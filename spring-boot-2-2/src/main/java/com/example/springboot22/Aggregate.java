package com.example.springboot22;

import lombok.Value;
import lombok.With;

@With
@Value
// @Table("aggregate")
// sql schema should have aggregate_root_key (int) and aggregate_root (uuid)
public class Aggregate {
    private String prop;
    private String value;
}
