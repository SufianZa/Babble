<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Massage</title>
</head>
<!-- Header Tools-->
<body>
<ul class="ul-head">
    <li class="head" style="float: left"><a href="../search"> <i class="fa fa-search" aria-hidden="true"></i> Search
        Babble</a></li>
    <li class="head"><a href="${loggedUser}"><i class="fa fa-home" aria-hidden="true"></i> ${loggedUser}</a></li>
</ul>


<!-- check if Blocked -->

    <div class="container" style="width: 100%;min-width: 200px;">
        <ul style="list-style-type: none;">
            <#list massages as m>

                <#if "${m.sender}" == "${loggedUser}">
                    <li style="float: right;width: 50%;margin-right: 50%">

                <#else>
                    <li style="float: left;width: 50%;margin-left: 50%">
                </#if>
                        <fieldset style="background: #c9d4fe; border-radius: 5px">
                            ${m.text}
                        </fieldset>
                    </li>
                 <div style="border-radius: 10px;background: #eeeeee;text-align: center; font-size: 12; color: #909090">
                <p>${m.created}</p>
            </div>
            </#list>
        </ul>
        <hr>
        <div style="padding-left: 40%;">
        <fieldset style="background: #c9d4fe; border-radius: 5px; border-color: transparent;">
            <textarea id="massageWrite" name="massage" cols="40" rows="5"></textarea>
            <input type="submit"></input>
        </fieldset>
        </div>
    </div>



</body>

</html>

<style>
    textarea {
        background: transparent;
        resize: none;
        outline: none;
        border: 0 none;
        width: 100%;
    }
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


