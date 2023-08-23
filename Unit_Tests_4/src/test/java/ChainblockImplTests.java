import org.junit.Before;
import org.junit.Test;

import java.util.*;


import static org.junit.Assert.*;


public class ChainblockImplTests {
    private Chainblock chainblock;

    @Before
    public void setUp() {
        chainblock = new ChainblockImpl();
    }

    @Test
    public void testAddTransaction() {
        TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        chainblock.add(transaction);
        assertEquals(1, chainblock.getCount());
    }

    @Test
    public void testContainsTransaction() {
        TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        chainblock.add(transaction);
        assertTrue(chainblock.contains(transaction));
    }

    @Test
    public void testContainsTransactionById() {
        TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        chainblock.add(transaction);
        assertTrue(chainblock.contains(transaction.getId()));
    }

    @Test
    public void testChangeTransactionStatus() {
        TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        chainblock.add(transaction);
        chainblock.changeTransactionStatus(1, TransactionStatus.ABORTED);
        assertEquals(TransactionStatus.ABORTED, transaction.getStatus());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusThrowsException() {
        chainblock.changeTransactionStatus(6, TransactionStatus.ABORTED);
    }

    @Test
    public void testRemoveTransactionById() {
        TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        chainblock.add(transaction);
        chainblock.removeTransactionById(1);
        assertEquals(0, chainblock.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdThrowsException() {
        chainblock.removeTransactionById(6);
    }

    @Test
    public void testGetById() {
        TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        chainblock.add(transaction);
        assertEquals(transaction, chainblock.getById(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdThrowsException(){
        chainblock.getById(4);
    }


    // Test getByTransactionStatus
    @Test
    public void testGetByTransactionStatus() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.ABORTED, "Alice", "Bob", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(1, result.size());
        assertEquals(TransactionStatus.SUCCESSFUL, result.get(0).getStatus());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusThrowsException() {
        chainblock.getByTransactionStatus(TransactionStatus.valueOf("PENDING"));
    }


    // Test getAllSendersWithTransactionStatus
    @Test
    public void testGetAllSendersWithTransactionStatus() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Sam", "Bob", 5.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Sam", "Bob", 6.0);
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.ABORTED, "Sam", "Bob", 8.0);
        TransactionImpl transaction4 = new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "Peter", "Bob", 2.0);

        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.add(transaction3);
        chainblock.add(transaction4);

        Iterable<String> result = chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        assertEquals(Arrays.asList("Sam", "Sam", "Peter"), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusNotFound() {
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.ABORTED, "Sam", "Bob", 8.0);
        chainblock.add(transaction3);
        chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
    }


    // Test getAllReceiversWithTransactionStatus
    @Test
    public void testGetAllReceiversWithTransactionStatus() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bobo", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "Mara", 200.0);
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.ABORTED, "Sam", "Lara", 50.0);
        TransactionImpl transaction4 = new TransactionImpl(4, TransactionStatus.FAILED, "Alice", "Bob", 75.0);

        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.add(transaction3);
        chainblock.add(transaction4);

        Iterable<String> result = chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<String> resultAsList = new ArrayList<>();
        result.forEach(resultAsList::add);

        assertTrue(resultAsList.contains("Bobo"));
        assertTrue(resultAsList.contains("Mara"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusThrowsException() {
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.ABORTED, "Sam", "Bob", 8.0);
        chainblock.add(transaction3);

        chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);
    }


    // Test getAllOrderedByAmountDescendingThenById
    @Test
    public void testGetAllOrderedByAmountDescendingThenById() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "David", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getAllOrderedByAmountDescendingThenById();
        assertEquals(2, result.size());
        assertEquals(200.0, result.get(0).getAmount(), 0.001); // Using delta for comparison
        assertEquals(100.0, result.get(1).getAmount(), 0.001); // Using delta for comparison
        assertEquals(2, result.get(0).getId());
        assertEquals(1, result.get(1).getId());
    }


    // Test getBySenderOrderedByAmountDescending
    @Test
    public void testGetBySenderOrderedByAmountDescending() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Alice", "Charlie", 200.0);
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Alice", "David", 50.0);
        TransactionImpl transaction4 = new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "Sam", "Eve", 75.0);

        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.add(transaction3);
        chainblock.add(transaction4);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getBySenderOrderedByAmountDescending("Alice");

        assertEquals(3, result.size());
        assertTrue(result.get(0).getAmount() >= result.get(1).getAmount());
        assertTrue(result.get(1).getAmount() >= result.get(2).getAmount());
        assertEquals("Alice", result.get(0).getFrom());
        assertEquals("Alice", result.get(1).getFrom());
        assertEquals("Alice", result.get(2).getFrom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingThrowsException() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Alice", "Charlie", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.getBySenderOrderedByAmountDescending("Lara");
    }

    // Test getByReceiverOrderedByAmountThenById
    @Test
    public void testGetByReceiverOrderedByAmountThenById() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "Bob", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getByReceiverOrderedByAmountThenById("Bob");
        assertEquals(2, result.size());
        assertEquals(200.0, result.get(0).getAmount(), 0.001);
        assertEquals(100.0, result.get(1).getAmount(), 0.001);
        assertEquals(2, result.get(0).getId());
        assertEquals(1, result.get(1).getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrderedByAmountThenByIdThrowsException() {
        chainblock.getByReceiverOrderedByAmountThenById("ReceiverName");
    }
    @Test
    public void testGetByTransactionStatusAndMaximumAmount() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.ABORTED, "Charlie", "Bob", 200.0);
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Eve", "Bob", 50.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.add(transaction3);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 150.0);

        assertEquals(2, result.size());
        assertEquals(TransactionStatus.SUCCESSFUL, result.get(0).getStatus());
        assertEquals(TransactionStatus.SUCCESSFUL, result.get(1).getStatus());
        assertTrue(result.get(0).getAmount() <= 150.0);
        assertTrue(result.get(1).getAmount() <= 150.0);
        assertTrue(result.get(0).getAmount() >= result.get(1).getAmount());
    }

    //Returns an empty collection if no such transactions were found.
    @Test
    public void testGetByTransactionStatusAndMaximumAmountIfNoSuchTransaction() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.ABORTED, "Charlie", "Bob", 200.0);
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Eve", "Bob", 50.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.add(transaction3);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 30.0);

        assertTrue(result.isEmpty());
    }



    // Test getBySenderAndMinimumAmountDescending
    @Test
    public void testGetBySenderAndMinimumAmountDescending() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Alice", "Charlie", 150.0);
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Eve", "Alice", 80.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.add(transaction3);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getBySenderAndMinimumAmountDescending("Alice", 120.0);

        assertEquals(1, result.size());
        assertEquals(150.0, result.get(0).getAmount(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingThrowsException() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Alice", "Charlie", 150.0);
        TransactionImpl transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Eve", "Alice", 80.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);
        chainblock.add(transaction3);

        chainblock.getBySenderAndMinimumAmountDescending("Alice", 200.0);
    }




    @Test
    public void testGetByReceiverAndAmountRange() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "Bob", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getByReceiverAndAmountRange("Bob", 100.0, 300.0);
        assertEquals(2, result.size());
        assertEquals(200.0, result.get(0).getAmount(), 0.001);
        assertEquals(100.0, result.get(1).getAmount(), 0.001);
        assertEquals(2, result.get(0).getId());
        assertEquals(1, result.get(1).getId());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeThrowsException() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "Bob", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        chainblock.getByReceiverAndAmountRange("David", 100.0, 200.0);
    }



    // Test getAllInAmountRange
    @Test
    public void testGetAllInAmountRange() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "David", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> result = (List<TransactionImpl>) chainblock.getAllInAmountRange(150.0, 300.0);
        assertEquals(1, result.size());
        assertEquals(200.0, result.get(0).getAmount(), 0.001);
    }

    @Test
    public void testIterator() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "David", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction1);
        expectedTransactions.add(transaction2);

        List<TransactionImpl> actualTransactions = new ArrayList<>();
        for (TransactionImpl transaction : chainblock) {
            actualTransactions.add(transaction);
        }

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testForEach() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "David", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction1);
        expectedTransactions.add(transaction2);

        List<TransactionImpl> actualTransactions = new ArrayList<>();
        chainblock.forEach(actualTransactions::add);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testSpliterator() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "David", 200.0);
        chainblock.add(transaction1);
        chainblock.add(transaction2);

        List<TransactionImpl> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction1);
        expectedTransactions.add(transaction2);

        List<TransactionImpl> actualTransactions = new ArrayList<>();
        chainblock.spliterator().forEachRemaining(actualTransactions::add);

        assertEquals(expectedTransactions, actualTransactions);
    }








}
