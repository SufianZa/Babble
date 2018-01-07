<html>
<head>
    <link href="http://designers.hubspot.com/hs-fs/hub/327485/file-2054199286-css/font-awesome.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Profile</title>
</head>
<!-- Header Tools-->
<body>
<ul class="ul-head">

<#if "${loggedUser}"!="${profile}">
    <li class="head" style="float: right"><a class="block_follow" href="">${blockState}</a></form></li>
    <li class="head" style="float: right"><a class="block_follow" href="">${followState}</a></li>
<#if "${sendMessage}"=="send">
    <li class="head" style="float: right; "><a class="sendMessage" href="../message">Send a Message </a> <span style="background: #ababad; border-radius: 20px; margin: 0px ;font-size: 12px;line-height: normal; padding-right: 4px; padding-left: 4px ;float: right; position: absolute; right: 5cm; top: 45px">*num*</span></li>
</#if>

</#if>
    <li class="head" style="float: left"><a href="../search"> <i class="fa fa-search" aria-hidden="true"></i> Search
        Babble</a></li>
    <li class="head"><a href="${loggedUser}"><i class="fa fa-home" aria-hidden="true"></i> ${loggedUser}</a></li>
</ul>

<!--Personal Information-->
<hr>
<div style="background: #399c53; color: white; text-align: center">Personal Information</div>
<hr>
<div class="container-personal-info" style="overflow:auto; background-color:#e8efff;">
    <img src="${Profile_Image_Path}" width="120" height="110"
         style="float: right; margin: 2%">
    <table>
        <tr>
            <th>Username</th>
            <td>${username}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${name}</td>
        </tr>
        <tr>
            <th>Status</th>
            <td>${status}</td>
        </tr>
    </table>
</div>

<!--new Babble-->

<!-- check if Blocked -->
<#if "${blockContent}" == "blocked">
<ul class="ul-head">
    <li class="head" style="float: right"><a href="../create">New Babble</a></li>
</ul>

    <div class="container" style="width: 100%;min-width: 200px;">
        <ul style="list-style-type: none;">
            <li style="padding-right: 20%; padding-left: 20%;">
                <fieldset
                        style="background: #c9d4fe; border-radius: 5px; padding-right: 150px border-color: transparent">
                    <strong>
                        you are blocked
                    </strong>
                    <div> Reason: <i>"${reason}"</i></div>
                </fieldset>
            </li>
        </ul>
    </div>
<#else>
<hr>
<div style="background: #ff5a42; color: white; text-align: center">Timeline</div>
<hr>
<ul class="ul-head">
    <li class="head" style="float: right"><a href="../create">New Babble</a></li>
</ul>
<!-- get own babbles -->
<diV class="sign"><a>babbles</a></diV>
<div class="container" style="width: 100%;min-width: 200px;">
    <ul style="list-style-type: none;">
    <#list own_babble as bab>
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
<!-- ..........................................-->
<hr>
    <diV class="sign"><a>Friends babbles</a></diV>
    <ul style="list-style-type: none;">
    <#list friends_babble as bab>
		<div style="border-radius: 10px;background: #eeeeee;padding-left: 10px;padding-right: 10px;margin-bottom: 10px ;margin-left: 15%;margin-right: 60%; font-size: 14; color: #909090">
			<#if "${profile}" == "${loggedUser}">
            <span>  You follow ${bab.author} and he wrote:</span>
            <#else>
            <span> ${profile} follows ${bab.author} and he wrote:</span>
            </#if>
        </div>

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
<hr>
<!-- ..........................................-->
    <diV class="sign"><a>Activities</a></diV>
    <ul style="list-style-type: none;">
    <#list interaction_babble as bab>
       <div style="border-radius: 10px;background: #eeeeee;padding-left: 10px;padding-right: 10px;margin-bottom: 10px ;margin-left: 15%;margin-right: 60%; font-size: 14; color: #909090">

            <#if "${profile}" == "${loggedUser}">
            <span> you have ${bab.activity}</span>
            <#else>
            <span> ${profile} has ${bab.activity}</span>
            </#if>
            <span style="float: right"> ${bab.activityTimestamp}</span>
        </div>
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
</div>
</#if>

</body>

</html>

<script>

    $(".block_follow").on("click", function (e) {
        var action = $(this).text();
        var reason = "";
        if (action === 'Block') {
            reason = prompt("Reason :", "");
        }
        e.preventDefault(); // cancel the link itself
        var data = {act: action, reason: reason};
        $.post(this.href, data, function () {
            location.reload();
        });
    });
</script>
<style>
    .sign{
        padding-top: 20px;
        position: relative;
    }
    .sign a{
        padding: 10px;
        margin: 5px ;
        background: #1f669c;
        border-top-right-radius: 20px;
        border-bottom-left-radius: 20px
    }
    hr{
        margin: 2;
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
    table {
        width: 80%;
        font-family: arial, sans-serif;
        border-collapse: collapse;
    }

     th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 18px;
    }
    td {
        border: 1px solid #dddddd;
        text-align: center;
        padding:15px;
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


