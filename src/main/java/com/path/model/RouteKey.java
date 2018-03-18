package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class RouteKey {
    private Integer rId;

    private Integer fId;

    private Integer vId;

}