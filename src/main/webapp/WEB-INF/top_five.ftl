<a id="top" style="position: fixed; right: 4%; bottom: 5%; border-radius: 25px; text-decoration: none; padding: 7; background: #ffd602"> <i class="fa fa-star" aria-hidden="true" style="color: white"></i> </a>
<div id="topList" style="border-radius: 20px; right:1px ;position: fixed; border:8px solid gold;; display: none; max-width: 500; max-height: 85%; overflow: auto; background: gold">
    <ul style=" list-style-type: none; margin: 0; padding: 0">
        <li style="margin-bottom: 5px; font-size: 18px; text-align: center; background: gold"> Top 5 Babbles</li>
    <#list friends_babble as bab>
        <li style="margin-bottom: 5px; font-size: 12px;">
            <fieldset style="background: #ececf2; border-color: #1f669c; border-radius: 20px">
                <legend><a style="padding: 0" href="/profile_view/${bab.author}">${bab.author}</a></legend>
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
<script>
    $('#top').click(function () {
        if(!$('#topList').is(":visible")){
            $('#topList').animate({width:'toggle', opacity: 100},350);
            $('#top').css("background","#bc9c02");


        }else{
            $('#topList').animate({width:'hide', opacity: 100},350);
            $('#top').css("background","#ffd602");

        }
    });
</script>
