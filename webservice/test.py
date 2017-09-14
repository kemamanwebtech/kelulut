# public
# static
# String
# URL_LOGIN = "http://145.239.86.102:5000/login";
# public
# static
# String
# URL_REGISTER = "http://145.239.86.102:5000/register";
# public
# static
# String
# URL_GET_GENUS = "http://145.239.86.102:5000/get_genus_info";
# public
# static
# String
# URL_GET_GENUS_SPECIES = "http://145.239.86.102:5000/get_genus_species";
# public
# static
# String
# URL_UPLOAD = "http://145.239.86.102:5000/upload_image";
# public
# static
# String
# URL_GETLOCATION = "http://145.239.86.102:5000/get_location";
# public
# static
# String
# URL_GET_IMAGE = "http://145.239.86.102:5000/get_image";
# public
# static
# String
# URL_GET_COMMENT = "http://145.239.86.102:5000/get_comment";
# public
# static
# String
# URL_ADD_COMMENT = "http://145.239.86.102:5000/add_comment";
# public
# static
# String
# URL_GET_QUESTION = "http://145.239.86.102:5000/get_question";
# public
# static
# String
# URL_SAVE_SCORE = "http://145.239.86.102:5000/save_score";




from flask import Flask, url_for, request, redirect
from werkzeug.utils import secure_filename



app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
UPLOAD_FOLDER = '/path/to/the/uploads'
ALLOWED_EXTENSIONS = set(['txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'])


# example : call http://localhost:5000/?name=hedada

@app.route('/')
def api_root():
    if 'name' in request.args:
        return 'Hello ' + request.args['name']
    else:
        return 'Hello John Doe'

@app.route('/articles')
def api_articles():
    return 'List of ' + url_for('api_articles')

@app.route('/articles/<articleid>')
def api_article(articleid):
    return 'You are reading ' + articleid

from flask import Flask, request, redirect, url_for




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