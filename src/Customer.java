import java.util.Random;

class Customer extends Thread {
    private static int nextId = 1;
    private final int id;
    private final BarberShop shop;
    private final int haircutTime;

    public Customer(BarberShop shop) {
        this.id = nextId++;
        this.shop = shop;
        this.haircutTime = new Random().nextInt(4) + 3;
    }

    public void run() {
        synchronized (shop) {
            shop.addCustomer(this);
        }
    }


    public long getId() {
        return id;
    }

    public int getHaircutTime() {
        return haircutTime;
    }
}   