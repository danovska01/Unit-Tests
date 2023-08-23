public interface Chainblock extends Iterable<TransactionImpl> {

    int getCount();

    void add(TransactionImpl transaction);

    boolean contains(int transaction);

    boolean contains(TransactionImpl id);

    void changeTransactionStatus(int id, TransactionStatus newStatus);

    void removeTransactionById(int id);

    Transaction getById(int id);

    Iterable<TransactionImpl> getByTransactionStatus(TransactionStatus status);

    Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status);

    Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status);

    Iterable<TransactionImpl> getAllOrderedByAmountDescendingThenById();

    Iterable<TransactionImpl> getBySenderOrderedByAmountDescending(String sender);

    Iterable<TransactionImpl> getByReceiverOrderedByAmountThenById(String receiver);

    Iterable<TransactionImpl> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount);

    Iterable<TransactionImpl> getBySenderAndMinimumAmountDescending(String sender, double amount);

    Iterable<TransactionImpl> getByReceiverAndAmountRange(String receiver, double lo, double hi);

    Iterable<TransactionImpl> getAllInAmountRange(double lo, double hi);

}
