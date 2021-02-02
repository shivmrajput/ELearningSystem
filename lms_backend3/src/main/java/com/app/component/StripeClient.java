package com.app.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.model.Charge;

@Component
public class StripeClient {
	@Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51I8ihXJLsbXHNAX5ixK291Xwo4MEnX53OMOIp87GnnYGuVXrdyiMRe7kDgZksG6DsJCtP6xgdE7UcuKOpBDpJWFJ00WM0AWzD5";
    }
	
	public Charge chargeCreditCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

}
