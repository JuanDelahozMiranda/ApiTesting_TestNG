package Models;

public class Orders {
    private String id;
    private String petId;
    private String quantity;
    private String date;
    private String status;
    private String completed;

    public Orders(String id, String petId, String quantity, String completed){
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.date = "2021-03-16T00:00:00.000+0000";
        this.status = "placed";
        this.completed = completed;
    }

    public String getId() { return id; }

    public String getPetId() { return petId; }

    public String getQuantity(){ return quantity; }

    public String getCompleted() { return completed; }
}
