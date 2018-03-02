package cn.com.exception;

/**
 * 业务异常
 * @author: JinBiaoHuang
 * @description:
 * @Date: 3/2/2018
 * @modify by:
 */
public class BusinessException extends RuntimeException {

    public BusinessException(){
        super();
    }

    public BusinessException(String format,String... args){
        super(String.format(format,args));
    }
}
