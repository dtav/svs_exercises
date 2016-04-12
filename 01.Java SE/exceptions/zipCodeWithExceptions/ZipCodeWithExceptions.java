/*
 Exercise: ZipCodeWithExceptions
Modify the Zip Code application so that it throws an exception if an invalid argument is 
passed.
*/

package zipCodeWithExceptions;

import zipCodeWithExceptions.InputAlphaException;
import zipCodeWithExceptions.InputEmptyException;
import zipCodeWithExceptions.InputLengthException;


public class ZipCodeWithExceptions {

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) throws InputAlphaException, InputEmptyException, InputLengthException {

		if (code == null) {
			InputEmptyException e = new InputEmptyException();
			throw e;
		} else {
			if (!((code.length() == 5) || (code.length() == 9))) {
				InputLengthException e = new InputLengthException();
				throw e;
			} else {
				boolean flag = true;
				for (int i = 0; i <= code.length() - 1; i++) {
					int value = Character.getNumericValue(code.charAt(i));
					if ((value >= 0) && (value <= 9)) {

					} else {
						flag = false;
						InputAlphaException e = new InputAlphaException();
						throw e;

					}
				}
				if (flag) {
					this.code = code;
				}
			}
		}

	}

	public static void main(String args[]) {
		ZipCodeWithExceptions z = new ZipCodeWithExceptions();

		try {
			z.setCode("12345");
			System.out.println(z.getCode());
			z.setCode("1234");
			System.out.println(z.getCode());
			z.setCode("1234a");
			System.out.println(z.getCode());
		} catch (InputEmptyException e) {
			System.err.println("Input is empty");
			e.printStackTrace();
		} catch (InputLengthException e) {
			System.err.println("Input is not 5 or 9 digits");
			e.printStackTrace();
		} catch (InputAlphaException e) {
			System.err.println("Input contains alpha characters");
			e.printStackTrace();
		}

	}

}
