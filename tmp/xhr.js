console.log("is this thing on?")
var msgData

function funkyFunc(x) {
    // console.log(x)
    msgData = JSON.parse(x.target.responseText)
    console.log("Messages:", msgData)

    // for loop to fill DOM
    var slackContent = ""
    for (var i = 0; i < msgData.messages.length; i++) {
        slackContent += `
      <div>
         <h5>Date: ${msgData.messages[i].date}</h5>
         <h3>Sender: ${msgData.messages[i].sender}</h3>
         <p>Sender: ${msgData.messages[i].content}</p>
         <hr>
      </div>
      ` 
        // assign stuff to #slack
        document.getElementById("slack").innerHTML = slackContent
    }
}

var request_1 = new XMLHttpRequest()
request_1.addEventListener("load", funkyFunc)
request_1.open("GET", "messages.json")
request_1.send()
