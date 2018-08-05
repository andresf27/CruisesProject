package runAll;

import java.io.IOException;

import org.junit.runner.JUnitCore;

public class ExportJar {

	public static void main(String[] args) throws IOException {
		
		JUnitCore.main("web.scenarios.SmokeTestUserStory01And02");
		
	}
	
}
