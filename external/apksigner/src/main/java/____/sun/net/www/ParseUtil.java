package ____.sun.net.www;
import java.util.BitSet;
import java.io.File;

public class ParseUtil
{
    static BitSet encodedInPath;

    static {
        encodedInPath = new BitSet(256);

        // Set the bits corresponding to characters that are encoded in the
        // path component of a URI.

        // These characters are reserved in the path segment as described in
        // RFC2396 section 3.3.
        encodedInPath.set('=');
        encodedInPath.set(';');
        encodedInPath.set('?');
        encodedInPath.set('/');

        // These characters are defined as excluded in RFC2396 section 2.4.3
        // and must be escaped if they occur in the data part of a URI.
        encodedInPath.set('#');
        encodedInPath.set(' ');
        encodedInPath.set('<');
        encodedInPath.set('>');
        encodedInPath.set('%');
        encodedInPath.set('"');
        encodedInPath.set('{');
        encodedInPath.set('}');
        encodedInPath.set('|');
        encodedInPath.set('\\');
        encodedInPath.set('^');
        encodedInPath.set('[');
        encodedInPath.set(']');
        encodedInPath.set('`');

        // US ASCII control characters 00-1F and 7F.
        for (int i=0; i<32; i++)
            encodedInPath.set(i);
        encodedInPath.set(127);
    }
    
    /**
     * Constructs an encoded version of the specified path string suitable
     * for use in the construction of a URL.
     *
     * A path separator is replaced by a forward slash. The string is UTF8
     * encoded. The % escape sequence is used for characters that are above
     * 0x7F or those defined in RFC2396 as reserved or excluded in the path
     * component of a URL.
     */
    public static String encodePath(String path) {
        return encodePath(path, true);
    }
    /*
     * flag indicates whether path uses platform dependent
     * File.separatorChar or not. True indicates path uses platform
     * dependent File.separatorChar.
     */
    public static String encodePath(String path, boolean flag) {
        char[] retCC = new char[path.length() * 2 + 16];
        int    retLen = 0;
        char[] pathCC = path.toCharArray();

        int n = path.length();
        for (int i=0; i<n; i++) {
            char c = pathCC[i];
            if ((!flag && c == '/') || (flag && c == File.separatorChar))
                retCC[retLen++] = '/';
            else {
                if (c <= 0x007F) {
                    if (c >= 'a' && c <= 'z' ||
                    c >= 'A' && c <= 'Z' ||
                    c >= '0' && c <= '9') {
                        retCC[retLen++] = c;
                    } else
                    if (encodedInPath.get(c))
                        retLen = escape(retCC, c, retLen);
                    else
                        retCC[retLen++] = c;
                } else if (c > 0x07FF) {
                    retLen = escape(retCC, (char)(0xE0 | ((c >> 12) & 0x0F)), retLen);
                    retLen = escape(retCC, (char)(0x80 | ((c >>  6) & 0x3F)), retLen);
                    retLen = escape(retCC, (char)(0x80 | ((c >>  0) & 0x3F)), retLen);
                } else {
                    retLen = escape(retCC, (char)(0xC0 | ((c >>  6) & 0x1F)), retLen);
                    retLen = escape(retCC, (char)(0x80 | ((c >>  0) & 0x3F)), retLen);
                }
            }
            //worst case scenario for character [0x7ff-] every single
            //character will be encoded into 9 characters.
            if (retLen + 9 > retCC.length) {
                int newLen = retCC.length * 2 + 16;
                if (newLen < 0) {
                    newLen = Integer.MAX_VALUE;
                }
                char[] buf = new char[newLen];
                System.arraycopy(retCC, 0, buf, 0, retLen);
                retCC = buf;
            }
        }
        return new String(retCC, 0, retLen);
    }
    /**
     * Appends the URL escape sequence for the specified char to the
     * specified StringBuffer.
     */
    private static int escape(char[] cc, char c, int index) {
        cc[index++] = '%';
        cc[index++] = Character.forDigit((c >> 4) & 0xF, 16);
        cc[index++] = Character.forDigit(c & 0xF, 16);
        return index;
    }
}
