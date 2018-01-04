<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Create a Babble</title>
</head>
<!-- Header Tools-->
<body>
<ul class="ul-head">
    <li class="head" style="float: left"><a href="../search"> <i class="fa fa-search" aria-hidden="true"></i> Search
        Babble</a></li>
    <li class="head"><a href="profile_view/${loggedUser}"><i class="fa fa-home" aria-hidden="true"></i> ${loggedUser}</a></li>
</ul>

<div style="height: 100%; color: #c1c1c1">
<!--new Babble-->
        <div style="padding-right: 30%; padding-left: 30%; text-align: center; margin-top: 10%; margin-bottom: 5%; ">
            <form action="/create" method="post">
            <fieldset style="background: #ececf2; border-color: #1f669c; border-radius: 20px">
                <legend><a href="/profile_view/${loggedUser}">${loggedUser}</a></legend>
                <fieldset style="min-height: 50px; background: #c9d4fe; border-radius: 5px; margin-bottom: 7px; border-color: transparent">
                        <textarea id="babbleText" name="babble" cols="40" rows="5"></textarea>
                </fieldset>
            </fieldset>
                <input type="submit" value="Create Babble" onClick="return empty()">
            </form>
        </div>
</div>
</body>
</html>

  <style>
      .container-personal-info > div {
          margin-top: 30px;
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

      body {
          font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
          font-size: 18px;
          font-style: normal;
          font-variant: normal;
          font-weight: 500;
          line-height: 26.4px;
      }
      textarea {
          background: transparent;
          resize: none;
          outline: none;
          border: 0 none;
          width: 100%;
      }
      input[type=submit] {
          float: right;
          background-color: #1f669c;
          color: white;
          padding: 14px 20px;
          margin: 8px 0;
          border: none;
          border-radius: 4px;
          cursor: pointer;
      }

      input[type=submit]:hover {
          background-color: #298ad2;
      }
  </style>


<style>
    legend a:link, a:visited {
        background-color: #1f669c;
        color: white;
        padding: 1px;
        text-align: center;
        font-size: 14px;
        text-decoration: none;
        display: inline-block;
        border-radius: 4px;
    }

    legend a:hover, a:active {
        background-color: #154165;
    }
</style>

<script>
    function empty() {
        var x;
        x = document.getElementById("babbleText").value;
        if (x.trim() == "") {
            alert("a Babble can't be empty");
            return false;
        }else if(x.length > 280){
            alert('a Babble can\'t be longer than 280 letters');
            return false;
        }
    }

</script>