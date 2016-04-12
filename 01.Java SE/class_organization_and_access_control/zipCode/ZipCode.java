/*
 Exercise: ZipCodeWithExceptions
Modify the Zip Code application so that it throws an exception if an invalid argument is 
passed.
*/

package zipCode;

public class ZipCode {

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {

		if (code == null) {
			System.out.println("Zip Code can't be empty");
			this.code = "";
			return;
		}
		if (!((code.length() == 5) || (code.length() == 9))) {
			System.out.println("Zip Code can't be with length different than 5 or 9");
			this.code = "";
			return;
		} else {
			for (int i = 0; i <= code.length() - 1; i++) {
				int value = Character.getNumericValue(code.charAt(i));
				if ((value >= 0) && (value <= 9)) {

				} else {
					System.out.println("Zip code can't contain alpha characters");
					this.code = "";
					return;
				}
			}
			this.code = code;
		}
	}

	public static void main(String args[]) {
		ZipCode z = new ZipCode();

			z.setCode("12345");
			System.out.println(z.getCode());
			z.setCode("1234");
			System.out.println(z.getCode());
			z.setCode("1234a");
			System.out.println(z.getCode());
		
	}

}
