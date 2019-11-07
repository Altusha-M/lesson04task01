package part1.lesson4.task01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CleanupTest {

    public class Animal {

        private final String name = "name";
        private int intValue = 5;
        private long longValue = 10;
        private short shortValue = 15;
        private double doubleValue = 5.5;
        private float floatValue = 5.8f;
        private byte byteValue = 125;
        private char charValue = 'h';
        private boolean booleanValue = true;

        public Animal() {
            // this.name = name;
        }


        public int getIntValue() {
            return intValue;
        }

        public long getLongValue() {
            return longValue;
        }

        public short getShortValue() {
            return shortValue;
        }

        public double getDoubleValue() {
            return doubleValue;
        }

        public float getFloatValue() {
            return floatValue;
        }

        public byte getByteValue() {
            return byteValue;
        }

        public char getCharValue() {
            return charValue;
        }

        public boolean isBooleanValue() {
            return booleanValue;
        }

        public String getName() {
            return name;
        }

    }
    Map<String, String> hashMap = new HashMap<>();

    @Test
    void cleanupTest() {
        Set<String> cleanSet = new HashSet<String>() {{
            add("str1");
            add("str2");
            add("str3");
            add("str14");
            add("str15");
            add("str6661");
            add("str177");
        }};
        Set<String> outSet = new HashSet<String>() {{
            add("1str1");
            add("1str2");
            add("1str3");
            add("1str14");
            add("1str15");
            add("1str6661");
            add("1str177");
        }};
        Iterator<String> itrO = outSet.iterator();
        Iterator<String> itrC = cleanSet.iterator();
        while (itrC.hasNext() && itrO.hasNext()) {
            hashMap.put(itrC.next(), itrO.next());
        }
        outSet.remove("1str1");
        outSet.remove("1str6661");
        cleanSet.remove("str1");
        cleanSet.remove("str6661");

        Animal Drozdov = new Animal();
        try {
            Cleanup.cleanup(hashMap, cleanSet, cleanSet);
            Cleanup.cleanup(Drozdov,
                    new HashSet<String>() {{
                        add("intValue");
                        add("longValue");
                        add("shortValue");
                        add("doubleValue");
                        add("charValue");
                        add("floatValue");
                    }},
                    new HashSet<String>() {{
                        add("byteValue");
                        add("charValue");
                        add("booleanValue");
                        add("doubleValue");
                        add("floatValue");
                    }});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {

        }
        assertFalse(hashMap.keySet().containsAll(cleanSet));
        assertEquals(Drozdov.getIntValue(), 0);
        assertEquals(Drozdov.getLongValue(), 0L);
        assertEquals(Drozdov.getCharValue(), '\u0000');
        assertEquals(Drozdov.getFloatValue(), 0.0f);

    }

}