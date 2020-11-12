// MINDsLab UX/UI팀. YMJ. 20200910

$(document).ready(function () {
    //문의하기(인풋 라벨)
    var contactLabel = $('#lyr_contact_us input,#lyr_contact_us textarea');

    contactLabel.on('focus', function () {
        $(this).siblings('label').hide();
    });

    contactLabel.on('focusout', function () {
        if ($(this).val() === '') {
            $(this).siblings('label').show();
        }
    });

    //문의하기(focus이동)
    $('#btn_contact').on('click', function () {
        $('#lyr_contact_us').find('#user_name').delay(300).queue(function () {
            $(this).focus();
        });
    });

    function validateEmail(sEmail) {
        var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if (filter.test(sEmail)) {
            return true;
        } else {
            return false;
        }
    }

    //문의하기(완료메세지)
    $('#btn_sendMail').on('click', function () {
        if (validateEmail($('#user_email').val()) == false) {
            alert("이메일 형식을 확인해 주세요!");
        } else if ($('#user_name').val() === '' || $('#user_phone').val() === '' || $('#user_e	mail').val() === '' || $('#user_txt').val() === '') {

            alert("내용을 모두 입력해야 이메일을 전달할 수 있어요!");

        } else {
            var noti = "maum.ai 아카데미(KR)";

            var param = location.search;
            var host = window.location.hostname;
            var pathname = window.location.pathname;


            let title = "[maumacademy.maum.ai] " + $("#user_name").val() + "님의 문의 사항입니다.";
            let msg = "이름 : " + $("#user_name").val() + "<br>이메일 : " + $('#user_email').val() + "<br>연락처 : " + $("#user_phone").val() + "<br>문의 내용 : " + $("#txt").val()
                + "<br><br><br>* " + noti + " 에서 보낸 메일입니다.";

            let formData = new FormData();

            formData.append('fromaddr', $('#user_email').val());
            formData.append('toaddr', 'maum_academy@mindslab.ai');
            formData.append('subject', title);
            formData.append('message', msg);

            $.ajax({
                type: 'POST',
                async: true,
                url: '/support/sendContactMail',
                // crossDomain: true,
                dataType: 'text',
                data: formData,
                processData: false,
                contentType: false,
                crossDomain: true,
                crossOrigin: true,
                success: function (obj) {
                    //문의하기 close
                    $('body').find('#lyr_contact_model').unwrap();
                    $('.lyr_bg').remove();

                    //완료메세지
                    $('body').append(' \
                <div class="lyrWrap moment"> \
                    <div class="lyr_bg"></div> \
                    <div class="lyrBox" >\
						<div class="lyr_mid"> \
							<div class="ani_icoBox"> \
								<img src="images/ani_email_send.gif" alt="접수완료"> \
							</div> \
							<p class="massage">문의가 접수되었습니다.<br>담당자 확인 후 연락드리겠습니다.</p> \
						</div> \
					</div> \
				</div> \
			');

                    //문의하기 초기화
                    $('.contact_form .ipt_txt').val('');
                    $('.contact_form .textArea').val('')
                    $('.contact_form label').css('display', 'block');

                    $('.moment, .moment .lyrBox').show();
                    setTimeout(function () {
                        $('.moment').addClass('lyr_hide').delay(300).queue(function () {
                            $(this).remove();
                        });
                    }, 2000);
                    $('body').css('overflow', '');
                    },
                error: function (xhr, status, error) {
                    console.log(error);
                    alert("Contact Us 메일발송 요청 실패하였습니다.");
                    // window.location.href = "/";
                }
            });
        }

    });
});

$(window).scroll(function () {
    var winTop = $(window).scrollTop(),
        imgPstion = winTop;

    if (winTop > 10) {
        $('.svc_visual .bg_img').css({
            'transform': 'matrix(1,0,0,1,0, -' + imgPstion + ')',
        });

    } else {
        $('.svc_visual .bg_img').css({
            'transform': 'matrix(1,0,0,1, 0, 0)',
        });
    }

});

