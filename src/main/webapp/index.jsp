<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Styles for the navbar */
        .navbar {
            background-color: #343a40; /* Set background color for the navbar */
        }

        .navbar-brand {
            color: #ffffff; /* Set text color for the navbar brand */
        }

        .navbar-nav .nav-link {
            color: #ffffff !important; /* Set text color for the navbar links */
        }

        /* Styles for the form container */
        .form-container {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 2;
            background-color: #4a5259; 
            padding: 70px;
            border-radius: 20px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

        .form-container h2 {
            margin-bottom: 20px;
            color: #ffffff;
        }

        /* Styles for Matrix rain */
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            background-color: #b5b5b457;
            color: rgb(11, 12, 6);
            overflow: hidden; /* Prevents scrollbar from appearing */
        }

        p {
            display: block;
             margin-block-start: 1em;
             margin-block-end: 1em;
            margin-inline-start: 0px;
            margin-inline-end: 0px;
            unicode-bidi: isolate;
            color: #ffffff; 
        }

        .matrix-container {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 0; /* Positioned behind other content */
        }

        .matrix-column {
            float: left;
            width: 20px;
            height: 100vh;
            overflow: hidden;
            font-size: 24px;
            position: relative;
        }

        .matrix-char {
            position: absolute;
            top: -20px;
            animation: fall 6s linear infinite, glow 1.5s infinite alternate; /* Increase animation duration */
            color: rgba(237, 243, 213, 0.966); /* Set character color */
        }

        .matrix-char.small {
            font-size: 18px;
        }

        .matrix-char.big {
            font-size: 30px;
        }

        @keyframes fall {
            to {
                top: 100%;
            }
        }

        @keyframes glow {
            from {
                text-shadow: 0 0 5px green;
            }
            to {
                text-shadow: 0 0 15px green, 0 0 20px green, 0 0 25px green;
            }
        }
    </style>
</head>
<body>
    <!-- Matrix rain container -->
    <div class="matrix-container"></div>

    <!-- Navbar -->
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

    <!-- Form container -->
    <div class="form-container">
        <h2>About Our Bank</h2>
<p>Welcome to our bank, where we are dedicated to providing exceptional financial services tailored to meet your needs. With a focus on customer satisfaction and innovation, we strive to be your trusted partner in achieving your financial goals.</p>

<p>At our bank, we understand that each customer is unique, with their own financial aspirations and challenges. That's why we offer a wide range of banking solutions designed to cater to individuals, families, and businesses alike.</p>

<p>Thank you for choosing our bank as your financial partner. We look forward to helping you achieve your financial dreams and aspirations.</p>

        
    </div>

    <!-- Matrix rain script -->
    <script>
        function createMatrixRain() {
            const columns = Math.floor(window.innerWidth / 20);
            const matrixContainer = document.querySelector('.matrix-container');

            for (let i = 0; i < columns; i++) {
                const column = document.createElement('div');
                column.className = 'matrix-column';
                matrixContainer.appendChild(column);
            }

            const characters = [' ', ' $',' '];
            setInterval(() => {
                const columns = document.querySelectorAll('.matrix-column');
                columns.forEach(column => {
                    const newChar = document.createElement('div');
                    newChar.className = 'matrix-char' + (Math.random() > 0.7 ? ' small' : Math.random() > 0.7 ? ' big' : '');
                    newChar.textContent = characters[Math.floor(Math.random() * characters.length)];
                    column.appendChild(newChar);
                    setTimeout(() => {
                        column.removeChild(column.firstChild);
                    }, Math.random() * 7000); /* Increase timeout for removing characters */
                });
            }, 200); /* Adjust interval for pouring */
        }

        window.onload = () => {
            createMatrixRain();
        };
    </script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
