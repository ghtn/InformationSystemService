package com.ghtn.util;

import org.junit.Test;

import java.util.List;

/**
 * Created by lihe on 14-6-26.
 */
public class FileUtilTest {

    @Test
    public void testExcelReader() throws Exception {
        List<String[]> list = FileUtil.ExcelReaderForList("/Users/lihe/Documents/题库模板.xlsx", "2007", 1);
        System.out.println(list.size());
        System.out.println(list.get(0)[1]);
//        System.out.println(FileUtil.ExcelReader("/Users/lihe/Documents/test.xlsx"));
    }

    @Test
    public void testExportExcel() throws Exception {
        List<String[]> list = FileUtil.ExcelReaderForList("/Users/lihe/Documents/题库模板.xlsx", "2007", 1);
        FileUtil.exportExcel(list);
    }

}
