function update_player() {
    var a = luooPlayer.playlist[luooPlayer.current];
    $(".PLTrackname").text(a.title).attr("title", a.title), $(".PLCover").attr("src", a.poster), $(".PLArtist").text(a.artist).attr("title", a.artist), $(".PLAlbum").text(a.album).attr("title", a.album), $(".PLShare, .PFShare").data({
        id: a.id,
        text: "推荐" + a.artist + "的歌曲《" + a.title + "》（分享自@落网）",
        img: a.poster
    }), $(".PLFav, .PFFav").data("id", a.id), a.is_fav || 1 == $("#track" + a.id).data("fav") ? ($(".PLFav").removeClass("icon-fav").addClass("icon-faved"), $(".PFFav").removeClass("icon-follow-fav").addClass("icon-follow-faved")) : ($(".PLFav").removeClass("icon-faved").addClass("icon-fav"), $(".PFFav").removeClass("icon-follow-faved").addClass("icon-follow-fav")), $("#playerCt .icon-lyric").data("sid", a.id), $("#lyricWrapper").is($(":visible")) && $("#lyricWrapper").fadeOut(150, function () {
        $("#playerCt").show()
    }), $("#lyricCt").html(""), $("#PFName").text(a.title).attr("title", a.title), $("#PFCover").attr("src", a.poster), $("#PFArtist").text(a.artist).attr("title", a.artist)
}

var luooPlayer, log_int, $staticImg = staticImg;
$(document).ready(function () {
    var a = $("#thanks_btn"), b = $("#script_thanks");
    a.click(function () {
        var e = $(this), f = $("#commentForm");
        if (!f.data("login")) {
            var g = e.tip();
            return g.set("events.hide", function (a, b) {
                $.luoo.destroy_tip(b.id)
            }), $.luoo.tips.push(g), !1
        }
        $("#popup_thanks_pay").length > 0 || (a.parent(".thanks-block").append(b.html()), c(), $("#form_thanks").submit(function () {
            return d($("#setMoney")) ? (setTimeout(function () {
                $("#popup_thanks_pay").remove()
            }, 200), !0) : !1
        }), i($("#popup_thanks_pay")))
    });
    var c = function () {
        $("#setMoney").focus(function () {
            var a = $("#thanks_submit");
            $(this).parent(".input-warpper").siblings("input[type=radio]").prop("checked", !0), a.html("表示感谢").css({
                "background-color": "#dd5862",
                cursor: "pointer"
            })
        })
    }, d = function (a) {
        var b = $.trim(a.val()), c = $("#setMoney").parent(".input-warpper").siblings("input[type=radio]"),
            d = $("#thanks_submit");
        if ("" != b) {
            if (isNaN(b)) return e(d, "金额必须为数字"), !1;
            if (.01 > b) return e(d, "金额必须大于0.01元"), !1;
            if (b >= 1e8) return e(d, "金额必须少于一亿"), !1;
            var f = /^[0-9]*[1-9][0-9]*$/;
            if (!f.test(100 * b)) return e(d, "金额请保留两位小数"), !1
        } else if (c.prop("checked")) return e(d, "金额不能空"), !1;
        return c.val(b), !0
    }, e = function (a, b) {
        a.html(b).css({"background-color": "#F2C5C3", cursor: "default"})
    }, f = $("#openList"), g = $("#script_thanks_list");
    f.click(function () {
        if (!($("#popup_thanks_list").length > 0)) {
            var b = a.parent(".thanks-block").append(g.html());
            $.ajax({
                url: "/vol/emoluments",
                type: "get",
                dataType: "json",
                data: {res_id: $("#resId").attr("value")},
                success: function (a) {
                    h(a.rows, b)
                },
                error: function (a) {
                }
            })
        }
    });
    var h = function (a, b) {
        for (var c = "", d = 0; d < a.length; d++) c += '<li><span class="item-container"><a target="_blank" href="/user/' + a[d].uid + '"><img class="lacy-sel" style="background:url(' + $staticImg + ') center no-repeat;" data-src="' + a[d].avatar + '" src="' + a[d].avatar + '"/><span class="nikeName">' + a[d].user_name + "</span></span></a></li>";
        b.find(".thanks-list").html(c), new j($(".lacy-sel"), $(".lazy-outer")), $(".thanks-list li").hover(function () {
            $(this).addClass("hover")
        }, function () {
            $(this).removeClass("hover")
        }), i($("#popup_thanks_list"))
    }, i = function (a) {
        a.find(".aui_close").click(function () {
            a.remove()
        })
    }, j = function (a, b) {
        var c, d = this, e = [], f = {}, g = b || $(window), h = function (a) {
            "img" == a.prop("tagName").toLowerCase() ? "" != a.attr("data-src") && a.attr("src", a.attr("data-src")) : "" != a.attr("data-src") && a.css({"background-image": "url(" + a.attr("data-src") + ")"})
        }, i = function () {
            f.width = g.width(), f.height = g.height(), f.scrollTop = g.scrollTop()
        }, j = function (a) {
            var b = a.offset();
            return b.top - g.offset().top < g.height() + 40 ? !0 : !1
        }, k = function () {
            e = $.grep(e, function (a) {
                return j(a) ? (h(a), !1) : !0
            }), 0 == e.length && g.off("scroll")
        }, l = function () {
            f.scrollTop = g.scrollTop(), clearTimeout(c), c = setTimeout(k, 100)
        }, m = function () {
            i(g), d.add(a), g.on("scroll", l), g.on("resize", i), setTimeout(function () {
                g.trigger("scroll")
            }, 50)
        };
        this.add = function (a) {
            a.each(function (a, b) {
                var c = $(b);
                e.push(c)
            })
        }, m()
    };
    init_player({playCback: "update_player"}), $.luoo.cfg.auto_play || update_player(), sid && luooPlayer.play($("#track" + sid).index()), $("#volCoverWrapper").hover(function () {
        $(this).children(".nav-prev, .nav-next").fadeIn(300)
    }, function () {
        $(this).children(".nav-prev, .nav-next").fadeOut()
    }), $(".icon-lyric-active").click(function () {
        $("#lyricWrapper").fadeOut(150, function () {
            $("#playerCt").show()
        })
    }), $(".icon-lyric").click(function () {
        var a = $(this).data("sid");
        "" == $("#lyricCt").html() && $.luoo.get(url("single/lyric/" + a), {}, function (a, b) {
            a.lyric ? $("#lyricCt").html(a.lyric.content) : $("#lyricCt").html('<p style="margin: 60px auto; text-align: center;"><a href="javascript:;" class="btn btn-positive btn-add-lyric" data-sid="' + b + '">添加歌词</a></p>')
        }, a), $("#playerCt").fadeOut(150, function () {
            $("#lyricWrapper").show()
        })
    }), $("#PLBtnVolume").click(function () {
        0 == $("#PLVolBar").width() ? ($("#PLDuration").animate({width: 0}, 100, function () {
            $(this).hide()
        }), $("#PLVolBar").animate({width: 86}, 150)) : ($("#PLVolBar").width(0), $("#PLDuration").show().animate({width: 85}, 150))
    }), $("body").on("click", function (a) {
        $(a.target).parents().is($("#PLVolBar")) || $(a.target).parents().is($("#PLBtnVolume")) || ($("#PLVolBar").width(0), $("#PLDuration").show().animate({width: 85}, 150))
    }), $("#playerFollow").stick_in_parent({offset_top: 0, parent: $("body")}).on("sticky_kit:stick", function (a) {
        $(a.target).animate({opacity: 1}, 300)
    }).on("sticky_kit:unstick", function (a) {
        $(a.target).animate({opacity: 0}, 300)
    }), $("body").on("keyup", function (a) {
        70 == a.keyCode && $.luoo.res.uid && $("#track" + luooPlayer.playlist[luooPlayer.current].id + " .btn-action-like").click()
    }), update_player()
});