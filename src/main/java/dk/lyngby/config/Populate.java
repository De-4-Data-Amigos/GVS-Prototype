package dk.lyngby.config;


import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;

public class Populate {
    /*public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        ResellerDTO rDTO1 = new ResellerDTO(1, "Lyngby Plantecenter", "Firskovvej 18", "+45 33212334", new HashSet<>());
        ResellerDTO rDTO2 = new ResellerDTO(2, "Glostrup Planter", "Tværvej 35", "+45 32233232", new HashSet<>());
        ResellerDTO rDTO3 = new ResellerDTO(3, "Holbæk Planteskole", "Stenhusvej 49", "+45 59430945", new HashSet<>());


        PlantDTO pDTO1 = new PlantDTO(1, "Rose", "Albertine", 400, 199.5f,1);
        PlantDTO pDTO2 = new PlantDTO(2, "Bush", "Aronia", 200, 169.5f,1);
        PlantDTO pDTO3 = new PlantDTO(3, "FruitAndBerries", "AromaApple", 350, 399.5f,2);
        PlantDTO pDTO4 = new PlantDTO(4, "Rhododendron", "Astrid", 40, 269.5f,3);
        PlantDTO pDTO5 = new PlantDTO(5, "Rose", "TheDarkLady", 100, 199.5f,3);

        Plant p1 = new Plant(pDTO1);
        Plant p2 = new Plant(pDTO2);
        Plant p3 = new Plant(pDTO3);
        Plant p4 = new Plant(pDTO4);
        Plant p5 = new Plant(pDTO5);

        try (var em = emf.createEntityManager()) {
            Reseller r1 = new Reseller(rDTO1);
            Reseller r2 = new Reseller(rDTO2);
            Reseller r3 = new Reseller(rDTO3);
            r1.addPlant(p1);
            r1.addPlant(p2);
            r2.addPlant(p3);
            r3.addPlant(p4);
            r3.addPlant(p5);
            em.getTransaction().begin();
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.getTransaction().commit();
        }
    }*/
}
