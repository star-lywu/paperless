package com.agile.paperless.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: WuYL
 * @Description: 实体结果集
 * @Date: Create in 2017/11/5 21:32
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectResult<T> implements Result{
    //状态码
    private Integer code;
    //返回信息
    private String msg;
    //返回具体数据
    private T result;
}
