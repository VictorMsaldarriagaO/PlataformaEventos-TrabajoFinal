package model;

/**
 * Interfaz publicadora del patrón Observer.
 */
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(String message);
}