class Barber extends Thread {
    private final int id;
    private final BarberShop shop;
    private boolean sleeping;

    public Barber(int id, BarberShop shop) {
        this.id = id;
        this.shop = shop;
        this.sleeping = true;
    }

    public void run() {
        while (true) {
            synchronized (shop) {
                sleeping = true;
                try {
                    shop.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            var customer = shop.getNextCustomer();
            if (customer != null) {
                sleeping = false;
                System.out.println("\nBarbeiro " + id + " está [cortando] o cabelo do Cliente " + customer.getId() +
                        ". Tempo do corte: " + customer.getHaircutTime() + " segundos.");
                try {
                    Thread.sleep(customer.getHaircutTime() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    shop.customerServiced();
                    shop.releaseScissors();
                    shop.releaseComb();
                    System.out.println("\nBarbeiro " + id + " [terminou] de cortar o cabelo do Cliente " + customer.getId());
                    shop.printState();
                }
            }
        }
    }

    public synchronized boolean isSleeping() {
        return sleeping;
    }

    public synchronized void wakeUp() {
        sleeping = false;
        synchronized (shop) {
            shop.notify();
        }
    }

}