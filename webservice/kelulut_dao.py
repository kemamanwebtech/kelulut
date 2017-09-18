#! /usr/bin/python3
# --------------------------------#
"""


"""

import pymysql.cursors
import pymysql
pymysql.install_as_MySQLdb()
from pymysql import DatabaseError, IntegrityError

class dao():
    conn = None

    # Connect to the database. Can make this statis? can make conn public var?
    def getConnection(self):
        conn= pymysql.connect(
            host='145.239.86.102',
            user='kelulut',
            password='Enter_123',
            db='kelulut',
            charset='utf8mb4',
            cursorclass=pymysql.cursors.DictCursor)
        return conn

    def closeConnection(self):
        self.conn.close()

    # insert into database table kelulut_user & kelulut_login
    def registerUser(self, username, email, password, conn):
        try:
            with conn.cursor() as cursor:
                sql = "insert into kelulut_user (username, created) values (%s, now())"
                cursor.execute(sql, (username))
                conn.commit()

                newUserId = cursor.lastrowid
                sql = "insert into kelulut_login (user_id, email, passwd, created) values (%s, %s, %s, now())"
                cursor.execute(sql, (newUserId, email, password))
                conn.commit()
        except DatabaseError as e:
            return 'Error : ' + str(e)
        else:
            return 'Success'
        finally:
            conn.close()

    # login
    def login(self, email, password, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select * from kelulut_login where email = %s and passwd = %s"
                cursor.execute(sql, (email, password))
                result = cursor.fetchone()
                if (result):
                    return 'Successfully login'
                else:
                    return 'Invalid login'
        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()

    # get genus info
    def getGenusInfo(self, genusName, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select genus_name, des, image from genus where genus_name = %s "
                cursor.execute(sql, (genusName))
                row = cursor.fetchone()
                cursor.close()
                if row:
                    return str(row)
                else:
                    return "Empty table : " + cursor._executed
        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()

    # get species info
    def getSpeciesInfo(self, genusName, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select genus_id from genus where genus_name = %s "
                cursor.execute(sql, (genusName))
                row = cursor.fetchone()
                if not row:
                    return "Empty table : " + cursor._executed

                genus_id = row["genus_id"]
                sql = "select species_name from species where genus_id = %s "
                cursor.execute(sql, (genus_id))
                row = cursor.fetchall()
                if row:
                    return str(row)
                else:
                    return "Empty table : " + cursor._executed

        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()