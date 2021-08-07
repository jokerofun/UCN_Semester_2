
public class SharedObject {
	private int count;

	public SharedObject(int value) {
		count = value;
	}

	public synchronized void setCount(int count) {
		this.count = count;
	}

	public synchronized int getCount() {
		return count;
	}

}
