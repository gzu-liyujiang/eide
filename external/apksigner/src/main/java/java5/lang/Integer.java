package java5.lang;

public class Integer
{
    /**
     * Returns the value obtained by reversing the order of the bytes in the
     * two's complement representation of the specified {@code int} value.
     *
     * @return the value obtained by reversing the bytes in the specified
     *     {@code int} value.
     * @since 1.5
     */
    public static int reverseBytes(int i) {
        return ((i >>> 24)           ) |
        ((i >>   8) &   0xFF00) |
        ((i <<   8) & 0xFF0000) |
        ((i << 24));
    }
}
