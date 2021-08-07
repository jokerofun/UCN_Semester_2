import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private static final String EMAIL_TEXT = "gianna.belle@gmailcom";
	private static final String DOC_TEXT = "This is an email address: fen@ucn.dk and this is"
			+ "also peter@peterfich.com and more:"
			+ "moh@solar.dk; jens@hilli.dk; Tazzz-321@gmail.com, Mgj3aa@gmail.com;" + "mhj@logimatic.dk and text igen."
			+ "Another email address uki@dadlnet.dk bla. bla."
			+ " In total 8 addresses  plus this here: gianna.belle@gmail.com";
	private static final int DOC_TEXT_N = 9;
	private static final String NUMBER_TEXT = "+123";
	private static final String EMAIL_PATTERN = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
	private static final String NUMBER_PATTERN = "^(\\+|-)?[0-9]+$";

	public static void main(String args[]) {

		Main m = new Main();

		m.checkPattern(EMAIL_PATTERN, EMAIL_TEXT);
		m.checkPattern(NUMBER_PATTERN, NUMBER_TEXT);
		m.getEmailList();
	}

	public void checkPattern(String pattern, String text) {

		if (Pattern.matches(pattern, text))
			System.out.println(text + " is ok");
		else
			System.out.println(text + " is not ok");

	}

	public void getEmailList() {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher m = pattern.matcher(DOC_TEXT);

		List<String> maillist = new LinkedList<>();
		while (m.find()) {
			maillist.add(m.group());
		}

		System.out.printf("Expecting %d email addresses:%n", DOC_TEXT_N);
		int i = 1;
		for (String email : maillist)
			System.out.println(i++ + "\t" + email);
	}

}
