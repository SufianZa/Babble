<html>
<head><title>Profile</title>

    <!-- Header Tools-->
<body>
    <ul class="ul-head">
    <li class="head" style="float: right"><a href="#">Block/Unblock</a></li>
    <li class="head" style="float: right"><a href="#">Follow/Unfollow</a></li>
    <li class="head" style="float: left"><a href="./search">Search Babble</a></li>
    <li class="head"><a href="#">logged in as ${loggedUser}</a> </li>
</ul>

    <!--Personal Information-->
<div class="container-personal-info" style="overflow:auto; background-color:#e8efff;">
    <img src="http://www.qatarliving.com/sites/all/themes/qatarliving_v3/images/avatar.jpeg" width="120" height="110"
         style="float: right; margin: 2%">
    <div>Benutzername :${name} </div>
    <div>Name : ${username}</div>
    <div>Status : ${status}</div>
</div>
    <body>

    <!--new Babble-->
    <ul class="ul-head">
        <li class="head" style="float: right"><a href="#">New Babble</a></li>
    </ul>
    <!-- Timeline -->
    <div class="container" style="width: 100%;min-width: 200px;">
    <ul style="list-style-type: none;">
    <#list babble as bab>
        <li>
            <fieldset style="background: #ececf2;">
                <legend>${bab.author}</legend>
                <fieldset style="min-height: 100px; background: #c9d4fe; border-radius: 5px; border-color: transparent">
                    <p>
                        ${bab.inhalt}
                    </p>
                </fieldset>
                <p style="font-size: 14">
                    <label> Liked ${bab.likes}/</label>
                    <label> Disliked ${bab.dislikes}/</label>
                    <label> Shared ${bab.shared}</label>
                </p>
                <p align="right" style="font-size: 11px">
                    ${bab.datum}
                </p>
            </fieldset>
        </li>
    </#list>
    </ul>
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
  </style>