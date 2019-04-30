package algo;

import graph.Graph;
import userInterface.GUIApp;

import javax.xml.bind.SchemaOutputResolver;

/** The Driver class for the MST project.
 *  Do not modify. */
public class MSTDriver {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("No arguments");
			return;
		}
		try {
			Graph graph = new Graph(args[0]); //load graph from the file given in args[0]
			GUIApp app = new GUIApp(graph);
			// this will run the GUI, and then the user will be able to interact with GUI
		}
		catch (Exception e){
			System.out.println(e);
		}


	}
}