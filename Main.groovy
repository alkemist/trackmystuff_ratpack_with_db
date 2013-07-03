import org.ratpackframework.groovy.bootstrap.RatpackScriptApp;
import org.ratpackframework.bootstrap.RatpackServer;

// For session handling
//import org.ratpackframework.session.Session
//import org.ratpackframework.session.store.MapSessionsModule
//import org.ratpackframework.session.store.SessionStorage

import java.io.File;

class Main {
    public static void main(String[] args) throws Exception {
        File ratpackFile = args.length == 0 ? new File("ratpack.groovy") : new File(args[0]);
        if (!ratpackFile.exists()) {
            System.err.println("Ratpack file " + ratpackFile.getAbsolutePath() + " does not exist");
            System.exit(1);
        }


		// RatpackServer rServer = RatpackScriptApp.ratpack(ratpackFile)
		// rServer.start();
        RatpackScriptApp.ratpack(ratpackFile).start();
    }
}