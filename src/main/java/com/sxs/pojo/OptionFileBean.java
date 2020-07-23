package com.sxs.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @program: selftools
 * @description: 抽象出来的操作文件的ben
 * @author: LiPan
 * @create: 2020-07-23 12:47
 **/
@Data
public class OptionFileBean {
    private String path;
    private Map<String, List<String>> delete;
    private Map<String, String> rename;
}
