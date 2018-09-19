package memento;

class PlayerStatus {
	private long playerPoints;
	private int playerLevel;

	public void setPlayerPoints(long playerPoints) {
		this.playerPoints = playerPoints;
	}

	public long getPlayerPoints() {
		return this.playerPoints;
	}

	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

	public int getPlayerLevel() {
		return this.playerLevel;
	}
}

//PlayerStatusMemento.java
class PlayerStatusMemento {
	private PlayerStatus playerStatus;

	public void setPlayerStatus(PlayerStatus playerStatus) {
		this.playerStatus = playerStatus;
	}

	public PlayerStatus getPlayerStatus() {
		return this.playerStatus;
	}
}

//CurrentPlayerStatus.java
class CurrentPlayerStatus {
	private int playerLevel = 1;
	private long playerPoints = 0;

	public void setStatusFromMemento(PlayerStatusMemento memento) {
		this.playerLevel = memento.getPlayerStatus().getPlayerLevel();
		this.playerPoints = memento.getPlayerStatus().getPlayerPoints();
	}

	public PlayerStatusMemento createMementoFromStatus() {
		PlayerStatusMemento memento = new PlayerStatusMemento();
		PlayerStatus playerStatus = new PlayerStatus();
		playerStatus.setPlayerLevel(this.playerLevel);
		playerStatus.setPlayerPoints(this.playerPoints);
		memento.setPlayerStatus(playerStatus);
		return memento;
	}

	public void setPlayerPoints(long playerPoints) {
		this.playerPoints = playerPoints;
	}

	public long getPlayerPoints() {
		return this.playerPoints;
	}

	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

	public int getPlayerLevel() {
		return this.playerLevel;
	}
}

//PlayerStatusCaretaker.java  
class PlayerStatusCaretaker {
	public static void main(String args[]) {
		PlayerStatusMemento playerStatusMemento;
		System.out.println("Game Started");
		CurrentPlayerStatus currentPlayerStatus = new CurrentPlayerStatus();
		currentPlayerStatus.setPlayerPoints(1200L);
		currentPlayerStatus.setPlayerLevel(2);
		System.out.println("Player reached 2nd level with 1200 points");
		printCurrentPlayerStatus(currentPlayerStatus);
		System.out.println("Player stores current status as memento");
		playerStatusMemento = currentPlayerStatus.createMementoFromStatus();
		currentPlayerStatus.setPlayerPoints(2200L);
		currentPlayerStatus.setPlayerLevel(4);
		printCurrentPlayerStatus(currentPlayerStatus);
		System.out.println("At this point player loses & is relegated to status saved in memento");
		currentPlayerStatus.setStatusFromMemento(playerStatusMemento);
		printCurrentPlayerStatus(currentPlayerStatus);
	}

	public static void printCurrentPlayerStatus(CurrentPlayerStatus currentPlayerStatus) {
		System.out.println("Player points->" + currentPlayerStatus.getPlayerPoints() + ",level->" + currentPlayerStatus.getPlayerLevel());
	}
}

/*
 * 

Originator is the object of which the state is to be stored. The responsibilities of storing the snapshot of its state and then restoring the state from Memento lie with Originator. This is the reason why the methods createMementoFromState()(for storing the state) and setStateFromMemento()(for restoring the state) have been defined on the Originator.

Memento stores the internal state of the Originator. Only Originator is allowed storing to/restoring from the Memento object. This allows the internal structure/state of Originator to not be visible to other classes thus achieving the encapsulation requirement of a Memento implementation.

Caretaker holds the memento object and is responsible for its safekeeping. When a snapshot of the Originator’s state is required then the Caretaker asks the Originator for the snapshot as a memento object and stores the snapshot. When the Originator’s state is to be restored then Caretaker passes the Memento object back to the Originator.

There is also a State class which holds the state of the Originator. Memento also holds this state of the Originator. As this is simply the representation of Originator’s internal state hence there is no separate class for State depicted in the class diagram. However, in the java example’s class diagram, which is next, I have included the State class also.






PlayerStatus represents the state of the Originator(CurrentPlayerStatus). The State/PlayerStatus consists of two attributes – playerPoints and playerLevel.

PlayerStatusMemento is the memento object which stores an instance of PlayerStatus which is nothing but a snapshot of CurrentPlayingStatus’s internal state.

CurrentPlayerStatus is the Originator i.e the object of which we need to store/restore the internal snapshot. CurrentPlayerStatus holds its state in two attributes playerPoints and playerLevel which are the exact same attributes in PlayerStatus object which is stored in the PlayerStatusMemento object.
PlayerStatusCaretaker in the above example drives the game and stores/restores the memento with the help of Originator. It starts the game. As the game progresses and the player reaches level 2 with 1200 points, PlayerStatusCaretaker asks the CurrentPlayerStatus(Originator) object to create a memento object and store the current PlayerStatus by calling its method createMementoFromStatus(). The player then moves to 2200 points and level 4 where he,hypothetically, loses a life and goes down to the last state stored. This is the state stored in the PlayerStatusMemento object which the PlayerStatusCaretaker sets by invoking the setStatusFromMemento(Momento) method of CurrentPlayerStatus. The gameplayer’s status is thus restored to 1200 points and level 2.

So, to sum-up, we were able to store a snapshot of CurrentPlayerStatus and then restore it later. This is how the Memento Design Pattern works.

 */
