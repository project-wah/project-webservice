var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function() {
            _this.update();
        });

        $('#btn-delete').on('click', function() {
            _this.delete();
        });

    },
    save : function () {
        var data = {
            nickname: $('#nickname').val(),
            aboutme: $('#aboutme').val(),
            mentoremail: $('#mentoremail').val(),
            githubemail: $('#githubemail').val(),
            blogaddress: $('#blogaddress').val(),
            phonenumber: $('#phonenumber').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/userdetails',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function() {
        var data = {
            username: $('#username').val(),
            nickname: $('#nickname').val(),
            githubemail: $('#githubemail').val(),
            blogaddress: $('#blogaddress').val(),
            aboutme: $('#aboutme').val(),
            phonenumber: $('#phonenumber').val()
        };

        var id = $('#id').val();

                $.ajax({
                    type: 'PUT',
                    url: '/api/v1/users',
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function() {
                    alert('사용자 정보가 수정되었습니다. 재 로그인 후 서비스를 이용해주시기 바랍니다 :-)');
                    window.location.href = '/';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
    },
    delete : function() {

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/users',
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('탈퇴되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }

};

main.init();