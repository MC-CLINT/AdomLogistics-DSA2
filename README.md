
# Adom Logistics Dispatcher System

## Project Overview

### **Course Code and Title:** DCIT 308 - Data Structures and Algorithms II

The **Adom Logistics Dispatcher System** is a Java-based console application designed for managing logistics operations including:

* Vehicle registration and tracking
* Driver assignment and rotation
* Delivery tracking
* Maintenance scheduling
* Fuel efficiency analysis

This project models real-world dispatcher logic with a focus on data management and reporting, implemented using Java Collections, custom data structures, and file-based persistence.


## Features

* ✅ Add, update, list, and remove vehicles
* ✅ Assign drivers fairly using a queue-based rotation system
* ✅ Track deliveries with package details and assigned resources
* ✅ Schedule and manage maintenance records per vehicle
* ✅ Analyze vehicle fuel efficiency with custom reports



## Getting Started

### Prerequisites

* **Java JDK 17+**
* **VS Code with Java Extension Pack** (optional but recommended)

### Running the Project

1. **Clone the repository or download the ZIP**
2. **Ensure the following folder structure is maintained:**

   ```
   /AdomLogistics-GRP52
     ├── src/           # Source code (.java)
     ├── bin/           # Compiled class files
     ├── data/          # Data files (vehicles.txt, drivers.txt, deliveries.txt)
     ├── README.md
     └── report.md
   ```
3. **Compile the project using command-line or VS Code terminal:**

   ```
   javac -d bin src/**/*.java
   ```
4. **Run the application:**

   ```
   java -cp bin utils.Main
   ```



## Folder Structure

| Folder      | Description                                         |
| ----------- | --------------------------------------------------- |
| `src/`      | Source code packages                                |
| `bin/`      | Compiled `.class` files                             |
| `data/`     | Flat file storage for vehicles, drivers, deliveries |
| `report.md` | Project report and design analysis                  |
| `README.md` | Project instructions                                |



## Data Management

* All persistent data is stored in the **`/data/`** folder as plain text files.
* Files are read and written manually using Java IO classes.
* Ensure the **`data/`** folder exists before running the application.



## Authors — Adom Logistics Group 52

| Role                         | Name           |
| ---------------------------- | -------------- |
| Lead Architect               | Clinton        |
| Driver Module Developer      | Thelma         |
| Vehicle Module Developer     | Emmanuel       |
| Delivery Module Developer    | Frank          |
| Maintenance Module Developer | Dorinda        |
| Fuel Report Analyst          | Prince William |
| File I/O & CLI Designer      | Melissa        |



## Future Improvements

* Integration with a database (e.g., SQLite, MySQL)
* Graphical User Interface (GUI)
* REST API for remote access
* Enhanced reporting with analytics




