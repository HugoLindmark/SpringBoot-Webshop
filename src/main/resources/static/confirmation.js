$(document).ready(function () {

    loadOrderConf();
    loadOrderTotal();

    let greeting = localStorage.getItem("firstname") + " " + localStorage.getItem("lastname");
    $('.greeting').text(greeting);


   function loadOrderConf() {

        let data  = JSON.parse(localStorage.getItem("cart"));
        console.log(data);
        console.log(data.length);

        let text = "";

        data.forEach(item => {
            text += "<tr><td><img class='cart-img' src = 'images/" + item.record.imgURL + "'/></td>" +
                "<td class='cart-text'>" + item.record.title + "</td>" +
                "<td class='cart-text'>" + item.record.artist + "</td>" +
                "<td class='cart-text'>" + item.quantity + "</td>" +
                "<td class='cart-text'>" + item.totalPrice + "</td></tr>"
            $('#confirm-table').html(text);
        })
    };


    $("#continue-btn").click(function () {
        window.location.href = "http://localhost:8080/shop.html";
    });



    $("#logout-btn").click(function () {
        fetch("http://localhost:8080/users/logout")
            .then(response => response.json())
            .then(data => console.log(data))
            .then(() => window.location.href = "login.html");
    });

    function loadOrderTotal() {
        let displayTotal = $("#checkout-total");

        let order = JSON.parse(localStorage.getItem("order"));
        console.log(order);

                displayTotal.html("<b>TOTAL: " + order.formattedOrderTotal + "</b>");

    }

});