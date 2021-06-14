var rec_id;
var send_id;
var pack_id;


function register() {
    addClientSender();
    addClientRecipient();
    addClientPackage();

    if(send_id===0){
        addClientSender();
    }else if(rec_id===0){
        addClientRecipient();
    }else if(pack_id===0){
        addClientPackage();
    }else if(send_id!==0&&rec_id!==0&&pack_id!==0) {
        registerInvoice();
        return 1;
    }
    setTimeout("", 100)
    register();
}

function registerInvoice() {


    // var package_id = document.getElementById("package-hidden").value;
    // var recipient_id = document.getElementById("recipient-hidden").value;
    // var sender_id = document.getElementById("sender-hidden").value;


    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:8080/package/regPack");
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.send(JSON.stringify({
        senderId: send_id,
        recipientId: rec_id,
        packageId: pack_id
    }));
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            // var sender_id=JSON.parse(this.responseText);
            // console.log(sender_id);
            successPage();
        }
    }
}

success:function successPage() {
    setTimeout(function () {
        alert("Done");
        window.location.href = "index.html";
    }, 500);

}


success: function addClientPackage() {
    var wight = document.getElementById("package-wight").value;
    var volume = document.getElementById("package-volume").value;
    var quantity = document.getElementById("package-quantity").value;
    var insurance = document.getElementById("package-insurance").value;
    var region = document.getElementById("sender-region").value;
    var city = document.getElementById("sender-city").value;
    var street = document.getElementById("sender-street").value;
    var postOfficeNumber = document.getElementById("sender-postOfficeNumber").value;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:8080/package/createPackage");
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.send(JSON.stringify({
        insurance: wight,
        quantity: volume,
        volume: quantity,
        weight: insurance,
        region: region,
        city: city,
        street: street,
        postOfficeNumber: postOfficeNumber
    }));


    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var sender_id = JSON.parse(this.responseText);
            console.log(sender_id);
            addValueToPackageHiddenInput(sender_id);
        }
    }

    // console.log(document.getElementById("sender-hidden").value);
}

success:function addValueToPackageHiddenInput(msg) {
    document.getElementById("package-hidden").value = JSON.parse(msg);
    pack_id = JSON.parse(msg);
    // alert(msg+" package");
}


success: function addClientSender() {
    var username = document.getElementById("sender-username").value;
    var middlename = document.getElementById("sender-middlename").value;
    var lastname = document.getElementById("sender-lastname").value;
    var phone = document.getElementById("sender-phone").value;
    var region = document.getElementById("sender-region").value;
    var city = document.getElementById("sender-city").value;
    var street = document.getElementById("sender-street").value;
    var postOfficeNumber = document.getElementById("sender-postOfficeNumber").value;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:8080/package/createClientSender");
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.send(JSON.stringify({
        firstname: username,
        middlename: middlename,
        lastname: lastname,
        phone: phone,
        region: region,
        city: city,
        street: street,
        postOfficeNumber: postOfficeNumber

    }));

    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var sender_id = JSON.parse(this.responseText);
            console.log(sender_id);
            addValueToSenderHiddenInput(sender_id);
        }
    }

    // console.log(document.getElementById("sender-hidden").value);
}

success:function addValueToSenderHiddenInput(msg) {
    document.getElementById("sender-hidden").value = JSON.parse(msg);
    send_id = JSON.parse(msg);
    // alert(msg+" sender");
}


success: function addClientRecipient() {
    var username = document.getElementById("resipient-username").value;
    var middlename = document.getElementById("resipient-middlename").value;
    var lastname = document.getElementById("resipient-lastname").value;
    var phone = document.getElementById("resipient-phone").value;
    var region = document.getElementById("resipient-region").value;
    var city = document.getElementById("resipient-city").value;
    var street = document.getElementById("resipient-street").value;
    var postOfficeNumber = document.getElementById("resipient-postOfficeNumber").value;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:8080/package/createClientRecipient");
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.send(JSON.stringify({
        firstname: username,
        middlename: middlename,
        lastname: lastname,
        phone: phone,
        region: region,
        city: city,
        street: street,
        postOfficeNumber: postOfficeNumber
    }));

    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var recipient_id = JSON.parse(this.responseText);
            console.log(recipient_id);
            addValueToRecipientHiddenInput(recipient_id);
        }
    }

    // console.log(document.getElementById("sender-hidden").value);
}

success:function addValueToRecipientHiddenInput(msg) {
    document.getElementById("recipient-hidden").value = JSON.parse(msg);
    rec_id = JSON.parse(msg);
    // alert(msg+" recipient");
}
