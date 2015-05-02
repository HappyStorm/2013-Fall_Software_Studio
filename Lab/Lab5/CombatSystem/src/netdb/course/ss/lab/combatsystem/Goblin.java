package netdb.course.ss.lab.combatsystem;

public class Goblin extends Creature{

	public Goblin()
	{
		hp = 60;
		strength = 20;
		defence = 0;
		name = "Goblin";
		
		
	}
	@Override
	public void attack(Creature cre) {
		// TODO Auto-generated method stub
		int realAtt = this.strength - cre.defence;
		
		System.out.printf("%s���z%s  %d �w�� :P\n", this.name, cre.name, realAtt);
		cre.takeDamage(this.strength);
	}

	@Override
	public void takeDamage(int strength) {
		// TODO Auto-generated method stub
		int realAtt = strength - this.defence;
		this.hp -= realAtt;
		
		System.out.printf("%s�Q���z�F  %d �w�� :P\n\n", this.name, realAtt);
	}
	
	@Override
	public boolean die() 
	{
		// TODO Auto-generated method stub
		if(this.hp<=0)
		{
			System.out.printf("%s �ڤw�g���F\n", this.name);
			return true;
		}
		else
			return false;
		
	}
}
