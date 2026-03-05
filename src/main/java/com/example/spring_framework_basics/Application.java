import com.example.spring_framework_basics.model.Payment;
import com.example.spring_framework_basics.provider.MockPaymentProvider;
import com.example.spring_framework_basics.service.PaymentServiceImpl;

void main() {
  var paymentProvider = new MockPaymentProvider();
  var paymentService = new PaymentServiceImpl(paymentProvider);

  var payment = new Payment("TX_01", 5466.78, "INR");

  paymentService.executePayment(payment);
}
