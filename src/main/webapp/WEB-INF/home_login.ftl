<html>
<head><title>Login</title>

    <!-- Header Welcome-->
<body>
<ul class="ul-head">
    <li class="head" style="text-align: center; margin: 6%; font-size: 40px;">Welcome to Babble</a></li>
</ul>

<!-- Login Information-->
<div class="container-personal-info" style="overflow:auto; background-color:#e8efff;">
    <br>

    <form action="/re">
        <label>Benutzername:</label>
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
          width: 100%;
          margin-top:5px;


      }
      .container-personal-info  {

          margin-top:10px;


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
      input[type=text] {

          padding: 12px 20px;
          margin: 8px 0;
          display: inline-block;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
      }

      input[type=submit] {
          background-color: #4CAF50;
          color: white;
          padding: 14px 20px;
          margin: 8px 0;
          border: none;
          border-radius: 4px;
          cursor: pointer;
      }

      input[type=submit]:hover {
          background-color: #45a049;
      }
  </style>