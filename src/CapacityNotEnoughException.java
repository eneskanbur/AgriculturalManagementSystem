
public class CapacityNotEnoughException extends Exception{
    String msg;

    public CapacityNotEnoughException(String msg) {
        super(msg);
    }
    
}
