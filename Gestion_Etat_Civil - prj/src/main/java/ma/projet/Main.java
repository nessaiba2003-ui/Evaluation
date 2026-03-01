package ma.projet;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Format pour les dates
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Initialisation des services
            FemmeService femmeService = new FemmeService();
            HommeService hommeService = new HommeService();
            MariageService mariageService = new MariageService();

            System.out.println("=== CRÉATION DES DONNÉES DE TEST ===\n");

            // Création de 10 femmes
            Femme f1 = new Femme("RANIA", "Salma", "0601010101", "Casablanca", sdf.parse("15/05/1985"));
            Femme f2 = new Femme("LINA", "Jalila", "0602020202", "Rabat", sdf.parse("20/08/1990"));
            Femme f3 = new Femme("ALIMA", "Wafa", "0603030303", "Fès", sdf.parse("10/03/1988"));
            Femme f4 = new Femme("ALAWI", "Hanae", "0604040404", "Marrakech", sdf.parse("05/12/1982"));
            Femme f5 = new Femme("BENANI", "Nihad", "0605050505", "Tanger", sdf.parse("25/07/1992"));
            Femme f6 = new Femme("IDRISI", "Safia", "0606060606", "Agadir", sdf.parse("30/01/1987"));
            Femme f7 = new Femme("PADUKONE", "Leila", "0607070707", "Oujda", sdf.parse("12/09/1991"));
            Femme f8 = new Femme("ILHAMI", "Fatima", "0608080808", "Meknès", sdf.parse("18/04/1989"));
            Femme f9 = new Femme("FARAJI", "Zineb", "0609090909", "Tétouan", sdf.parse("22/11/1993"));
            Femme f10 = new Femme("QADIRI", "Hiba", "0610101010", "Safi", sdf.parse("08/06/1986"));

            femmeService.create(f1);
            femmeService.create(f2);
            femmeService.create(f3);
            femmeService.create(f4);
            femmeService.create(f5);
            femmeService.create(f6);
            femmeService.create(f7);
            femmeService.create(f8);
            femmeService.create(f9);
            femmeService.create(f10);

            System.out.println("Femmes crées avec succès!");

            Homme h1 = new Homme("LATIF", "Mohamed", "0711121300", "Tanger", sdf.parse("10/02/1989"));
            Homme h2 = new Homme("MAHMOUD", "Saad", "0702222202", "Laayoun", sdf.parse("25/06/1990"));
            Homme h3 = new Homme("BENJALOUN", "Khalid", "0702020303", "Fès", sdf.parse("14/11/1976"));
            Homme h4 = new Homme("BOURQUIA", "Kamal", "0704040774", "Casablanca", sdf.parse("30/03/1978"));
            Homme h5 = new Homme("NAJI", "Hassan", "0705555505", "Marrakech", sdf.parse("05/09/1991"));

            hommeService.create(h1);
            hommeService.create(h2);
            hommeService.create(h3);
            hommeService.create(h4);
            hommeService.create(h5);

            System.out.println(" Les hommes sont créés avec succès:\n");

            // Création des mariages
            System.out.println(" CRÉATION DES MARIAGES :\n");

            Mariage m1 = new Mariage(sdf.parse("04/09/1990"), null, 4, h1, f1);
            Mariage m2 = new Mariage(sdf.parse("09/09/1999"), null, 2, h1, f2);
            Mariage m3 = new Mariage(sdf.parse("04/11/2002"), null, 3, h1, f3);
            Mariage m4 = new Mariage(sdf.parse("03/09/1980"), sdf.parse("03/09/1990"), 0, h1, f4);

            Mariage m5 = new Mariage(sdf.parse("16/04/2012"), null, 2, h2, f5);
            Mariage m6 = new Mariage(sdf.parse("22/02/2014"), null, 1, h2, f6);

            Mariage m7 = new Mariage(sdf.parse("10/05/2012"), null, 3, h3, f7);

            // Mariages pour h4 - 2 mariages (1 en cours, 1 échoué)
            Mariage m8 = new Mariage(sdf.parse("22/08/2008"), sdf.parse("15/03/2015"), 2, h4, f8);
            Mariage m9 = new Mariage(sdf.parse("20/06/2016"), null, 1, h4, f9);

            // Mariages pour h5 - 1 mariage
            Mariage m10 = new Mariage(sdf.parse("05/12/2018"), null, 0, h5, f10);

            mariageService.create(m1);
            mariageService.create(m2);
            mariageService.create(m3);
            mariageService.create(m4);
            mariageService.create(m5);
            mariageService.create(m6);
            mariageService.create(m7);
            mariageService.create(m8);
            mariageService.create(m9);
            mariageService.create(m10);

            System.out.println(" Les mariages sont créés\n");

            // TESTS

            System.out.println("1. AFFICHER LA LISTE DES FEMMES");
            List<Femme> femmes = femmeService.findAll();
            for (Femme f : femmes) {
                System.out.println("- " + f.getNom() + " " + f.getPrenom() +
                        " (née le " + f.getDateNaissance() + ")");
            }

            System.out.println("2. AFFICHER LA FEMME LA PLUS ÂGÉE");
            Femme plusAgee = femmeService.getFemmeLaPlusAgee();
            if (plusAgee != null) {
                System.out.println("La femme la plus âgée est: " + plusAgee.getNom() + " " +
                        plusAgee.getPrenom() + " née le " + plusAgee.getDateNaissance());
            }

            System.out.println("3. AFFICHER LES ÉPOUSES D'UN HOMME ENTRE DEUX DATES");
            Date date1 = sdf.parse("01/01/1990");
            Date date2 = sdf.parse("31/12/2000");
            hommeService.afficherEpousesEntreDates(h1, date1, date2);

            System.out.println("\n========================================");
            System.out.println("4. NOMBRE D'ENFANTS D'UNE FEMME ENTRE DEUX DATES");
            System.out.println("========================================");
            int nbEnfants = femmeService.getNombreEnfantsEntreDates(f1.getId(),
                    sdf.parse("01/01/1990"), sdf.parse("31/12/2020"));
            System.out.println("Nombre d'enfants de " + f1.getNom() + " " + f1.getPrenom() +
                    " entre 1990 et 2020: " + nbEnfants);

            System.out.println("5. FEMMES MARIÉES DEUX FOIS OU PLUS");
            List femmesMariees2fois = femmeService.getFemmesMarieesDeuxFois();
            for (Object f : femmesMariees2fois) {
                System.out.println("- " + f.getClass() + " " + f.getPrenom() +
                        " (nombre de mariages: " + f.getClass().size() + ")");
            }

            System.out.println("6. HOMMES MARIÉS À QUATRE FEMMES ENTRE DEUX DATES");
            int nbHommes4femmes = hommeService.compterHommesMarieds4FemmesEntreDates(
                    sdf.parse("01/01/1980"), sdf.parse("31/12/2025"));
            System.out.println("Nombre d'hommes mariés à 4 femmes entre 1980 et 2025: " + nbHommes4femmes);

            System.out.println("7. AFFICHER LES MARIAGES D'UN HOMME AVEC DÉTAILS");
            hommeService.afficherMariagesAvecDetails(h1);

            System.out.println("=== TOUS LES TESTS SONT TERMINÉS ===");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture de la SessionFactory
            HibernateUtil.shutDown();
        }
    }
}
