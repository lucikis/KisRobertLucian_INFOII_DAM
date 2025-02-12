public class EmailNotification extends Notification implements Notifiable{
    @Override
    public void sendNotification() {
        System.out.println("Email notification sent!");
    }
}