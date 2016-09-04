from flask import Flask, request

app = Flask(__name__)

@app.route("/data", methods = ("GET",))
def handle_data():
    convostr =''
    for i in chat:
        convostr+= i+ "\n"
    return convostr

@app.route("/meow", methods = ("POST",))
def handle_meow():
    spoken = request.values
    chat.append(spoken['name'] + " : " + spoken['message'])
    return "nonbiasedchat"

if __name__=='__main__':
    chat = ['dont be biased *airhorn*']
    app.run(host='0.0.0.0')
