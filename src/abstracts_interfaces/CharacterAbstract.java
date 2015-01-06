package abstracts_interfaces;

import abstracts_interfaces.behaviours.BehaviourCombatAbstract;
import abstracts_interfaces.behaviours.BehaviourMoveAbstract;
import abstracts_interfaces.behaviours.BehaviourTalkAbstract;
import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.weapons.WeaponAbstract;


public abstract class CharacterAbstract extends ObserverAbstract{
	

	private String name;
	protected BehaviourCombatAbstract behaviour;
	protected BehaviourTalkAbstract speech;
	protected BehaviourMoveAbstract move;
	protected Organisation subject;
	protected String operatingState;
	protected WeaponAbstract weapon;
	protected StatisticsAbstract stats;
	
	public WeaponAbstract getWeapon() {
		return weapon;
	}

	public void setWeapon(WeaponAbstract weapon) {
		this.weapon = weapon;
	}

	public void setOperatingState(String operatingState) {
		this.operatingState = operatingState;
	}

	public abstract String display();
	
	public String getOperatingState() {
		return operatingState;
	}

	public abstract String move();

	
	public CharacterAbstract(Organisation subject, String name){
		this.name=name;
		this.behaviour=null;
		this.speech=null;
		this.weapon= null;
		this.subject= subject;
		this.stats = null;
		if (this.subject != null) {
			this.subject.attach(this);
		}
	}
	
	public StatisticsAbstract getStats() {
		return stats;
	}

	public void setStats(StatisticsAbstract stats) {
		this.stats = stats;
	}

	public void update(){
		this.operatingState = subject.getOperatingMode();
	}

	public String fight(){
		if (behaviour != null) {
			return behaviour.fight();
		}
		return "I do not fight.";
	}
	
	public String checkWeapons(){
		if (weapon != null) {
			return  "(" + weapon.name
					+ ": Damage "+ weapon.damage
					+ ", Accuracy " + weapon.accuracy
					+ ", Range " + weapon.range
					+")";
		}
		return "I do not have a weapon.";
	}
	
	public String speak(){
		if (speech != null) {
			return speech.speak();
		}
		return "";
	}
	
	public Organisation getSubject() {
		return subject;
	}

	public void setSubject(Organisation subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BehaviourCombatAbstract getBehaviour() {
		return behaviour;
	}
	
	public void setBehaviour(BehaviourCombatAbstract behaviour) {
		this.behaviour = behaviour;
	}
	
	public BehaviourTalkAbstract getSpeech() {
		return speech;
	}
	
	public void setSpeech(BehaviourTalkAbstract speech) {
		this.speech = speech;
	}
	
	public BehaviourMoveAbstract getMove() {
		return move;
	}
	
	public void setMoveBehaviour(BehaviourMoveAbstract move) {
		this.move = move;
	}
	
}
