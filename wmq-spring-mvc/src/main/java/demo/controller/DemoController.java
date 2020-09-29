package demo.controller;

import framework.annotation.MyController;
import framework.annotation.MyRequestMapping;
import framework.annotation.MyRequestParam;

/**
 * @author wangmengqi
 * @Title: DemoCotroller
 * @ProjectName maniuk-parent-hsm
 * @Description:
 * @date 2020/6/30 21:42
 */
@MyController
@MyRequestMapping("/demo")
public class DemoController {

    @MyRequestMapping("/test")
    public String getName(@MyRequestParam("name") String name){
        return name;
    }
}
