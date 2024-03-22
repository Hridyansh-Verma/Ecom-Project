package com.project.paymentservice.controllers;

import com.razorpay.RazorpayException;
import com.project.paymentservice.dto.CreatePaymentLinkReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.paymentservice.service.PaymentService;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
    @GetMapping("/getPaymentLink")
    public String getPaymentLink(@RequestBody CreatePaymentLinkReqDto createPaymentLinkReqDto) throws RazorpayException {
        String orderId = createPaymentLinkReqDto.getOrderId();
        return paymentService.getPaymentLink(orderId, "1000", "INR", "https://www.google.com");
    }

    @PostMapping("/webhook")
    public void handleWebhookEvent(@RequestBody Map<String, String> webhookEvent) {
        System.out.println(webhookEvent);
    }
}
