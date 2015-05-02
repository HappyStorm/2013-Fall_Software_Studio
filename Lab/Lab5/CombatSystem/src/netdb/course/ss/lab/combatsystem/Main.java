package netdb.course.ss.lab.combatsystem;
import java.util.ArrayList;
import java.util.Random;


public class Main {

	public static void main(String[] args) throws InterruptedException
	{
		// initialize group A and B
		ArrayList<Creature> heroes = new ArrayList<Creature>();
		ArrayList<Creature> monsters = new ArrayList<Creature>();
		
		// TODO add creatures into each group
		Warrior warrior1 = new Warrior();
		Mage mage1 = new Mage();
		Wolf wolf1 = new Wolf();
		Goblin goblin1 = new Goblin();
		
		heroes.add(warrior1);
		heroes.add(mage1);
		
		monsters.add(wolf1);
		monsters.add(goblin1);
		
		while(true)
		{
			
			showHp(heroes);
			showHp(monsters);
			
			// TODO heroes and monsters group attack each other
			
			Random random = new Random();
			int heIndex = random.nextInt(2);
			int monIndex = random.nextInt(2);
			int heflag=0;
			int monflag=0;
			
			if(!monsters.get(monIndex).die() && !heroes.get(heIndex).die())
				heroes.get(heIndex).attack(monsters.get(monIndex));
			
			if(!heroes.get(heIndex).die() && !monsters.get(monIndex).die())
				monsters.get(monIndex).attack(heroes.get(heIndex));
			
			for(int i=0 ; i<heroes.size() ; i++)
			{
				if(!heroes.get(i).die())
				{
					heflag=1;
					break;
				}
			}
			
			for(int i=0 ; i<monsters.size() ; i++)
			{
				if(!monsters.get(i).die())
				{
					monflag=1;
					break;
				}
			}
			
			if(heflag==0 || monflag==0)
			{
				if(heflag==1)
					System.out.printf("英雄贏了ㄏㄏ\n");
				if(monflag==1)
					System.out.printf("怪物贏了XD\n");
				
				break;
			}
				
			// use this line of code to wait for 1.5 second
			// you can add this code after each creature attack
			Thread.sleep(1500);
		}
		
		// TODO print the combat result

	}
	
	private static void showHp(ArrayList<Creature> c)
	{
		System.out.println("====================");
		for(Creature a : c)
		{
			System.out.println(a.getName() + " HP: " + a.getHp());
		}
		System.out.println("====================");
	}

}
