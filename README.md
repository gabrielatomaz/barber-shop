# 💈 Barber Shop Multi-Thread Scheduler

Welcome to the Barber Shop Multi-Thread Scheduler! This Java project simulates the classic "Barber Shop Problem" using multi-threading. Created for the Operating Systems class at UFPel, this project demonstrates synchronization and thread management.

## ✨ Features

- 🧵 Multi-threaded simulation of barber and customer interactions
- 🪑 Configurable number of seats in the waiting area
- ⏳ Randomized customer arrival times

## 🛠️ Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/barber-shop-scheduler.git
   cd barber-shop-scheduler
2. **Clone the repository:**
   ```bash
   javac src/com/barbershop/*.java
3. **Clone the repository:**
   ```bash
   java -cp src com.barbershop.BarberShop

## 📂 Project Structure
- **Main.java**: Entry point to start the simulation.
- **BarberShop.java**: Initializes and manages the barber and customers.
- **Barber.java**: Represents the barber's thread.
- **Customer.java**: Represents a customer's thread.

 ## 📚 How It Works
The Barber Shop Multi-Thread Scheduler simulates a barber shop with one barber and multiple customers. Here's a detailed breakdown:

1. Barber:
- The barber thread (Barber.java) starts and waits for customers.
- If a customer is available, the barber serves the customer.
- If no customers are waiting, the barber goes to sleep until a new customer arrives.

2. Customer:
- The customer threads (Customer.java) simulate customers arriving at the shop.
- Upon arrival, each customer checks if there are available seats in the waiting room.
- If a seat is available, the customer sits and waits for the barber.
- If no seats are available, the customer leaves the shop.

3. Waiting Room:
- Managed by the BarberShop.java class, the waiting room has a limited number of seats.
- Customers sit in the waiting room until the barber is ready to serve them.
- The waiting room ensures that the barber serves customers in a first-come, first-served manner.

5. Synchronization:
- Proper synchronization mechanisms (like wait() and notify()) are used to ensure that the barber and customers interact correctly without race conditions.
- The barber sleeps when no customers are present and is notified when a new customer arrives. 

## 👤 Author
 - Name: Gabriela Ribeiro
 - University: Universidade Federal de Pelotas (UFPel)
 - Course: Operating Systems
