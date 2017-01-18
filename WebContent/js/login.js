var URLS = {
    VERIFICATION_CODE : contextPath + "/kaptcha.jpg"
}

$(function() {
    $('#verification-code-img').click(function() {
        var src = URLS.VERIFICATION_CODE + "?" + Math.floor(Math.random() * 100);
        $(this).hide().attr('src', src).fadeIn();
    });

    $('#login-form').submit(function() {
        var data = $(this).serializeJson();

        if (data.username == '') {
            new Message().tipLeft('#to-login-btn', '请输入用户名');
            return false;
        }
        if (data.password == '') {
            new Message().tipLeft('#to-login-btn', '请输入密码');
            return false;
        }
        if (data.verificationCode == '') {
            new Message().tipLeft('#to-login-btn', '请输入验证码');
            return false;
        }

        $('#to-login-btn').button('loading');
        return true;
    });

});