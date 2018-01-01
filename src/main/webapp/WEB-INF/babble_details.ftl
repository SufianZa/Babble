<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Babble Details</title>
</head>
<!-- Header Tools-->
<body>
<ul class="ul-head">
    <li class="head" style="float: left"><a href="../search"> <i class="fa fa-search" aria-hidden="true"></i> Search
        Babble</a></li>
    <li class="head"><a href="/profile_view/${loggedUser}"><i class="fa fa-home" aria-hidden="true"></i> ${loggedUser}</a></li>
</ul>

<div style="height: 100%;">
<!--new Babble-->
        <div style="padding-right: 30%; padding-left: 30%; align: center; margin-top: 10%; margin-bottom: 5%; ">
            <form>
            <fieldset style="background: #ececf2; border-color: #1f669c; border-radius: 20px">
                <legend><a href="/profile_view/${author}">${author}</a></legend>
                <fieldset style="min-height: 50px; background: #c9d4fe; border-radius: 5px; margin-bottom: 7px; border-color: transparent">
                        ${inhalt}
                </fieldset>

                <span style="font-size: 16">
                    <i class="fa fa-thumbs-up" aria-hidden="true" style="color:#305a80; margin-left: 20px"></i>
                    <label>${likes}</label>
                    <i class="fa fa-thumbs-down" aria-hidden="true" style="color:#8b0008;margin-left: 20px"></i>
                    <label>${dislikes}<label>
                        <i class="fa fa-share" aria-hidden="true" style="color:#348037;margin-left: 20px"></i>
                        <label>${shared}</label>
                </span>
                <span style="font-size: 11px; float: right" >
                ${datum}
                </span>
            </fieldset>
                <div id="group">
                <input class="like-dislike-rebabble-${likeBtn}" state="${likeBtn}" style="margin-left: 20px;" type="submit" value="Like">
                <input class="like-dislike-rebabble-${dislikeBtn}" state="${dislikeBtn}" type="submit" value="Dislike">
                <input class="like-dislike-rebabble-${rebabbleBtn}" state="${rebabbleBtn}" type="submit" value="Rebabble">
                </div>
                <#if "${author}" == "${loggedUser}">
                    <input id="delete" type="submit" value="Delete">
                </#if>

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
      .like-dislike-rebabble-true {
          float: left;
          background-color: #139c08;
          color: white;
          padding: 14px 20px;
          margin-right: 10px;

          margin-top: 10px;
          border: none;
          border-radius: 4px;
          cursor: pointer;
      }

      .like-dislike-rebabble-false {
          float: left;
          background-color: #1f669c;
          color: white;
          padding: 14px 20px;
          margin-right: 10px;

          margin-top: 10px;
          border: none;
          border-radius: 4px;
          cursor: pointer;
      }
      #delete {
          float: right;
          background-color: #9c4638;
          color: white;
          padding: 14px 20px;
          margin: 8px 0;
          margin-right: 15px;
          border: none;
          border-radius: 4px;
          cursor: pointer;
      }

      .like-dislike-rebabble {
          background-color: #298ad2;
      }
  </style>


<style>
    legend a:link, a:visited {
        background-color: #1f669c;
        color: white;
        padding: 1px;
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
    $('#group > input[type=submit]').on("click", function (e) {
        var action = $(this).val();
        var state = $(this).attr("state");
        e.preventDefault(); // cancel the link itself
        var data = {act: action, state:state};
        $.post(this.href, data, function () {
            location.reload()
        });
    });


    $('#delete').on("click", function (e) {
        var action = $(this).val();
        e.preventDefault(); // cancel the link itself
        var data = {act: action};
        $.post(this.href, data, function () {
            $('form').html('<strong style="font-size: 30px"> <i class="fa fa-check" aria-hidden="true" style="color: #348037"></i> Babble has been deleted </strong>');
        });

    });
</script>