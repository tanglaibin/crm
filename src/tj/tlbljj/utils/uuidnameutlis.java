package tj.tlbljj.utils;

import java.util.UUID;

public class uuidnameutlis{
//将文件名重命名
	
	public static String getname(String name){
		int index=name.lastIndexOf(".");
		String lastname=name.substring(index, name.length());
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid+lastname;
	}
	
}
