package me.zhouzhuo810.apicreator.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by admin on 2017/5/19.
 */
public class FileUtil {
    public static void writeFile(String filePath, String fileName, String fileContent) {
        try {
            File f1 = new File(filePath);
            if (!f1.exists()) {
                f1.mkdirs();
            }
            File f = new File(filePath+File.separator+fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
            BufferedWriter writer=new BufferedWriter(write);
            //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePathAndName)));
            //PrintWriter writer = new PrintWriter(new FileWriter(filePathAndName));
            writer.write(fileContent);
            writer.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
        }
    }
}
