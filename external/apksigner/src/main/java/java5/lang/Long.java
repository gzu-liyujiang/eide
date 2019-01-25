package java5.lang;

public class Long{

    /**
     * Returns the value obtained by reversing the order of the bytes in the
     * two's complement representation of the specified {@code long} value.
     *
     * @return the value obtained by reversing the bytes in the specified
     *     {@code long} value.
     * @since 1.5
     */
    public static long reverseBytes(long i) {
        i = (i & 0x00ff00ff00ff00ffL) << 8 | (i >>> 8) & 0x00ff00ff00ff00ffL;
        return (i << 48) | ((i & 0xffff0000L) << 16) |
        ((i >>> 16) & 0xffff0000L) | (i >>> 48);
    }
}
