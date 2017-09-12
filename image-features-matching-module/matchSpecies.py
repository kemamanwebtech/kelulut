#! /usr/bin/python3
#--------------------------------#
""" 
loop throu stored images
perform analysis
save data to db

"""
import numpy as np
import sys
import cv2
import os
import glob
from fileMatcher import process_image

# required arguments
input_image = sys.argv[1]
process_id = sys.argv[2]


stored_features_path = "./known_species/*.jpg"

result = [[]]
for filename in glob.glob(stored_features_path):
    score, output = process_image(input_image, filename, process_id)
    name = filename.split("-")
    species_id = name[0]


    result.append([score, output, species_id])

pos = 0
i = 0
max = 0
print(result)
for row in result:
    index = result.index(row)
    if row:
        print(str(index) + "- " + str((result[index])[0]))
        if int((result[index])[0]) > max:
            max = int((result[index])[0])
            pos = result.index(row)

score = str(max)
species_id = str(result[pos][0])
output_image = result[pos][1]

print("Found best match with score : " + score)
print("Species ID : " + species_id)
print("Output Image : " + output_image)

# # Connect to the database
# connection = pymysql.connect(host='localhost',
#                              user='root',
#                              password='Enter_123',
#                              db='kelulut',
#                              charset='utf8mb4',
#                              cursorclass=pymysql.cursors.DictCursor)
# # insert into database
# try:
#     with connection.cursor() as cursor:
#         sql = "insert into species_features (process_id, input_image, score, species_id, output_image) values (%s, %s, %s, %s, %s)"
#         cursor.execute(sql, (process_id, input_image, score, species_id, output_image))
#         result = cursor.fetchone()
#         print(result)
# finally:
#     connection.close()
