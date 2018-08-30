package com.bob.demo.myo2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月29日 下午10:16:22 
* 类说明 
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class MyTest {
	@Test
	@Ignore
	public void test() throws IOException {
		//写
		FileWriter fileWriter = new FileWriter(new File("e:/meigui3.py"));
		BufferedWriter out = new BufferedWriter(fileWriter);
		//读
		File file = new File("e:/meigui.py");
		FileReader fileReader = new FileReader(file);
		BufferedReader in = new BufferedReader(fileReader);
		String line = in.readLine();
		while(line!=null) {
//			if(!line.endsWith("\\)")) {
//				line.replace("\n", "");
//				out.write(line);
//				
//			}
			if(line.startsWith("turtle")) {
				line = "\n"+line;
				
			}
			out.write(line);
			line = in.readLine();
		}
		in.close();
		out.close();
		
	}

}
