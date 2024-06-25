import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BarberShop {
    private final int numBarbers;
    private final int numChairs;
    private final List<Customer> waitingCustomers;
    private int numCustomersServiced;
    private int numCustomersTurnedAway;
    private final Barber[] barbers;
    private int numScissors;
    private int numCombs;
    private final Object lock = new Object();

    public BarberShop(int numBarbers, int numChairs) {
        this.numBarbers = numBarbers;
        this.numChairs = numChairs;
        this.waitingCustomers = new ArrayList<>();
        this.numCustomersServiced = 0;
        this.numCustomersTurnedAway = 0;
        this.barbers = new Barber[numBarbers];
        this.numScissors = numBarbers / 2;
        this.numCombs = numBarbers / 2;
        
        startBarbers(numBarbers);
    }

    private void startBarbers(int numBarbers) {
        for (int i = 0; i < numBarbers; i++) {
            barbers[i] = new Barber(i + 1, this);
            barbers[i].start();
        }
    }
    public boolean isChairAvailable() {
        synchronized (lock) {
            return waitingCustomers.size() < numChairs;
        }
    }

    public boolean isScissorsAvailable() {
        synchronized (lock) {
            return numScissors > 0;
        }
    }

    public boolean isCombAvailable() {
        synchronized (lock) {
            return numCombs > 0;
        }
    }

    public void addCustomer(Customer customer) {
        synchronized (lock) {
            if (isChairAvailable()) {
                waitingCustomers.add(customer);
                System.out.println("\nCliente " + customer.getId() + " [chegou] na barbearia.");
                notifyBarber();
                printState();
            } else {
                numCustomersTurnedAway++;
                System.out.println("\nCliente " + customer.getId() + " [desistiu] de cortar o cabelo, pois não há cadeiras disponíveis na sala de espera.");
                printState();
            }
        }
    }

    private void notifyBarber() {
        synchronized (lock) {
            for (var barber : barbers) {
                if (barber.isSleeping()) {
                    barber.wakeUp();
                    break;
                }
            }

            if (isScissorsAvailable() && isCombAvailable()) {
                useScissors();
                useComb();
            }
        }
    }



    public Customer getNextCustomer() {
        synchronized (lock) {
            if (!waitingCustomers.isEmpty()) {
                return waitingCustomers.remove(0);
            }
            return null;
        }
    }

    public void customerServiced() {
        synchronized (lock) {
            numCustomersServiced++;
        }
    }


    public void useScissors() {
        synchronized (lock) {
            if (numScissors > 0) {
                numScissors--;
            }
        }
    }

    public void useComb() {
        synchronized (lock) {
            if (numCombs > 0) {
                numCombs--;
            }
        }
    }

    public void releaseScissors() {
        synchronized (lock) {
            numScissors++;
        }
    }

    public void releaseComb() {
        synchronized (lock) {
            numCombs++;
        }
    }

    void printState() {
        synchronized (lock) {
            int numBarbersAwake = (int) Arrays.stream(barbers).filter(barber -> !barber.isSleeping()).count();

            System.out.println("\nEstado da barbearia:");
            System.out.println("===============================================");
            System.out.println("Num Tesouras: " + numScissors);
            System.out.println("Num Pentes: " + numCombs);
            System.out.println("Num Clientes Atendidos: " + numCustomersServiced);
            System.out.println("Num Clientes Desistiram: " + numCustomersTurnedAway);
            System.out.println("Num Barbeiros Dormindo: " + (numBarbers - numBarbersAwake));
            System.out.println("Num Clientes na Fila: " + waitingCustomers.size());
            for (var cliente : waitingCustomers) {
                System.out.println("Cliente " + cliente.getId());
            }
            System.out.println("===============================================");
        }
    }
}