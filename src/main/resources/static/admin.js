$(document).ready(function () {

    let orders = JSON.parse(localStorage.orders || "[]");
    let items = JSON.parse(localStorage.items || "[]");
    loadUserTable();
    loadOrderTable();
    loadItemTable();
    loadNames();


    function loadUserTable() {
        fetch("http://localhost:8080/users/customers")
            .then(response => response.json())
            .then(data => {
                let html = "";
                let customers = data;
                customers.forEach(customer => {
                    html += "<tr class='clickable-row-u' tabindex='0' id=" + customer.id + "><td>" + customer.userName + "</td>" +
                        "<td>" + customer.firstName + "</td>" +
                        "<td>" + customer.lastName + "</td>" +
                        "<td>" + customer.totalSpent + "</td>" +
                        "<td>" + customer.role + "</td>" +
                        "</tr>"
                })
                $(".tbody-user").html(html);
                $('.clickable-row-u').click(getOrderByUser);

            });
    }

    function getOrderByUser() {

        let id = $(this).attr("id");

        fetch("http://localhost:8080/orders/"+ id)
            .then(response => response.json())
            .then(data => localStorage.setItem('orders', JSON.stringify(data)))
            .then(() => window.location.replace("orderOverview.html"))
    }

    function loadOrderTable() {
        let html1 = "";
        orders.forEach(order => {
            html1 += "<tr class='clickable-row-o' tabindex='0' id=" + order.id + ">" +
                "<td>" + order.id + "</td>" +
                "<td>" + order.date + "</td>" +
                "<td>" + order.itemsQuantity + "</td>" +
                "<td>" + order.formattedOrderTotal + "</td>" +
                "</tr>"
        })
        $(".tbody-order").html(html1);

        $('.clickable-row-o').click(getItemsByOrder);
    }

    function getItemsByOrder() {

        let id = $(this).attr("id");

        fetch("http://localhost:8080/orderinfo/"+ id)
            .then(response => response.json())
            .then(data => localStorage.setItem('items', JSON.stringify(data)))
            .then(() => window.location.replace("itemOverview.html"))
    }


    function loadItemTable() {
        let html2 = "";
        items.forEach(item => {
            html2 += "<tr class='clickable-row-i' tabindex='0'>" +
                "<td>" + item.record.title + "</td>" +
                "<td>" + item.record.artist + "</td>" +
                "<td>" + item.formattedQuantity + "</td>" +
                "<td>" + item.totalPriceFormatted + "</td>" +
                "</tr>"
        })
        $(".tbody-item").html(html2)
    }


    $("#logout-btn").click(function () {
        fetch("http://localhost:8080/users/logout")
            .then(response => response.json())
            .then(data => console.log(data))
            .then(() => window.location.href = "login.html");
    })


    function loadNames() {
        let nameDisplay = $("#name-display");
        let firstname = localStorage.getItem("firstname");
        let lastname = localStorage.getItem("lastname");

        nameDisplay.text(firstname + " " + lastname);
    }
});