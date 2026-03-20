package utils;

/**
* utilities containing validations
*/
public class Validators {
    private Validators() {};

    /**
     * check for s not to be empty or not null
     * @param s
     * @return boolean
     */
    public static boolean StringValidator(String s) {
        if (s == "" || s == null) return false;
        return true;
    }
}
