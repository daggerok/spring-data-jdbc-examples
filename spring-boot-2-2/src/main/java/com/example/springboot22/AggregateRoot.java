package com.example.springboot22;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
// @Table("aggregate_root")
public class AggregateRoot {

    @Id
    private UUID id;
    private List<Aggregate> aggregates = new ArrayList<>();
}
