package com.internjava.task2;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PropertyClass {
    private String stringProperty;
    @Property(name = "numberProperty")
    private Integer myNumber;

    @Property
    private Instant timeProperty;

}
