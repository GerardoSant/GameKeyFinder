package Persistence;

public interface Writer<T> {
    void add(T object);
    void remove(T object);
}
