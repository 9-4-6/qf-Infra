package org.gz.qfinfra.web;

/**
 * @author guozhong
 * @date 2025/11/15
 */
public class WebRBuilder {
    /**
     * 构建处理成功请求
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebR success(T data){
        WebREnum success = WebREnum.SUCCESS;
        return WebR.builder().success(true).code(success.getCode())
                .msg(success.getMsg()).data(data).build();
    }

    /**
     * 构建处理成功请求
     * @param code 响应代码
     * @param msg 响应消息
     * @param data 响应数据
     * @return
     */
    public static <T> WebR success(Integer code, String msg, T data){
        return WebR.builder().success(true).code(code)
                .msg(msg).data(data).build();
    }

    /**
     * 构建处理失败请求
     * @param
     * @param <T>
     * @return
     */
    public static <T> WebR fail(){
        WebREnum fail = WebREnum.FAIL;
        return WebR.builder().success(false).code(fail.getCode())
                .msg(fail.getMsg()).build();
    }

    /**
     * 构建自定义参数的处理失败请求
     * @param code 响应代码
     * @param msg 响应消息
     * @return
     */
    public static <T> WebR fail(Integer code, String msg){
        return WebR.builder().success(false).code(code)
                .msg(msg).build();
    }
}
