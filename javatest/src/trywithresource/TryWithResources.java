package trywithresource;

import java.io.File;
import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
public class TryWithResources {
	public static void main(String[] args) throws Exception {
		File file = new File("D:/cp/jar/testjar.jar");
		JarEntry je;
	    try(
    		FileInputStream fis = new FileInputStream(file);
    	    JarInputStream jis = new JarInputStream(fis);
	    ){
	    	while((je=jis.getNextJarEntry())!=null){
	        	System.out.println(je.getName());
	    	}
		}
	}
}