package dk.lyngby.config;



import dk.lyngby.model.Employee;
import dk.lyngby.model.Manager;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

public class Populate {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

            Manager m1 = getManager();

            try (var em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(m1);
                em.getTransaction().commit();
            }
            System.out.println("Data successfully populated.");
        } catch (Exception e) {
            System.err.println("Error populating data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @NotNull
    private static Manager getManager() {
        Employee emp1 = new Employee("Marco", "Pollo", "Employees", "dinlille@hot.dk", 54658545);
        Employee emp2 = new Employee("Rasi", "Ræv", "Employees", "jojo@gmail.com", 26655545);
        Employee emp3 = new Employee("Vici", "Katti", "Employees", "Katti@hot.com", 22556688);
        Employee emp4 = new Employee("Dano", "Bello", "Employees", "bello@gamil.com", 12457896);

        Manager m1 = new Manager("Mikkel", "Mikkelsen", "info@manager.dk", 12345678);

        m1.addEmployee(emp1);
        m1.addEmployee(emp2);
        m1.addEmployee(emp3);
        m1.addEmployee(emp4);
        return m1;
    }

}
