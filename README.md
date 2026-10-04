# 🍽️ Système de Gestion de Tables de Restaurant (V1)

> 🚀 **Projet 2 / 7 - Défi 7 Jours (7 Days Streak)**

Une application web "Full-Stack" légère et interactive permettant au personnel d'un restaurant de visualiser et de gérer l'état de leurs tables en temps réel. Ce projet adopte une approche de développement itérative, commençant par un Produit Minimum Viable (MVP) solide avant d'intégrer des fonctionnalités d'automatisation avancées.

## 🎯 Aperçu de la Version 1 (Actuelle)
Cette première version se concentre sur l'efficacité, la clarté visuelle et la robustesse de la base de données. 

**Fonctionnalités clés :**
- 🗺️ **Cartographie des zones :** Gestion de 5 zones distinctes (Aile Principale, Terrasse, Secondaire 1 & 2, 1er Étage) réparties intelligemment.
- 🚦 **Suivi en temps réel :** 3 statuts visuels clairs (Libre [Vert], Occupée [Rouge], Réservée [Orange]).
- 🖱️ **Interface Interactive :** Modification du statut des tables via une fenêtre modale moderne en un clic.
- 💾 **Persistance des données :** Sauvegarde instantanée des modifications dans une base de données cloud.

## 🛠️ Technologies Utilisées
Le projet a été construit sans frameworks lourds côté frontend pour consolider les bases algorithmiques et l'architecture REST :
- **Backend :** Java 21+, Javalin (Micro-framework web), JDBC
- **Base de données :** PostgreSQL hébergé sur Supabase
- **Frontend :** HTML5, CSS3 (Design moderne/Glassmorphism), Vanilla JavaScript (API Fetch)

## 🚀 Roadmap : Vers la Version 2 (V2)
La V1 pose des fondations solides pour accueillir l'automatisation métier. La V2 introduira :
- 📱 **Notifications Automatisées :** Intégration de bots (Telegram / WhatsApp API) pour envoyer des confirmations de réservation automatiques aux clients.
- ⏳ **Gestion Temporelle Intelligente :** Enregistrement de l'heure exacte des réservations. Le système changera dynamiquement la couleur d'une table (ex: passage à l'orange) à l'approche de l'heure de réservation.
- 📅 **Formulaire de réservation côté client :** Interface permettant aux clients de réserver directement en ligne.

## ⚙️ Installation et Lancement (Local)

1. **Base de données :**
   - Créez un projet sur Supabase avec une base PostgreSQL.
   - Exécutez le script SQL fourni dans le projet pour initialiser la table `tables_restaurant` et générer le plan de salle.

2. **Configuration Backend :**
   - Clonez le dépôt et ouvrez-le dans votre IDE (ex: IntelliJ IDEA).
   - Configurez vos identifiants Supabase dans la classe `DatabaseConnection.java`.
   - Assurez-vous que les dépendances Maven (`javalin`, `postgresql`, `jackson-databind`) sont bien téléchargées.

3. **Lancement :**
   - Exécutez la méthode `main` de la classe `Main.java`.
   - Le serveur hébergera automatiquement l'API et le frontend.
   - Ouvrez votre navigateur et accédez à : `http://localhost:8080/index.html`

---
*Développé dans le cadre d'un défi personnel de 7 jours de code.*
