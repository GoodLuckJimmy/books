package command;

interface Receiver {
	public void connect();
	public void diagnostics();
	public void reboot();
	public void shutdown();
	public void disconnect();
}


class AsiaServer implements Receiver {
	public AsiaServer() {
	}

	@Override
	public void connect() {
		System.out.println("You're connected to the Asia server.");
	}

	@Override
	public void diagnostics() {
		System.out.println("The Asia server diagnostics check out OK.");
	}

	@Override
	public void shutdown() {
		System.out.println("Shutting down the Asia server.");
	}

	@Override
	public void disconnect() {
		System.out.println("You're disconnected from the Asia server.");
	}

	@Override
	public void reboot() {
		System.out.println("Rebooting the Asia server.");
	}
	
}


class EuroServer implements Receiver {
	public EuroServer() {
	}

	public void connect() {
		System.out.println("You're connected to the Euro server.");
	}

	public void diagnostics() {
		System.out.println("The Euro server diagnostics check out OK.");
	}

	public void shutdown() {
		System.out.println("Shutting down the Euro server.");
	}

	public void reboot() {
		System.out.println("Rebooting the Euro server.");
	}

	public void disconnect() {
		System.out.println("You're disconnected from the Euro server.");
	}
}


class USServer implements Receiver {
	public USServer() {
	}

	public void connect() {
		System.out.println("You're connected to the US server.");
	}

	public void diagnostics() {
		System.out.println("The US server diagnostics check out OK.");
	}

	public void shutdown() {
		System.out.println("Shutting down the US server.");
	}

	public void reboot() {
		System.out.println("Rebooting the US server.");
	}

	public void disconnect() {
		System.out.println("You're disconnected from the US server.");
	}
}


interface Command {
	public void execute();
	public void undo();
}


class ShutDownCommand implements Command {
	Receiver receiver;
	
	public ShutDownCommand(Receiver r) {
		receiver = r;
	}
	@Override
	public void execute() {
		receiver.connect();
		receiver.shutdown();
		receiver.disconnect();
		System.out.println();
	}
	@Override
	public void undo() {
		System.out.println("Undoing");
		receiver.connect();
		receiver.reboot();
		receiver.disconnect();
		System.out.println();
	}
	
}


class RunDiagnosticsCommand implements Command {
	Receiver receiver;

	public RunDiagnosticsCommand(Receiver r) {
		receiver = r;
	}

	public void execute() {
		receiver.connect();
		receiver.diagnostics();
		receiver.disconnect();
		System.out.println();
	}

	@Override
	public void undo() {
		System.out.println("Can't Undo");
		System.out.println();
	}
}


class RebootCommand implements Command {
	Receiver receiver;

	public RebootCommand(Receiver r) {
		receiver = r;
	}

	public void execute() {
		receiver.connect();
		receiver.reboot();
		receiver.disconnect();
		System.out.println();
	}

	@Override
	public void undo() {
		System.out.println("Undoing...");
		receiver.connect();
		receiver.shutdown();
		receiver.disconnect();
		System.out.println();	
	}
}


class Invoker {
	Command commands[] = new Command[5];
	int position;
	
	public Invoker() {
		position = -1;
	}
	
	public void setCommand(Command c) {
		if (position < commands.length - 1) {
			position++;
			commands[position] = c;
		} else {
			for (int i = 0; i < commands.length -2; i++) {
				commands[i] = commands[i + 1];
			}
			commands[commands.length - 1] = c;
		}
	}
	
	public void run() {
		commands[position].execute();
	}
	
	public void undo() {
		if (position >= 0) {
			commands[position].undo();
		}
		position--;
	}
}


public class CommandTest {

	public static void main(String[] args) {
		CommandTest t = new CommandTest();
	}
	
	public CommandTest() {
		Invoker invoker = new Invoker();
		
		// Create the receivers
		AsiaServer asiaServer = new AsiaServer();
		EuroServer euroServer = new EuroServer();
		USServer usServer = new USServer();
		
		// Creat the commands
		ShutDownCommand shutDownAsia = new ShutDownCommand(asiaServer);
		RunDiagnosticsCommand runDiagnosticsAsia = new RunDiagnosticsCommand(asiaServer);
		RebootCommand rebootAsia = new RebootCommand(asiaServer);
		ShutDownCommand shutDownEuro = new ShutDownCommand(euroServer);
		RunDiagnosticsCommand runDiagnosticsEuro = new RunDiagnosticsCommand(euroServer);
		RebootCommand rebootEuro = new RebootCommand(euroServer);
		ShutDownCommand shutDownUS = new ShutDownCommand(usServer);
		RunDiagnosticsCommand runDiagnosticsUS = new RunDiagnosticsCommand(usServer);
		RebootCommand rebootUS = new RebootCommand(usServer);
		
		/*
		invoker.setCommand(shutDownAsia);
		invoker.run();
		invoker.setCommand(rebootAsia);
		invoker.run();
		invoker.setCommand(runDiagnosticsAsia);
		invoker.run();
		invoker.setCommand(shutDownEuro);
		invoker.run();
		invoker.setCommand(runDiagnosticsEuro);
		invoker.run();
		*/
		
		invoker.setCommand(shutDownAsia);
		invoker.run();
		invoker.setCommand(rebootAsia);
		invoker.run();
		
		invoker.undo();
		invoker.undo();
	}

}
