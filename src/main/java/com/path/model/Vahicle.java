package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vahicle extends VahicleKey {
    /**
     * 车辆类型。如东风牌拖拉机t-1号
     */
    private String vType;
    /**
     * 车的装载量
     */
    private Float vCapacity;
    /**
    * 耗油量升/百公里每小时
     */
    private Float vOil;
    /**
     * 运费元/每公里
     */
    private Float vPrice;
    /**
     * 注册时间
     */
    private Date vDate;

    private String vRem;


}