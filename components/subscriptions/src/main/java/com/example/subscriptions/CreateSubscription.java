package com.example.subscriptions;

import com.example.billing.BillingClient;
import com.example.email.SendEmail;

public class CreateSubscription {

    //private final ChargeUser chargeUser;
    private final SendEmail emailSender;
    private final SubscriptionRepository subscriptions;
    private BillingClient billingClient;

    public CreateSubscription(
            //ChargeUser chargeUser,
            BillingClient billingClient,
            SendEmail emailSender, SubscriptionRepository subscriptions) {
        //this.chargeUser = chargeUser;
        this.emailSender = emailSender;
        this.subscriptions = subscriptions;
        this.billingClient = billingClient;
    }

    public void run(String userId, String packageId) {
        subscriptions.create(new Subscription(userId, packageId));
        billingClient.billUser(userId,100   );
        emailSender.run("me@example.com", "Subscription Created", "Some email body");
    }
}
