#! /usr/bin/python3
# --------------------------------#
"""
This script is written to perform analysis on two images :
    1. The supplied image of unknown Kelulut species and perform a comparison with
    1. The stored image of known Kelulut species
This script takes arguments : images path relative to this script.
This script will apply ORB (Oriented FAST and Rotated BRIEF) feature extraction on the supplied images
It then run a comparison and return a number of 'good feature matches': the higher the number, the images looks more alike
"""

import sys
import cv2
import numpy as np
from matplotlib import pyplot as plt

def process_image(image1, image2, process_id):
    print("Image 1 : " + image1)
    print("Image 2 : " + image2)
    img1 = cv2.imread(image1, cv2.IMREAD_COLOR)
    img2 = cv2.imread(image2, cv2.IMREAD_COLOR)

    # Initiate ORB detector
    # print("perform feature extractions...")
    orb = cv2.ORB_create()

    # find the keypoints and descriptors with ORB
    kp1, des1 = orb.detectAndCompute(img1, None)
    kp2, des2 = orb.detectAndCompute(img2, None)

    # perform feature matching
    # print("calculate matching criteria...")
    bf = cv2.BFMatcher()
    matches = bf.knnMatch(des1,des2, k=2)

    # Apply ratio test
    good = []
    # create a mask to draw good matches
    matchesMask = [[0,0] for i in range(len(matches))]
    for i,(m,n) in enumerate(matches):
        # use threshold 0.75 for 'good' feature matches
        if m.distance < 0.75*n.distance:
            # create an array of 'good' feature matches
            good.append(m)
            matchesMask[i] = [1, 0]

    # setup and create output image
    draw_params = dict(matchColor = (0,255,0),
                       singlePointColor = (255,0,0),
                       matchesMask = matchesMask,
                       flags = 0)
    img3 = cv2.drawMatchesKnn(img1,kp1,img2,kp2,matches,None,**draw_params)
    output_path = './output_image/result-' + process_id + '.png'
    # save output image
    cv2.imwrite(output_path, img3)

    # display output image. Disable this later
    plt.imshow(img3),plt.show()

    # print no of 'good' feature matches
    print(len(good))
    sys.stdout.flush()

    cv2.waitKey(1)
    cv2.destroyAllWindows()

    return len(good), output_path

# for testing test
# image1 = sys.argv[1]
# image2 = sys.argv[2]
# process_id = sys.argv[3]
#
# score, output_path = process_image(image1, image2, process_id)
# print("Score : " + str(score))
# print("Output image ; " + output_path)