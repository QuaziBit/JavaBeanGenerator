package quazibit.main;

import java.awt.EventQueue;

import quazibit.gui.*;
import quazibit.javabeangenerator.*;

public class Main 
{
	public static void main(String[] args) 
	{
		// console rsc/csv/data.csv
		
		if (args.length > 1 && args.length <= 2)
		{
			if (args[0].equals("console"))
			{
				StartConsole startConsole = new StartConsole();
				
				// the second argument must be the file path
				args[0] = args[1];
				
				startConsole.start(args);
			}
			else
			{
				System.out.println("You have to provide 'console' and 'file path' as arguments to run this application in a console mode!");
			}
		}
		else
		{
			EventQueue.invokeLater(new Runnable() 
			{
				public void run() 
				{
					try 
					{
						StartGUI frame = new StartGUI();
						frame.setVisible(true);
						frame.setResizable(false);
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			});	
		}
	}
}
