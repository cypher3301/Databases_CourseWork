function closePackage(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {


            setTimeout(function () {

            }, 200);
            showAllPackages();
        }
    }
    xhttp.open("GET", "http://192.168.0.120:8080/package/closeInvoicePackage?id=" + id, true);
    xhttp.send();
}


function editClient(id) {
    var allId = "client-" + id;


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var client = JSON.parse(this.responseText);
            id = client.id;
            console.log(client);
            document.getElementById(allId).innerHTML =
                '    <tr id="client-' + client.id + '">' +
                '        <td>' + client.id + '</td>\n' +
                '        <td><input id="firstname-' + id + '" type="text" value="' + client.firstname + '"></td>\n' +
                '        <td><input id="middlename-' + id + '" type="text" value="' + client.middlename + '"></td>\n' +
                '        <td><input id="lastname-' + id + '" type="text" value="' + client.lastname + '"></td>' +
                '        <td><input id="phone-' + id + '" type="tel" value="' + client.phone + '"></td>' +
                '        <td><input id="region-' + id + '" type="text" value="' + client.region + '"></td>' +
                '        <td><input id="city-' + id + '" type="text" value="' + client.city + '"></td>' +
                '        <td><input id="street-' + id + '" type="text" value="' + client.street + '"></td>' +
                '        <td><input id="postOfficeNumber-' + id + '" type="number" value="' + client.postOfficeNumber + '"></td>' +
                '        <td><button onclick="getClientOnEdit(' + client.id + ')">Применить</button>' +
                '        <button onclick="showAllClients()">Отмена</button></td></tr>';
        }
        document.getElementById("table-objects").innerHTML = html;
    };
    xhttp.open("GET", "http://192.168.0.120:8080/package/findClientById?id=" + id, true);
    xhttp.send();

}

success: function done() {
    setTimeout(function () {
        alert("Done");
        showAllClients();
    }, 100)
}

function getClientOnEdit(id) {

    var username = document.getElementById("firstname-" + id).value;
    var middlename = document.getElementById("middlename-" + id).value;
    var lastname = document.getElementById("lastname-" + id).value;
    var phone = document.getElementById("phone-" + id).value;
    var region = document.getElementById("region-" + id).value;
    var city = document.getElementById("city-" + id).value;
    var street = document.getElementById("street-" + id).value;
    var postOfficeNumber = document.getElementById("postOfficeNumber-" + id).value;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://192.168.0.120:8080/package/createClientRecipient");
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.send(JSON.stringify({
        id: id,
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
            console.log(id);
            done();
        }
    }
}

