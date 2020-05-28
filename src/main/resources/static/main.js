$(document).ready(function () {


    $('#login-btn').click(function () {
        let url = 'http://localhost:8080/users/login?username=' + $('#username-input').val() + '&password=' + $('#password-input').val();
        $.getJSON(url, function (data) {
            console.log(data);
            if (data.verified) {
                localStorage.setItem("firstname", data.user.firstName);
                localStorage.setItem("lastname", data.user.lastName);
                localStorage.setItem("role", data.user.role);
                localStorage.setItem("cartCount",JSON.stringify(0));
                if (data.user.role === 'ADMIN') {
                    window.location.href = 'userOverview.html'
                } else {
                    window.location.href = 'shop.html'
                }
            } else {
                document.getElementById("wrong-label").style.visibility = "visible";
                $('#password-input').val("");
            }
        });

    });

    cartVisibility();

    //Logged in user
    let greeting = localStorage.getItem("firstname") + " " + localStorage.getItem("lastname");

    if (localStorage.getItem("role")==="PREMIUM") {
      greeting += "- Premium Customer"
    }
    $('.greeting').text(greeting);

    //Regbtn listener
    $('#registerBtn').click(function () {
        let role = "CUSTOMER";
        let username = $('#username-input');
        let firstName = $('#firstname-input');
        let lastName = $('#lastname-input');
        let password = $('#password-input');

        let User = {
            role: role,
            userName: username.val(),
            firstName: firstName.val(),
            lastName: lastName.val(),
            password: password.val(),
            totalSpent: "0 SEK"
        };

        let user1 = User;

        let response;
            if (username.val() !== "" || firstname.val() !== "" ||
            lastname.val() !== "" || password.val() !== ""){

                response = $.ajax({
                    type: "POST",
                    data: JSON.stringify(user1),
                    url: "http://localhost:8080/users/register/?password=" + password.val(),
                    contentType: "application/json",
                    success: function (data) {

                        let label = document.getElementById("reg-wrong-label");
                        if (data.userName === null) {
                            label.innerText = "The username: " + username.val() + " is taken, try again!";
                            label.style.visibility = "visible";
                            username.val("");
                        } else {
                            label.innerText = "Registration successful!";
                            label.style.visibility = "visible";
                            username.val("");
                        }
                    }
                });
            }

        response.success;

        firstName.val("");
        lastName.val("");
        password.val("");


    });


    $("#logout-btn").click(function () {
        fetch("http://localhost:8080/users/logout")
            .then(response => response.json())
            .then(data => console.log(data))
            .then(() => localStorage.clear())
            .then(() => window.location.href = "login.html");
    });

    $("#loginBtn").click(function () {
        window.location.href = "login.html";
    });

    $('#cart-btn').click(function () {
        window.location.href = "cart.html";
    });

    $('.cart-qty').text(localStorage.getItem("cartCount"));

    $('#register-btn').click(function () {
        window.location.href = 'register.html'
    });

function cartVisibility() {
    let count = parseInt(localStorage.getItem("cartCount"));
    if (count > 0) {
        $('.cart-qty').show();
    } else {
        $('.cart-qty').hide();
    }
}




//present all records
    function presentAllRecords() {
        $.getJSON("http://localhost:8080/records",
            function (data) {
                $.each(data, function (index, object) {
                    addProduct(object);
                    $('#record-link-' + object.id).click(function () {
                        storeCurrentRecord(object);
                        window.location.href = 'productinfo.html';
                    });
                });
            });
    }

    presentAllRecords();

//store currentRecord in local storage
    function storeCurrentRecord(record) {
        localStorage.setItem('record', JSON.stringify(record));
    }

//return currentRecord from local storage
    function getStoredCurrentRecord() {
        return JSON.parse(localStorage.getItem('record'));
    }

//searchFunction
    $('#search-link').click(function () {
        let searchInput = $('.searchInput').val();
        if (searchInput === "") {
            presentAllRecords();
        } else {
            let url = "http://localhost:8080/records/search/" + $('.searchInput').val();
            console.log(url);
            $.getJSON(url,
                function (data) {
                    clearProducts();
                    $.each(data, function (index, object) {
                        addProduct(object);
                        $('#record-link-' + object.id).click(function () {
                            storeCurrentRecord(object);
                            window.location.href = 'productinfo.html';
                        });
                    });
                });
        }
    });

    function clearProducts() {
        $('.box').html("");
    }

    function addProduct(record) {
        $('.box').append(`<a class="record-link" id="record-link-${record.id}">
            <div class="record" id="${record.id}">
            <img class="record-img-top" src="images/${record.imgURL}" alt="Card image cap"/>
            <div class="record-body">
            <h5 class="record-artist">${record.artist}</h5>
            <h5 class="record-title">${record.title}</h5>
        <h5 class="card-text">
            Price: ${record.price} kr
        </h5>
            </div>
            </div></a>`);
    }

    $('.info').ready(function () {
        let record = getStoredCurrentRecord();
        setProductInfo(record);
        $('#back-To-Shop').click(function () {
            window.location.href = "shop.html"
        });
        $('#add-to-cart-btn-' + record.id).click(function () {
            let counter = parseInt(localStorage.getItem("cartCount"));
            console.log(counter);
            counter +=1;
            localStorage.setItem("cartCount",JSON.stringify(counter));
            let record = getStoredCurrentRecord();
            let url = "http://localhost:8080/cart/add/" + record.id;
            $.post(url,
                function (data) {
                    console.log(data);
                });
            window.location.href = 'productinfo.html';
        });
    });

    function setProductInfo(record) {
        $('.info').append(`
            <div class="record-info">
            <img class="record-img-info" src="images/${record.imgURL}" alt="Info image"/>
           <div class="info-description">
            <h3 class="info-artist">${record.artist}</h3>
            <h4 class="info-title">${record.title}</h4>
        <h4 class="info-price">Price: ${record.price} KR</h4>
        <button id="add-to-cart-btn-${record.id}" class="add-To-Cart-Btn">Add To Cart</button>
        <button id="back-To-Shop" class="add-To-Cart-Btn">Go back</button>
        </div>
            </div>`);
    };








});
