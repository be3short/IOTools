package bs.commons.io.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import bs.commons.io.system.RoutedOutput;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class ThreaddedGUIOutput extends BorderPane
{

	private PrintStream ps;
	private Console console;

	public ThreaddedGUIOutput(boolean system_out)
	{
		setupTerminal(system_out);
	}

	private void setupTerminal(boolean system_out)
	{
		console = new Console();
		setCenter(console.output);
		if (system_out)
		{
			ps = new PrintStream(console, true);
			System.setOut(ps);
			System.setErr(ps);
		}
	}

	public PrintStream getPrintStream()
	{
		return ps;
	}

	public static class Console extends RoutedOutput
	{

		private TextArea output;

		public Console()
		{
			super(true);
			output = new TextArea();
		}

		@Override
		public void appendText(String valueOf) throws IOException
		{
			Platform.runLater(() -> output.appendText(valueOf));
		}

	}
}