package com.agile.paperless.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: WuYL
 * @Description: 集合结果集
 * @Date: Create in 2017/11/5 21:30
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResult<T> implements Result{
    //状态码
    private Integer code;
    //返回信息
    private String msg;
    //返回具体数据
    private List<T> result;
    //返回数据数量
    private Integer count;
}
