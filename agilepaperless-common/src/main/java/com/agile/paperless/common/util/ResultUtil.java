package com.agile.paperless.common.util;


import com.agile.paperless.common.enums.ResultState;
import com.agile.paperless.common.result.ListResult;
import com.agile.paperless.common.result.ObjectResult;
import com.agile.paperless.common.result.Result;

import java.util.List;

/**
 * @Author: WuYL
 * @Description: 结果集封装工具类
 * @Date: Create in 2017/11/5 21:58
 * @Modified By:
 */
public class ResultUtil {

    public static ListResult success(List objects) {
        ListResult result = new ListResult();
        result.setCode(ResultState.RESULT_SUCCESS.code());
        result.setMsg(ResultState.RESULT_SUCCESS.message());
        result.setResult(objects);
        result.setCount(objects.size());
        return result;
    }

    public static Result error(Integer code, String msg) {
        ObjectResult result = new ObjectResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setResult(null);
        return result;
    }

    public static Result error() {
        return error(ResultState.RESULT_ERROR.code(), ResultState.RESULT_ERROR.message());
    }

    public static ObjectResult success(Object object) {
        ObjectResult result = new ObjectResult();
        result.setCode(ResultState.RESULT_SUCCESS.code());
        result.setMsg(ResultState.RESULT_SUCCESS.message());
        result.setResult(object);
        return result;
    }

    public static ObjectResult success(){
        return success(new Object());
    }
}
