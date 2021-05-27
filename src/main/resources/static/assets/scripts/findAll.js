function showAllClients() {

    var tableState = '<tr>\n' +
        '        <th>Client id</th>\n' +
        '        <th>Client firstname</th>\n' +
        '        <th>Client middlename</th>\n' +
        '        <th>Client lastname</th>\n' +
        '        <th>Client phone</th>\n' +
        '        <th>Client region</th>\n' +
        '        <th>Client city</th>\n' +
        '        <th>Client street</th>\n' +
        '        <th>Client postOfficeNumber</th>\n' +
        '        <th>Actions</th>\n' +
        '    </tr>';

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var clients = JSON.parse(this.responseText);
            var html = tableState;
            for (var i = 0; i < clients.length; i++) {
                var client = clients[i];
                id = client.id;
                console.log(client);
                html = html +
                    '    <tr id="client-' + client.id + '"><td>' + client.id + '</td>\n' +
                    '        <td>' + client.firstname + '</td>\n' +
                    '        <td>' + client.middlename + '</td>\n' +
                    '        <td>' + client.lastname + '</td>' +
                    '        <td>' + client.phone + '</td>' +
                    '        <td>' + client.region + '</td>' +
                    '        <td>' + client.city + '</td>' +
                    '        <td>' + client.street + '</td>' +
                    '        <td>' + client.postOfficeNumber + '</td>' +
                    '        <td><button onclick="editClient(' + client.id + ')">Изменить</button>' +
                    '        <button onclick="removeClient(' + client.id + ')">Удалить</button></td></tr>';
            }
            document.getElementById("table-objects").innerHTML = html;
        }
    };
    xhttp.open("GET", "http://192.168.0.120:8080/package/findAllClients", true);
    xhttp.send();
}


function showAllPackages() {

    var tableState = '<tr>\n' +
        '        <th>Package id</th>\n' +
        '        <th>Package region</th>\n' +
        '        <th>Package city</th>\n' +
        '        <th>Package street</th>\n' +
        '        <th>Package postOfficeNumber</th>\n' +
        '        <th>Package insurance</th>\n' +
        '        <th>Package quantity</th>\n' +
        '        <th>Package status</th>\n' +
        '        <th>Package volume</th>\n' +
        '        <th>Package weight</th>\n' +
        '        <th>Package fromDatetime</th>\n' +
        '        <th>Package toDatetime</th>\n' +
        '        <th>Actions</th>\n' +
        '    </tr>';

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var packages = JSON.parse(this.responseText);
            var html = tableState;
            for (var i = 0; i < packages.length; i++) {
                var pack = packages[i];
                id = pack.id;
                console.log(pack);
                html = html +
                    '    <tr id="package-' + pack.id + '" ><td>' + pack.id + '</td>\n' +
                    '        <td>' + pack.region + '</td>\n' +
                    '        <td>' + pack.city + '</td>\n' +
                    '        <td>' + pack.street + '</td>' +
                    '        <td>' + pack.postOfficeNumber + '</td>' +
                    '        <td>' + pack.insurance + '</td>' +
                    '        <td>' + pack.quantity + '</td>' +
                    '        <td>' + pack.status + '</td>' +
                    '        <td>' + pack.volume + '</td>' +
                    '        <td>' + pack.weight + '</td>' +
                    '        <td>' + pack.fromDatetime + '</td>' +
                    '        <td>' + pack.toDatetime + '</td>';
                if (pack.toDatetime === null) {
                    html = html +
                        '<td><button onclick="closePackage(' + pack.id + ')">Зкрыть накладную</button>' +
                        '        <button onclick="reverseInvoice(' + pack.id + ')">Отправить обратно</button></td></tr>';
                }
            }


            document.getElementById("table-objects").innerHTML = html;
        }
    };
    xhttp.open("GET", "http://192.168.0.120:8080/package/findAllPackages", true);
    xhttp.send();
}


function showAllOperators() {

    var tableState = '<tr>\n' +
        '        <th>Operator id</th>\n' +
        '        <th>Operator firstname</th>\n' +
        '        <th>Operator middlename</th>\n' +
        '        <th>Operator lastname</th>\n' +
        '        <th>Operator stationNumber</th>\n' +
        '        <th>Actions</th>\n' +
        '    </tr>';

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var operators = JSON.parse(this.responseText);
            var html = tableState;
            for (var i = 0; i < operators.length; i++) {
                var operator = operators[i];
                id = operator.id;
                console.log(operator);
                html = html +
                    '    <tr><td>' + operator.id + '</td>\n' +
                    '        <td>' + operator.firstname + '</td>\n' +
                    '        <td>' + operator.middlename + '</td>\n' +
                    '        <td>' + operator.lastname + '</td>' +
                    '        <td>' + operator.stationNumber + '</td>' +
                    '        <td><button onclick="editOperator(' + operator.id + ')">Изменить</button>' +
                    '        <button onclick="removeOperator(' + operator.id + ')">Удалить</button></td></tr>';
            }
            document.getElementById("table-objects").innerHTML = html;
        }
    };
    xhttp.open("GET", "http://192.168.0.120:8080/package/findAllOperators", true);
    xhttp.send();
}

function changeTable() {
    var selectBox = document.getElementById("show-any");
    var value = selectBox.options[selectBox.selectedIndex].value;
    if (value === "operators") {
        showAllOperators();
    } else if (value === "clients") {
        showAllClients();
    } else
        showAllPackages();
}



