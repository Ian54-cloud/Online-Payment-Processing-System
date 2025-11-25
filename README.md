# Online-Payment-Processing-System
A console-based payment system providing two payment methods: Credit Card and Blik, including input validation, custom exceptions, file logging, and polymorphic payment handling.

# Features
## Credit Card

Card number validation

CVV validation

Account holder validation

Expiry date input

Masked card formatting using symbol "*"

Writes payment details to Credit_Card.txt

## Blik

Generates a 6-digit Blik code

Writes Blik code to blik.txt

Requires user to type the code back

Supports 5 attempts

Stores transaction info in blik_receipt.txt

## File Outputs 

Credit_Card.txt- Credit card transactions

blik.txt- Generated Blik code

blik_receipt.txt - Blik transaction details

## Custom Exceptions

IncorretCardNumberException

IncorrectCVVException

EmptyAccountHolder

UnmatchedBlikCodeException

InvalidPinException


