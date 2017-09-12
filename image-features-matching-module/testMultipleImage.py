import cv2
import numpy as np
import sys
import os
import glob

loc_known_species = "./known_species/*.png"

result = []
matches = ""
# Read test Images
orb = cv2.ORB_create()
test = cv2.imread('bee_test.jpg',0)
kp2,testDes2  = orb.detectAndCompute(test, None)

bf = cv2.BFMatcher(cv2.NORM_L1, crossCheck=False)  # crossCheck not supported by BFMatcher

for filename in glob.glob(loc_known_species):
    train = cv2.imread('filename', 0)

    # Find Descriptors
    kp1,trainDes1 = orb.detectAndCompute(train, None)

    # Create BFMatcher and add cluster of training images. One for now.
    clusters = np.array([trainDes1])
    bf.add(clusters)

    # Train: Does nothing for BruteForceMatcher though.
    bf.train()

    matches = bf.match(testDes2)
    matches = sorted(matches, key = lambda x:x.distance)

    # Since, we have index of only one training image,
    # all matches will have imgIdx set to 0.
    for i in range(len(matches)):
        print(matches[i].imgIdx)

    print(matches)