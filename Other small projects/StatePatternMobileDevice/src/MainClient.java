
public class MainClient {
	public static void main(String[] args) {
		AlertStateContext stateContext = new AlertStateContext();
		stateContext.alert();
		stateContext.alert();
		stateContext.setSilent();
		stateContext.alert();
		stateContext.setVibration();
		stateContext.alert();
		stateContext.setRinging();
		stateContext.alert();
	}
}
