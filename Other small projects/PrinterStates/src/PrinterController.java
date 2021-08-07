import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simulates controlling of a printer
 * 
 * @author (FEN)
 * @version (2015-03-17)
 * 
 *          2018 quick fix for avoiding Console, and small changes enum
 */

enum Events {
	PRINT("print"), GOREADY("goReady"), DONE("done"), CANCEL("cancel"), READY_TO_PRINT("readyToPrint"),
	PAPER_JAM("paperJam"), UNKNOWN("unknown");

	private final String label;

	Events(String label) {
		this.label = label;
	}

	String label() {
		return label;
	}
}

class PrintController {

	private Start start;
	private Printing printing;
	private Ready ready;
	private End end;
	private PaperJam jam;

	public PrintController() {
		start = new Start();
		printing = new Printing();
		ready = new Ready();
		end = new End();
		jam = new PaperJam();

		start.addTransition(Events.READY_TO_PRINT, printing);
		start.addTransition(Events.CANCEL, end);

		printing.addTransition(Events.CANCEL, end);
		printing.addTransition(Events.DONE, end);
		printing.addTransition(Events.PAPER_JAM, jam);

		ready.addTransition(Events.PRINT, start);

		end.addTransition(Events.GOREADY, ready);
	}

	public void printer() throws IOException {
		boolean done = false;
		State currState = ready;
		while (!done) {
			currState.action();
			Events e = getNextEvent(currState);
			currState = currState.transition(e);

			System.out.println();
			System.out.print("Switch printer off (y/n)? ");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String answer = bufferedReader.readLine();

			System.out.println();
			if (answer.equals("y"))
				done = true;
		}
		System.out.print("System has stopped.");
	}

	private Events getNextEvent(State s) throws IOException {
		boolean error = false;
		Events e;
		do {
			error = false;
			e = Events.UNKNOWN;
			System.out.println("Possible events: " + s.eventString());
			System.out.print("Next event: ");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String inputStr = bufferedReader.readLine();

			if (inputStr.equals(Events.PRINT.label()))
				e = Events.PRINT;
			else if (inputStr.equals(Events.GOREADY.label()))
				e = Events.GOREADY;
			else if (inputStr.equals(Events.DONE.label()))
				e = Events.DONE;
			else if (inputStr.equals(Events.CANCEL.label()))
				e = Events.CANCEL;
			else if (inputStr.equals(Events.READY_TO_PRINT.label()))
				e = Events.READY_TO_PRINT;
			else if (inputStr.equals(Events.PAPER_JAM.label()))
				e = Events.PAPER_JAM;
			else
				error = true;
		} while (error);

		return e;
	}
}