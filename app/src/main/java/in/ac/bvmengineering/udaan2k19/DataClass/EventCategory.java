package in.ac.bvmengineering.udaan2k19.DataClass;

import java.io.Serializable;
import java.util.ArrayList;


public class EventCategory implements Serializable {
    private int image;
    private String name;
    private ArrayList<Event> events;

    public EventCategory(int image, String name) {
        this.image = image;
        this.name = name;
        events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
