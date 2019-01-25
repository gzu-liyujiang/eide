package ____.sun.security.validator;

public class Validator
{
    /**
     * Constant for a Generic variant of a validator.
     * @see #getInstance
     */
    public final static String VAR_GENERIC = "generic";
    
    /**
     * Constant for a TLS Client variant of a validator.
     * @see #getInstance
     */
    public final static String VAR_TLS_CLIENT = "tls client";
    
    /**
     * Constant for a TLS Server variant of a validator.
     * @see #getInstance
     */
    public final static String VAR_TLS_SERVER = "tls server";
    
    /**
     * Constant for a Code Signing variant of a validator for use by
     * the J2SE Plugin/WebStart code.
     * @see #getInstance
     */
    public final static String VAR_PLUGIN_CODE_SIGNING = "plugin code signing";
    
}
