import java.util.Objects;

public class TransactionImpl implements Comparable<TransactionImpl>, Transaction {

    private int id;
    private TransactionStatus status;
    private String from;
    private String to;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public int compareTo(TransactionImpl o) {
        // Compare based on IDs
        return Integer.compare(this.id, o.id);
    }

    public int getId() {
        return id;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionImpl that = (TransactionImpl) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
