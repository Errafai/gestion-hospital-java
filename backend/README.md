## 🏥 Hospital Management System - Architecture Microservices

Système de gestion hospitalière développé avec **Spring Boot 3.x** et **Architecture Microservices**.

### 📋 Description

Backend REST API en architecture microservices pour un système de gestion hospitalière complet incluant :
- Gestion des patients
- Gestion des médecins
- Gestion des rendez-vous
- Gestion des dossiers médicaux
- Gestion des consultations
- Authentification JWT avec rôles (Admin, Médecin, Réceptionniste)

## 🏗️ Architecture Microservices

Le système est composé de **6 services** :

1. **Eureka Server** (Port 8761) - Service Discovery
2. **API Gateway** (Port 8080) - Point d'entrée unique
3. **Auth Service** (Port 8081) - Authentification et gestion des utilisateurs
4. **Patient Service** (Port 8082) - Gestion des patients
5. **Rendez-vous Service** (Port 8083) - Gestion des rendez-vous et médecins
6. **Dossier Service** (Port 8084) - Gestion des dossiers médicaux et consultations

## 🛠️ Technologies

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Cloud** (Eureka, Gateway)
- **Spring Security** (JWT Authentication)
- **Spring Data JPA**
- **MySQL 8.x**
- **Maven**
- **Docker**

## 📁 Structure du Projet

```
hospital-microservices/
├── pom.xml                          # Parent POM
├── docker-compose.yml               # Orchestration Docker
├── eureka-server/                   # Service Discovery
├── api-gateway/                     # API Gateway
├── auth-service/                    # Service d'authentification
├── patient-service/                 # Service Patients
├── rendez-vous-service/             # Service Rendez-vous
└── dossier-service/                 # Service Dossiers
```

## 🚀 Installation et Démarrage

### Prérequis

- Java 17+
- Maven 3.6+
- MySQL 8.x
- Docker (optionnel)

### Configuration

1. **Créer les bases de données MySQL :**
```sql
CREATE DATABASE auth_db;
CREATE DATABASE patient_db;
CREATE DATABASE rendezvous_db;
CREATE DATABASE dossier_db;
```

2. **Configurer les paramètres** dans chaque `application.yml` si nécessaire

### Démarrage des Services

**Ordre de démarrage recommandé :**

1. **Eureka Server** (d'abord)
```bash
cd eureka-server
mvn spring-boot:run
```

2. **Auth Service**
```bash
cd auth-service
mvn spring-boot:run
```

3. **Patient Service**
```bash
cd patient-service
mvn spring-boot:run
```

4. **Rendez-vous Service**
```bash
cd rendez-vous-service
mvn spring-boot:run
```

5. **Dossier Service**
```bash
cd dossier-service
mvn spring-boot:run
```

6. **API Gateway** (en dernier)
```bash
cd api-gateway
mvn spring-boot:run
```

### Démarrage avec Docker

```bash
docker-compose up -d
```

## 📡 API Endpoints (via API Gateway)

Tous les endpoints sont accessibles via l'API Gateway sur le port **8080** :

### Authentification
- `POST /api/auth/register` - Inscription
- `POST /api/auth/login` - Connexion

### Patients
- `GET /api/patients` - Liste des patients
- `GET /api/patients/{id}` - Détails d'un patient
- `GET /api/patients/search?q={query}` - Recherche
- `POST /api/patients` - Créer un patient
- `PUT /api/patients/{id}` - Modifier un patient
- `DELETE /api/patients/{id}` - Supprimer un patient

### Rendez-vous
- `GET /api/rendez-vous` - Liste des rendez-vous
- `GET /api/rendez-vous/patient/{patientId}` - Par patient
- `GET /api/rendez-vous/medecin/{medecinId}` - Par médecin
- `POST /api/rendez-vous` - Créer un rendez-vous
- `PUT /api/rendez-vous/{id}` - Modifier un rendez-vous

### Dossiers Médicaux
- `GET /api/dossiers/{id}` - Détails d'un dossier
- `GET /api/dossiers/patient/{patientId}` - Par patient
- `GET /api/dossiers/{id}/consultations` - Consultations
- `POST /api/dossiers` - Créer un dossier

### Consultations
- `GET /api/consultations/{id}` - Détails d'une consultation
- `POST /api/consultations` - Créer une consultation
- `PUT /api/consultations/{id}` - Modifier une consultation

### 🔐 Sécurité

- Authentification JWT via Auth Service
- Rôles : ADMIN, MEDECIN, RECEPTIONNISTE
- API Gateway route les requêtes vers les services appropriés

### 🗄️ Bases de Données

Chaque service a sa propre base de données (pattern *Database per Service*) :
- `auth_db` : table `users` (comptes applicatifs et rôles)
- `patient_db` : table `patients` (informations patients)
- `rendezvous_db` : tables `medecins`, `rendez_vous`
- `dossier_db` : tables `dossiers_medicaux`, `consultations`, `prescriptions`, `documents`

### 🧩 Modèle de données & relations (ERD logique)

> Remarque : chaque microservice a sa propre base, donc l’ERD ci‑dessous est **logique/fonctionnel** (les relations entre services se font par IDs, pas par de vraies foreign keys SQL entre bases).

- **Utilisateurs & rôles (`auth_db`)**
  - `users (id, username, email, password, nom, prenom, role, actif, created_at, updated_at)`
  - `role ∈ {ADMIN, MEDECIN, RECEPTIONNISTE}`

- **Patients (`patient_db`)**
  - `patients (id, numero_patient, nom, prenom, date_naissance, sexe, cin, telephone, email, adresse, ville, groupe_sanguin, allergies, created_at, updated_at)`

- **Médecins & Rendez-vous (`rendezvous_db`)**
  - `medecins (id, user_id, numero_ordre, specialite, telephone, disponible, created_at)`  
    - `medecins.user_id` → référence logique vers `users.id` (dans `auth_db`) avec `role = MEDECIN`.
  - `rendez_vous (id, patient_id, medecin_id, date_rdv, heure_debut, heure_fin, motif, statut, notes, created_at, updated_at)`  
    - `rendez_vous.patient_id` → référence logique vers `patients.id` (dans `patient_db`)  
    - `rendez_vous.medecin_id` → référence vers `medecins.id` (même base `rendezvous_db`)

- **Dossiers médicaux & consultations (`dossier_db`)**
  - `dossiers_medicaux (id, patient_id, numero_dossier, date_creation, antecedents_medicaux, antecedents_chirurgicaux, antecedents_familiaux, updated_at)`  
    - `dossiers_medicaux.patient_id` → référence logique vers `patients.id` (dans `patient_db`)
  - `consultations (id, dossier_medical_id, medecin_id, rendez_vous_id, date_consultation, symptomes, diagnostic, traitement, observations, created_at)`  
    - `consultations.dossier_medical_id` → FK vers `dossiers_medicaux.id` (même base `dossier_db`)  
    - `consultations.medecin_id` → référence logique vers `medecins.id` (dans `rendezvous_db`)  
    - `consultations.rendez_vous_id` → référence logique vers `rendez_vous.id` (dans `rendezvous_db`)
  - `prescriptions (id, consultation_id, ... )`  
    - `prescriptions.consultation_id` → FK vers `consultations.id`
  - `documents (id, dossier_medical_id, ... )`  
    - `documents.dossier_medical_id` → FK vers `dossiers_medicaux.id`

#### ERD logique (vue d’ensemble)

```text
auth_db.users (1) ──< (N) rendezvous_db.medecins

patient_db.patients (1) ──< (N) rendezvous_db.rendez_vous
patient_db.patients (1) ──< (1) dossier_db.dossiers_medicaux

rendezvous_db.medecins (1) ──< (N) rendezvous_db.rendez_vous
rendezvous_db.medecins (1) ──< (N) dossier_db.consultations

rendezvous_db.rendez_vous (1) ──< (0..1) dossier_db.consultations  (via rendez_vous_id)

dossier_db.dossiers_medicaux (1) ──< (N) dossier_db.consultations
dossier_db.dossiers_medicaux (1) ──< (N) dossier_db.documents

dossier_db.consultations (1) ──< (N) dossier_db.prescriptions
```

Cette représentation peut servir de base pour dessiner un vrai **diagramme ERD** (Draw.io, dbdiagram.io, Lucidchart…) dans le rapport.

### 🔍 Service Discovery

Accéder à Eureka Dashboard : **http://localhost:8761**

Vous verrez tous les services enregistrés.

### 📝 Notes Importantes

- **Tous les endpoints (sauf /auth/**) nécessitent un token JWT**
- **Format du header :** `Authorization: Bearer VOTRE_TOKEN`
- **Le token expire après 24 heures**
- **Les services communiquent via Eureka Service Discovery**

### 🐳 Docker

```bash
# Build et Run tous les services
docker-compose up -d

# Voir les logs
docker-compose logs -f

# Arrêter tous les services
docker-compose down
```

### 👥 Auteurs

ENSA Safi - Java Avancée - 4ème Année

### 📄 Licence

Ce projet est développé dans le cadre d'un projet académique.
