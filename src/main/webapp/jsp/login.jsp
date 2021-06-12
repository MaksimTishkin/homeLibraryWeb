<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <link rel="stylesheet" href="css/loginPageStyle.css">
</head>
<body>
    <div class="container-xxl">
    <p class="text-center">${errorAuthorization}</p>
        <div class="row justify-content-center">
            <div class="col-3 align-self-center">
                <img class="mainIcon" src="img/icon.png">
            </div>
            <div class="col-5">
                <form name="LoginForm" action="controller">
                    <input type="hidden" name="command" value="login">
                    <div class="row mb-3">
                        <label for="inputLogin" class="col-sm-2 col-form-label">Login</label>
                        <div class="col-sm-6">
                            <input type="text" name="name" id="inputLogin">
                        </div>
                    </div>
                    <div class="row mb-4">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-6">
                            <input type="password" name="password" id="inputPassword">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
            </div>
        </div>
    </div>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</body>
</html>