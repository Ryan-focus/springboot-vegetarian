package com.eeit45.champion.vegetarian.util.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.PaypalRequest;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class PayPalClient {

    String clientId = "AeUyriY-4yp17yjRVYJ8pQU8JCYE1KU4VA8PHxlMbkgVUE7asOuYBG8sqo1Aq99jneBeWJm57FHwxWy3";
    String clientSecret = "ELIidMH1ZGLqRl2465-wRqraK5CG2Msi6qxoZl1SY3tVMHERMiWYF4Z_IccPs48cIbkCQ9ZjH9RE0vSf";
    public Map<String, Object> createPayment(String sum){
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("TWD");
        amount.setTotal(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/#/shopping");
        redirectUrls.setReturnUrl("http://localhost:8080/#/shopping/checkoutSuccess");
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }

    public Map<String, Object> completePayment(PaypalRequest paypalRequest){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(paypalRequest.getPaymentId());

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(paypalRequest.getPayerID());
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                System.out.println(createdPayment.toString());
                response.put("status", "success");
                response.put("payment", createdPayment.toString());
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
    }
}
