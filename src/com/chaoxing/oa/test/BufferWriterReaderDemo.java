package com.chaoxing.oa.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferWriterReaderDemo   
{  
    public static void main(String[] args)   
    {  
        try  
        {  
            //缓冲System.in输入流  
            //System.in是位流，可以通过InputStreamReader将其转换为字符流  
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));  
            //缓冲FileWriter  
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("d:/test.txt"));  
  
            String input = null;  
  
            //每读一行进行一次写入动作  
            while(!(input = bufReader.readLine()).equals("quit"))  
            {  
                bufWriter.write(input);  
                //newLine()方法写入与操作系统相依的换行字符，依执行环境当时的OS来决定该输出那种换行字符  
                bufWriter.newLine();  
            }  
            bufReader.close();  
            bufWriter.close();  
        }  
        catch(ArrayIndexOutOfBoundsException e)  
        {  
            System.out.println("没有指定文件");  
        }  
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}