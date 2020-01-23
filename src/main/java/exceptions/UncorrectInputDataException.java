package exceptions;

public class UncorrectInputDataException extends RuntimeException {

    @Override
    public void printStackTrace() {
        System.err.println("UncorrectInputDataException");
    }
}
