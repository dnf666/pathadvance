package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Distance extends DistanceKey {
    private Integer dId;

    private String startId;

    private String endId;
    private String startAddress;
    private String endAddress;
    private Integer standardDis;

    private Integer standardTime;

}