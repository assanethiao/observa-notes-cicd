# ObservaNotes — Application Java avec CI/CD et supervision

**Projet de portfolio DevOps, Cloud et développement logiciel**

> Projet de bout en bout : développement d'une application Java avec Spring Boot, tests automatisés, conteneurisation Docker, intégration et déploiement continus, puis supervision avec Prometheus et Grafana.

## Présentation

ObservaNotes est une application de gestion des étudiants et de leurs résultats scolaires. Elle expose ses fonctionnalités sous la forme d'une API REST développée en Java avec Spring Boot.

Le projet vise à mettre en pratique une chaîne complète de développement et d'exploitation logicielle (DevOps), depuis le versionnement du code jusqu'au déploiement et à la supervision de l'application.

**Statut :** en cours de réalisation.

## Objectifs

- Développer une API REST avec Java et Spring Boot.
- Gérer les étudiants et leurs notes.
- Calculer les moyennes et consulter les résultats.
- Persister les données dans PostgreSQL.
- Automatiser les tests avec JUnit 5 et Mockito.
- Contrôler la qualité et la couverture du code.
- Conteneuriser l'application avec Docker.
- Orchestrer les services avec Docker Compose.
- Automatiser les workflows avec GitHub Actions.
- Publier les images Docker sur Docker Hub.
- Déployer l'application dans un environnement Ubuntu.
- Collecter les métriques avec Prometheus.
- Visualiser les indicateurs avec Grafana.
- Surveiller le système et les conteneurs avec Node Exporter et cAdvisor.

## Architecture cible

```text
Développeur
    |
    v
Git / GitHub
    |
    v
GitHub Actions
    |
    +--> Compilation Java
    |
    +--> Vérification de la qualité
    |
    +--> Tests automatisés
    |
    +--> Construction de l'image Docker
    |
    +--> Publication sur Docker Hub
    |
    v
Déploiement Ubuntu
    |
    v
Docker Compose
    |
    +--> Application Java / Spring Boot
    |
    +--> PostgreSQL
    |
    +--> Prometheus
    |
    +--> Grafana
    |
    +--> Node Exporter
    |
    +--> cAdvisor
```

## Technologies

| Domaine | Technologies |
|---|---|
| Langage | Java 25 |
| Framework | Spring Boot |
| API | Spring Web, API REST |
| Persistance | Spring Data JPA, PostgreSQL |
| Tests | JUnit 5, Mockito |
| Qualité et couverture | Checkstyle, JaCoCo |
| Gestion de projet Java | Maven |
| Versionnement | Git, GitHub |
| CI/CD | GitHub Actions |
| Conteneurisation | Docker, Docker Compose |
| Registre | Docker Hub |
| Déploiement | Ubuntu, self-hosted runner |
| Supervision | Prometheus |
| Visualisation | Grafana |
| Monitoring | Node Exporter, cAdvisor |

## Fonctionnalités prévues

- Créer, consulter, modifier et supprimer des étudiants.
- Ajouter et consulter les notes.
- Calculer les moyennes des étudiants.
- Consulter les résultats à travers l'API REST.
- Vérifier l'état de santé de l'application.
- Exposer les métriques nécessaires à Prometheus.
- Visualiser les indicateurs dans Grafana.

## API REST

Les routes seront définies et documentées pendant le développement. Elles couvriront notamment les opérations CRUD sur les étudiants et les notes, ainsi que le calcul des moyennes.

## Pipeline CI/CD

Le pipeline devra automatiser :

1. La récupération du code source.
2. La compilation de l'application Java avec Maven.
3. Les contrôles de qualité.
4. L'exécution des tests automatisés.
5. La construction de l'image Docker.
6. La publication de l'image sur Docker Hub.
7. Le déploiement automatisé après validation.

## Supervision

Prometheus collectera les métriques de l'application et de l'infrastructure.

Grafana permettra notamment de visualiser :

- Le nombre de requêtes HTTP.
- Le taux d'erreurs.
- Les temps de réponse de l'API.
- L'utilisation CPU et mémoire.
- Les ressources consommées par les conteneurs.
- La disponibilité des services.

Node Exporter fournira les métriques système et cAdvisor les métriques des conteneurs.

## Environnement de déploiement

Le premier environnement de démonstration reposera sur Ubuntu dans WSL, Docker et Docker Compose. GitHub Actions assurera la CI et un self-hosted runner exécutera le déploiement dans cet environnement.

Une extension vers un VPS Ubuntu pourra ensuite permettre de démontrer un déploiement sur un serveur distant.

## Documentation et démonstration

Cette section sera complétée au fil du projet avec :

- Les instructions d'installation et de lancement.
- Les exemples d'utilisation de l'API.
- Les résultats des tests automatisés.
- Les captures des workflows GitHub Actions.
- Les étapes de publication et de déploiement Docker.
- Les dashboards Grafana.
- Les résultats de la supervision.

## Auteur

**Assane Thiao**  
Élève-ingénieur en Télécommunications et Réseaux  
Sup Galilée – Paris 13

[Portfolio](https://assanethiao.github.io) · [LinkedIn](https://www.linkedin.com/in/assane-thiao-54a159278)

---

*Projet réalisé dans le cadre d'un apprentissage pratique du développement Java, du DevOps, de la CI/CD, de la conteneurisation et de l'observabilité.*