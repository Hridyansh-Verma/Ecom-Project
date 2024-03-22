1 ) User -> OrderService: order is created
2 ) User -> PaymentService:
    PaymentService -> OrderService : Get Details like amount
    Create Payment Link using third party Payment gateway like RazorPay,Stripe
    Send back a payment link with call back
3 ) User -> PaymentLink
    make the payment
    user gets redirected to call back url
4 ) CallBack -> PaymentService
    Check the status of Payment
5 ) PaymentGateway -> PaymentService
    webhookUrl

PaymentService
1 ) createPaymentLink
2 ) getPaymentStatus
3 ) handleWebhookEvent