<h1 align="center">
  Illegal Landfill Detection and Risk Assessment System
</h1>

<p align="center">
  A system that combines illegal landfill detection on orthophoto and satellite imagery using machine learning, with a knowledge-based system for assessing ecological risk and supporting remediation decision-making.
</p>

<div align="center">

![Java](https://img.shields.io/badge/Java-11-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.9-brightgreen)
![Drools](https://img.shields.io/badge/Drools-7.49.0-red)
![React](https://img.shields.io/badge/React-19-61DAFB)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-PostGIS-336791)
![status](https://img.shields.io/badge/Status-Thesis%20Project-yellow)

</div>

## About the Project

Illegal landfills are a serious environmental problem — detecting them through field inspections is slow and inefficient, and existing machine-learning-based solutions treat each detection in isolation, without any insight into the actual risk a landfill poses given its surrounding context.

This system combines two modules:

- **Detection and segmentation** — a YOLO11m model detects landfills in orthophoto/satellite imagery, complemented by the SAM model for approximate area estimation.
- **Knowledge-based system (KBS)** — using the Drools platform, the system assesses the risk level of each landfill based on geographic context (proximity to rivers, lakes, settlements, roads, schools, industrial zones), checks the prerequisites for remediation, and detects emerging landfill patterns in real time.

## Features

- Detection of illegal landfills from orthophoto imagery using the YOLO11m model

- Approximate landfill area estimation using SAM segmentation

- Estimation of waste volume, mass, and annual methane emissions (IPCC methodology)

- Landfill risk level assessment based on geographic context (forward rule chaining)

- Verification of prerequisites required for landfill remediation (backward rule chaining)

- Real-time notifications about newly detected landfill patterns (complex event processing — CEP)

- Interactive web map with marker clustering, detailed landfill overview, and notification history

## Technologies

**Detection/segmentation:** Python, Ultralytics YOLO11, Meta AI SAM

**Knowledge-based system:** Drools (forward/backward chaining, CEP, rule templates)

**Backend:** Java 11, Spring Boot 2.7.9, Spring Data JPA, WebSocket (STOMP)

**Database:** PostgreSQL + PostGIS

**Frontend:** React, Leaflet (react-leaflet, leaflet.markercluster), @stomp/stompjs

## Project Structure

```
📦 root
 ┣ 📂 model    — domain entities and Drools facts
 ┣ 📂 kjar     — knowledge base (DRL rules, templates, CEP)
 ┣ 📂 service  — Spring Boot REST/WebSocket service layer
 ┗ 📂 frontend — React application (map, info panel, CEP notifications)
```

## Running the Project

### Prerequisites

- Java 11
- Node.js
- PostgreSQL with the PostGIS extension
- Maven

### Backend

```bash
# From the root directory, build the modules in order: model -> kjar -> service
cd model && mvn clean install
cd ../kjar && mvn clean install
cd ../service && mvn spring-boot:run
```

Configure the database connection in `service/src/main/resources/application.properties` (or a `.env` file, since the project uses `spring-dotenv`).

### Frontend

```bash
cd frontend
npm install
npm start
```

The application will be available at `http://localhost:3000`, with the backend running at `http://localhost:8080`.

## Author

Andrija Slović — final thesis, Faculty of Technical Sciences, University of Novi Sad, Software Engineering and Information Technologies, 2026.

Mentor: Assist. Prof. Siniša Nikolić, PhD
