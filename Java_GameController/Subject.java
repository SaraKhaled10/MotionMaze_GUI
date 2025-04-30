package project;

public interface Subject {
    void addObserver(Observer o);
    void notifyObservers(String status);
}