<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <title>search</title>
</head>

    <!-- Header Welcome-->
<body>
<ul class="ul-head">
    <li class="head"><a href="profile_view/${loggedUser}"><i class="fa fa-home" aria-hidden="true"></i> ${loggedUser}</a></li>
</ul>


<div class="container">
    <form action="">
        <input id="search" type="text" name="searched">
        <input id="btn" type="submit" value="Search">
    </form>
</div>
<div class="container-results">
    <#if  "${ss}" == "world">
    <form action="">
        <p>resault for </p>
        <p>${searchedfor}</p>
    </form>
</#if>
</div>
</body>
</html>

  <style>
      .container {
          width: 100%;
          text-align: center;


      }
      .container-results {
          height: 100%;
          margin-top: 5px;

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
          width: 50%;
          display: inline-block;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
      }

      input[type=submit] {
          background-color: #4CAF50;
          color: white;
          padding: 14px 20px;
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