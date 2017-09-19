
# Main Application for the webservice for Kelulut
# To run : FLASK_APP=main.py flask run
# Running on http://localhost:5000/


from flask import Flask, url_for, request, redirect
from werkzeug.utils import secure_filename
from kelulut_dao import dao
import time
import os

UPLOAD_FOLDER = 'uploaded-images'
app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER



# example : call http://localhost:5000/?name=hedada
# example args
# @app.route('/')
# def api_root():
#     if 'name' in request.args:
#         return 'Hello ' + request.args['name']
#     else:
#         return 'Hello John Doe'


@app.route('/register')
def api_register():
    name = request.args['name']
    email = request.args['email']
    password = request.args['passwd']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.registerUser(email, name, password, conn)
    return result

@app.route('/login')
def api_login():
    email = request.args['email']
    password = request.args['passwd']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.login(email, password, conn)
    return result

@app.route('/get-genus-info')
def api_getGenusInfo():
    genus_name = request.args['genus_name']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.getGenusInfo(genus_name, conn)
    return result

@app.route('/get-species-info')
def api_getSpeciesInfo():
    genus_name = request.args['genus_name']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.getSpeciesInfo(genus_name, conn)
    return result

@app.route('/get-questions')
def api_getQuestions():
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.getQuestions(conn)
    return result

@app.route('/get-comments')
def api_getComments():
    image_id = request.args['image_id']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.getComments(image_id, conn)
    return result

@app.route('/add-comments')
def api_addComments():
    image_id = request.args['image_id']
    user_id = request.args['user_id']
    comment = request.args['comment']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.addComments(image_id, user_id, comment, conn)
    return result

@app.route('/save-score')
def api_saveScore():
    user_id = request.args['user_id']
    score = request.args['score']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.saveScore(user_id, score, conn)
    return result

@app.route('/get-location')
def api_getLocation():
    user_id = request.args['user_id']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.getLocation(user_id, conn)
    return result

@app.route('/save-location')
def api_saveLocation():
    user_id = request.args['user_id']
    location = request.args['location']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.saveLocation(user_id, location, conn)
    return result

@app.route('/get-image')
def api_getImages():
    image_id = request.args['image_id']
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    result = kelulutDao.getImage(image_id, conn)
    return result


# upload image onto a server file system
@app.route('/upload-image')
def uploadImage():
    # get new image_id
    kelulutDao = dao()
    conn = kelulutDao.getConnection()
    image_id = kelulutDao.getNewImageId(conn)
    image_id = image_id + 1

    # save image
    timestr = time.strftime("%Y%m%d-%H%M")
    file = request.files['image_data']
    user_id = request.args['user_id']
    image_des = request.args['image_des']
    location = request.args['location']
    filename = str(image_id) + "-" + user_id + "-" + timestr + ".png"
    file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
    image_loc = "uploaded-images/" + filename
    result = kelulutDao.saveImages(user_id, image_des, image_loc, location)
    # TOOD perform analysis & update table uploaded_images here
    return result

@app.route('/shutdown', methods=['POST', 'GET'])
def shutdown():
    shutdown_server()
    return 'Server shutting down...'

if __name__ == '__main__':
    app.run()



def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()