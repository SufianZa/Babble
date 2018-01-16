<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>search</title>
</head>

    <!-- Header Welcome-->
<body>
<ul class="ul-head">
    <li class="head"><a href="profile_view/${loggedUser}"><i class="fa fa-home" aria-hidden="true"></i> ${loggedUser}</a></li>
</ul>


<div class="container">
    <form action="" style="margin-bottom: 0px;">
        <input id="search" type="text" name="searched">
        <input id="btn" type="submit" value="Search" onClick="return empty()">
    </form>
</div>
<div class="container-words">
    <#if  "${ss}" == "first">

    <#elseif "${ss}" == "world" >
        <span>result for <strong style="font-style: italic">  "${searchedFor}"</strong></span>
    <#else>
        <span >no results were found for <strong style="font-style: italic">  "${searchedFor}"</strong></span>
    </#if>
</div>

<!-- get result babbles -->
<div class="container-results" style="width: 100%;min-width: 200px; height: 100%">
	<#if "${ss}" == "world">
    <ul id="res" style="list-style-type: none; margin: 0; display: none">
    <#list resultbabble as bab>
        <li style="padding-right: 20%; padding-left: 20%;">
            <fieldset style="background: #ececf2; border-color: #1f669c; border-radius: 20px; margin-top: 10px">
                <legend><a href="/profile_view/${bab.author}">${bab.author}</a></legend>
                <a href="/babble_details/${bab.id}" style="text-decoration: none; color: inherit">
                    <fieldset style="min-height: 50px; background: #c9d4fe; border-radius: 5px; margin-bottom: 7px; border-color: transparent">
                        <p>
                            ${bab.inhalt}
                        </p>
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
<script>
    $("#res").slideDown();
</script>
  <style>
      .container {
          width: 100%;
          text-align: center;


      }
      .container-results {
          overflow: auto;
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

      form > input[type=text] {

          padding: 12px 20px;
          width: 50%;
          display: inline-block;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
      }

      form > input[type=submit] {
          background-color: #4CAF50;
          color: white;
          padding: 14px 20px;
          border: none;
          border-radius: 4px;
          cursor: pointer;
      }

      form >  input[type=submit]:hover {
          background-color: #45a049;
      }

      div {
          background-color: #f2f2f2;
      }

      body {
          font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
          font-size: 18px;
          font-style: normal;
          font-variant: normal;
          font-weight: 500;
          line-height: 26.4px;
      }
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
       x = document.getElementById("search").value.trim();
       if (x == "") {
           return false;
       }
   }

</script>