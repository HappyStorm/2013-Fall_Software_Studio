package netdb.course.ss.lab.combatsystem;

public class Warrior extends Creature
{
	public Warrior()
	{
		hp=150;
		strength=50;
		defence=10;
		name="Warrior";
	}
	
	@Override
	public void attack(Creature cre) 
	{
		// TODO Auto-generated method stub
		int realAtt = this.strength-cre.defence;
		
		System.out.printf("%s打爆%s  %d 滴血 :P\n", this.name, cre.name, realAtt);
		cre.takeDamage(this.strength);
	}

	@Override
	public void takeDamage(int strength) 
	{
		// TODO Auto-generated method stub
		int realAtt = strength - this.defence;
		this.hp -= realAtt;
		
		System.out.printf("%s被打爆了  %d 滴血 :P\n\n", this.name, realAtt);
	}

	@Override
	public boolean die() 
	{
		// TODO Auto-generated method stub
		if(this.hp<=0)
		{
			System.out.printf("%s 我已經掛了\n", this.name);
			return true;
		}
		else
			return false;
		
	}

}
