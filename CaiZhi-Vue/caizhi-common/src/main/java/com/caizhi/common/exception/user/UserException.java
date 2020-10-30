package com.caizhi.common.exception.user;

import com.caizhi.common.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author caizhi
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
