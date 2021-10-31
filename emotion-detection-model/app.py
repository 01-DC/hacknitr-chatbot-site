
import numpy as np
import pickle
# text preprocessing
import re
from keras.models import load_model
from sklearn.metrics import accuracy_score, f1_score, confusion_matrix
# preparing input to our model
from keras.preprocessing.text import Tokenizer
from keras.preprocessing.sequence import pad_sequences

from flask import Flask, jsonify


app = Flask(__name__)

@app.route("/pred/<string:message>", methods=['GET', 'POST'])

def pred(message):
    class_names = ['joy', 'fear', 'anger', 'sadness', 'neutral']
    tokenizer=None
    with open('tokenizer.pickle', 'rb') as handle:
        tokenizer = pickle.load(handle)

    model = load_model('cnn_w2v.h5')
    seq = tokenizer.texts_to_sequences([message])
    padded = pad_sequences(seq, maxlen=500)
    pred = model.predict(padded)
    result= {
        "message": message,
        "emotion": class_names[np.argmax(pred)]
        }
    with app.app_context():
        jsonify(result)
    return result



if __name__=="__main__":
    app.run(host='0.0.0.0', port=5000)
