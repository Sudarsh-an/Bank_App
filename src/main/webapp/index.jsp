<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .bank-info {
            padding: 20px;
            margin-bottom: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f8f9fa;
            text-align: center; /* Center align content */
        }

        .bank-info h2 {
            margin-bottom: 20px;
            color: #007bff;
        }

        .bank-info p {
            line-height: 1.6;
            margin-bottom: 15px;
            text-align: justify; /* Justify text */
        }

        .bank-image {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
        <span class="navbar-brand">Banking Application</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="loginForm">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registerForm">Register</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container">
        <div class="row justify-content-center"> <!-- Center align row content -->
            <div class="col-md-6">
                <div class="bank-info">
                    <h2>About Our Bank</h2>
                    <p>Welcome to our bank, where we are dedicated to providing exceptional financial services tailored to meet your needs. With a focus on customer satisfaction and innovation, we strive to be your trusted partner in achieving your financial goals.</p>
                    <p>Our bank offers a wide range of products and services, including personal and business banking, investment management, loans, mortgages, and more. Whether you're saving for your future, managing your day-to-day finances, or planning for major life events, we have the expertise and resources to help you succeed.</p>
                    <p>At our bank, we understand that every customer is unique, which is why we take the time to listen to your needs and provide personalized solutions that align with your financial objectives. Our team of experienced professionals is committed to delivering exceptional service and building long-lasting relationships based on trust and integrity.</p>
                    <p>With a strong focus on technology and innovation, we continuously strive to enhance our services and improve your banking experience. Whether you prefer banking online, through our mobile app, or in person at one of our convenient branch locations, we offer flexible and convenient options to meet your needs.</p>
                    <p>Thank you for choosing our bank as your financial partner. We look forward to serving you and helping you achieve your financial goals.</p>
                </div>
            </div>
        </div>
    </div>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
