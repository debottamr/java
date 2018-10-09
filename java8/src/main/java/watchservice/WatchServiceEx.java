package watchservice;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class WatchServiceEx {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Path path = Paths.get(".");
		WatchService watchService =  path.getFileSystem().newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		WatchKey watchKey = null;
		while (true) {
		    watchKey = watchService.poll(10, TimeUnit.MINUTES);
		    if(watchKey != null) {
		        watchKey.pollEvents().stream().forEach(event -> System.out.println(event.context()));
		    }
		    watchKey.reset();
		}
		
	}
	
	//Below method will register a single directory to watcher and then store the directory and key inside a map.
	private void registerDirectory(Path dir) throws IOException
	{
		Map<WatchKey, Path> keys = new HashMap<>();
		Path path = Paths.get(".");
		WatchService watcher =  path.getFileSystem().newWatchService();
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	    keys.put(key, dir);
	}
//We will call this method recursively while walking down a directory structure and calling this for each directory we encounter.

	private void walkAndRegisterDirectories(final Path start) throws IOException {
	    // register directory and sub-directories
	    Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
	        @Override
	        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	            registerDirectory(dir);
	            return FileVisitResult.CONTINUE;
	        }
	    });
	}

}
