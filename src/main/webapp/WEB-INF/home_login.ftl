<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Login</title>
</head>
<!-- Header Welcome-->
<body>
<ul class="ul-head">
    <li class="head" style=" margin: 6%; font-size: 40px;"><a href="/">Welcome to Babble</a></li>

</ul>
<hr>
<!-- Login Information-->
<div class="container-personal-info" style="overflow:auto; background-color:#e8efff;">
    <br>

    <form action="/redirect">
        <p>Benutzername</p>
        <input id="user" type="text" name="sessionID" value="">
        <input type="submit" value="Login" onClick="return empty()">
    </form>
    <br>
    <br>
    <br>

</div>



<style>
    .container-personal-info {
        margin-top: 10px;
        text-align: center;
        height: 100%;
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
        width: 30%;
        display: inline-block;
        <#if "${user}"=="wrong">
             border: 1px solid #cc1917;
        <#else>
        border: 1px solid #ccc;
        </#if>
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

    body {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        font-size: 18px;
        font-style: normal;
        font-variant: normal;
        font-weight: 500;
        line-height: 26.4px;
    }
</style></body></html>
<script>
function empty() {
    var x;
    x = document.getElementById("user").value;
    if (x == "") {
        alert("Enter a valid username");
        return false;
    }
}
</script>
