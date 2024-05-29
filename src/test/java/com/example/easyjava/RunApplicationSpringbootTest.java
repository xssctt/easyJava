package com.example.easyjava;

import com.example.easyjava.bean.Constants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = RunApplication.class)
public class RunApplicationSpringbootTest {

    @Test
    void demo(){
        HashMap<String,Integer> hashMap=new HashMap<>();
        hashMap.put("a",11);
        hashMap.put("b",22);
        hashMap.put("c",33);


        if (hashMap.containsKey("b")){
            hashMap.put("b",44);
        }

        for (Map.Entry<String,Integer> entry: hashMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }

    @Test
    void dema(){
        System.out.println(Constants.PACKAGE_SERVICE);
        System.out.println(Constants.PACKAGE_SERVICE_IMPL);
        System.out.println(Constants.PATH_SERVICE);
        System.out.println(Constants.PATH_SERVICE_IMPL);

    }

}
