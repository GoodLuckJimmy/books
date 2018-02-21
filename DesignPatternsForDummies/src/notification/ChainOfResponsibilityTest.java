package notification;

interface HelpInterface {
	public void getHelp(int helpConstant);
}

class FrontEnd implements HelpInterface {
	final int FRONT_END_HELP = 1;
	HelpInterface successor;
	
	public FrontEnd(HelpInterface s) {
		successor = s;
	}

	@Override
	public void getHelp(int helpConstant) {
		if (helpConstant != FRONT_END_HELP) {
			successor.getHelp(helpConstant);
		} else {
			System.out.println("This is the front end. Don't you like it?");
		}
	}
}


class IntermediateLayer implements HelpInterface {
	final int INTERMEDIATE_LAYER_HELP = 2;
	HelpInterface successor;
	
	public IntermediateLayer(HelpInterface s) {
		successor = s;
	}

	@Override
	public void getHelp(int helpConstant) {
		if (helpConstant != INTERMEDIATE_LAYER_HELP) {
			successor.getHelp(helpConstant);
		} else {
			System.out.println("This is the intermediate layer. Nice, eh?");
		}
	}
}


class Application implements HelpInterface {
	public Application() {}
	
	@Override
	public void getHelp(int helpConstant) {
		System.out.println("This is the MegaGigaCo application.");
	}
	
}
class ChainOfResponsibilityTest {

	public static void main(String[] args) {

		final int FRONT_END_HELP = 1;
		final int INTERMEDIATE_LAYER_HELP = 2;
		final int GENERAL_HELP = 3;
		
		Application app = new Application();
		IntermediateLayer intermediateLayer = new IntermediateLayer(app);
		FrontEnd frontEnd = new FrontEnd(intermediateLayer);
		frontEnd.getHelp(GENERAL_HELP);
	}

}

/*
Since the front end can’t handle help requests of this kind, it passes the
request on to the intermediate layer. Since that layer can’t handle the help
request either, the request is passed on to the application object.
*/
