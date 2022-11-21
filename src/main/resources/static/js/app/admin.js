var main = {
    init : function () {
        var _this = this;

        $(".sidebar ul li").on('click', function(){
            $(".sidebar ul li.active").removeClass('active');
            $(this).addClass('active');
        });

        $('#btn-role-update').on('click', function() {
            _this.roleUpdate();
        });

    },

    roleUpdate : function() {
        var data = {
            role: $('#roleupdate option:selected').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/users/update/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('사용자 권한이 수정되었습니다.');
            window.location.href = '/admin';
        }).fail(function (error) {
            alert(JSON.stringify(error));
            });

    }

};

main.init();