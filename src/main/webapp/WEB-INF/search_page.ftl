<html>
<head><title>search</title>

    <!-- Header Welcome-->
<body>
<ul class="ul-head">
    <li class="head"><a href="#">logged in as ${loggedUser}</a></li>
</ul>


<div class="container">
    <form>
         <input id="search" type="text" >
         <input id="btn" type="submit" value="Search">

    </form>
</div>
</body>
</html>

  <style>
        .container {
            width: 400px;
            height: 100px;
            margin: 30%;
        }
      #search{
          border-radius: 7px;
          background: #ececf2;
          font-size: 25px;
      }
        #btn{
            border-radius: 7px;
            background: #ececf2;
            height: 30px;
            font-size: 18px;
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

        .head a:hover {
            background-color: #111;
        }

  </style>