package ApplicationLayer;

public class Message extends Exception{

	String message;
	public Message(String msg) {
		message=msg;
	}
	public String toString() {
		return (message);
	}
}
