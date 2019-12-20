package com.springboot.cloud.util;

import lombok.Data;

@Data
public class GaodeLocation{
    private static final long serialVersionUID = 5894304327211503218L;
    /**
     * 状态
     */
    private String status;
    /**
     * 响应信息
     */
    private String info;
    /**
     * 响应编码
     */
    private String infocode;

    private String province;
    private String city;
    private String adcode;
    private String rectangle;


}
