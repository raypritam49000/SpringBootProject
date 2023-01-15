const toggleSidebar = () => {
    if ($(".sidebar").is(":visible")) {
        //true
        //band Karna Hai
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%")
    } else {
        //false
        //show karna Hai
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%")

    }
}

const search = () => {
    console.log("Searching....")
    let query = $("#search-input").val();

    if(query==''){
        $(".search-result").hide();
    }
    else{
        // search
        console.log(query);

        // sending request to server
        let url = `http://localhost:8081/search/${query}`;

        fetch(url).then((response)=>{
            return response.json();
        }).then((data) =>{
            //data.....
            console.log(data)

            let text = `<div class="list-group">`;

            data.forEach(contact=>{
                text+=`<a href="/user/contact/${contact.cid}" class="list-group-item list-group-action">${contact.cname}</a>`
            });

            text+=`</div>`;

            $(".search-result").html(text);
            $(".search-result").show();
        })

    }
}