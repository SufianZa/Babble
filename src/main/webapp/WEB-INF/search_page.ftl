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
<div class="container-words">
    <#if  "${ss}" == "world">
    <form action="">
        <p>result for </p>
        <p><b>${searchedFor}</b></p>
    </form>
    <#else>
    <form action="">
        <p>Keine Ergebnisse</p>
    </form>
    </#if>
</div>

<!-- get result babbles -->
<div class="container-results" style="width: 100%;min-width: 200px;">
	<#if "${ss}" == "world">
    <ul style="list-style-type: none;">
    <#list resultbabble as bab>
        <li style="padding-right: 20%; padding-left: 20%; margin-bottom: 20px">
            <fieldset style="background: #ececf2; border-color: #1f669c; border-radius: 20px">
                <legend><a href="/profile_view/${bab.author}">${bab.author}</a></legend>
                <a href="/babble_details/${bab.id}" style="text-decoration: none; color: inherit">
                    <fieldset
                            style="min-height: 50px; background: #c9d4fe; border-radius: 5px; margin-bottom: 7px; border-color: transparent">
                        <div>

                            ${bab.inhalt}

                        </div>

                    </fieldset>
                </a>
                <span style="font-size: 16">
                    <i class="fa fa-thumbs-up" aria-hidden="true" style="color:#305a80; margin-left: 20px"></i>
                    <label>${bab.likes}</label>
                    <i class="fa fa-thumbs-down" aria-hidden="true" style="color:#8b0008;margin-left: 20px"></i>
                    <label>${bab.dislikes}<label>
                        <i class="fa fa-share" aria-hidden="true" style="color:#348037;margin-left: 20px"></i>
                        <label>${bab.shared}</label>
                </span>
                <span style="font-size: 11px; float: right">
                    ${bab.datum}
                </span>
            </fieldset>
        </li>
    </#list>
    </ul>
    </#if>
</div>
<!-- ..........................................-->




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
