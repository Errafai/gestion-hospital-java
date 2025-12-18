-- Script de création des bases de données pour le pôle hospitalier

-- 1. Base de données pour le service d'authentification
CREATE DATABASE IF NOT EXISTS auth_db;
USE auth_db;
-- Les tables seront générées automatiquement par Hibernate (update)

-- 2. Base de données pour le service patients
CREATE DATABASE IF NOT EXISTS patient_db;
USE patient_db;
-- Les tables seront générées automatiquement par Hibernate

-- 3. Base de données pour le service rendez-vous
CREATE DATABASE IF NOT EXISTS rendezvous_db;
USE rendezvous_db;
-- Les tables seront générées automatiquement par Hibernate

-- 4. Base de données pour le service dossier médical
CREATE DATABASE IF NOT EXISTS dossier_db;
USE dossier_db;
-- Les tables seront générées automatiquement par Hibernate

-- Vérification des bases créées
SHOW DATABASES LIKE '%_db';
