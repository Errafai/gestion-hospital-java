# Backend hospital

Backend Spring Boot 

- Java 17
- Maven
- MySQL

## Installation


Configurer la base de données MySQL :
   - Créer une base de données nommée `hospital_db`.
   - Vérifier les identifiants dans `src/main/resources/application.properties` (défaut: root / password).
Compiler le projet :
	method 1 with intellij:
		maven-->hospital-backend-->Lifecycle-->clean(double clique) after build success double click install
		and run BackendApplication sur src\main\java\com\hospital
	method 2 with terminal:
  		mvn clean install
   		mvn spring-boot:run
	L'application sera accessible sur http://localhost:8080 
	puis aller à http://localhost:8080/swagger-ui/index.html pour documentation API
  

## Utilisateurs par défaut

Au premier démarrage, les utilisateurs suivants sont créés :
- **Admin**: admin / admin123
- **Médecin**: doctor1 / doctor123

## Structure du Projet

- `src/main/java/com/hospital` : Code source
  - `config` : Configurations (Security, Swagger, CORS)
  - `controller` : Points d'entrée de l'API REST
  - `dto` : Objets de transfert de données
  - `entity` : Entités JPA
  - `service` : Logique métier
  - `repository` : Accès aux données
  - `security` : Gestion JWT et authentification
  - `exception` : Gestion globale des erreurs

## Sécurité

L'API est sécurisée par JWT. Pour accéder aux endpoints protégés :
1. S'authentifier via `/api/auth/login`.
2. Utiliser le token reçu dans le header Authorization : `Bearer <token>`.
