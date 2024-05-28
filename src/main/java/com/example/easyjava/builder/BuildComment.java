package com.example.easyjava.builder;

import com.example.easyjava.Util.DateUtil;
import com.example.easyjava.Util.TimeUtil;
import com.example.easyjava.bean.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

public class BuildComment {
    /**
     *
     * @Desoription comment
     * @Date yy mm dd
     */
    public static void createClassComment(BufferedWriter bufferedWriter,String comment) throws IOException {

        bufferedWriter.write("\t"+"/** ");
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+" *");
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+" * "+"@Desoription "+comment);
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+" * "+"@Auther "+ Constants.AUTHER_COMMENT);
        bufferedWriter.newLine();
       // bufferedWriter.write("\t"+" * "+"@Date "+ TimeUtil.NowTimeToString());
        bufferedWriter.write("\t"+" * "+"@Date "+ DateUtil.format(new Date(),DateUtil.YYYY_MM_DD));
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+" */");
        bufferedWriter.newLine();
    }
    public static void createFieldComment(BufferedWriter bufferedWriter,String fieldComment) throws IOException {
        bufferedWriter.write("\t"+"/** ");
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+" *");
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+" * "+" "+ (fieldComment==null ? "" : fieldComment) );
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+" */");
        bufferedWriter.newLine();
    }
    public static void createMethodComment(){

    }
}
