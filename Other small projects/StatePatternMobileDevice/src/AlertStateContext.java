
public class AlertStateContext {

	private MobileAlertState currentState;

	public AlertStateContext() {
		currentState = new Vibration();
	}

	public void setSilent() {
		currentState = new Silent();
	}

	public void setVibration() {
		currentState = new Vibration();
	}

	public void setRinging() {
		currentState = new Ringing();
	}

	public void alert() {
		currentState.alert(this);
	}
}
