package org.eldslott.hb.concept;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/19/13
 */
public interface Infinite<T> {
    public Infinite higher();
    public Infinite lower();
    public T value();
    public int magnitude();


    default public String higherToString(String klass) {
        StringBuilder builder = new StringBuilder();
        StringBuilder space = new StringBuilder();

        if (higher() != null) {
            builder.append(higher().higherToString(klass));
        }

        for (int i = 0; i < magnitude(); i++) {
            space.append(" ");
        }

        return String.format("%s\n%s%s{magnitude=%d, value=%s}", builder.toString(), space, klass, magnitude(), value());
    }

    default public String lowerToString(String klass) {
        StringBuilder builder = new StringBuilder();
        StringBuilder space = new StringBuilder();

        if (lower() != null) {
            builder.append(lower().lowerToString(klass));
        }

        for (int i = 0; i < magnitude(); i++) {
            space.append(" ");
        }

        return String.format("%s\n%s%s{magnitude=%d, value=%s}", builder.toString(), space, klass, magnitude(), value());
    }

    default public String toString(Class klass) {
        return klass.getSimpleName() + "{" +
                "magnitude=" + magnitude() +
                ", value=" + value() +
                (higher() != null ? higher().higherToString(klass.getSimpleName()) : "") +
                (lower() != null ? lower().lowerToString(klass.getSimpleName()) : "") +
                '}';
    }
}
