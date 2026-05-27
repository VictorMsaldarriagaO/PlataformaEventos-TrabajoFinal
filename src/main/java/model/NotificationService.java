package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Componente concreto de publicación de mensajes de alerta masiva e individual.
 */
public class NotificationService implements Subject {
    private List<Observer> observers;

    public NotificationService() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) { observers.add(observer); }
    }

    @Override
    public void detach(Observer observer) { observers.remove(observer); }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) { observer.update(message); }
    }
}