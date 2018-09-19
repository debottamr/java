package strategy;

//BaseFileParser.java
 abstract class BaseFileParser {
	public abstract void parseFile();
}

//XMLFileParser.java
class XMLFileParser extends BaseFileParser {
	public void parseFile() {
		// Logic for parsing an XML file goes here
	}
}

//CSVFileParser.java
class CSVFileParser extends BaseFileParser {
	public void parseFile() {
		// Logic for parsing a CSV file goes here
	}
}

//Client.java
public class Client {
	private BaseFileParser baseFileParser;

	public Client(BaseFileParser baseFileParser) {
		this.baseFileParser = baseFileParser;
	}

	public void parseFile() {
		baseFileParser.parseFile();
	}

	public static void main(String args[]) {
		// Lets say the client needs to parse an XML file
		// The file type(XML/CSV) can also be taken as
		// input from command line args[]
		Client client = new Client(new XMLFileParser());
		client.parseFile();
	}
}