<html>
<head><title>search</title>

    <!-- Header Welcome-->
<body>
<ul class="ul-head">
    <li class="head"><a href="#">logged in as ${loggedUser}</a></li>
</ul>


<div class="container">
    <form action="">
         <input id="search" type="text" name="searched" >
         <input id="btn" type="submit" value="Search">
    </form>
</div>

<div class="container">
    <form action="">
        <p>resault for </p>
        <p>${searchedfor}</p>
    </form>
</div>
</body>
</html>

  <style>
        .container {
            width: 100%;
            margin-top:20px;


        }


        .ul-head {
            list-style-type: none;
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

        div {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 18px;
            font-style: normal;
            font-variant: normal;
            font-weight: 500;
            line-height: 26.4px;
        }
  </style>