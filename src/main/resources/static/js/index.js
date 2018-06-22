function index_login_cback(a, b) {
    b.data("disabled", 0), a.status ? (b.find(".btn-login-submit").hide(), b.find(".btn-login-msg").text(a.msg).show(), setTimeout(function () {
        document.location.reload()
    }, 1e3)) : (b.find(".btn-login-submit").hide(), b.find(".btn-login-msg").text(a.msg).show())
}

$(function () {
    $(".index-logged-out-wrapper").mouseenter(function () {
        return $("#qtip-indexLoginDialog").is(":visible") ? !1 : void $(this).tip({
            remote: url("login/dialog"),
            tipid: "indexLoginDialog",
            width: 235,
            adjusty: -5
        })
    }), $("#backTop").remove(), $(".play-btn-mask").hover(function () {
        $(this).siblings("span").stop(!0).animate({opacity: 1}, 150), $(this).stop(!0).animate({opacity: .5}, 200)
    }, function (a) {
        return $(a.relatedTarget).is($("span")) ? !1 : ($(this).siblings("span").animate({opacity: 0}, 200), void $(this).animate({opacity: 0}, 200))
    }), 0 == $("#txtCityId").val() && $.luoo.get(url("event/local"), {}, function (a) {
        a.data && $("#eventHolder").html(a.data)
    }), $("#slider").flexslider({
        animationLoop: !0,
        controlNav: !0,
        directionNav: !1,
        itemWidth: 960,
        move: 1,
        slideshowSpeed: 5e3
    })
});