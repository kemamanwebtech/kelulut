#! /usr/bin/python3
# --------------------------------#
"""


"""

import pymysql.cursors
import pymysql
pymysql.install_as_MySQLdb()

class dao():
    conn = None

    # Connect to the database. Can make this statis? can make conn public var?
    def getConnection(self):
        conn= pymysql.connect(
            host='localhost',
            user='root',
            password='Enter_123',
            db='kelulut',
            charset='utf8mb4',
            cursorclass=pymysql.cursors.DictCursor)
        return conn

    def closeConnection(self):
        self.conn.close()


    def registerUser(username, email, password, conn):
        # insert into database
        try:
            with conn.cursor() as cursor:
                sql = "insert into kelulut_user (username, email, password) values (%s, %s, %s)"
                cursor.execute(sql, (username, email, password))
                result = cursor.fetchone()
                print(result)
        finally:
            conn.close()


    def login(email, password, conn):
            # insert into database
        try:
            with conn.cursor() as cursor:
                sql = "select * from kelulut_pass where email = %s and password = %s"
                cursor.execute(sql, (email, password))
                result = cursor.fetchone()
                print(result)
        finally:
            conn.close()











    # insert into database
    try:
        with connection.cursor() as cursor:
            sql = "insert into species_features (process_id, input_image, score, species_id, output_image) values (%s, %s, %s, %s, %s)"
            cursor.execute(sql, (process_id, input_image, score, species_id, output_image))
            result = cursor.fetchone()
            print(result)
    finally:
        connection.close()