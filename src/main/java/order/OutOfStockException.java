package order;

public class OutOfStockException extends RuntimeException {

    private final String bookName;
    private final int requestedQuantity;
    private final int availableQuantity;

    public OutOfStockException(String bookName, int requestedQuantity, int availableQuantity) {
        super("Not enough stock for " + bookName);
        this.bookName = bookName;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String getBookName() {
        return bookName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}


