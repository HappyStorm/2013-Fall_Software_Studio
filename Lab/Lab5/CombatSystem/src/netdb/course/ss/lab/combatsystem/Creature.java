package netdb.course.ss.lab.combatsystem;

public abstract class Creature 
{
	protected int hp;
	protected int strength;
	protected int defence;
	protected String name;
	protected boolean isDead;
	
	public int getHp()
	{
		return hp;
	}
	
	public String getName()
	{
		return name;
	}
	
	abstract public boolean die();
	abstract public void attack(Creature cre);
	abstract public void takeDamage(int strength);
}
