package interview.assignment.currex.pojo;

public class PurchaseResponse {
    private double totalAmount;

    PurchaseResponse () {};

    public PurchaseResponse(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
