
public class Ringing implements MobileAlertState {

	@Override
	public void alert(AlertStateContext ctx) {
		System.out.println("ringing...");
	}

}
