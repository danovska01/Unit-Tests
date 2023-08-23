import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionImplTest {

    @Test
    public void testCompareToEqualTransactions() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);

        assertEquals(0, transaction1.compareTo(transaction2));
    }

    @Test
    public void testCompareToDifferentIds() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);

        assertTrue(transaction1.compareTo(transaction2) < 0);
        assertTrue(transaction2.compareTo(transaction1) > 0);
    }

    @Test
    public void testCompareToDifferentAmounts() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 200.0);

        assertTrue(transaction1.compareTo(transaction2) < 0);
        assertTrue(transaction2.compareTo(transaction1) > 0);
    }

    @Test
    public void testCompareToEqualAmountsDifferentIds() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);

        assertTrue(transaction1.compareTo(transaction2) < 0);
        assertTrue(transaction2.compareTo(transaction1) > 0);
    }

    @Test
    public void testCompareToEqualAmountsEqualIds() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);

        assertEquals(0, transaction1.compareTo(transaction2));
    }




        @Test
        public void testGetters() {
            TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);

            assertEquals(1, transaction.getId());
            assertEquals(TransactionStatus.SUCCESSFUL, transaction.getStatus());
            assertEquals("Alice", transaction.getFrom());
            assertEquals("Bob", transaction.getTo());
            assertEquals(100.0, transaction.getAmount(), 0.001);
        }

        @Test
        public void testSetters() {
            TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);

            transaction.setId(2);
            transaction.setStatus(TransactionStatus.UNAUTHORIZED);
            transaction.setFrom("Charlie");
            transaction.setTo("David");
            transaction.setAmount(200.0);

            assertEquals(2, transaction.getId());
            assertEquals(TransactionStatus.UNAUTHORIZED, transaction.getStatus());
            assertEquals("Charlie", transaction.getFrom());
            assertEquals("David", transaction.getTo());
            assertEquals(200.0, transaction.getAmount(), 0.001);
        }

    @Test
    public void testEqualsAndHashCode() {
        TransactionImpl transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction2 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        TransactionImpl transaction3 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Charlie", "David", 150.0);

        // Test equals method
        assertTrue(transaction1.equals(transaction2));
        assertFalse(transaction1.equals(transaction3));

        // Test hashCode method
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeDifferentTypes() {
        TransactionImpl transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Alice", "Bob", 100.0);
        Object object = new Object();

        assertFalse(transaction.equals(object));
        assertNotEquals(transaction.hashCode(), object.hashCode());
    }
    }


