<a id="top" style="z-index: 2;position: fixed; right: 4%; bottom: 5%; border-radius: 25px; text-decoration: none; padding: 7; background: #ffd602"> <i class="fa fa-star" aria-hidden="true" style="color: white"></i> </a>
<div id="shadow" style="display: none;position: fixed;right: 0;top: 0;bottom: 0;left: 0;z-index: 1;background: #111111; opacity: 0.5"></div>
<div id="topList" style="z-index: 2;border-radius: 20px; right:1px ;position: fixed; border:8px solid gold;; display: none; max-width: 450; width: 500; min-height: 70%; max-height: 70%;overflow: auto; word-wrap: break-word; background: gold">
    <ul style=" list-style-type: none; margin: 0; padding: 0">
        <li style="text-align: center;" > <strong style="margin-bottom: 5px; font-size: 18px; white-space: nowrap;">Top 5 Babbles</strong></li>
    <#list friends_babble as bab>

        <li style="margin-bottom: 5px; font-size: 12px;">
            <fieldset style="min-width: 300px;background: #ececf2; border-color: #1f669c; border-radius: 20px">
                <legend><a style="padding: 0 !important;" href="/profile_view/${bab.author}">${bab.author}</a></legend>
                <a href="/babble_details/${bab.id}" style="text-decoration: none; color: inherit">
                    <fieldset
                            style=" background: #c9d4fe; border-radius: 5px; margin-bottom: 2px; border-color: transparent">
                        <div>

                            ${bab.inhalt}

                        </div>

                    </fieldset>
                </a>
                <span>
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
<script>
    $('#top').click(function () {
        if(!$('#topList').is(":visible")){
            $('#topList').animate({width:'toggle'},350);
            $('#top').css("background","#bc9c02");
            $('#shadow').fadeIn();
        }else{
            $('#topList').animate({width:'hide'},350);
            $('#shadow').fadeOut();
            $('#top').css("background","#ffd602");

        }
    });

    $('#shadow').click(function () {
        if($('#topList').is(":visible")) {
            $('#topList').animate({width:'hide', opacity: 100},350);
            $('#shadow').fadeOut();
            $('#top').css("background","#ffd602");
        }
    });
</script>
