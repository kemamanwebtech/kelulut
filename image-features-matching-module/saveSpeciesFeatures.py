#! /usr/bin/python3
#--------------------------------#
""" 
This script is written to perform analysis on the image of known kelulut species and save their features into a file for reference.
This script takes 2 arguments : The 1st one is for species_id and the 2nd for image full path relative to this script.
In image matching terms, the supplied images for these scripts are "trained images", that is, images for reference.
This script will apply ORB (Oriented FAST and Rotated BRIEF) feature extraction on the supplied image.
The output file name will be "species-<species_id>-features.txt
"""
import numpy as np
import sys
import cv2
import os
import pymysql.cursors
import pymysql
import pickle
import json

# pymysql.install_as_MySQLdb()

# required arguments
species_id = sys.argv[1]
image_location = sys.argv[2]


def pack_keypoint(keypoints, descriptors):
    kpts = np.array([[kp.pt[0], kp.pt[1], kp.size,
                  kp.angle, kp.response, kp.octave,
                  kp.class_id]
                 for kp in keypoints])
    desc = np.array(descriptors)
    return kpts, desc

def unpack_keypoint(array):
    try:
        kpts = array[:,:7]
        desc = array[:,7:]
        keypoints = [cv2.KeyPoint(x, y, _size, _angle, _response, int(_octave), int(_class_id))
                 for x, y, _size, _angle, _response, _octave, _class_id in list(kpts)]
        return keypoints, np.array(desc)
    except(IndexError):
        return np.array([]), np.array([])



def read_features_from_file(filename):
    """ Read feature properties and return in matrix form. """
    if os.path.getsize(filename) <= 0:
        return np.array([]), np.array([])
    f = np.load(filename)
    if f.size == 0:
        return np.array([]), np.array([])
    f = np.atleast_2d(f)
    return f[:,:7], f[:,7:] # feature locations, descriptors

def write_features_to_file(filename, locs, desc):
    np.savetxt(filename, np.hstack((locs,desc)))


def process_image(image_location, species_id):
    filename = "species-" + species_id + "-features.txt"

    img = cv2.imread(image_location, 0)
    # Initiate SIFT detector
    orb = cv2.ORB_create()

    # find the keypoints and descriptors with SIFT
    keypoints, descriptors = orb.detectAndCompute(img, None)

    keypoints, descriptors = pack_keypoint(keypoints, descriptors)

    write_features_to_file(filename, keypoints, descriptors)

process_image(image_location, species_id)


# # read the supplied image
# img = cv2.imread(image_location,0) 

# # Initiate SIFT detector
# orb = cv2.ORB_create()

# # find the keypoints and descriptors with SIFT
# keypoints, descriptors = orb.detectAndCompute(img, None)


# # save keypoints to a file
# index = []
# for point in keypoints:
#     temp = (point.pt, point.size, point.angle, point.response, point.octave, point.class_id)
# index.append(temp)
# f = open(species_id + "-" + "keypoints.txt", "wb")
# f.write(pickle.dumps(index))
# f.close()

# # save features to a file
# f = open(species_id + "-" + "features.txt", "wb")
# f.write(descriptors)
# f.close()



#print(keypoints)
#print(descriptors)


# keypoints = keypoints.tolist()
# keypoints = json.dump(keypoints)

# # descriptors = "descriptors.tostring()"
# descriptors = "descriptors.tostring()"

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
#         sql = "insert into species_features (species_id, keypoints, descriptors, image_location) values (%s, %s, %s, %s)"
#         cursor.execute(sql, (species_id, keypoints, descriptors, image_location))
#         result = cursor.fetchone()
#         print(result)
# finally:
#     connection.close()
