package adapter;

import java.io.File;

//FileCommiter.java
class FileCommiter {
	String diskLocation;

	public FileCommiter() {
		// default constructor
	}

	public FileCommiter(String diskLocation) {
		this.diskLocation = diskLocation;
	}

	public void saveFile(File file) {
		// Logic for saving the file at the diskLocation goes here
	}
}

//FileEmailer.java
class FileEmailer {
	public String emailAddress;

	public FileEmailer(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void emailFile(File file) {
		// Logic for emailing the file goes here
	}
}

//FileEmailerAdapter.java
class FileEmailerAdapter extends FileCommiter {
	public FileEmailer fileEmailer;

	public FileEmailerAdapter(FileEmailer fileEmailer) {
		this.fileEmailer = fileEmailer;
	}

	public void saveFile(File file) {
		this.fileEmailer.emailFile(file);
	}
}

//Client.java
public class Client {
	public static void main(String args[]) {
		File file = new File(args[0]);
		FileCommiter fileCommiter = new FileCommiter("C:\\");
		fileCommiter.saveFile(file);// This will save the file on the disk location C: drive
		// Now we want to email with the same Target class FileCommiter
		fileCommiter = new FileEmailerAdapter(new FileEmailer("abcd@javabrahman.com"));
		fileCommiter.saveFile(file);// This will email the file to abcd@javabrahman.com
	}
}