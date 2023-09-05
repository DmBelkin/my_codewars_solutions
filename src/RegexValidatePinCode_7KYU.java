public class RegexValidatePinCode_7KYU {

//    ATM machines allow 4 or 6 digit PIN codes and PIN codes cannot contain anything but exactly 4 digits or exactly 6 digits.
//
//    If the function is passed a valid PIN string, return true, else return false.
//
//    Examples (Input --> Output)
//        "1234"   -->  true
//        "12345"  -->  false
//        "a234"   -->  false

    public static boolean validatePin(String pin) {
        String regex = "^([0-9]{4}|[0-9]{6})$";
        if (!pin.matches(regex)) {
            return false;
        }
        return true;
    }


}
