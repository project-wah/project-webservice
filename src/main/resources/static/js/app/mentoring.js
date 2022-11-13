var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        })
        $('#btn-delete').on('click', function () {
            _this.delete();
        })
    },
    save : function() {
        var data = {
            mentor: $('#mentor').val(),
            title: $('#title').val(),
            job: $('#job').val(),
            career: $('#career').val(),
            office: $('#office').val(),
            content: $('#content').val(),
            hour: $('#hour').val(),
            minutes: $('#minutes').val(),
            price: $('#price').val(),
            person: $('#person').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/mentorIntro',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('멘토로 등록되었습니다.');
            window.location.href = '/mentoring';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            mentor: $('#mentor').val(),
            title: $('#title').val(),
            job: $('#job').val(),
            career: $('#career').val(),
            office: $('#office').val(),
            content: $('#content').val(),
            hour: $('#hour').val(),
            minutes: $('#minutes').val(),
            price: $('#price').val(),
            person: $('#person').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/mentorIntro/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('멘토링 소개글이 수정되었습니다.');
            window.location.href = '/mentoring';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/mentorIntro/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('멘토링 소개글이 삭제되었습니다.');
            window.location.href = '/mentoring';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();