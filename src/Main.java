import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        // Create a CompletableFuture
        CompletableFuture<Boolean> paymentPromise = new CompletableFuture<>();

        // Simulate an asynchronous operation
        simulatePaymentOperation(paymentPromise);

        // Attach callbacks to handle the result when the operation completes
        paymentPromise
                .thenAccept(result -> System.out.println("Payment operation completed with result: " + result))
                .exceptionally(ex -> {
                    System.err.println("Payment operation failed with exception: " + ex.getMessage());
                    return null;
                });

        // Simulate the completion of the asynchronous operation
        paymentPromise.complete(true); // Resolves the promise with a value of true
        // Alternatively, you can use: paymentPromise.completeExceptionally(new RuntimeException("Payment failed"));

        // Note: In a real-world scenario, the asynchronous operation would complete in a separate thread.
    }

    private static void simulatePaymentOperation(CompletableFuture<Boolean> promise) {
        // Simulate an asynchronous operation (e.g., fetching data from a server)
        new Thread(() -> {
            try {
                // Simulate some processing time
                Thread.sleep(2000);
                // Simulate a successful payment
                promise.complete(true);
            } catch (InterruptedException e) {
                // Simulate an error in the asynchronous operation
                promise.completeExceptionally(e);
            }
        }).start();
    }
}
