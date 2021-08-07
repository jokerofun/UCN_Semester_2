package GUI;

import java.util.regex.Pattern;

public enum RegexEnum {

	EMAIL(Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")),
	PHONENUMBER(Pattern.compile("^[+]{1}[0-9 ]{1,20}$")), ZIPCODE(Pattern.compile("^[0-9]{4,8}$")),
	LETTERANDNUMBER(Pattern.compile("^[a-zA-Z0-9.; ]{1,25}$")), PRICE(Pattern.compile("^[0-9]+[.]{1}[0-9]+$")),
	DATE(Pattern.compile("^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$")), LETTERONLY(Pattern.compile("^[a-zA-Z]{1,30}$")),
	NUMBERONLY(Pattern.compile("^[0-9]{1,5}$"));

	private Pattern regex;

	private RegexEnum(Pattern s) {
		this.regex = s;
	}

	public boolean isMatch(String toTest) {
		return regex.matcher(toTest).find();
	}
}
