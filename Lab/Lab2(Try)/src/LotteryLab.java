import java.io.BufferedReader;
import java.io.InputStreamReader;


public class LotteryLab {
	
	private static int[] winningNumbers = {0, 0, 0, 0, 0, 0};
    
	public static void main(String[] args) throws Exception {
		
		/**
		 * TODO
		 * Parse the arguments 'from', 'to', 'count'.
		 *
		 * - Your code here -
		 *
		 */
		int from  = Integer.parseInt(args[0]);
		int to    = Integer.parseInt(args[1]);
		int count = Integer.parseInt(args[2]);
		
		
		/**
		 * TODO
		 * Use number generator's static method to generate the winning numbers
		 * and store it in the winningNumbers variable.
		 *
		 * - Your code here -
		 *
		 */
		NumberGenerator NG = new NumberGenerator();
		winningNumbers = NG.NumGenerator(from, to, count);
		
		
		// print the winning numbers
		printNumbers("Winning Numbers: ", winningNumbers);
		
		// create a scanner for input
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// print the user interaction message
		System.out.println("Please enter your ticket numbers: (ex:'1 2 3 4 5 6')");
		String inputString = "";
		
		// read a line and create a new ticket from input string
		while(!(inputString = reader.readLine()).equals("quit")){
			printNumbers("Your ticket numbers: ", inputString);
            
			/**
			 * TODO
			 * The input numbers string is stored in the inputString.
			 * (You need to split the string first, and then convert the string number into integer)
			 *
			 * Create a lottery ticket with the number you picks and raffle it.
			 *
			 * - Your code here -
			 *
			 */
			String[] mynumberstring = inputString.split(" ");
			int[] mynumbers = new int[count];
			for(int i=0 ; i<count ; i++)
				{
					mynumbers[i] = Integer.parseInt(mynumberstring[i]);
				}
			LotteryTicket myLottery = new LotteryTicket(mynumbers);
			myLottery.Raffle(winningNumbers);
			
			
			// prepare for the next loop
			Thread.sleep(1000);
			System.out.println("\nPlease enter your ticket numbers: (ex:'1 2 3 4 5 6')");
		}
		
		System.out.println("\nGoodbye~");
		
	}
	
	private static void printNumbers(String info, int[] numbers){
		System.out.print(info + " ");
		for(Integer i : numbers)
			System.out.print(i + " ");
		System.out.println();
	}
	
	private static void printNumbers(String info, String numbers){
		System.out.println(info + " " + numbers);
	}
	
}