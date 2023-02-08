package com.simiam.awekit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <p>Title: ScriptEngineTest</p>
 * <p>Description:使用脚本引擎Nashorn执行javascript代码</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/7/7 9:12</p>
 */
public class ScriptEngineTest {
    public static void main(String[] args) {
        //获取脚本引擎对象
        ScriptEngineManager sem = new ScriptEngineManager();
        //这里参数可为"javascript"，也可为"nashorn"
        ScriptEngine engine = sem.getEngineByName("javascript");
        System.out.println(engine.getClass());
        System.out.println("--------分割线--------");

        //定义变量，存储到引擎的上下文中
        engine.put("msg", "Alei is a good man!");
        String str = "var user = {name:'Alei', age:18, schools:['巴黎高师', '复旦大学']};";
        str += "print(user.name);";

        //执行脚本
        try {
            engine.eval(str);
            engine.eval("msg = 'Ali is a pretty woman!'");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println(engine.get("msg"));
        System.out.println("--------分割线--------");


        try {
            //定义函数
            engine.eval("function add(a, b) {var sum = a + b; return sum;}");
            //获取调用接口
            Invocable jsInvoke = (Invocable) engine;
//          Object result = jsInvoke.invokeFunction("add", 30, 50);
            //执行函数
            Object result = jsInvoke.invokeFunction("add", new Object[] {30, 50});
            System.out.println(result);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("--------分割线--------");

        //导入其他java包，使用其他包中的java类
        String jsCode = "var list = java.util.Arrays.asList([\"巴黎高师\", \"复旦大学\", \"以色列理工学院\"])";
        try {
            engine.eval(jsCode);
            List<String> list = (List<String>) engine.get("list");
            for (String s : list) {
                System.out.println(s);
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println("--------分割线--------");

        //执行一个js文件（放在src下即可）
        URL url = ScriptEngineTest.class.getClassLoader().getResource("a.js");
        FileReader fr = null;
        try {
            fr = new FileReader(url.getPath());
            engine.eval(fr);
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

