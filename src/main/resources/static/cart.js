$(document).ready(function () {
    console.log("HEJ HUGO")
    loadCartTable();
    loadOrderTotal();

    let greeting = localStorage.getItem("firstname") + " " + localStorage.getItem("lastname");
    $('.greeting').text(greeting);

    function loadCartTable() {

        let $cart = $("#cart-body");

        fetch("http://localhost:8080/cart")
            .then(response => response.json())
            .then(data => {
                let text = "";
                localStorage.setItem("cart",JSON.stringify(data));
                data.forEach(item => {
                    text += "<tr><td><img class='cart-img' src = 'images/" + item.record.imgURL + "'/></td>" +
                        "<td class='cart-text'>" + item.record.title + "</td>" +
                        "<td class='cart-text'>" + item.record.artist + "</td>" +
                        "<td class='cart-text'>" + item.quantity + "</td>" +
                        "<td class='cart-text'>" + item.totalPrice + "</td></tr>"
                    $cart.html(text);
                })
            });
    }


    $("#empty-btn").click(function () {
        fetch("http://localhost:8080/cart/empty");

        localStorage.setItem("cartCount",JSON.stringify(0));
        window.location.href = "shop.html";
    });

    $("#continue-btn").click(function () {
        window.location.href = "http://localhost:8080/shop.html";
    })

    $("#checkOut-btn").click(function () {
        if (parseInt(localStorage.getItem("cartCount"))>0) {
            fetch("http://localhost:8080/orders/create")
                .then(response => response.json())
                .then(data => localStorage.setItem("order", JSON.stringify(data)))
                .then(() => localStorage.setItem("cartCount", JSON.stringify(0)))
                .then(() => window.location.href = "checkOut.html");
        }
    });


    $("#logout-btn").click(function () {
        fetch("http://localhost:8080/users/logout")
            .then(response => response.json())
            .then(data => console.log(data))
            .then(() => window.location.href = "login.html");
    });

    function loadOrderTotal() {
        let displayTotal = $("#display-total");
        fetch("http://localhost:8080/users/current")
            .then(response => response.json())
            .then(data => {
                if (data.user.role === "PREMIUM")
                    document.getElementById("premium-text").style.visibility = "visible";
                else
                    document.getElementById("premium-text").style.visibility = "hidden";
                displayTotal.html("<b>TOTAL: " + data.total + "</b>");
            });
    }

});