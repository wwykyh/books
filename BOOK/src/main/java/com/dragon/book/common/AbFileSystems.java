package com.dragon.book.common;

/**
 * @ClassName AbFileSystems
 * @Description 文件系统抽象类
 * @Author Administrator
 * @Date 2019/4/4  10:01
 * @Version 1.0
 */
public abstract class AbFileSystems {

    protected abstract Object getServerConnection();

    protected abstract void closeConnection();

}
