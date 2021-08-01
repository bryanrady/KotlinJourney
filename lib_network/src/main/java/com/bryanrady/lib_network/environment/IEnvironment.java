package com.bryanrady.lib_network.environment;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/25 16:14
 * desc   : 环境
 */
public interface IEnvironment {

    /**
     * 正式环境Url
     * @return
     */
    String getFormal();

    /**
     * 测试环境Url
     * @return
     */
    String getTest();

}
