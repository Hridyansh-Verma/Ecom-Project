package com.project.paymentservice.service;

import com.razorpay.RazorpayException;

public interface PaymentService {
    String getPaymentLink(String orderId, String amount, String currency, String redirectUrl) throws RazorpayException;
    String getPaymentStatus(String orderId);
}
