package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {
        try {
            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();

            System.out.println("=== TEST DE L'APPLICATION DE GESTION DE STOCK ===\n");

            // Création
            System.out.println("Création des catégories...");
            Categorie cat1 = new Categorie("CAT1", "PCs");
            Categorie cat2 = new Categorie("CAT2", "Téléphones");
            Categorie cat3 = new Categorie("CAT3", "Accessoires");

            categorieService.create(cat1)
            categorieService.create(cat2);
            categorieService.create(cat3);
            System.out.println("Catégories créées avec succès\n");

            // 2. Création des produits
            System.out.println("Création des produits:");
            Produit p1 = new Produit("ES12", 120.0f);
            p1.setCategorie(cat1);

            Produit p2 = new Produit("ZR85", 100.0f);
            p2.setCategorie(cat1);

            Produit p3 = new Produit("EE85", 200.0f);
            p3.setCategorie(cat2);

            Produit p4 = new Produit("AB56", 45.0f);
            p4.setCategorie(cat3);

            produitService.create(p1);
            produitService.create(p2);
            produitService.create(p3);
            produitService.create(p4);
            System.out.println("Produits créés avec succès\n");

            //Commande
            System.out.println("Création d'une commande:");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateCommande = sdf.parse("14/03/2013");

            Commande commande = new Commande(dateCommande);
            commandeService.create(commande);
            System.out.println("Commande créée avec succès\n");

            // Lignes de commande
            System.out.println("Création des lignes de commande :");
            LigneCommandeProduit lcp1 = new LigneCommandeProduit(7);
            lcp1.setProduit(p1);
            lcp1.setCommande(commande);

            LigneCommandeProduit lcp2 = new LigneCommandeProduit(14);
            lcp2.setProduit(p2);
            lcp2.setCommande(commande);

            LigneCommandeProduit lcp3 = new LigneCommandeProduit(5);
            lcp3.setProduit(p3);
            lcp3.setCommande(commande);

            ligneCommandeService.create(lcp1);
            ligneCommandeService.create(lcp2);
            ligneCommandeService.create(lcp3);
            System.out.println("Lignes de commande créées avec succès\n");

            // 5. TEST DES MÉTHODES DEMANDÉES

            System.out.println("=== TEST ===\n");

            System.out.println("TEST1: Produits par catégorie 'PCs'");
            produitService.afficherProduitsParCategorie("PCs");
            System.out.println();

            System.out.println("TEST2: Produits commandés entre le 01/03/2013 et 31/03/2013");
            Date dateDebut = sdf.parse("01/03/2013");
            Date dateFin = sdf.parse("28/04/2013");
            produitService.afficherProduitsCommandesEntreDeuxDates(dateDebut, dateFin);
            System.out.println();

            System.out.println("TEST3: Produits de la commande n°2");
            produitService.afficherProduitsCommande(2);
            System.out.println();

            // Produits avec prix > 100 DH
            System.out.println("TEST4: Produits avec prix supérieur à 100 DH");
            List<Produit> produitsChers = produitService.afficherProduitsPrixSuperieur100();
            System.out.printf("%-16s %-9s%n", "Référence", "Prix");
            for (Produit p : produitsChers) {
                System.out.printf("%-16s %-10.2f DH%n", p.getReference(), p.getPrix());
            }
            System.out.println();

            System.out.println("=== C'EST OK!! ===");

        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        } finally {
              HibernateUtil.shutdown();
        }
    }
}
