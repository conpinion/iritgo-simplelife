/**
 * This file is part of the Iritgo/SimpleLife Library.
 *
 * Copyright (C) 2005-2011 Iritgo Technologies.
 * Copyright (C) 2003-2005 BueroByte GbR.
 *
 * Iritgo licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.iritgo.simplelife.process;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Use a ProcessCommunicator to start an external systen command and communicate
 * with it through it's input and output streams.
 *
 * The proecess is started asynchronously and the input streams are
 * non-blocking.
 */
public class ProcessCommunicator
{
	/** The command file to execute */
	private File commandFile;

	/** Command parameters */
	private String commandParams;

	/** The executing process */
	private Process process;

	/** Connection to the process' input stream */
	private PrintWriter processIn;

	/** Connection to the process' output stream */
	private NonblockingStream processOut;

	/** Connection to the process input stream */
	private NonblockingStream processError;

	/** Schedule a timeout action that terminates the process (timer) */
	private Timer timeoutTimer;

	/** Schedule a timeout action that terminates the process (task) */
	private TimerTask timeoutTask;

	/**
	 * Schedule a timeout action that terminates the process (timeout in
	 * seconds)
	 */
	private int timeout;

	/**
	 * Create a new ProcessCommunicator.
	 *
	 * @param commandPath The system command to execute
	 * @param commandParams Optionally command parameters
	 * @throws IOException In case of an io exception
	 */
	public ProcessCommunicator(String commandPath, String commandParams)
	{
		commandFile = new File(commandPath);

		if (! commandFile.isFile())
		{
			throw new IllegalArgumentException("Command " + commandPath + " not found");
		}

		this.commandParams = commandParams;
	}

	/**
	 * Create a new ProcessCommunicator.
	 *
	 * @param commandPath The system command to execute
	 * @throws IOException In case of an io exception
	 */
	public ProcessCommunicator(String commandPath)
	{
		this(commandPath, null);
	}

	/**
	 * Start the process and the communication stream threads.
	 *
	 * @throws IOException In case of a failure
	 */
	public void start() throws IOException
	{
		process = Runtime.getRuntime().exec(
						commandFile.getAbsolutePath() + (commandParams != null ? " " + commandParams : ""));
		processIn = new PrintWriter(process.getOutputStream());
		processOut = new NonblockingStream(process.getInputStream());
		processError = new NonblockingStream(process.getErrorStream());
		processOut.start();
		processError.start();
		timeoutTimer = new Timer();
	}

	/**
	 * Terminate the process and the communication stream threads.
	 */
	public void terminate()
	{
		if (timeoutTask != null)
		{
			timeoutTask.cancel();
			timeoutTask = null;
		}

		processOut.terminate();
		processError.terminate();
		process.destroy();
		process = null;
	}

	/**
	 * Retrieve the last results of the process' output stream.
	 *
	 * @return The process output stream data
	 */
	public String getProcessOutput()
	{
		return processOut.getDataAsString();
	}

	/**
	 * Retrieve the last results of the process' error stream.
	 *
	 * @return The process error stream data
	 */
	public String getProcessError()
	{
		return processError.getDataAsString();
	}

	/**
	 * Send data to the process' input stream.
	 *
	 * @param data The data to send
	 */
	public void send(String data)
	{
		processIn.print(data + "\n");
		processIn.flush();
		setTimeout(timeout);
	}

	/**
	 * Check if the process is currently running.
	 *
	 * @return True if the process is running
	 */
	public boolean isRunning()
	{
		return process != null;
	}

	/**
	 * Set an inactivity timeout. Inactivity means, that no commands are send to
	 * the process during the specified time. If a timeout occurred, the process
	 * is terminated.
	 *
	 * @param seconds The timeout duration in seconds
	 */
	public void setTimeout(int seconds)
	{
		this.timeout = seconds;

		if (timeoutTask != null)
		{
			timeoutTask.cancel();
		}
		else
		{
			timeoutTask = new TimerTask()
			{
				@Override
				public void run()
				{
					System.out.println("************************");
				}
			};
		}

		timeoutTimer.schedule(timeoutTask, seconds * 1000);
	}
}
