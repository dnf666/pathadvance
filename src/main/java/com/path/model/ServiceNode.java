package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceNode extends ServiceNodeKey {
    /**
     *     参见centernode
     */
    private String sName;

    private String sAddress;

    private Integer sType;

    private String sLongitude;

    private String sLatitude;

    private Float sBaidulongitude;

    private Float sBaidulatitude;

    private Double sQuatity;

    private String sRem;


}