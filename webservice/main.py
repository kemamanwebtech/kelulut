
# Main Application for the webservice for Kelulut
# To run : FLASK_APP=main.py flask run
# Running on http://localhost:5000/


from flask import Flask, url_for, request, redirect
from werkzeug.utils import secure_filename
from kelulut_dao import dao

UPLOAD_FOLDER = '/image-features-matching-module/upload-images'
app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

ALLOWED_EXTENSIONS = set(['txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'])


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




@app.route('/uploadImage')
def upload_file():
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            flash('No file part')
            return redirect(request.url)
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            flash('No selected file')
            return redirect(request.url)
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))



if __name__ == '__main__':
    app.run()




# List of API
# URL_UPLOAD = "http://145.239.86.102:5000/upload_image";
# URL_GETLOCATION = "http://145.239.86.102:5000/get_location";
# URL_GET_IMAGE = "http://145.239.86.102:5000/get_image";
# URL_GET_COMMENT = "http://145.239.86.102:5000/get_comment";
# URL_ADD_COMMENT = "http://145.239.86.102:5000/add_comment";
# URL_GET_QUESTION = "http://145.239.86.102:5000/get_question";
# URL_SAVE_SCORE = "http://145.239.86.102:5000/save_score";