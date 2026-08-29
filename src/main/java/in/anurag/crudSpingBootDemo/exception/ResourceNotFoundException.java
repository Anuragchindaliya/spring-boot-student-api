package in.anurag.crudSpingBootDemo.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
            super(message);
    }
}
