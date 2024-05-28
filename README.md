# PAO Project

## Introduction
This project is a digital banking application that simulates basic banking operations, including ATM management and client services. It is developed using Java and provides both user and administrator functionalities.

## Features
### Main Features
- **Bank Creation**: Initialize a new bank and configure the number of ATMs.
- **Client Management**: Add new clients and manage existing client accounts.
- **ATM Management**: Add and display ATMs.
- **Administrator Mode**: Access additional administrative functions such as viewing client details and managing ATMs.

### BankService Functionalities
- **createBank()**: Initializes the bank with a test client and basic plan.
- **addAtm()**: Adds a new ATM with user-defined location details.
- **readInt()**: Reads an integer input from the user with error handling.
- **showAtm()**: Displays the list of ATMs.
- **addClient()**: Adds a new client with options to open bank accounts and attach cards.
- **addAccount()**: Creates a bank account for clients
- **addCard()**: Allows the client to add a credit card for the bank account
- **connect()**: Allows existing clients to log in and perform various actions like creating accounts, making transactions, and querying balances.
- **sendmoney()**: Allows clients to send money to other clients
- **withdrawfromATM()**: Allows clients to withdraw money from an ATM
- **deposittoATM()**: Allows clients to deposit money to an ATM
- **generateStatement()**: Display statement generated from the client`s bank account 
- **displayClientDetails()**: Displays detailed information about a client by name.

### Audit 
- implemented an audit system that shows each action an the time it was made
