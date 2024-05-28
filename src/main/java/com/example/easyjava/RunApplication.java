package com.example.easyjava;

import com.example.easyjava.bean.TableInfo;
import com.example.easyjava.builder.*;

import java.util.List;

public class RunApplication {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList = BuildTable.getTables();

        BuilBase.execute();
        for (TableInfo t: tableInfoList) {
            BuildePo.execute(t);
//                        BuildMapperXml.execute(t);
            BuildeQuery.execute(t);
            BuildMapper.execute(t);

            BuildMapperXml.execute(t);
        }

    }
}
