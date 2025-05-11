# Pet Shop Application

**Supervised Programming Project (APS) for University**

## Overview

This Java-based Pet Shop Application is a supervised programming project developed for a university course (APS). It follows the **Model-View-Controller (MVC)** and **Data Access Object (DAO)** patterns, providing basic functionality to manage clients and their pets.

- **Frontend**: Swing-based UI (`PetShopApp.java`)
- **Backend**: Java with JDBC for MySQL (Laragon)
- **Build & Dependency Management**: Maven (`pom.xml`)
- **Database**: MySQL (Laragon), initial schema and data in `resources/database.sql`

## Key Features

- **Client & Pet Management**: Create, read, update, and delete clients and their pets.
- **MVC & DAO Architecture**: Clean separation between UI, business logic, and data access.
- **MySQL Integration**: Uses JDBC to connect to a MySQL database (Laragon).
- **Data Validation**: Input validation for names, CPF, and numeric fields.
- **Error Handling**: Informative UI messages and logging for troubleshooting.

## Project Structure

```
.
├── .gitattributes
├── .gitignore
├── LICENSE
├── pom.xml
├── README.md
├── resources
│   └── database.sql          # SQL script to create schema and initial data
└── src
    └── main
        └── java
            └── com.petshop
                ├── Main.java         # Application entry point
                ├── controller
                │   ├── ClientController.java
                │   └── PetController.java
                ├── dao
                │   ├── ClientDAO.java
                │   └── PetDAO.java
                ├── model
                │   ├── Client.java
                │   └── Pet.java
                └── view
                    └── PetShopApp.java
```

## Installation

Begin by cloning the repository:

```sh
git clone https://github.com/jvictorfsilva/Petshop-MVC.git
cd Petshop-MVC
```

### Prerequisites

- Java JDK 8 or higher
- Maven 3.x
- MySQL server (Laragon recommended)
- IntelliJ IDEA or Eclipse IDE

### Database Setup

1. Start Laragon and ensure MySQL is running.
2. Open the Laragon MySQL console or use a GUI (e.g., HeidiSQL, MySQL Workbench).
3. Execute the SQL script located at `resources/database.sql` to create the `petshop_db` database, tables, and initial data:

   ```sh
   mysql -u root -p < resources/database.sql
   ```

4. Verify that the database `petshop_db` has been created with `clients` and `pets` tables.

## Importing and Running in IntelliJ IDEA

1. **Clone the repository** to your local machine.
2. **Open IntelliJ IDEA**:
   - Choose **File > New > Project from Existing Sources**.
   - Select the project directory and click **OK**.
3. **Import Maven Project**:
   - IntelliJ will detect `pom.xml`. Click **Import** when prompted.
   - Wait for Maven to download dependencies.
4. **Configure Run Configuration**:
   - Go to **Run > Edit Configurations**.
   - Click **+ > Application**.
   - Set **Main class** to `com.petshop.Main`.
   - Ensure **Use classpath and JDK of module** is set to the project module.
5. **Run the Application**:
   - Click the **Run** button or press **Shift+F10**.
   - The Pet Shop UI window will appear.

## Importing and Running in Eclipse

1. **Clone the repository** to your local machine.
2. **Open Eclipse**:
   - Choose **File > Import... > Maven > Existing Maven Projects**.
   - Select the project root directory and click **Finish**.
3. **Verify Dependencies**:
   - Eclipse will read `pom.xml` and download required dependencies.
4. **Run the Application**:
   - Right-click `Main.java` in `src/main/java/com/petshop`.
   - Select **Run As > Java Application**.
   - The Pet Shop UI window will appear.

## Project Usage

In the UI, you can:

- **Add Client**: Register new clients with name, CPF, and phone.
- **List Clients**: View all clients.
- **Search by Name/CPF**: Find clients by name or CPF.
- **Remove Client**: Delete clients by ID.
- **Add Pet**: Register a pet under an existing client.
- **List Pets**: View all pets.
- **Remove Pet**: Delete pets by ID.

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
