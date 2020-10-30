import mysql.connector
import sys

try:
    db = mysql.connector.connect(host="localhost", user="root", passwd="12345")
except:
    sys.exit("Error connecting to the host. Please check your inputs.")

db_cursor = db.cursor()

try:
    db_cursor.execute(operation="CREATE DATABASE ftl_db")
except mysql.connector.DatabaseError:
    sys.exit("Error creating the database. Check if database already exists!")

db_cursor.execute("SHOW DATABASES")
databases = db_cursor.fetchall()
print(databases)

# Users Table
try:
    db_cursor.execute("CREATE TABLE users (id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, username VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(32) NOT NULL, registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
    print("users table created successfully.")
except mysql.connector.DatabaseError:
    sys.exit("Error creating the table. Please check if it already exists.")

describe_query = "DESCRIBE users"
db_cursor.execute(describe_query)
records = db_cursor.fetchall()
print(records)