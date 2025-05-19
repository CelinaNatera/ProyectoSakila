package modelo;

public class Actor extends Entity {
    private int actorId;
    private String firstName;
    private String lastName;
    private int id;

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Actor() {
       
    }
    
    public void setId(int id) {
        this.id = id;
    } 
    
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int getId() {
        return actorId;
    }

    @Override
    public String toString() {
        return actorId + " - " + firstName + " " + lastName;
    }
}