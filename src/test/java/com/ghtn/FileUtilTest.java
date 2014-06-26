package com.ghtn;

import com.ghtn.util.FileUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by lihe on 14-6-26.
 */
public class FileUtilTest {

    @Test
    public void testExcelReader() throws Exception {
        List<String[]> list = FileUtil.ExcelReaderForList("/Users/lihe/Documents/test.xlsx", "2007", 1);
        System.out.println(list.size());
        System.out.println(list.get(0)[1]);
        System.out.println(FileUtil.ExcelReader("/Users/lihe/Documents/test.xlsx"));

        String s = "12$34";
        String s2 = "1234";
        System.out.println(s.contains("$"));
        System.out.println(s2.contains("$"));

        Integer.parseInt("s");
    }

}
