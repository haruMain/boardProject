/*
* /board/read.html
* */

let replyService = (function(){
    function add(reply, callback, error){
        $.ajax({
            url: "/reply/new",
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8", //data에 JSON 작성 시 반드시 contentType 작성
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(a, b, c){
                if(error){
                    error(a, b, c);
                }
            }
        });
    }
    function getList(param, callback, error){
        let page = param.page || 1;
        $.ajax({
            url: "/reply/list/" + param.boardNumber + "/" + page,
            success: function(replies){
                if (callback){
                    callback(replies);
                }
            }
        })
    }
    function read(replyNumber, callback, error){
        $.ajax({
            url: "/reply/" + replyNumber,
            type: "get",
            success: function(reply){
                console.log(reply);
            }
        })
    }
    function remove(replyNumber, callback){
        $.ajax({
            url: "/reply/" + replyNumber,
            type: "delete",
            success: function(){
                if(callback){
                    callback();
                }
            }
        })
    }
    function modify(reply, callback, error){
        $.ajax({
            url: "/reply/modify",
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    //댓글 작성 시간
    function timeForToday(value) {
        const today = new Date();
        //지금날짜
        const timeValue = new Date(value);
        //타임리프에서 전달받은 매개변수를 전달받은 값

        //

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금 전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
    }

    return {add: add, getList: getList, read: read, remove: remove, modify: modify, timeForToday: timeForToday}
})();