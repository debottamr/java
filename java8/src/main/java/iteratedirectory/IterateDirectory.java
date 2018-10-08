package iteratedirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

public class IterateDirectory {

	public static void main(String[] args) throws IOException {
		// 1. List all files and sub-directories using Files.list()
		Files.list(Paths.get(".")).forEach(System.out::println);

		// 2. List only files inside directory using filter expression
		Files.list(Paths.get(".")).filter(Files::isRegularFile).forEach(System.out::println);

		// 3. List files and sub-directories with Files.newDirectoryStream()
		Files.newDirectoryStream(Paths.get(".")).forEach(System.out::println);

		// 4. List only files with Files.newDirectoryStream()
		Files.newDirectoryStream(Paths.get("."), path -> path.toFile().isFile()).forEach(System.out::println);

		//5. List files of certain extention with Files.newDirectoryStream()
		Files.newDirectoryStream(Paths.get("."), path -> path.toString().endsWith(".java"))
				.forEach(System.out::println);;
		
		//6. Find all hidden files in directory
		File[] listFiles = new File(".").listFiles(file -> file.isHidden() );		
		File[] listFiles1 = new File(".").listFiles(File::isHidden);		

				
	}
}
