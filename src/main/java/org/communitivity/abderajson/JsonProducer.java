
package org.communitivity.abderajson;

import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.abdera.Abdera;
import org.apache.abdera.parser.Parser;
import org.apache.abdera.writer.Writer;

import java.net.URL;

public class JsonProducer {

    public static void main(String[] args) {
	String feedSource;
	if (args.length != 1) {
	    System.err.println("Must have one arg, a URL to an Atom XML feed that will be converted to a JSON representation");
	    System.err.println("For now using snellspace feed as default: http://www.snellspace.com/wp/wp-atom1.php");
	    feedSource = "http://www.snellspace.com/wp/wp-atom1.php";
	}
	else {
	    feedSource = args[0];
	}

	try {
	    Abdera abdera = new Abdera();
	    Parser parser = abdera.getParser();
  
	    URL url = new URL(feedSource);
	    Document<Feed> doc = parser.parse(url.openStream(),feedSource);
	    Feed feed = doc.getRoot();
	    if (feed == null) throw new IllegalStateException("Feed not parsed, was null");
	    Writer writer = abdera.getWriterFactory().getWriter("json");
	    if (writer == null) throw new IllegalStateException("Json Writer not found, add abdera.extensions.json jar");
	    writer.writeTo(feed, System.out);
	} catch (Exception ex) {
	    throw new RuntimeException(ex);
	}
    }
}
