import java.util.Random;



public class NumberGenerator
	{
		public static int[] NumGenerator(int from, int to, int count)
		{
			int[] num = new int[count];
			Random random = new Random();
			int flag=0;
			
			for(int i=0 ; i<count ; i++) 
				{
					num[i] = random.nextInt(to-from+1)+1;
					flag=0;
					while(flag==0)
						{
							for(int j=0 ; j<count ; j++)
								{
									if(num[i]==num[j])
										{
											num[i] = random.nextInt(to-from+1)+1;
										}
									else
										{
											flag=1;
											break;
										}	
								}
						}					
				}		
			return num;
		}
	}
