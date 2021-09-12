package revolut;

import java.util.Objects;

public class PaymentService {
    private String serviceName;
    private boolean rejects = false;

    public PaymentService(String name){

        this.serviceName = name;

        if (Objects.equals(name, "CreditCard")) {
            this.rejects = true;
        }
    }

    public String getType() {
        return serviceName;
    }

    public boolean isRejects() {
        return rejects;
    }
}
