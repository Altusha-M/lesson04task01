package part1.lesson4.task01;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Cleanup {

    public static void main(String[] args) {

    }

    static void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (fieldsToCleanup.isEmpty() && fieldsToOutput.isEmpty()) {
            return;
        }
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        ArrayList<String> fieldsList = new ArrayList<>();

        if (!(object instanceof Map)) {
            for (Field f : fields) {
                fieldsList.add(f.getName());
            }
            if (!fieldsList.containsAll(fieldsToCleanup)
                    || !fieldsList.containsAll(fieldsToOutput)) {
                throw new IllegalArgumentException();
            }
            for (Field f : fields) {
                if (fieldsToOutput.contains(f.getName())) {
                    f.setAccessible(true);
                    System.out.println((String) f.get(object));
                    f.setAccessible(false);
                }

                if (fieldsToCleanup.contains(f.getName())) {
                    f.setAccessible(true);
                    switch (f.getType().getName()) {
                        case ("int"): {
                            f.setInt(object, 0);
                            f.setAccessible(false);
                            break;
                        }
                        case ("char"): {
                            f.setChar(object, '\u0000');
                            f.setAccessible(false);
                            break;
                        }
                        case ("byte"): {
                            f.setByte(object, (byte) 0);
                            f.setAccessible(false);
                            break;
                        }
                        case ("short"): {
                            f.setShort(object, (short) 0);
                            f.setAccessible(false);
                            break;
                        }
                        case ("long"): {
                            f.setLong(object, 0L);
                            f.setAccessible(false);
                            break;
                        }
                        case ("float"): {
                            f.setFloat(object, 0.0f);
                            f.setAccessible(false);
                            break;
                        }
                        case ("double"): {
                            f.setDouble(object, 0.0d);
                            f.setAccessible(false);
                            break;
                        }
                        case ("boolean"): {
                            f.setBoolean(object, false);
                            f.setAccessible(false);
                            break;
                        }
                        default: {
                            f.set(object, null);
                            f.setAccessible(false);
                            break;
                        }
                    }
                }
            }
        } else {
            Method getMeth = clazz.getMethod("get", Object.class);
            Method removeMeth = clazz.getMethod("remove", Object.class);
            Set<String> ks = (Set<String>) clazz.getMethod("keySet").invoke(object);
            if (!ks.containsAll(fieldsToCleanup)
                    || !ks.containsAll(fieldsToOutput)) {
                throw new IllegalArgumentException();
            }
            for (String outField : fieldsToOutput) {
                System.out.println(getMeth.invoke(object, outField));
            }
            for (String clearField : fieldsToCleanup) {
                removeMeth.invoke(object, clearField);
            }
        }
    }
}
