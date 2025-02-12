public class SMSNotification extends Notification implements Notifiable{

    @Override
    public void sendNotification() {
        System.out.println("SMS notification sent!");
    }
}