<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <title>Profile</title>
</head>
    <!-- Header Tools-->
<body>
<ul class="ul-head">

<#if "${loggedUser}"!="${profile}">
    <li class="head" style="float: right"><a href="${blockState}">${blockState}</a></li>
    <li class="head" style="float: right"><a href="${followState}">${followState}</a></li>
</#if>
    <li class="head" style="float: left"><a href="../search"> <i class="fa fa-search" aria-hidden="true"></i> Search Babble</a></li>
    <li class="head"><a href="${loggedUser}"><i class="fa fa-home" aria-hidden="true"></i> ${loggedUser}</a></li>
</ul>

<!--Personal Information-->
<div class="container-personal-info" style="overflow:auto; background-color:#e8efff;">
    <img src="http://www.qatarliving.com/sites/all/themes/qatarliving_v3/images/avatar.jpeg" width="120" height="110"
         style="float: right; margin: 2%">
    <div>Benutzername : ${username} </div>
    <div>Name : ${name} </div>
    <div>Status : ${status} </div>
</div>
<body>

<!--new Babble-->
<ul class="ul-head">
    <li class="head" style="float: right"><a href="#">New Babble</a></li>
</ul>
<!-- Timeline -->
<#if "${blockContent}" == "blocked">
    <div class="container" style="width: 100%;min-width: 200px;">
        <ul style="list-style-type: none;">
        <li>
                <fieldset style="min-height: 100px; background: #c9d4fe; border-radius: 5px; padding-right: 150px border-color: transparent">
                    <p>
                        you are blocked
                    </p>
                    <div> Reason: <i>"${reason}"</i></div>
            </fieldset>
        </li>
        </ul>
    </div>
<#else>
<div class="container" style="width: 100%;min-width: 200px;">
    <ul style="list-style-type: none;">
    <#list babble as bab>
        <li id="" style="padding-right: 7cm; padding-left: 5cm; margin-bottom: 20px">
            <fieldset href="#" style="background: #ececf2; border-color: #1f669c">
                <legend> <a href="/profile_view/${bab.author}">${bab.author}</a></legend>
                <fieldset style="min-height: 100px; background: #c9d4fe; border-radius: 5px; border-color: transparent">
                    <p>
                        ${bab.inhalt}
                    </p>
                </fieldset>
                <p style="font-size: 18">
                    <i class="fa fa-thumbs-up" aria-hidden="true" style="color:#305a80; margin-left: 20px"></i>   <label>${bab.likes}</label>
                    <i class="fa fa-thumbs-down" aria-hidden="true" style="color:#8b0008;margin-left: 20px"></i>  <label>${bab.dislikes}<label>
                    <i class="fa fa-share" aria-hidden="true" style="color:#348037;margin-left: 20px"></i> <label>${bab.shared}</label>
                </p>
                <p align="right" style="font-size: 11px">
                    ${bab.datum}
                </p>
            </fieldset>
        </li>
    </#list>
    </ul>
</div>
</#if>

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
  </style>


<style>
    legend a:link, a:visited {
        background-color: #1f669c;
        color: white;
        padding: 2px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
    }


    legend a:hover, a:active {
        background-color: #154165;
    }
</style>