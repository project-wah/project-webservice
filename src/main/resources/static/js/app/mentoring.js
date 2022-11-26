var main = {
    init : function () {
        const _this = this;

        //멘토링 소개글 저장
        $('#btn-saveMentoring').on('click', function () {
            _this.saveMentoring();
        });
        //멘토링 소개글 수정
        $('#btn-updateMentoring').on('click', function () {
            _this.updateMentoring();
        });
        //멘토링 소개글 삭제
        $('#btn-deleteMentoring').on('click', function () {
            _this.deleteMentoring();
        });
    },

    //멘토링 소개글 작성
    saveMentoring : function() {
        const data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            job: $('#job').val(),
            career: $('#career').val(),
            office: $('#office').val()
        };

        //공백 및 빈 문자열 검사
        if (!data.title || data.title.trim() === "" ||
            !data.content || data.content.trim() === "" ||
            !data.job || data.job.trim() === "" ||
            !data.career || data.career.trim() === "") {
            alert("입력하지 않은 부분이 있으니 확인해주세요.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/mentoring',
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('등록되었습니다.');
                window.location.href = '/mentoring';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    //멘토링 소개글 수정
    updateMentoring : function () {
        const data = {
            id: $('#id').val(),
            title: $('#title').val(),
            content: $('#content').val(),
            job: $('#job').val(),
            career: $('#career').val(),
            office: $('#office').val(),
        };

        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            if (!data.title || data.title.trim() === "" ||
                !data.content || data.content.trim() === "" ||
                !data.job || data.job.trim() === "" ||
                !data.career || data.career.trim() === "") {
                alert("입력하지 않은 부분이 있으니 확인해주세요.");
                return false;
            } else {
                $.ajax({
                    type: 'PUT',
                    url: '/api/v1/mentoring/' + data.id,
                    dataType: 'JSON',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function () {
                    alert("수정이 완료되었습니다.");
                    window.location.href = '/mentoring/read/' + data.id;
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        }
    },

    //멘토링 소개글 삭제
    deleteMentoring : function () {
        const id = $('#id').val();
        const con_check = confirm("삭제 후 복구는 불가능합니다. 삭제하시겠습니까?");

        if(con_check === true) {
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/mentoring/' + id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert("삭제되었습니다.");
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        } else {
            return false;
        }
    }

};

main.init();