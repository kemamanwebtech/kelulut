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

    # Connect to the database, return the connection object
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

    # insert user info into table kelulut_user & kelulut_login
    def registerUser(self, username, email, password, conn):
        try:
            with conn.cursor() as cursor:
                sql = "insert into kelulut_user (username, created) values (%s, now())"
                cursor.execute(sql, (username))
                conn.commit()

                newUserId = cursor.lastrowid
                sql = "insert into kelulut_login (user_id, name, passwd, created) values (%s, %s, %s, now())"
                cursor.execute(sql, (newUserId, email, password))
                conn.commit()
        except DatabaseError as e:
            return 'Error : ' + str(e)
        else:
            return 'Success'
        finally:
            conn.close()

    # perform login
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
                sql = "select genus_name, des, image from genus where genus_name like %s "
                cursor.execute(sql, ("%" + genusName + "%"))
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
                sql = "select genus_id from genus where genus_name like %s "
                cursor.execute(sql, ("%" + genusName + "%"))
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

    # get all questions
    def getQuestions(self, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select * from quiz "
                cursor.execute(sql)
                row = cursor.fetchall()
                cursor.close()
                if row:
                    return str(row)
                else:
                    return "Empty table : " + cursor._executed
        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()

    # get comments based on given image id
    def getComments(self, image_id, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select * from comments where image_id = %s "
                cursor.execute(sql, (image_id))
                row = cursor.fetchall()
                if row:
                    return str(row)
                else:
                    return "Empty table : " + cursor._executed

        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()


    # insert new comments into table comments
    def addComments(self, image_id, user_id, comment, conn):
        try:
            with conn.cursor() as cursor:
                sql = "insert into comments (image_id, user_id, comment, date_submitted) values (%s, %s, %s, now())"
                cursor.execute(sql, (image_id, user_id, comment))
                conn.commit()
        except DatabaseError as e:
            return 'Error : ' + str(e)
        else:
            return 'Success'
        finally:
            conn.close()

    # insert new score into table score
    def saveScore(self, user_id, score, conn):
        try:
            with conn.cursor() as cursor:
                sql = "insert into score (user_id, score, date_submitted) values (%s, %s, now())"
                cursor.execute(sql, (user_id, score))
                conn.commit()
        except DatabaseError as e:
            return 'Error : ' + str(e)
        else:
            return 'Success'
        finally:
            conn.close()

    # get last saved location based on given user id
    def getLocation(self, user_id, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select location from kelulut_user where user_id = %s "
                cursor.execute(sql, (user_id))
                row = cursor.fetchone()
                if row:
                    return str(row)
                else:
                    return "Empty table : " + cursor._executed

        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()

    # insert new location into table kelulut_user
    def saveLocation(self, user_id, location, conn):
        try:
            with conn.cursor() as cursor:
                sql = "insert into kelulut_user (user_id, location) values (%s, %s)"
                cursor.execute(sql, (user_id, location))
                conn.commit()
        except DatabaseError as e:
            return 'Error : ' + str(e)
        else:
            return 'Success'
        finally:
            conn.close()

    # get new image_id
    def getNewImageId(self, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select image_id from uploaded_images order by image_id desc"
                cursor.execute(sql)
                row = cursor.fetchone()
                if row:
                    return row
                else:
                    return 0

        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()

    # get image info based on given image_id
    def getImage(self, image_id, conn):
        try:
            with conn.cursor() as cursor:
                sql = "select image_loc, image_des, username from uploaded_images a" \
                      " left join kelulut_user b on (user_id) where user_id = %s "
                cursor.execute(sql)
                row = cursor.fetchone()
                if row:
                    return row
                else:
                    return 0
        except DatabaseError as e:
            return 'Error' + str(e)
        finally:
            conn.close()

    # insert new image info into table uploaded-images
    def saveImages(self, user_id, image_des, image_loc, location,  conn):
        try:
            with conn.cursor() as cursor:
                sql = "insert into uploaded_images (user_id, image_des, image_loc, location, is_analyzed, date_uploaded) values (%s, %s, %s, %s, 0, now()) "
                cursor.execute(sql, (user_id, image_des, image_loc, location))
                conn.commit()
        except DatabaseError as e:
            return 'Error : ' + str(e)
        else:
            return 'Success'
        finally:
            conn.close()
