Exercice 1 - Gestion de Stock

### Contexte
Développer une application de gestion de stock permettant de gérer les produits, les catégories et les mouvements de stock.

### Fonctionnalités attendues :
-  CRUD complet sur les produits
-  Gestion des catégories de produits
-  Suivi des quantités en stock
-  Historique des entrées/sorties
-  Relations : Produit ↔ Catégorie (Many-to-One)

### Entités principales :
```java
- Produit (id, nom, description, prix, quantité, catégorie)
- Catégorie (id, nom, description)
- MouvementStock (id, produit, date, type, quantité)





### Exercice 2 - Gestion de Projets

Création d une application de gestion de projets avec suivi des tâches et des ressources humaines.
### Fonctionnalités attendues :
 Gestion des projets et des phases
 Attribution des tâches aux employés
 Suivi de l'avancement des projets
 Gestion des compétences requises
 Relations : Projet ↔ Employé (Many-to-Many)
### Entités principales :
Projet (id, nom, description, dateDébut, dateFin, budget)
- Employé (id, nom, prénom, poste, salaire)
- Tâche (id, description, projet, assignéÀ, statut, priorité)
- Compétence (id, libellé, niveau)


Exercice 3 - Gestion de l'État Civil
 Contexte
Développer une application permettant de gérer les citoyens et leurs relations matrimoniales dans une province 

### Fonctionnalités attendues :
 Gestion des personnes (citoyens)
 Enregistrement des mariages et divorces
 Gestion des relations familiales
 Historique de l'état civil
 Relations complexes : Personne ↔ Personne (Mariage)
### Entités principales :
