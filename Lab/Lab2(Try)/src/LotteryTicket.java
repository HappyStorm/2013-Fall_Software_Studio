
public class LotteryTicket
	{
		private int[] pickedNumbers;
		public LotteryTicket(int[] num)
		{
			pickedNumbers = num;
		}
		
		public void Raffle(int[] num)
		{
			int bingo=0;
			for(int i=0 ; i<pickedNumbers.length ; i++)
				{
					for(int j=0 ; j<num.length ; j++)
						{
							if(pickedNumbers[i]==num[j])
								{
									System.out.printf("%d ",pickedNumbers[i]);
									bingo++;
									break;
								}
						}
				}
			System.out.printf("\nYou have %d bingo numbers.",bingo);
		}
	}
