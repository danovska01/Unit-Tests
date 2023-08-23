import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public  class ChainblockImpl implements Chainblock{

    private Map<Integer, TransactionImpl> transactions;

    public ChainblockImpl() {
        this.transactions = new HashMap<>();
    }
    @Override
    public int getCount() {
        return transactions.size();
    }

    @Override
    public void add(TransactionImpl transaction) {
        transactions.putIfAbsent(transaction.getId(), transaction);
    }

    @Override
    public boolean contains(int id) {
        return transactions.containsKey(id);
    }

    @Override
    public boolean contains(TransactionImpl transaction) {
        return transactions.containsValue(transaction);
    }

    @Override
    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!transactions.containsKey(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        transactions.get(id).setStatus(newStatus);
    }

    @Override
    public void removeTransactionById(int id) {
        if (!transactions.containsKey(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        transactions.remove(id);
    }

    @Override
    public TransactionImpl getById(int id) {
        if (!transactions.containsKey(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        return transactions.get(id);
    }

    @Override
    public Iterable<TransactionImpl> getByTransactionStatus(TransactionStatus status) {
        List<TransactionImpl> result = transactions.values().stream()
                .filter(t -> t.getStatus() == status)
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed())
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException("No transactions with the given status");
        }
        return result;
    }

    @Override
    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> senders = transactions.values().stream()
                .filter(t -> t.getStatus() == status)
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed())
                .map(TransactionImpl::getFrom)
                .collect(Collectors.toList());

        if (senders.isEmpty()) {
            throw new IllegalArgumentException("No transactions with the given status");
        }
        return senders;
    }

    @Override
    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> receivers = transactions.values().stream()
                .filter(t -> t.getStatus() == status)
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed())
                .map(TransactionImpl::getTo)
                .collect(Collectors.toList());

        if (receivers.isEmpty()) {
            throw new IllegalArgumentException("No transactions with the given status");
        }
        return receivers;
    }

    @Override
    public Iterable<TransactionImpl> getAllOrderedByAmountDescendingThenById() {
        return transactions.values().stream()
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed()
                        .thenComparing(TransactionImpl::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<TransactionImpl> getBySenderOrderedByAmountDescending(String sender) {
        List<TransactionImpl> senderTransactions = transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed())
                .collect(Collectors.toList());

        if (senderTransactions.isEmpty()) {
            throw new IllegalArgumentException("No transactions with the given sender");
        }
        return senderTransactions;
    }

    @Override
    public Iterable<TransactionImpl> getByReceiverOrderedByAmountThenById(String receiver) {
        List<TransactionImpl> receiverTransactions = transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver))
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed()
                        .thenComparing(TransactionImpl::getId))
                .collect(Collectors.toList());

        if (receiverTransactions.isEmpty()) {
            throw new IllegalArgumentException("No transactions with the given receiver");
        }
        return receiverTransactions;
    }

    @Override
    public Iterable<TransactionImpl> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        List<TransactionImpl> result = transactions.values().stream()
                .filter(t -> t.getStatus() == status && t.getAmount() <= amount)
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed())
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Iterable<TransactionImpl> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<TransactionImpl> senderTransactions = transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender) && t.getAmount() > amount)
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed())
                .collect(Collectors.toList());

        if (senderTransactions.isEmpty()) {
            throw new IllegalArgumentException("No transactions with the given sender and amount");
        }
        return senderTransactions;
    }

    @Override
    public Iterable<TransactionImpl> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<TransactionImpl> receiverTransactions = transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver) && t.getAmount() >= lo && t.getAmount() < hi)
                .sorted(Comparator.comparingDouble(TransactionImpl::getAmount).reversed()
                        .thenComparing(TransactionImpl::getId))
                .collect(Collectors.toList());

        if (receiverTransactions.isEmpty()) {
            throw new IllegalArgumentException("No transactions with the given receiver and amount range");
        }
        return receiverTransactions;
    }

    @Override
    public Iterable<TransactionImpl> getAllInAmountRange(double lo, double hi) {
        List<TransactionImpl> result = transactions.values().stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .collect(Collectors.toList());

        return result;
    }


    @Override
    public Iterator<TransactionImpl> iterator() {
        return transactions.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super TransactionImpl> action) {
        transactions.values().forEach(action);
    }

    @Override
    public Spliterator<TransactionImpl> spliterator() {
        return transactions.values().spliterator();
    }


}
