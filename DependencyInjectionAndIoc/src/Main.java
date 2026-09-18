import Notification.EmailService;
import Notification.SmsService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        OrderService order = new OrderService(new SmsService());
        order.setNotification(new EmailService());
        order.placeOrder();
    }
}