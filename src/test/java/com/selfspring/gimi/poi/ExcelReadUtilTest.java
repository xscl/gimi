package com.selfspring.gimi.poi;

import com.google.gson.GsonBuilder;
import com.selfspring.gimi.ResourceUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by ckyang on 2019/12/27.
 */
public class ExcelReadUtilTest {

    @Test
    public void testExe() throws Exception {

        String file = ResourceUtil.getTestResourcePath("excel-to-json.xlsx");

        List list = ExcelReadUtil.exe(new File(file));
        for (Object o : list) {
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(o));
            System.out.println();
        }
    }

}