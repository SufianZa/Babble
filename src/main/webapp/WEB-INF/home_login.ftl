<html>
<head><title>Login</title>

    <!-- Header Welcome-->
<body>
<ul class="ul-head">
    <li class="head" style="text-align: center; margin: 6%; font-size: 40px;">Welcome to Babble</a></li>
</ul>

<!-- Login Information-->
<div class="container-personal-info" style="overflow:auto; background-color:#e8efff; margin: 5%">
    <br>
    <br>Benutzername:<br>
    <form action="/profile_view">
        <input type="text" name="sessionID" value="">
        <input type="submit" value="Login">
    </form>
    <br>
    <br>
    <br>

</div>
</body>
</html>

  <style>
      .container-personal-info > div {
          margin-top: 30%;
      }

      .ul-head {
          list-style-type: none;
          margin: 0;
          padding: 0;
          overflow: hidden;
          background-color: #1f669c;
      }

      .head {
          float: left;
      }

      .head a {
          display: block;
          color: white;
          text-align: center;
          padding: 14px 16px;
          text-decoration: none;
      }

  </style>