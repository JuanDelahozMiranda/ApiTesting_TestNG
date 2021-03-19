package Models;

public class OrderFail {
    private String code;
    private String type;
    private String message;

    public OrderFail(String code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public String getCode() { return code; }

    public String getType() { return type; }

    public String getMessage() { return message; }
}
