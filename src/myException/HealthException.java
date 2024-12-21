package myException;

public class HealthException extends Exception{
    private String myMessage;

    public HealthException(String message){
        super(message);
        this.myMessage = message;
    }

    @Override
    public String getMessage() {
        // Добавление дополнительной информации к сообщению об ошибке
        return "Health Error: " + myMessage;
    }
}
