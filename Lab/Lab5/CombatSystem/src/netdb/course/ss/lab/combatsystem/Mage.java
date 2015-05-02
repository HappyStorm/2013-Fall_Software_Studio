package netdb.course.ss.lab.combatsystem;

public class Mage extends Creature
{
	public Mage()
	{
		hp=100;
		strength=30;
		defence=5;
		name="Mage";
	}
	
	@Override
	public void attack(Creature cre) 
	{
		// TODO Auto-generated method stub
		int realAtt = this.strength-cre.defence;
		
		System.out.printf("%s硬生生扣了%s  %d 滴血 :P\n", this.name,cre.name,realAtt);
		cre.takeDamage(this.strength);
	}

	@Override
	public void takeDamage(int strength) 
	{
		// TODO Auto-generated method stub
		int realAtt = strength - this.defence;
		this.hp -= realAtt;
		
		System.out.printf("%s 硬生生被扣了  %d 滴血 :P\n\n", this.name, realAtt);
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
