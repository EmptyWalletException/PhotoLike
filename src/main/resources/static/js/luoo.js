

function check_login(a) {
    $.luoo.get(url("login/user"), "", function (b) {
        b.status ? ($("#loggedOutWrapper").hide(), $("#loggedInWrapper").html(b.msg).show(), $("#commentAvatarHolder").attr("href", $.luoo.root + "/user/" + b.data.uid), $("#commentAvatarHolder img").attr("src", b.data.user_avatar), $("#commentForm").data("login", 1), $("#lnActiveTip").tip("", !1)) : "undefined" != typeof a ? ($("#loggedOutWrapper").hide(), $("#loggedInWrapper").html(b.msg).show(), $("#commentForm").data("login", 0)) : ($("#loggedOutWrapper").show(), $("#loggedInWrapper").html("").hide(), $("#commentAvatarHolder").attr("href", "javascript:;"), $("#commentAvatarHolder img").attr("src", $.luoo.url_static + "/img/avatar.gif"), $("#commentForm").data("login", 0))
    })
}

function common_ajax_callback(a) {
    if (a.msg) {
        var b = '<p style="margin-bottom: 10px;">' + a.msg + "</p>";
        return $.luoo.msg(b), !1
    }
}

function like_callback(a, b) {
    if (b.data("disabled", !1), -1 === a.status) {
        b.data({remote: url("login/dialog"), width: 235});
        var c = b.tip();
        c.set("events.hide", function (a, b) {
            b.destroy(), $.luoo.destroy_tip(b.id)
        }), $.luoo.tip.push(c)
    } else 1 === a.status ? b.removeClass("icon-fav").addClass("icon-faved").attr("title", b.data("langliked")) : 2 === a.status ? b.removeClass("icon-faved").addClass("icon-fav").attr("title", b.data("langlike")) : a.msg && $.luoo.tip(a.msg)
}

function single_like_callback(a, b) {
    b.data("disabled", !1);
    var c = $("#track" + b.data("id"));
    if (-1 === a.status) {
        b.data({remote: url("login/dialog"), width: 235});
        var d = b.tip();
        d.set("events.hide", function (a, b) {
            b.destroy(), $.luoo.destroy_tip(b.id)
        }), $.luoo.tip.push(d)
    } else 1 === a.status ? (c.find(".btn-action-like").removeClass("icon-fav").addClass("icon-faved").attr("title", b.data("langliked")), c.data("fav", 1), $(".PLFav").data("id") == b.data("id") && ($(".PLFav").removeClass("icon-fav").addClass("icon-faved"), $(".PFFav").removeClass("icon-follow-fav").addClass("icon-follow-faved"))) : 2 === a.status ? (c.find(".btn-action-like").removeClass("icon-faved").addClass("icon-fav").attr("title", b.data("langlike")), c.data("fav", 0), $(".PLFav").data("id") == b.data("id") && ($(".PLFav").removeClass("icon-faved").addClass("icon-fav"), $(".PFFav").removeClass("icon-follow-faved").addClass("icon-follow-fav"))) : a.msg && $.luoo.tip(a.msg)
}

function refresh_captcha() {
    var a = $.luoo.root + "/data/captcha?" + (new Date).getTime();
    $(".luoo-captcha-wrapper img").attr("src", a)
}

function url(a) {
    return $.luoo.root + "/" + a
}

function head_login_cback(a, b) {
    b.data("disabled", 0), a.status ? (b.find(".btn-login-submit").hide(), b.find(".btn-login-msg").text(a.msg).show(), setTimeout(function () {
        check_login(a.uid)
    }, 1e3)) : (b.find(".btn-login-submit").hide(), b.find(".btn-login-msg").text(a.msg).show())
}

function login_cback(a, b) {
    b.data("disabled", 0), a.status ? (b.find(".btn-login-submit").hide(), b.find(".btn-login-msg").text(a.msg).show(), setTimeout(function () {
        check_login(a.uid), $.luoo.destroy_tip()
    }, 1e3)) : (b.find(".btn-login-submit").hide(), b.find(".btn-login-msg").text(a.msg).show())
}



function head_register_cback(a, b) {
    b.data("disabled", 0), a.status ? (b.find(".btn-register-submit").hide(), b.find(".btn-register-msg").text(a.msg).show(), setTimeout(function () {
        check_login(a.uid)
    }, 1e3)) : (b.find(".btn-register-submit").hide(), b.find(".btn-register-msg").text(a.errors.msg).show())
}

function register_cback(a, b) {
    b.data("disabled", 0), a.status ? (b.find(".btn-register-submit").hide(), b.find(".btn-register-msg").text(a.msg).show(), setTimeout(function () {
        check_login(a.uid), $.luoo.destroy_tip()
    }, 1e3)) : (b.find(".btn-register-submit").hide(), b.find(".btn-register-msg").text(a.errors.msg).show())
}

function active_email_cback(a) {
    $(".active-info-wrapper").html(a.msg)
}

function create_share_btns(a, b) {
    if (!a.app || !a.id || !a.text) return !1;
    if ("vol" == a.app) var c = url("vol/index/" + parseInt(a.id)); else if ("musician" == a.app) var c = url("musician/index/") + a.id; else var c = url(a.app + "/" + parseInt(a.id));
    var d = a.img ? a.img : "",
        e = "http://service.weibo.com/share/share.php?url=" + encodeURIComponent(c) + "&title=" + encodeURIComponent(a.text) + "&source=" + encodeURIComponent("落网") + "&sourceUrl=" + encodeURIComponent("http://luoo.net") + "&content=utf-8&pic=" + encodeURIComponent(d),
        f = "http://www.douban.com/share/service?bm=1&image=" + encodeURIComponent(d) + "&href=" + encodeURIComponent(c) + "&updated=&name=" + encodeURIComponent(a.text),
        g = "http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" + encodeURIComponent(c) + "&desc=" + encodeURIComponent(a.text) + "&pics=" + encodeURIComponent(d);
    if ("undefined" == typeof b) var h = '<div class="share-btn-wrapper">分享到：<a href="javascript:;" class="btn-action-linkout" data-href="' + e + '" target="_blank" title="分享到微博"><span class="icon-share-weibo"></span></a><a href="javascript:;" class="btn-action-linkout" data-href="' + f + '" target="_blank" title="分享到豆瓣"><span class="icon-share-douban"></span></a><a href="javascript:;" class="btn-action-linkout" data-href="' + g + '" target="_blank" title="分享到QQ空间"><span class="icon-share-qzone"></span></a><a href="javascript:;" class="btn-show-qr" data-href="' + c + '" target="_blank" title="分享到朋友圈"><span class="icon-share-wechat"></span></a></div>'; else var h = '<a href="javascript:;" class="btn-action-linkout" data-href="' + e + '" target="_blank"><span class="icon-share-weibo" title="分享到微博"></span></a><a href="javascript:;" class="btn-action-linkout" data-href="' + f + '" target="_blank" title="分享到豆瓣"><span class="icon-share-douban"></span></a><a href="javascript:;" class="btn-action-linkout" data-href="' + g + '" target="_blank" title="分享到QQ空间"><span class="icon-share-qzone"></span></a><a href="javascript:;" class="btn-show-qr" data-href="' + c + '" target="_blank" title="分享到朋友圈"><span class="icon-share-wechat"></span></a>';
    return h
}

function feedback_cback(a, b) {
    b.data("disabled", 0), a.status ? ($("#frmFeedback").animate({height: 75}, 300, function () {
        $(this).hide(), $("#feedbackResult").show()
    }), setTimeout(function () {
        $.luoo.close_msg()
    }, 3e3)) : $("#feedbackMsg").html('<span style="color: #E43E4A">※ ' + a.msg + "</span>")
}

function update_playlist_btn_status() {
    $(".track-item").each(function (a, b) {
        if ("undefined" != typeof b) {
            var c = $(b);
            "play" == c.data("status") ? (c.find(".duration, .progress, .player-btns").show(), c.find(".icon-status-pause").css("display", "inline-block"), c.find(".icon-status-play").hide(), c.find(".icon-info, .icon-fav, .icon-faved, .icon-share, .icon-trash").css("visibility", "visible"), c.find(".player-ctl").removeClass("icon-detail-play").addClass("icon-detail-pause")) : "pause" == c.data("status") ? (c.find(".duration, .progress, .player-btns").show(), c.find(".icon-status-play").css("display", "inline-block"), c.find(".icon-status-pause").hide(), c.find(".icon-info, .icon-fav, .icon-faved, .icon-share, .icon-trash").css("visibility", "visible"), c.find(".player-ctl").removeClass("icon-detail-pause").addClass("icon-detail-play")) : (c.find(".icon-status-pause, .icon-status-play, .duration, .progress, .player-btns").hide(), c.find(".icon-info, .icon-fav, .icon-faved, .icon-share, .icon-trash").css("visibility", "hidden")), $(".icon-faved").css("visibility", "visible")
        }
    })
}

function show_track_detail(a, b) {
    0 == $("#trackDetailWrapper" + a).find(".lyric-content").html() && $.luoo.get(url("single/lyric/" + a), {}, function (a, b) {
        a.lyric ? $("#trackDetailWrapper" + b).find(".lyric-content").html(a.lyric.content) : $("#trackDetailWrapper" + a.sid).find(".lyric-content").html('<div class="single-report"><a href="javascript:;" class="icon-single-report btn-single-report" data-sname="' + a.sname + '" data-sid="' + a.sid + '">报错</a></div><p style="margin: 60px auto; text-align: center;"><a href="javascript:;" class="btn btn-positive btn-add-lyric" data-sid="' + b + '">添加歌词</a></p>')
    }, a), $("#trackDetailWrapper" + a).show()
}

function init_player(module_options) {
    if ("undefined" == typeof pl || !pl) return !1;
    try {
        eval("var playlist = " + GibberishAES.dec(pl, $.luoo.cfg.aes))
    } catch (e) {
        console.error("Error initating player")
    }
    if ("undefined" == typeof playlist) return !1;
    $(".track-wrapper").hover(function () {
        $(this).find(".icon-info, .icon-fav, .icon-share, .icon-trash").css("visibility", "visible")
    }, function () {
        return $(this).siblings(".track-detail-wrapper").is($(":visible")) || "play" == $(this).parents(".track-item").data("status") ? !1 : ($(this).find(".icon-info, .icon-fav,.icon-share, .icon-trash").css("visibility", "hidden"), void $(".icon-faved").css("visibility", "visible"))
    });
    var cssSelector = {currentTime: ".current-time", duration: ".total-time", seekBar: ".loaded", playBar: ".escaped"},
        cssSelectorAncestor = {jPlayer: "#luooPlayerContent", cssSelectorAncestor: "#luooPlayerPlaylist"},
        luoovolume = $.cookie("luoovolume") ? $.cookie("luoovolume") : .8;
    if ("undefined" != typeof module_options && "undefined" != typeof module_options.autoplay) var autoplay = 0 == module_options.autoplay ? !1 : !0; else var autoplay = $.luoo.cfg.auto_play;
    var options = {
        playlistOptions: {autoPlay: autoplay, loopOnPrevious: !0, repeatone: !1},
        swfPath: $.luoo.root + "/static/thirdparty/jplayer",
        supplied: "mp3",
        loop: !0,
        playbackRate: 2,
        keyEnabled: !0,
        cssSelector: cssSelector,
        wmode: "window",
        volume: luoovolume
    };
    return luooPlayer = new jPlayerPlaylist(cssSelectorAncestor, playlist, options), $("#luooPlayerPlaylist").bind($.jPlayer.event.ready, function () {
        var a = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (a) {
            var b = 16 * Math.random() | 0, c = "x" == a ? b : 3 & b | 8;
            return c.toString(16)
        });
        luooPlayer.playerid = a
    }), $("#luooPlayerPlaylist").bind($.jPlayer.event.play, function () {
        var trackid = luooPlayer.playlist[luooPlayer.current].id, $track = $("#track" + trackid);
        if ($(".track-item").data("status", "ready"), $track.data("status", "play"), update_playlist_btn_status(), "undefined" != typeof module_options && "undefined" != typeof module_options.updateTitle && 0 != module_options.updateTitle && $("title").text(luooPlayer.playlist[luooPlayer.current].title), "undefined" != typeof module_options && "undefined" != typeof module_options.playCback && eval(module_options.playCback + "()"), parseInt($.luoo.res.uid) > 0 && (clearTimeout(log_int), log_int = setTimeout(function () {
            $.luoo.get(url("data/play/" + luooPlayer.playlist[luooPlayer.current].id))
        }, 3e4)), "undefined" != typeof window.WebSocket) try {
            var wsServer = "ws://muma.luoo.net:8000/", websocket = new WebSocket(wsServer);
            websocket.onopen = function () {
                websocket.send(JSON.stringify({
                    sessid: $.luoo.res.sessid,
                    playerid: luooPlayer.playerid
                })), luooPlayer.readyStatus = 1
            }, websocket.onmessage = function (a) {
                var a = JSON.parse(a.data);
                a.sessid == $.luoo.res.sessid && luooPlayer.playerid != a.playerid && luooPlayer.pause()
            }, websocket.onerror = function (a) {
            }, websocket.onclose = function () {
            }
        } catch (e) {
            console.error("websocket error")
        }
    }), $("#luooPlayerPlaylist").bind($.jPlayer.event.pause, function (a) {
        $("#track" + luooPlayer.playlist[luooPlayer.current].id).data("status", "pause"), update_playlist_btn_status()
    }), $("#luooPlayerPlaylist").bind($.jPlayer.event.error, function (a) {
        console.log(a)
    }), $("#luooPlayerPlaylist").bind($.jPlayer.event.volumechange, function (a) {
        "undefined" != typeof a.jPlayer.options.volume && a.jPlayer.options.volume > 0 && $.cookie("luoovolume", a.jPlayer.options.volume)
    }), $(".icon-info").click(function () {
        var a = $("#track" + $(this).data("sid"));
        a.find(".track-detail-wrapper").is($(":visible")) ? a.find(".track-detail-wrapper").hide() : show_track_detail($(this).data("sid"), $(this).data("sname"))
    }), $(".btn-play, .player-ctl").click(function () {
        var a = $(this).parents(".track-item"), b = a.index();
        "play" == a.data("status") ? (a.data("status", "pause"), luooPlayer.pause()) : luooPlayer.current == b ? ($(this).data("status", "play"), luooPlayer.play()) : ($(this).data("status", "play"), luooPlayer.play(b))
    }), !0
}

function add_lyric_cback(a, b) {
    b.data("disabled", 0), 1 == a.status ? (a.data && ($("#trackDetailWrapper" + a.data).find(".lyric-content").html(""), show_track_detail(a.data)), $.luoo.close_msg()) : (b.find(".btn-positive").hide(), b.find(".btn-not-ready").text(a.msg).show())
}

function single_report_cback(a, b) {
    b.data("disabled", 0), 1 == a.status ? $("#singleReportWrapper").animate({height: 0}, 300, function () {
        $(this).html("<p>提交成功，感谢你的热心支持</p>").height(45), setTimeout(function () {
            $.luoo.close_msg()
        }, 1500)
    }) : (b.find(".btn-positive").hide(), b.find(".btn-not-ready").text(a.msg).show())
}

function send_mail_cback(a, b) {
    b.data("disabled", 0), a.status ? ($("#frmMail").animate({height: 75}, 300, function () {
        $(this).hide(), $("#sendMailResult").show()
    }), setTimeout(function () {
        $.luoo.close_msg()
    }, 3e3)) : $("#sendMailMsg").html('<span style="color: #E43E4A">※ ' + a.msg + "</span>")
}

function html_encode(a) {
    var b = "";
    return 0 == a.length ? "" : (b = a.replace(/<br(>| \/>|\/>)/g, "\n\r"), b = b.replace(/&/g, "&gt;"), b = b.replace(/</g, "&lt;"), b = b.replace(/>/g, "&gt;"), b = b.replace(/\n\r/g, "<br>"))
}

!function (a, b) {
    "object" == typeof exports ? module.exports = b() : "function" == typeof define && define.amd ? define(b) : a.GibberishAES = b()
}(this, function () {
    "use strict";
    var a = 14, b = 8, c = !1, d = function (a) {
            try {
                return unescape(encodeURIComponent(a))
            } catch (b) {
                throw"Error on UTF-8 encode"
            }
        }, e = function (a) {
            try {
                return decodeURIComponent(escape(a))
            } catch (b) {
                throw"Bad Key"
            }
        }, f = function (a) {
            var b, c, d = [];
            for (16 > a.length && (b = 16 - a.length, d = [b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b]), c = 0; a.length > c; c++) d[c] = a[c];
            return d
        }, g = function (a, b) {
            var c, d, e = "";
            if (b) {
                if (c = a[15], c > 16) throw"Decryption error: Maybe bad key";
                if (16 === c) return "";
                for (d = 0; 16 - c > d; d++) e += String.fromCharCode(a[d])
            } else for (d = 0; 16 > d; d++) e += String.fromCharCode(a[d]);
            return e
        }, h = function (a) {
            var b, c = "";
            for (b = 0; a.length > b; b++) c += (16 > a[b] ? "0" : "") + a[b].toString(16);
            return c
        }, i = function (a) {
            var b = [];
            return a.replace(/(..)/g, function (a) {
                b.push(parseInt(a, 16))
            }), b
        }, j = function (a, b) {
            var c, e = [];
            for (b || (a = d(a)), c = 0; a.length > c; c++) e[c] = a.charCodeAt(c);
            return e
        }, k = function (c) {
            switch (c) {
                case 128:
                    a = 10, b = 4;
                    break;
                case 192:
                    a = 12, b = 6;
                    break;
                case 256:
                    a = 14, b = 8;
                    break;
                default:
                    throw"Invalid Key Size Specified:" + c
            }
        }, l = function (a) {
            var b, c = [];
            for (b = 0; a > b; b++) c = c.concat(Math.floor(256 * Math.random()));
            return c
        }, m = function (c, d) {
            var e, f = a >= 12 ? 3 : 2, g = [], h = [], i = [], j = [], k = c.concat(d);
            for (i[0] = O(k), j = i[0], e = 1; f > e; e++) i[e] = O(i[e - 1].concat(k)), j = j.concat(i[e]);
            return g = j.slice(0, 4 * b), h = j.slice(4 * b, 4 * b + 16), {key: g, iv: h}
        }, n = function (a, b, c) {
            b = w(b);
            var d, e = Math.ceil(a.length / 16), g = [], h = [];
            for (d = 0; e > d; d++) g[d] = f(a.slice(16 * d, 16 * d + 16));
            for (0 === a.length % 16 && (g.push([16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16]), e++), d = 0; g.length > d; d++) g[d] = 0 === d ? v(g[d], c) : v(g[d], h[d - 1]), h[d] = p(g[d], b);
            return h
        }, o = function (a, b, c, d) {
            b = w(b);
            var f, h = a.length / 16, i = [], j = [], k = "";
            for (f = 0; h > f; f++) i.push(a.slice(16 * f, 16 * (f + 1)));
            for (f = i.length - 1; f >= 0; f--) j[f] = q(i[f], b), j[f] = 0 === f ? v(j[f], c) : v(j[f], i[f - 1]);
            for (f = 0; h - 1 > f; f++) k += g(j[f]);
            return k += g(j[f], !0), d ? k : e(k)
        }, p = function (b, d) {
            c = !1;
            var e, f = u(b, d, 0);
            for (e = 1; a + 1 > e; e++) f = r(f), f = s(f), a > e && (f = t(f)), f = u(f, d, e);
            return f
        }, q = function (b, d) {
            c = !0;
            var e, f = u(b, d, a);
            for (e = a - 1; e > -1; e--) f = s(f), f = r(f), f = u(f, d, e), e > 0 && (f = t(f));
            return f
        }, r = function (a) {
            var b, d = c ? E : D, e = [];
            for (b = 0; 16 > b; b++) e[b] = d[a[b]];
            return e
        }, s = function (a) {
            var b, d = [],
                e = c ? [0, 13, 10, 7, 4, 1, 14, 11, 8, 5, 2, 15, 12, 9, 6, 3] : [0, 5, 10, 15, 4, 9, 14, 3, 8, 13, 2, 7, 12, 1, 6, 11];
            for (b = 0; 16 > b; b++) d[b] = a[e[b]];
            return d
        }, t = function (a) {
            var b, d = [];
            if (c) for (b = 0; 4 > b; b++) d[4 * b] = L[a[4 * b]] ^ J[a[1 + 4 * b]] ^ K[a[2 + 4 * b]] ^ I[a[3 + 4 * b]], d[1 + 4 * b] = I[a[4 * b]] ^ L[a[1 + 4 * b]] ^ J[a[2 + 4 * b]] ^ K[a[3 + 4 * b]], d[2 + 4 * b] = K[a[4 * b]] ^ I[a[1 + 4 * b]] ^ L[a[2 + 4 * b]] ^ J[a[3 + 4 * b]], d[3 + 4 * b] = J[a[4 * b]] ^ K[a[1 + 4 * b]] ^ I[a[2 + 4 * b]] ^ L[a[3 + 4 * b]]; else for (b = 0; 4 > b; b++) d[4 * b] = G[a[4 * b]] ^ H[a[1 + 4 * b]] ^ a[2 + 4 * b] ^ a[3 + 4 * b], d[1 + 4 * b] = a[4 * b] ^ G[a[1 + 4 * b]] ^ H[a[2 + 4 * b]] ^ a[3 + 4 * b], d[2 + 4 * b] = a[4 * b] ^ a[1 + 4 * b] ^ G[a[2 + 4 * b]] ^ H[a[3 + 4 * b]], d[3 + 4 * b] = H[a[4 * b]] ^ a[1 + 4 * b] ^ a[2 + 4 * b] ^ G[a[3 + 4 * b]];
            return d
        }, u = function (a, b, c) {
            var d, e = [];
            for (d = 0; 16 > d; d++) e[d] = a[d] ^ b[c][d];
            return e
        }, v = function (a, b) {
            var c, d = [];
            for (c = 0; 16 > c; c++) d[c] = a[c] ^ b[c];
            return d
        }, w = function (c) {
            var d, e, f, g, h = [], i = [], j = [];
            for (d = 0; b > d; d++) e = [c[4 * d], c[4 * d + 1], c[4 * d + 2], c[4 * d + 3]], h[d] = e;
            for (d = b; 4 * (a + 1) > d; d++) {
                for (h[d] = [], f = 0; 4 > f; f++) i[f] = h[d - 1][f];
                for (0 === d % b ? (i = x(y(i)), i[0] ^= F[d / b - 1]) : b > 6 && 4 === d % b && (i = x(i)), f = 0; 4 > f; f++) h[d][f] = h[d - b][f] ^ i[f]
            }
            for (d = 0; a + 1 > d; d++) for (j[d] = [], g = 0; 4 > g; g++) j[d].push(h[4 * d + g][0], h[4 * d + g][1], h[4 * d + g][2], h[4 * d + g][3]);
            return j
        }, x = function (a) {
            for (var b = 0; 4 > b; b++) a[b] = D[a[b]];
            return a
        }, y = function (a) {
            var b, c = a[0];
            for (b = 0; 4 > b; b++) a[b] = a[b + 1];
            return a[3] = c, a
        }, z = function (a, b) {
            var c, d = [];
            for (c = 0; a.length > c; c += b) d[c / b] = parseInt(a.substr(c, b), 16);
            return d
        }, A = function (a) {
            var b, c = [];
            for (b = 0; a.length > b; b++) c[a[b]] = b;
            return c
        }, B = function (a, b) {
            var c, d;
            for (d = 0, c = 0; 8 > c; c++) d = 1 === (1 & b) ? d ^ a : d, a = a > 127 ? 283 ^ a << 1 : a << 1, b >>>= 1;
            return d
        }, C = function (a) {
            var b, c = [];
            for (b = 0; 256 > b; b++) c[b] = B(a, b);
            return c
        },
        D = z("637c777bf26b6fc53001672bfed7ab76ca82c97dfa5947f0add4a2af9ca472c0b7fd9326363ff7cc34a5e5f171d8311504c723c31896059a071280e2eb27b27509832c1a1b6e5aa0523bd6b329e32f8453d100ed20fcb15b6acbbe394a4c58cfd0efaafb434d338545f9027f503c9fa851a3408f929d38f5bcb6da2110fff3d2cd0c13ec5f974417c4a77e3d645d197360814fdc222a908846eeb814de5e0bdbe0323a0a4906245cc2d3ac629195e479e7c8376d8dd54ea96c56f4ea657aae08ba78252e1ca6b4c6e8dd741f4bbd8b8a703eb5664803f60e613557b986c11d9ee1f8981169d98e949b1e87e9ce5528df8ca1890dbfe6426841992d0fb054bb16", 2),
        E = A(D), F = z("01020408102040801b366cd8ab4d9a2f5ebc63c697356ad4b37dfaefc591", 2), G = C(2), H = C(3),
        I = C(9), J = C(11), K = C(13), L = C(14), M = function (a, b, c) {
            var d, e = l(8), f = m(j(b, c), e), g = f.key, h = f.iv, i = [[83, 97, 108, 116, 101, 100, 95, 95].concat(e)];
            return a = j(a, c), d = n(a, g, h), d = i.concat(d), P.encode(d)
        }, N = function (a, b, c) {
            var d = P.decode(a), e = d.slice(8, 16), f = m(j(b, c), e), g = f.key, h = f.iv;
            return d = d.slice(16, d.length), a = o(d, g, h, c)
        }, O = function (a) {
            function b(a, b) {
                return a << b | a >>> 32 - b
            }

            function c(a, b) {
                var c, d, e, f, g;
                return e = 2147483648 & a, f = 2147483648 & b, c = 1073741824 & a, d = 1073741824 & b, g = (1073741823 & a) + (1073741823 & b), c & d ? 2147483648 ^ g ^ e ^ f : c | d ? 1073741824 & g ? 3221225472 ^ g ^ e ^ f : 1073741824 ^ g ^ e ^ f : g ^ e ^ f
            }

            function d(a, b, c) {
                return a & b | ~a & c
            }

            function e(a, b, c) {
                return a & c | b & ~c
            }

            function f(a, b, c) {
                return a ^ b ^ c
            }

            function g(a, b, c) {
                return b ^ (a | ~c)
            }

            function h(a, e, f, g, h, i, j) {
                return a = c(a, c(c(d(e, f, g), h), j)), c(b(a, i), e)
            }

            function i(a, d, f, g, h, i, j) {
                return a = c(a, c(c(e(d, f, g), h), j)), c(b(a, i), d)
            }

            function j(a, d, e, g, h, i, j) {
                return a = c(a, c(c(f(d, e, g), h), j)), c(b(a, i), d)
            }

            function k(a, d, e, f, h, i, j) {
                return a = c(a, c(c(g(d, e, f), h), j)), c(b(a, i), d)
            }

            function l(a) {
                for (var b, c = a.length, d = c + 8, e = (d - d % 64) / 64, f = 16 * (e + 1), g = [], h = 0, i = 0; c > i;) b = (i - i % 4) / 4, h = 8 * (i % 4), g[b] = g[b] | a[i] << h, i++;
                return b = (i - i % 4) / 4, h = 8 * (i % 4), g[b] = g[b] | 128 << h, g[f - 2] = c << 3, g[f - 1] = c >>> 29, g
            }

            function m(a) {
                var b, c, d = [];
                for (c = 0; 3 >= c; c++) b = 255 & a >>> 8 * c, d = d.concat(b);
                return d
            }

            var n, o, p, q, r, s, t, u, v, w = [],
                x = z("67452301efcdab8998badcfe10325476d76aa478e8c7b756242070dbc1bdceeef57c0faf4787c62aa8304613fd469501698098d88b44f7afffff5bb1895cd7be6b901122fd987193a679438e49b40821f61e2562c040b340265e5a51e9b6c7aad62f105d02441453d8a1e681e7d3fbc821e1cde6c33707d6f4d50d87455a14eda9e3e905fcefa3f8676f02d98d2a4c8afffa39428771f6816d9d6122fde5380ca4beea444bdecfa9f6bb4b60bebfbc70289b7ec6eaa127fad4ef308504881d05d9d4d039e6db99e51fa27cf8c4ac5665f4292244432aff97ab9423a7fc93a039655b59c38f0ccc92ffeff47d85845dd16fa87e4ffe2ce6e0a30143144e0811a1f7537e82bd3af2352ad7d2bbeb86d391", 8);
            for (w = l(a), s = x[0], t = x[1], u = x[2], v = x[3], n = 0; w.length > n; n += 16) o = s, p = t, q = u, r = v, s = h(s, t, u, v, w[n + 0], 7, x[4]), v = h(v, s, t, u, w[n + 1], 12, x[5]), u = h(u, v, s, t, w[n + 2], 17, x[6]), t = h(t, u, v, s, w[n + 3], 22, x[7]), s = h(s, t, u, v, w[n + 4], 7, x[8]), v = h(v, s, t, u, w[n + 5], 12, x[9]), u = h(u, v, s, t, w[n + 6], 17, x[10]), t = h(t, u, v, s, w[n + 7], 22, x[11]), s = h(s, t, u, v, w[n + 8], 7, x[12]), v = h(v, s, t, u, w[n + 9], 12, x[13]), u = h(u, v, s, t, w[n + 10], 17, x[14]), t = h(t, u, v, s, w[n + 11], 22, x[15]), s = h(s, t, u, v, w[n + 12], 7, x[16]), v = h(v, s, t, u, w[n + 13], 12, x[17]), u = h(u, v, s, t, w[n + 14], 17, x[18]), t = h(t, u, v, s, w[n + 15], 22, x[19]), s = i(s, t, u, v, w[n + 1], 5, x[20]), v = i(v, s, t, u, w[n + 6], 9, x[21]), u = i(u, v, s, t, w[n + 11], 14, x[22]), t = i(t, u, v, s, w[n + 0], 20, x[23]), s = i(s, t, u, v, w[n + 5], 5, x[24]), v = i(v, s, t, u, w[n + 10], 9, x[25]), u = i(u, v, s, t, w[n + 15], 14, x[26]), t = i(t, u, v, s, w[n + 4], 20, x[27]), s = i(s, t, u, v, w[n + 9], 5, x[28]), v = i(v, s, t, u, w[n + 14], 9, x[29]), u = i(u, v, s, t, w[n + 3], 14, x[30]), t = i(t, u, v, s, w[n + 8], 20, x[31]), s = i(s, t, u, v, w[n + 13], 5, x[32]), v = i(v, s, t, u, w[n + 2], 9, x[33]), u = i(u, v, s, t, w[n + 7], 14, x[34]), t = i(t, u, v, s, w[n + 12], 20, x[35]), s = j(s, t, u, v, w[n + 5], 4, x[36]), v = j(v, s, t, u, w[n + 8], 11, x[37]), u = j(u, v, s, t, w[n + 11], 16, x[38]), t = j(t, u, v, s, w[n + 14], 23, x[39]), s = j(s, t, u, v, w[n + 1], 4, x[40]), v = j(v, s, t, u, w[n + 4], 11, x[41]), u = j(u, v, s, t, w[n + 7], 16, x[42]), t = j(t, u, v, s, w[n + 10], 23, x[43]), s = j(s, t, u, v, w[n + 13], 4, x[44]), v = j(v, s, t, u, w[n + 0], 11, x[45]), u = j(u, v, s, t, w[n + 3], 16, x[46]), t = j(t, u, v, s, w[n + 6], 23, x[47]), s = j(s, t, u, v, w[n + 9], 4, x[48]), v = j(v, s, t, u, w[n + 12], 11, x[49]), u = j(u, v, s, t, w[n + 15], 16, x[50]), t = j(t, u, v, s, w[n + 2], 23, x[51]), s = k(s, t, u, v, w[n + 0], 6, x[52]), v = k(v, s, t, u, w[n + 7], 10, x[53]), u = k(u, v, s, t, w[n + 14], 15, x[54]), t = k(t, u, v, s, w[n + 5], 21, x[55]), s = k(s, t, u, v, w[n + 12], 6, x[56]), v = k(v, s, t, u, w[n + 3], 10, x[57]), u = k(u, v, s, t, w[n + 10], 15, x[58]), t = k(t, u, v, s, w[n + 1], 21, x[59]), s = k(s, t, u, v, w[n + 8], 6, x[60]), v = k(v, s, t, u, w[n + 15], 10, x[61]), u = k(u, v, s, t, w[n + 6], 15, x[62]), t = k(t, u, v, s, w[n + 13], 21, x[63]), s = k(s, t, u, v, w[n + 4], 6, x[64]), v = k(v, s, t, u, w[n + 11], 10, x[65]), u = k(u, v, s, t, w[n + 2], 15, x[66]), t = k(t, u, v, s, w[n + 9], 21, x[67]), s = c(s, o), t = c(t, p), u = c(u, q), v = c(v, r);
            return m(s).concat(m(t), m(u), m(v))
        }, P = function () {
            var a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", b = a.split(""), c = function (a) {
                var c, d, e = [], f = "";
                for (Math.floor(16 * a.length / 3), c = 0; 16 * a.length > c; c++) e.push(a[Math.floor(c / 16)][c % 16]);
                for (c = 0; e.length > c; c += 3) f += b[e[c] >> 2], f += b[(3 & e[c]) << 4 | e[c + 1] >> 4], f += void 0 !== e[c + 1] ? b[(15 & e[c + 1]) << 2 | e[c + 2] >> 6] : "=", f += void 0 !== e[c + 2] ? b[63 & e[c + 2]] : "=";
                for (d = f.slice(0, 64) + "\n", c = 1; Math.ceil(f.length / 64) > c; c++) d += f.slice(64 * c, 64 * c + 64) + (Math.ceil(f.length / 64) === c + 1 ? "" : "\n");
                return d
            }, d = function (b) {
                b = b.replace(/\n/g, "");
                var c, d = [], e = [], f = [];
                for (c = 0; b.length > c; c += 4) e[0] = a.indexOf(b.charAt(c)), e[1] = a.indexOf(b.charAt(c + 1)), e[2] = a.indexOf(b.charAt(c + 2)), e[3] = a.indexOf(b.charAt(c + 3)), f[0] = e[0] << 2 | e[1] >> 4, f[1] = (15 & e[1]) << 4 | e[2] >> 2, f[2] = (3 & e[2]) << 6 | e[3], d.push(f[0], f[1], f[2]);
                return d = d.slice(0, d.length - d.length % 16)
            };
            return "function" == typeof Array.indexOf && (a = b), {encode: c, decode: d}
        }();
    return {
        size: k,
        h2a: i,
        expandKey: w,
        encryptBlock: p,
        decryptBlock: q,
        Decrypt: c,
        s2a: j,
        rawEncrypt: n,
        rawDecrypt: o,
        dec: N,
        openSSLKey: m,
        a2h: h,
        enc: M,
        Hash: {MD5: O},
        Base64: P
    }
}), function (a, b) {
    "function" == typeof define && define.amd ? define(["jquery"], b) : b(a.jQuery ? a.jQuery : a.Zepto)
}(this, function (a, b) {
    a.fn.jPlayer = function (c) {
        var d = "jPlayer", e = "string" == typeof c, f = Array.prototype.slice.call(arguments, 1), g = this;
        return c = !e && f.length ? a.extend.apply(null, [!0, c].concat(f)) : c, e && "_" === c.charAt(0) ? g : (e ? this.each(function () {
            var e = a(this).data(d), h = e && a.isFunction(e[c]) ? e[c].apply(e, f) : e;
            return h !== e && h !== b ? (g = h, !1) : void 0
        }) : this.each(function () {
            var b = a(this).data(d);
            b ? b.option(c || {}) : a(this).data(d, new a.jPlayer(c, this))
        }), g)
    }, a.jPlayer = function (b, c) {
        if (arguments.length) {
            this.element = a(c), this.options = a.extend(!0, {}, this.options, b);
            var d = this;
            this.element.bind("remove.jPlayer", function () {
                d.destroy()
            }), this._init()
        }
    }, "function" != typeof a.fn.stop && (a.fn.stop = function () {
    }), a.jPlayer.emulateMethods = "load play pause", a.jPlayer.emulateStatus = "src readyState networkState currentTime duration paused ended playbackRate", a.jPlayer.emulateOptions = "muted volume", a.jPlayer.reservedEvent = "ready flashreset resize repeat error warning", a.jPlayer.event = {}, a.each(["ready", "setmedia", "flashreset", "resize", "repeat", "click", "error", "warning", "loadstart", "progress", "suspend", "abort", "emptied", "stalled", "play", "pause", "loadedmetadata", "loadeddata", "waiting", "playing", "canplay", "canplaythrough", "seeking", "seeked", "timeupdate", "ended", "ratechange", "durationchange", "volumechange"], function () {
        a.jPlayer.event[this] = "jPlayer_" + this
    }), a.jPlayer.htmlEvent = ["loadstart", "abort", "emptied", "stalled", "loadedmetadata", "loadeddata", "canplay", "canplaythrough"], a.jPlayer.pause = function () {
        a.each(a.jPlayer.prototype.instances, function (a, b) {
            b.data("jPlayer").status.srcSet && b.jPlayer("pause")
        })
    }, a.jPlayer.timeFormat = {
        showHour: !1,
        showMin: !0,
        showSec: !0,
        padHour: !1,
        padMin: !0,
        padSec: !0,
        sepHour: ":",
        sepMin: ":",
        sepSec: ""
    };
    var c = function () {
        this.init()
    };
    c.prototype = {
        init: function () {
            this.options = {timeFormat: a.jPlayer.timeFormat}
        }, time: function (a) {
            a = a && "number" == typeof a ? a : 0;
            var b = new Date(1e3 * a), c = b.getUTCHours(),
                d = this.options.timeFormat.showHour ? b.getUTCMinutes() : b.getUTCMinutes() + 60 * c,
                e = this.options.timeFormat.showMin ? b.getUTCSeconds() : b.getUTCSeconds() + 60 * d,
                f = this.options.timeFormat.padHour && 10 > c ? "0" + c : c,
                g = this.options.timeFormat.padMin && 10 > d ? "0" + d : d,
                h = this.options.timeFormat.padSec && 10 > e ? "0" + e : e, i = "";
            return i += this.options.timeFormat.showHour ? f + this.options.timeFormat.sepHour : "", i += this.options.timeFormat.showMin ? g + this.options.timeFormat.sepMin : "", i += this.options.timeFormat.showSec ? h + this.options.timeFormat.sepSec : ""
        }
    };
    var d = new c;
    a.jPlayer.convertTime = function (a) {
        return d.time(a)
    }, a.jPlayer.uaBrowser = function (a) {
        var b = a.toLowerCase(), c = /(webkit)[ \/]([\w.]+)/, d = /(opera)(?:.*version)?[ \/]([\w.]+)/,
            e = /(msie) ([\w.]+)/, f = /(mozilla)(?:.*? rv:([\w.]+))?/,
            g = c.exec(b) || d.exec(b) || e.exec(b) || b.indexOf("compatible") < 0 && f.exec(b) || [];
        return {browser: g[1] || "", version: g[2] || "0"}
    }, a.jPlayer.uaPlatform = function (a) {
        var b = a.toLowerCase(), c = /(ipad|iphone|ipod|android|blackberry|playbook|windows ce|webos)/,
            d = /(ipad|playbook)/, e = /(android)/, f = /(mobile)/, g = c.exec(b) || [],
            h = d.exec(b) || !f.exec(b) && e.exec(b) || [];
        return g[1] && (g[1] = g[1].replace(/\s/g, "_")), {platform: g[1] || "", tablet: h[1] || ""}
    }, a.jPlayer.browser = {}, a.jPlayer.platform = {};
    var e = a.jPlayer.uaBrowser(navigator.userAgent);
    e.browser && (a.jPlayer.browser[e.browser] = !0, a.jPlayer.browser.version = e.version);
    var f = a.jPlayer.uaPlatform(navigator.userAgent);
    f.platform && (a.jPlayer.platform[f.platform] = !0, a.jPlayer.platform.mobile = !f.tablet, a.jPlayer.platform.tablet = !!f.tablet), a.jPlayer.getDocMode = function () {
        var b;
        return a.jPlayer.browser.msie && (document.documentMode ? b = document.documentMode : (b = 5, document.compatMode && "CSS1Compat" === document.compatMode && (b = 7))), b
    }, a.jPlayer.browser.documentMode = a.jPlayer.getDocMode(), a.jPlayer.nativeFeatures = {
        init: function () {
            var a, b, c, d = document, e = d.createElement("video"), f = {
                w3c: ["fullscreenEnabled", "fullscreenElement", "requestFullscreen", "exitFullscreen", "fullscreenchange", "fullscreenerror"],
                moz: ["mozFullScreenEnabled", "mozFullScreenElement", "mozRequestFullScreen", "mozCancelFullScreen", "mozfullscreenchange", "mozfullscreenerror"],
                webkit: ["", "webkitCurrentFullScreenElement", "webkitRequestFullScreen", "webkitCancelFullScreen", "webkitfullscreenchange", ""],
                webkitVideo: ["webkitSupportsFullscreen", "webkitDisplayingFullscreen", "webkitEnterFullscreen", "webkitExitFullscreen", "", ""]
            }, g = ["w3c", "moz", "webkit", "webkitVideo"];
            for (this.fullscreen = a = {
                support: {
                    w3c: !!d[f.w3c[0]],
                    moz: !!d[f.moz[0]],
                    webkit: "function" == typeof d[f.webkit[3]],
                    webkitVideo: "function" == typeof e[f.webkitVideo[2]]
                }, used: {}
            }, b = 0, c = g.length; c > b; b++) {
                var h = g[b];
                if (a.support[h]) {
                    a.spec = h, a.used[h] = !0;
                    break
                }
            }
            if (a.spec) {
                var i = f[a.spec];
                a.api = {
                    fullscreenEnabled: !0, fullscreenElement: function (a) {
                        return a = a ? a : d, a[i[1]]
                    }, requestFullscreen: function (a) {
                        return a[i[2]]()
                    }, exitFullscreen: function (a) {
                        return a = a ? a : d, a[i[3]]()
                    }
                }, a.event = {fullscreenchange: i[4], fullscreenerror: i[5]}
            } else a.api = {
                fullscreenEnabled: !1, fullscreenElement: function () {
                    return null
                }, requestFullscreen: function () {
                }, exitFullscreen: function () {
                }
            }, a.event = {}
        }
    }, a.jPlayer.nativeFeatures.init(), a.jPlayer.focus = null, a.jPlayer.keyIgnoreElementNames = "INPUT TEXTAREA";
    var g = function (b) {
        var c, d = a.jPlayer.focus;
        d && (a.each(a.jPlayer.keyIgnoreElementNames.split(/\s+/g), function (a, d) {
            return b.target.nodeName.toUpperCase() === d.toUpperCase() ? (c = !0, !1) : void 0
        }), c || a.each(d.options.keyBindings, function (c, e) {
            return e && b.which === e.key && a.isFunction(e.fn) ? (b.preventDefault(), e.fn(d), !1) : void 0
        }))
    };
    a.jPlayer.keys = function (b) {
        var c = "keydown.jPlayer";
        a(document.documentElement).unbind(c), b && a(document.documentElement).bind(c, g)
    }, a.jPlayer.keys(!0), a.jPlayer.prototype = {
        count: 0,
        version: {script: "2.6.0", needFlash: "2.6.0", flash: "unknown"},
        options: {
            swfPath: "js",
            solution: "html, flash",
            supplied: "mp3",
            preload: "metadata",
            volume: .8,
            muted: !1,
            remainingDuration: !1,
            toggleDuration: !1,
            playbackRate: 1,
            defaultPlaybackRate: 1,
            minPlaybackRate: .5,
            maxPlaybackRate: 4,
            wmode: "opaque",
            backgroundColor: "#000000",
            cssSelectorAncestor: "#jp_container_1",
            cssSelector: {
                videoPlay: ".jp-video-play",
                play: ".jp-play",
                pause: ".jp-pause",
                stop: ".jp-stop",
                seekBar: ".jp-seek-bar",
                playBar: ".jp-play-bar",
                mute: ".jp-mute",
                unmute: ".jp-unmute",
                volumeBar: ".jp-volume-bar",
                volumeBarValue: ".jp-volume-bar-value",
                volumeMax: ".jp-volume-max",
                playbackRateBar: ".jp-playback-rate-bar",
                playbackRateBarValue: ".jp-playback-rate-bar-value",
                currentTime: ".jp-current-time",
                duration: ".jp-duration",
                title: ".jp-title",
                fullScreen: ".jp-full-screen",
                restoreScreen: ".jp-restore-screen",
                repeat: ".jp-repeat",
                repeatOff: ".jp-repeat-off",
                gui: ".jp-gui",
                noSolution: ".jp-no-solution"
            },
            smoothPlayBar: !1,
            fullScreen: !1,
            fullWindow: !1,
            autohide: {restored: !1, full: !0, fadeIn: 200, fadeOut: 600, hold: 1e3},
            loop: !1,
            repeat: function (b) {
                b.jPlayer.options.loop ? a(this).unbind(".jPlayerRepeat").bind(a.jPlayer.event.ended + ".jPlayer.jPlayerRepeat", function () {
                    a(this).jPlayer("play")
                }) : a(this).unbind(".jPlayerRepeat")
            },
            nativeVideoControls: {},
            noFullWindow: {
                msie: /msie [0-6]\./,
                ipad: /ipad.*?os [0-4]\./,
                iphone: /iphone/,
                ipod: /ipod/,
                android_pad: /android [0-3]\.(?!.*?mobile)/,
                android_phone: /android.*?mobile/,
                blackberry: /blackberry/,
                windows_ce: /windows ce/,
                iemobile: /iemobile/,
                webos: /webos/
            },
            noVolume: {
                ipad: /ipad/,
                iphone: /iphone/,
                ipod: /ipod/,
                android_pad: /android(?!.*?mobile)/,
                android_phone: /android.*?mobile/,
                blackberry: /blackberry/,
                windows_ce: /windows ce/,
                iemobile: /iemobile/,
                webos: /webos/,
                playbook: /playbook/
            },
            timeFormat: {},
            keyEnabled: !1,
            audioFullScreen: !1,
            keyBindings: {
                play: {
                    key: 32, fn: function (a) {
                        a.status.paused ? a.play() : a.pause()
                    }
                }, fullScreen: {
                    key: 13, fn: function (a) {
                        (a.status.video || a.options.audioFullScreen) && a._setOption("fullScreen", !a.options.fullScreen)
                    }
                }, muted: {
                    key: 8, fn: function (a) {
                        a._muted(!a.options.muted)
                    }
                }, volumeUp: {
                    key: 38, fn: function (a) {
                        a.volume(a.options.volume + .1)
                    }
                }, volumeDown: {
                    key: 40, fn: function (a) {
                        a.volume(a.options.volume - .1)
                    }
                }
            },
            verticalVolume: !1,
            verticalPlaybackRate: !1,
            globalVolume: !1,
            idPrefix: "jp",
            noConflict: "jQuery",
            emulateHtml: !1,
            consoleAlerts: !0,
            errorAlerts: !1,
            warningAlerts: !1
        },
        optionsAudio: {
            size: {width: "0px", height: "0px", cssClass: ""},
            sizeFull: {width: "0px", height: "0px", cssClass: ""}
        },
        optionsVideo: {
            size: {width: "480px", height: "270px", cssClass: "jp-video-270p"},
            sizeFull: {width: "100%", height: "100%", cssClass: "jp-video-full"}
        },
        instances: {},
        status: {
            src: "",
            media: {},
            paused: !0,
            format: {},
            formatType: "",
            waitForPlay: !0,
            waitForLoad: !0,
            srcSet: !1,
            video: !1,
            seekPercent: 0,
            currentPercentRelative: 0,
            currentPercentAbsolute: 0,
            currentTime: 0,
            duration: 0,
            remaining: 0,
            videoWidth: 0,
            videoHeight: 0,
            readyState: 0,
            networkState: 0,
            playbackRate: 1,
            ended: 0
        },
        internal: {ready: !1},
        solution: {html: !0, flash: !0},
        format: {
            mp3: {codec: 'audio/mpeg; codecs="mp3"', flashCanPlay: !0, media: "audio"},
            m4a: {codec: 'audio/mp4; codecs="mp4a.40.2"', flashCanPlay: !0, media: "audio"},
            m3u8a: {codec: 'application/vnd.apple.mpegurl; codecs="mp4a.40.2"', flashCanPlay: !1, media: "audio"},
            m3ua: {codec: "audio/mpegurl", flashCanPlay: !1, media: "audio"},
            oga: {codec: 'audio/ogg; codecs="vorbis, opus"', flashCanPlay: !1, media: "audio"},
            flac: {codec: "audio/x-flac", flashCanPlay: !1, media: "audio"},
            wav: {codec: 'audio/wav; codecs="1"', flashCanPlay: !1, media: "audio"},
            webma: {codec: 'audio/webm; codecs="vorbis"', flashCanPlay: !1, media: "audio"},
            fla: {codec: "audio/x-flv", flashCanPlay: !0, media: "audio"},
            rtmpa: {codec: 'audio/rtmp; codecs="rtmp"', flashCanPlay: !0, media: "audio"},
            m4v: {codec: 'video/mp4; codecs="avc1.42E01E, mp4a.40.2"', flashCanPlay: !0, media: "video"},
            m3u8v: {
                codec: 'application/vnd.apple.mpegurl; codecs="avc1.42E01E, mp4a.40.2"',
                flashCanPlay: !1,
                media: "video"
            },
            m3uv: {codec: "audio/mpegurl", flashCanPlay: !1, media: "video"},
            ogv: {codec: 'video/ogg; codecs="theora, vorbis"', flashCanPlay: !1, media: "video"},
            webmv: {codec: 'video/webm; codecs="vorbis, vp8"', flashCanPlay: !1, media: "video"},
            flv: {codec: "video/x-flv", flashCanPlay: !0, media: "video"},
            rtmpv: {codec: 'video/rtmp; codecs="rtmp"', flashCanPlay: !0, media: "video"}
        },
        _init: function () {
            var c = this;
            if (this.element.empty(), this.status = a.extend({}, this.status), this.internal = a.extend({}, this.internal), this.options.timeFormat = a.extend({}, a.jPlayer.timeFormat, this.options.timeFormat), this.internal.cmdsIgnored = a.jPlayer.platform.ipad || a.jPlayer.platform.iphone || a.jPlayer.platform.ipod, this.internal.domNode = this.element.get(0), this.options.keyEnabled && !a.jPlayer.focus && (a.jPlayer.focus = this),
                this.androidFix = {
                    setMedia: !1,
                    play: !1,
                    pause: !1,
                    time: NaN
                }, a.jPlayer.platform.android && (this.options.preload = "auto" !== this.options.preload ? "metadata" : "auto"), this.formats = [], this.solutions = [], this.require = {}, this.htmlElement = {}, this.html = {}, this.html.audio = {}, this.html.video = {}, this.flash = {}, this.css = {}, this.css.cs = {}, this.css.jq = {}, this.ancestorJq = [], this.options.volume = this._limitValue(this.options.volume, 0, 1), a.each(this.options.supplied.toLowerCase().split(","), function (b, d) {
                var e = d.replace(/^\s+|\s+$/g, "");
                if (c.format[e]) {
                    var f = !1;
                    a.each(c.formats, function (a, b) {
                        return e === b ? (f = !0, !1) : void 0
                    }), f || c.formats.push(e)
                }
            }), a.each(this.options.solution.toLowerCase().split(","), function (b, d) {
                var e = d.replace(/^\s+|\s+$/g, "");
                if (c.solution[e]) {
                    var f = !1;
                    a.each(c.solutions, function (a, b) {
                        return e === b ? (f = !0, !1) : void 0
                    }), f || c.solutions.push(e)
                }
            }), this.internal.instance = "jp_" + this.count, this.instances[this.internal.instance] = this.element, this.element.attr("id") || this.element.attr("id", this.options.idPrefix + "_jplayer_" + this.count), this.internal.self = a.extend({}, {
                id: this.element.attr("id"),
                jq: this.element
            }), this.internal.audio = a.extend({}, {
                id: this.options.idPrefix + "_audio_" + this.count,
                jq: b
            }), this.internal.video = a.extend({}, {
                id: this.options.idPrefix + "_video_" + this.count,
                jq: b
            }), this.internal.flash = a.extend({}, {
                id: this.options.idPrefix + "_flash_" + this.count,
                jq: b,
                swf: this.options.swfPath + (".swf" !== this.options.swfPath.toLowerCase().slice(-4) ? (this.options.swfPath && "/" !== this.options.swfPath.slice(-1) ? "/" : "") + "Jplayer.swf" : "")
            }), this.internal.poster = a.extend({}, {
                id: this.options.idPrefix + "_poster_" + this.count,
                jq: b
            }), a.each(a.jPlayer.event, function (a, d) {
                c.options[a] !== b && (c.element.bind(d + ".jPlayer", c.options[a]), c.options[a] = b)
            }), this.require.audio = !1, this.require.video = !1, a.each(this.formats, function (a, b) {
                c.require[c.format[b].media] = !0
            }), this.require.video ? this.options = a.extend(!0, {}, this.optionsVideo, this.options) : this.options = a.extend(!0, {}, this.optionsAudio, this.options), this._setSize(), this.status.nativeVideoControls = this._uaBlocklist(this.options.nativeVideoControls), this.status.noFullWindow = this._uaBlocklist(this.options.noFullWindow), this.status.noVolume = this._uaBlocklist(this.options.noVolume), a.jPlayer.nativeFeatures.fullscreen.api.fullscreenEnabled && this._fullscreenAddEventListeners(), this._restrictNativeVideoControls(), this.htmlElement.poster = document.createElement("img"), this.htmlElement.poster.id = this.internal.poster.id, this.htmlElement.poster.onload = function () {
                (!c.status.video || c.status.waitForPlay) && c.internal.poster.jq.show()
            }, this.element.append(this.htmlElement.poster), this.internal.poster.jq = a("#" + this.internal.poster.id), this.internal.poster.jq.css({
                width: this.status.width,
                height: this.status.height
            }), this.internal.poster.jq.hide(), this.internal.poster.jq.bind("click.jPlayer", function () {
                c._trigger(a.jPlayer.event.click)
            }), this.html.audio.available = !1, this.require.audio && (this.htmlElement.audio = document.createElement("audio"), this.htmlElement.audio.id = this.internal.audio.id, this.html.audio.available = !!this.htmlElement.audio.canPlayType && this._testCanPlayType(this.htmlElement.audio)), this.html.video.available = !1, this.require.video && (this.htmlElement.video = document.createElement("video"), this.htmlElement.video.id = this.internal.video.id, this.html.video.available = !!this.htmlElement.video.canPlayType && this._testCanPlayType(this.htmlElement.video)), this.flash.available = this._checkForFlash(10.1), this.html.canPlay = {}, this.flash.canPlay = {}, a.each(this.formats, function (a, b) {
                c.html.canPlay[b] = c.html[c.format[b].media].available && "" !== c.htmlElement[c.format[b].media].canPlayType(c.format[b].codec), c.flash.canPlay[b] = c.format[b].flashCanPlay && c.flash.available
            }), this.html.desired = !1, this.flash.desired = !1, a.each(this.solutions, function (b, d) {
                if (0 === b) c[d].desired = !0; else {
                    var e = !1, f = !1;
                    a.each(c.formats, function (a, b) {
                        c[c.solutions[0]].canPlay[b] && ("video" === c.format[b].media ? f = !0 : e = !0)
                    }), c[d].desired = c.require.audio && !e || c.require.video && !f
                }
            }), this.html.support = {}, this.flash.support = {}, a.each(this.formats, function (a, b) {
                c.html.support[b] = c.html.canPlay[b] && c.html.desired, c.flash.support[b] = c.flash.canPlay[b] && c.flash.desired
            }), this.html.used = !1, this.flash.used = !1, a.each(this.solutions, function (b, d) {
                a.each(c.formats, function (a, b) {
                    return c[d].support[b] ? (c[d].used = !0, !1) : void 0
                })
            }), this._resetActive(), this._resetGate(), this._cssSelectorAncestor(this.options.cssSelectorAncestor), this.html.used || this.flash.used ? this.css.jq.noSolution.length && this.css.jq.noSolution.hide() : (this._error({
                type: a.jPlayer.error.NO_SOLUTION,
                context: "{solution:'" + this.options.solution + "', supplied:'" + this.options.supplied + "'}",
                message: a.jPlayer.errorMsg.NO_SOLUTION,
                hint: a.jPlayer.errorHint.NO_SOLUTION
            }), this.css.jq.noSolution.length && this.css.jq.noSolution.show()), this.flash.used) {
                var d,
                    e = "jQuery=" + encodeURI(this.options.noConflict) + "&id=" + encodeURI(this.internal.self.id) + "&vol=" + this.options.volume + "&muted=" + this.options.muted;
                if (a.jPlayer.browser.msie && (Number(a.jPlayer.browser.version) < 9 || a.jPlayer.browser.documentMode < 9)) {
                    var f = '<object id="' + this.internal.flash.id + '" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="0" height="0" tabindex="-1"></object>',
                        g = ['<param name="movie" value="' + this.internal.flash.swf + '" />', '<param name="FlashVars" value="' + e + '" />', '<param name="allowScriptAccess" value="always" />', '<param name="bgcolor" value="' + this.options.backgroundColor + '" />', '<param name="wmode" value="' + this.options.wmode + '" />'];
                    d = document.createElement(f);
                    for (var h = 0; h < g.length; h++) d.appendChild(document.createElement(g[h]))
                } else {
                    var i = function (a, b, c) {
                        var d = document.createElement("param");
                        d.setAttribute("name", b), d.setAttribute("value", c), a.appendChild(d)
                    };
                    d = document.createElement("object"), d.setAttribute("id", this.internal.flash.id), d.setAttribute("name", this.internal.flash.id), d.setAttribute("data", this.internal.flash.swf), d.setAttribute("type", "application/x-shockwave-flash"), d.setAttribute("width", "1"), d.setAttribute("height", "1"), d.setAttribute("tabindex", "-1"), i(d, "flashvars", e), i(d, "allowscriptaccess", "always"), i(d, "bgcolor", this.options.backgroundColor), i(d, "wmode", this.options.wmode)
                }
                this.element.append(d), this.internal.flash.jq = a(d)
            }
            this.html.used && !this.flash.used ? this.status.playbackRateEnabled = this._testPlaybackRate("audio") : this.status.playbackRateEnabled = !1, this._updatePlaybackRate(), this.html.used && (this.html.audio.available && (this._addHtmlEventListeners(this.htmlElement.audio, this.html.audio), this.element.append(this.htmlElement.audio), this.internal.audio.jq = a("#" + this.internal.audio.id)), this.html.video.available && (this._addHtmlEventListeners(this.htmlElement.video, this.html.video), this.element.append(this.htmlElement.video), this.internal.video.jq = a("#" + this.internal.video.id), this.status.nativeVideoControls ? this.internal.video.jq.css({
                width: this.status.width,
                height: this.status.height
            }) : this.internal.video.jq.css({
                width: "0px",
                height: "0px"
            }), this.internal.video.jq.bind("click.jPlayer", function () {
                c._trigger(a.jPlayer.event.click)
            }))), this.options.emulateHtml && this._emulateHtmlBridge(), this.html.used && !this.flash.used && setTimeout(function () {
                c.internal.ready = !0, c.version.flash = "n/a", c._trigger(a.jPlayer.event.repeat), c._trigger(a.jPlayer.event.ready)
            }, 100), this._updateNativeVideoControls(), this.css.jq.videoPlay.length && this.css.jq.videoPlay.hide(), a.jPlayer.prototype.count++
        },
        destroy: function () {
            this.clearMedia(), this._removeUiClass(), this.css.jq.currentTime.length && this.css.jq.currentTime.text(""), this.css.jq.duration.length && this.css.jq.duration.text(""), a.each(this.css.jq, function (a, b) {
                b.length && b.unbind(".jPlayer")
            }), this.internal.poster.jq.unbind(".jPlayer"), this.internal.video.jq && this.internal.video.jq.unbind(".jPlayer"), this._fullscreenRemoveEventListeners(), this === a.jPlayer.focus && (a.jPlayer.focus = null), this.options.emulateHtml && this._destroyHtmlBridge(), this.element.removeData("jPlayer"), this.element.unbind(".jPlayer"), this.element.empty(), delete this.instances[this.internal.instance]
        },
        enable: function () {
        },
        disable: function () {
        },
        _testCanPlayType: function (a) {
            try {
                return a.canPlayType(this.format.mp3.codec), !0
            } catch (b) {
                return !1
            }
        },
        _testPlaybackRate: function (a) {
            var b, c = .5;
            a = "string" == typeof a ? a : "audio", b = document.createElement(a);
            try {
                return "playbackRate" in b ? (b.playbackRate = c, b.playbackRate === c) : !1
            } catch (d) {
                return !1
            }
        },
        _uaBlocklist: function (b) {
            var c = navigator.userAgent.toLowerCase(), d = !1;
            return a.each(b, function (a, b) {
                return b && b.test(c) ? (d = !0, !1) : void 0
            }), d
        },
        _restrictNativeVideoControls: function () {
            this.require.audio && this.status.nativeVideoControls && (this.status.nativeVideoControls = !1, this.status.noFullWindow = !0)
        },
        _updateNativeVideoControls: function () {
            this.html.video.available && this.html.used && (this.htmlElement.video.controls = this.status.nativeVideoControls, this._updateAutohide(), this.status.nativeVideoControls && this.require.video ? (this.internal.poster.jq.hide(), this.internal.video.jq.css({
                width: this.status.width,
                height: this.status.height
            })) : this.status.waitForPlay && this.status.video && (this.internal.poster.jq.show(), this.internal.video.jq.css({
                width: "0px",
                height: "0px"
            })))
        },
        _addHtmlEventListeners: function (b, c) {
            var d = this;
            b.preload = this.options.preload, b.muted = this.options.muted, b.volume = this.options.volume, this.status.playbackRateEnabled && (b.defaultPlaybackRate = this.options.defaultPlaybackRate, b.playbackRate = this.options.playbackRate), b.addEventListener("progress", function () {
                c.gate && (d.internal.cmdsIgnored && this.readyState > 0 && (d.internal.cmdsIgnored = !1), d.androidFix.setMedia = !1, d.androidFix.play && (d.androidFix.play = !1, d.play(d.androidFix.time)), d.androidFix.pause && (d.androidFix.pause = !1, d.pause(d.androidFix.time)), d._getHtmlStatus(b), d._updateInterface(), d._trigger(a.jPlayer.event.progress))
            }, !1), b.addEventListener("timeupdate", function () {
                c.gate && (d._getHtmlStatus(b), d._updateInterface(), d._trigger(a.jPlayer.event.timeupdate))
            }, !1), b.addEventListener("durationchange", function () {
                c.gate && (d._getHtmlStatus(b), d._updateInterface(), d._trigger(a.jPlayer.event.durationchange))
            }, !1), b.addEventListener("play", function () {
                c.gate && (d._updateButtons(!0), d._html_checkWaitForPlay(), d._trigger(a.jPlayer.event.play))
            }, !1), b.addEventListener("playing", function () {
                c.gate && (d._updateButtons(!0), d._seeked(), d._trigger(a.jPlayer.event.playing))
            }, !1), b.addEventListener("pause", function () {
                c.gate && (d._updateButtons(!1), d._trigger(a.jPlayer.event.pause))
            }, !1), b.addEventListener("waiting", function () {
                c.gate && (d._seeking(), d._trigger(a.jPlayer.event.waiting))
            }, !1), b.addEventListener("seeking", function () {
                c.gate && (d._seeking(), d._trigger(a.jPlayer.event.seeking))
            }, !1), b.addEventListener("seeked", function () {
                c.gate && (d._seeked(), d._trigger(a.jPlayer.event.seeked))
            }, !1), b.addEventListener("volumechange", function () {
                c.gate && (d.options.volume = b.volume, d.options.muted = b.muted, d._updateMute(), d._updateVolume(), d._trigger(a.jPlayer.event.volumechange))
            }, !1), b.addEventListener("ratechange", function () {
                c.gate && (d.options.defaultPlaybackRate = b.defaultPlaybackRate, d.options.playbackRate = b.playbackRate, d._updatePlaybackRate(), d._trigger(a.jPlayer.event.ratechange))
            }, !1), b.addEventListener("suspend", function () {
                c.gate && (d._seeked(), d._trigger(a.jPlayer.event.suspend))
            }, !1), b.addEventListener("ended", function () {
                c.gate && (a.jPlayer.browser.webkit || (d.htmlElement.media.currentTime = 0), d.htmlElement.media.pause(), d._updateButtons(!1), d._getHtmlStatus(b, !0), d._updateInterface(), d._trigger(a.jPlayer.event.ended))
            }, !1), b.addEventListener("error", function () {
                c.gate && (d._updateButtons(!1), d._seeked(), d.status.srcSet && (clearTimeout(d.internal.htmlDlyCmdId), d.status.waitForLoad = !0, d.status.waitForPlay = !0, d.status.video && !d.status.nativeVideoControls && d.internal.video.jq.css({
                    width: "0px",
                    height: "0px"
                }), d._validString(d.status.media.poster) && !d.status.nativeVideoControls && d.internal.poster.jq.show(), d.css.jq.videoPlay.length && d.css.jq.videoPlay.show(), d._error({
                    type: a.jPlayer.error.URL,
                    context: d.status.src,
                    message: a.jPlayer.errorMsg.URL,
                    hint: a.jPlayer.errorHint.URL
                })))
            }, !1), a.each(a.jPlayer.htmlEvent, function (e, f) {
                b.addEventListener(this, function () {
                    c.gate && d._trigger(a.jPlayer.event[f])
                }, !1)
            })
        },
        _getHtmlStatus: function (a, b) {
            var c = 0, d = 0, e = 0, f = 0;
            isFinite(a.duration) && (this.status.duration = a.duration), c = a.currentTime, d = this.status.duration > 0 ? 100 * c / this.status.duration : 0, "object" == typeof a.seekable && a.seekable.length > 0 ? (e = this.status.duration > 0 ? 100 * a.seekable.end(a.seekable.length - 1) / this.status.duration : 100, f = this.status.duration > 0 ? 100 * a.currentTime / a.seekable.end(a.seekable.length - 1) : 0) : (e = 100, f = d), b && (c = 0, f = 0, d = 0), this.status.seekPercent = e, this.status.currentPercentRelative = f, this.status.currentPercentAbsolute = d, this.status.currentTime = c, this.status.remaining = this.status.duration - this.status.currentTime, this.status.videoWidth = a.videoWidth, this.status.videoHeight = a.videoHeight, this.status.readyState = a.readyState, this.status.networkState = a.networkState, this.status.playbackRate = a.playbackRate, this.status.ended = a.ended
        },
        _resetStatus: function () {
            this.status = a.extend({}, this.status, a.jPlayer.prototype.status)
        },
        _trigger: function (b, c, d) {
            var e = a.Event(b);
            e.jPlayer = {}, e.jPlayer.version = a.extend({}, this.version), e.jPlayer.options = a.extend(!0, {}, this.options), e.jPlayer.status = a.extend(!0, {}, this.status), e.jPlayer.html = a.extend(!0, {}, this.html), e.jPlayer.flash = a.extend(!0, {}, this.flash), c && (e.jPlayer.error = a.extend({}, c)), d && (e.jPlayer.warning = a.extend({}, d)), this.element.trigger(e)
        },
        jPlayerFlashEvent: function (b, c) {
            if (b === a.jPlayer.event.ready) if (this.internal.ready) {
                if (this.flash.gate) {
                    if (this.status.srcSet) {
                        var d = this.status.currentTime, e = this.status.paused;
                        this.setMedia(this.status.media), this.volumeWorker(this.options.volume), d > 0 && (e ? this.pause(d) : this.play(d))
                    }
                    this._trigger(a.jPlayer.event.flashreset)
                }
            } else this.internal.ready = !0, this.internal.flash.jq.css({
                width: "0px",
                height: "0px"
            }), this.version.flash = c.version, this.version.needFlash !== this.version.flash && this._error({
                type: a.jPlayer.error.VERSION,
                context: this.version.flash,
                message: a.jPlayer.errorMsg.VERSION + this.version.flash,
                hint: a.jPlayer.errorHint.VERSION
            }), this._trigger(a.jPlayer.event.repeat), this._trigger(b);
            if (this.flash.gate) switch (b) {
                case a.jPlayer.event.progress:
                    this._getFlashStatus(c), this._updateInterface(), this._trigger(b);
                    break;
                case a.jPlayer.event.timeupdate:
                    this._getFlashStatus(c), this._updateInterface(), this._trigger(b);
                    break;
                case a.jPlayer.event.play:
                    this._seeked(), this._updateButtons(!0), this._trigger(b);
                    break;
                case a.jPlayer.event.pause:
                    this._updateButtons(!1), this._trigger(b);
                    break;
                case a.jPlayer.event.ended:
                    this._updateButtons(!1), this._trigger(b);
                    break;
                case a.jPlayer.event.click:
                    this._trigger(b);
                    break;
                case a.jPlayer.event.error:
                    this.status.waitForLoad = !0, this.status.waitForPlay = !0, this.status.video && this.internal.flash.jq.css({
                        width: "0px",
                        height: "0px"
                    }), this._validString(this.status.media.poster) && this.internal.poster.jq.show(), this.css.jq.videoPlay.length && this.status.video && this.css.jq.videoPlay.show(), this.status.video ? this._flash_setVideo(this.status.media) : this._flash_setAudio(this.status.media), this._updateButtons(!1), this._error({
                        type: a.jPlayer.error.URL,
                        context: c.src,
                        message: a.jPlayer.errorMsg.URL,
                        hint: a.jPlayer.errorHint.URL
                    });
                    break;
                case a.jPlayer.event.seeking:
                    this._seeking(), this._trigger(b);
                    break;
                case a.jPlayer.event.seeked:
                    this._seeked(), this._trigger(b);
                    break;
                case a.jPlayer.event.ready:
                    break;
                default:
                    this._trigger(b)
            }
            return !1
        },
        _getFlashStatus: function (a) {
            this.status.seekPercent = a.seekPercent, this.status.currentPercentRelative = a.currentPercentRelative, this.status.currentPercentAbsolute = a.currentPercentAbsolute, this.status.currentTime = a.currentTime, this.status.duration = a.duration, this.status.remaining = a.duration - a.currentTime, this.status.videoWidth = a.videoWidth, this.status.videoHeight = a.videoHeight, this.status.readyState = 4, this.status.networkState = 0, this.status.playbackRate = 1, this.status.ended = !1
        },
        _updateButtons: function (a) {
            a === b ? a = !this.status.paused : this.status.paused = !a, this.css.jq.play.length && this.css.jq.pause.length && (a ? (this.css.jq.play.hide(), this.css.jq.pause.show()) : (this.css.jq.play.show(), this.css.jq.pause.hide())), this.css.jq.restoreScreen.length && this.css.jq.fullScreen.length && (this.status.noFullWindow ? (this.css.jq.fullScreen.hide(), this.css.jq.restoreScreen.hide()) : this.options.fullWindow ? (this.css.jq.fullScreen.hide(), this.css.jq.restoreScreen.show()) : (this.css.jq.fullScreen.show(), this.css.jq.restoreScreen.hide())), this.css.jq.repeat.length && this.css.jq.repeatOff.length && (this.options.loop ? (this.css.jq.repeat.hide(), this.css.jq.repeatOff.show()) : (this.css.jq.repeat.show(), this.css.jq.repeatOff.hide()))
        },
        _updateInterface: function () {
            this.css.jq.seekBar.length && this.css.jq.seekBar.width(this.status.seekPercent + "%"), this.css.jq.playBar.length && (this.options.smoothPlayBar ? this.css.jq.playBar.stop().animate({width: this.status.currentPercentAbsolute + "%"}, 250, "linear") : this.css.jq.playBar.width(this.status.currentPercentRelative + "%"));
            var a = "";
            this.css.jq.currentTime.length && (a = this._convertTime(this.status.currentTime), a !== this.css.jq.currentTime.text() && this.css.jq.currentTime.text(this._convertTime(this.status.currentTime)));
            var b = "", c = this.status.duration, d = this.status.remaining;
            this.css.jq.duration.length && ("string" == typeof this.status.media.duration ? b = this.status.media.duration : ("number" == typeof this.status.media.duration && (c = this.status.media.duration, d = c - this.status.currentTime), b = this.options.remainingDuration ? (d > 0 ? "-" : "") + this._convertTime(d) : this._convertTime(c)), b !== this.css.jq.duration.text() && this.css.jq.duration.text(b))
        },
        _convertTime: c.prototype.time,
        _seeking: function () {
            this.css.jq.seekBar.length && this.css.jq.seekBar.addClass("jp-seeking-bg")
        },
        _seeked: function () {
            this.css.jq.seekBar.length && this.css.jq.seekBar.removeClass("jp-seeking-bg")
        },
        _resetGate: function () {
            this.html.audio.gate = !1, this.html.video.gate = !1, this.flash.gate = !1
        },
        _resetActive: function () {
            this.html.active = !1, this.flash.active = !1
        },
        _escapeHtml: function (a) {
            return a.split("&").join("&amp;").split("<").join("&lt;").split(">").join("&gt;").split('"').join("&quot;")
        },
        _qualifyURL: function (a) {
            var b = document.createElement("div");
            return b.innerHTML = '<a href="' + this._escapeHtml(a) + '">x</a>', b.firstChild.href
        },
        _absoluteMediaUrls: function (b) {
            var c = this;
            return a.each(b, function (a, d) {
                d && c.format[a] && (b[a] = c._qualifyURL(d))
            }), b
        },
        setMedia: function (b) {
            var c = this, d = !1, e = this.status.media.poster !== b.poster;
            this._resetMedia(), this._resetGate(), this._resetActive(), this.androidFix.setMedia = !1, this.androidFix.play = !1, this.androidFix.pause = !1, b = this._absoluteMediaUrls(b), a.each(this.formats, function (e, f) {
                var g = "video" === c.format[f].media;
                return a.each(c.solutions, function (e, h) {
                    if (c[h].support[f] && c._validString(b[f])) {
                        var i = "html" === h;
                        return g ? (i ? (c.html.video.gate = !0, c._html_setVideo(b), c.html.active = !0) : (c.flash.gate = !0, c._flash_setVideo(b), c.flash.active = !0), c.css.jq.videoPlay.length && c.css.jq.videoPlay.show(), c.status.video = !0) : (i ? (c.html.audio.gate = !0, c._html_setAudio(b), c.html.active = !0, a.jPlayer.platform.android && (c.androidFix.setMedia = !0)) : (c.flash.gate = !0, c._flash_setAudio(b), c.flash.active = !0), c.css.jq.videoPlay.length && c.css.jq.videoPlay.hide(), c.status.video = !1), d = !0, !1
                    }
                }), d ? !1 : void 0
            }), d ? (this.status.nativeVideoControls && this.html.video.gate || this._validString(b.poster) && (e ? this.htmlElement.poster.src = b.poster : this.internal.poster.jq.show()), this.css.jq.title.length && "string" == typeof b.title && (this.css.jq.title.html(b.title), this.htmlElement.audio && this.htmlElement.audio.setAttribute("title", b.title), this.htmlElement.video && this.htmlElement.video.setAttribute("title", b.title)), this.status.srcSet = !0, this.status.media = a.extend({}, b), this._updateButtons(!1), this._updateInterface(), this._trigger(a.jPlayer.event.setmedia)) : this._error({
                type: a.jPlayer.error.NO_SUPPORT,
                context: "{supplied:'" + this.options.supplied + "'}",
                message: a.jPlayer.errorMsg.NO_SUPPORT,
                hint: a.jPlayer.errorHint.NO_SUPPORT
            })
        },
        _resetMedia: function () {
            this._resetStatus(), this._updateButtons(!1), this._updateInterface(), this._seeked(), this.internal.poster.jq.hide(), clearTimeout(this.internal.htmlDlyCmdId), this.html.active ? this._html_resetMedia() : this.flash.active && this._flash_resetMedia()
        },
        clearMedia: function () {
            this._resetMedia(), this.html.active ? this._html_clearMedia() : this.flash.active && this._flash_clearMedia(), this._resetGate(), this._resetActive()
        },
        load: function () {
            this.status.srcSet ? this.html.active ? this._html_load() : this.flash.active && this._flash_load() : this._urlNotSetError("load")
        },
        focus: function () {
            this.options.keyEnabled && (a.jPlayer.focus = this)
        },
        play: function (a) {
            a = "number" == typeof a ? a : NaN, this.status.srcSet ? (this.focus(), this.html.active ? this._html_play(a) : this.flash.active && this._flash_play(a)) : this._urlNotSetError("play")
        },
        videoPlay: function () {
            this.play()
        },
        pause: function (a) {
            a = "number" == typeof a ? a : NaN, this.status.srcSet ? this.html.active ? this._html_pause(a) : this.flash.active && this._flash_pause(a) : this._urlNotSetError("pause")
        },
        tellOthers: function (b, c) {
            var d = this, e = "function" == typeof c, f = Array.prototype.slice.call(arguments);
            "string" == typeof b && (e && f.splice(1, 1), a.each(this.instances, function () {
                d.element !== this && (!e || c.call(this.data("jPlayer"), d)) && this.jPlayer.apply(this, f)
            }))
        },
        pauseOthers: function (a) {
            this.tellOthers("pause", function () {
                return this.status.srcSet
            }, a)
        },
        stop: function () {
            this.status.srcSet ? this.html.active ? this._html_pause(0) : this.flash.active && this._flash_pause(0) : this._urlNotSetError("stop")
        },
        playHead: function (a) {
            a = this._limitValue(a, 0, 100), this.status.srcSet ? this.html.active ? this._html_playHead(a) : this.flash.active && this._flash_playHead(a) : this._urlNotSetError("playHead")
        },
        _muted: function (a) {
            this.mutedWorker(a), this.options.globalVolume && this.tellOthers("mutedWorker", function () {
                return this.options.globalVolume
            }, a)
        },
        mutedWorker: function (b) {
            this.options.muted = b, this.html.used && this._html_setProperty("muted", b), this.flash.used && this._flash_mute(b), this.html.video.gate || this.html.audio.gate || (this._updateMute(b), this._updateVolume(this.options.volume), this._trigger(a.jPlayer.event.volumechange))
        },
        mute: function (a) {
            a = a === b ? !0 : !!a, this._muted(a)
        },
        unmute: function (a) {
            a = a === b ? !0 : !!a, this._muted(!a)
        },
        _updateMute: function (a) {
            a === b && (a = this.options.muted), this.css.jq.mute.length && this.css.jq.unmute.length && (this.status.noVolume ? (this.css.jq.mute.hide(), this.css.jq.unmute.hide()) : a ? (this.css.jq.mute.hide(), this.css.jq.unmute.show()) : (this.css.jq.mute.show(), this.css.jq.unmute.hide()))
        },
        volume: function (a) {
            this.volumeWorker(a), this.options.globalVolume && this.tellOthers("volumeWorker", function () {
                return this.options.globalVolume
            }, a)
        },
        volumeWorker: function (b) {
            b = this._limitValue(b, 0, 1), this.options.volume = b, this.html.used && this._html_setProperty("volume", b), this.flash.used && this._flash_volume(b), this.html.video.gate || this.html.audio.gate || (this._updateVolume(b), this._trigger(a.jPlayer.event.volumechange))
        },
        volumeBar: function (b) {
            if (this.css.jq.volumeBar.length) {
                var c = a(b.currentTarget), d = c.offset(), e = b.pageX - d.left, f = c.width(),
                    g = c.height() - b.pageY + d.top, h = c.height();
                this.options.verticalVolume ? this.volume(g / h) : this.volume(e / f)
            }
            this.options.muted && this._muted(!1)
        },
        _updateVolume: function (a) {
            a === b && (a = this.options.volume), a = this.options.muted ? 0 : a, this.status.noVolume ? (this.css.jq.volumeBar.length && this.css.jq.volumeBar.hide(), this.css.jq.volumeBarValue.length && this.css.jq.volumeBarValue.hide(), this.css.jq.volumeMax.length && this.css.jq.volumeMax.hide()) : (this.css.jq.volumeBar.length && this.css.jq.volumeBar.show(), this.css.jq.volumeBarValue.length && (this.css.jq.volumeBarValue.show(), this.css.jq.volumeBarValue[this.options.verticalVolume ? "height" : "width"](100 * a + "%")), this.css.jq.volumeMax.length && this.css.jq.volumeMax.show())
        },
        volumeMax: function () {
            this.volume(1), this.options.muted && this._muted(!1)
        },
        _cssSelectorAncestor: function (b) {
            var c = this;
            this.options.cssSelectorAncestor = b, this._removeUiClass(), this.ancestorJq = b ? a(b) : [], b && 1 !== this.ancestorJq.length && this._warning({
                type: a.jPlayer.warning.CSS_SELECTOR_COUNT,
                context: b,
                message: a.jPlayer.warningMsg.CSS_SELECTOR_COUNT + this.ancestorJq.length + " found for cssSelectorAncestor.",
                hint: a.jPlayer.warningHint.CSS_SELECTOR_COUNT
            }), this._addUiClass(), a.each(this.options.cssSelector, function (a, b) {
                c._cssSelector(a, b)
            }), this._updateInterface(), this._updateButtons(), this._updateAutohide(), this._updateVolume(), this._updateMute()
        },
        _cssSelector: function (b, c) {
            var d = this;
            if ("string" == typeof c) if (a.jPlayer.prototype.options.cssSelector[b]) {
                if (this.css.jq[b] && this.css.jq[b].length && this.css.jq[b].unbind(".jPlayer"), this.options.cssSelector[b] = c, this.css.cs[b] = this.options.cssSelectorAncestor + " " + c, c ? this.css.jq[b] = a(this.css.cs[b]) : this.css.jq[b] = [], this.css.jq[b].length && this[b]) {
                    var e = function (c) {
                        c.preventDefault(), d[b](c), a(this).blur()
                    };
                    this.css.jq[b].bind("click.jPlayer", e)
                }
                c && 1 !== this.css.jq[b].length && this._warning({
                    type: a.jPlayer.warning.CSS_SELECTOR_COUNT,
                    context: this.css.cs[b],
                    message: a.jPlayer.warningMsg.CSS_SELECTOR_COUNT + this.css.jq[b].length + " found for " + b + " method.",
                    hint: a.jPlayer.warningHint.CSS_SELECTOR_COUNT
                })
            } else this._warning({
                type: a.jPlayer.warning.CSS_SELECTOR_METHOD,
                context: b,
                message: a.jPlayer.warningMsg.CSS_SELECTOR_METHOD,
                hint: a.jPlayer.warningHint.CSS_SELECTOR_METHOD
            }); else this._warning({
                type: a.jPlayer.warning.CSS_SELECTOR_STRING,
                context: c,
                message: a.jPlayer.warningMsg.CSS_SELECTOR_STRING,
                hint: a.jPlayer.warningHint.CSS_SELECTOR_STRING
            })
        },
        duration: function (a) {
            this.options.toggleDuration && this._setOption("remainingDuration", !this.options.remainingDuration)
        },
        seekBar: function (b) {
            if (this.css.jq.seekBar.length) {
                var c = a(b.currentTarget), d = c.offset(), e = b.pageX - d.left, f = c.width(), g = 100 * e / f;
                this.playHead(g)
            }
        },
        playbackRate: function (a) {
            this._setOption("playbackRate", a)
        },
        playbackRateBar: function (b) {
            if (this.css.jq.playbackRateBar.length) {
                var c, d, e = a(b.currentTarget), f = e.offset(), g = b.pageX - f.left, h = e.width(),
                    i = e.height() - b.pageY + f.top, j = e.height();
                c = this.options.verticalPlaybackRate ? i / j : g / h, d = c * (this.options.maxPlaybackRate - this.options.minPlaybackRate) + this.options.minPlaybackRate, this.playbackRate(d)
            }
        },
        _updatePlaybackRate: function () {
            var a = this.options.playbackRate,
                b = (a - this.options.minPlaybackRate) / (this.options.maxPlaybackRate - this.options.minPlaybackRate);
            this.status.playbackRateEnabled ? (this.css.jq.playbackRateBar.length && this.css.jq.playbackRateBar.show(), this.css.jq.playbackRateBarValue.length && (this.css.jq.playbackRateBarValue.show(), this.css.jq.playbackRateBarValue[this.options.verticalPlaybackRate ? "height" : "width"](100 * b + "%"))) : (this.css.jq.playbackRateBar.length && this.css.jq.playbackRateBar.hide(), this.css.jq.playbackRateBarValue.length && this.css.jq.playbackRateBarValue.hide())
        },
        repeat: function () {
            this._loop(!0)
        },
        repeatOff: function () {
            this._loop(!1)
        },
        _loop: function (b) {
            this.options.loop !== b && (this.options.loop = b, this._updateButtons(), this._trigger(a.jPlayer.event.repeat))
        },
        option: function (c, d) {
            var e = c;
            if (0 === arguments.length) return a.extend(!0, {}, this.options);
            if ("string" == typeof c) {
                var f = c.split(".");
                if (d === b) {
                    for (var g = a.extend(!0, {}, this.options), h = 0; h < f.length; h++) {
                        if (g[f[h]] === b) return this._warning({
                            type: a.jPlayer.warning.OPTION_KEY,
                            context: c,
                            message: a.jPlayer.warningMsg.OPTION_KEY,
                            hint: a.jPlayer.warningHint.OPTION_KEY
                        }), b;
                        g = g[f[h]]
                    }
                    return g
                }
                e = {};
                for (var i = e, j = 0; j < f.length; j++) j < f.length - 1 ? (i[f[j]] = {}, i = i[f[j]]) : i[f[j]] = d
            }
            return this._setOptions(e), this
        },
        _setOptions: function (b) {
            var c = this;
            return a.each(b, function (a, b) {
                c._setOption(a, b)
            }), this
        },
        _setOption: function (b, c) {
            var d = this;
            switch (b) {
                case"volume":
                    this.volume(c);
                    break;
                case"muted":
                    this._muted(c);
                    break;
                case"globalVolume":
                    this.options[b] = c;
                    break;
                case"cssSelectorAncestor":
                    this._cssSelectorAncestor(c);
                    break;
                case"cssSelector":
                    a.each(c, function (a, b) {
                        d._cssSelector(a, b)
                    });
                    break;
                case"playbackRate":
                    this.options[b] = c = this._limitValue(c, this.options.minPlaybackRate, this.options.maxPlaybackRate), this.html.used && this._html_setProperty("playbackRate", c), this._updatePlaybackRate();
                    break;
                case"defaultPlaybackRate":
                    this.options[b] = c = this._limitValue(c, this.options.minPlaybackRate, this.options.maxPlaybackRate), this.html.used && this._html_setProperty("defaultPlaybackRate", c), this._updatePlaybackRate();
                    break;
                case"minPlaybackRate":
                    this.options[b] = c = this._limitValue(c, .1, this.options.maxPlaybackRate - .1), this._updatePlaybackRate();
                    break;
                case"maxPlaybackRate":
                    this.options[b] = c = this._limitValue(c, this.options.minPlaybackRate + .1, 16), this._updatePlaybackRate();
                    break;
                case"fullScreen":
                    if (this.options[b] !== c) {
                        var e = a.jPlayer.nativeFeatures.fullscreen.used.webkitVideo;
                        (!e || e && !this.status.waitForPlay) && (e || (this.options[b] = c), c ? this._requestFullscreen() : this._exitFullscreen(), e || this._setOption("fullWindow", c))
                    }
                    break;
                case"fullWindow":
                    this.options[b] !== c && (this._removeUiClass(), this.options[b] = c, this._refreshSize());
                    break;
                case"size":
                    this.options.fullWindow || this.options[b].cssClass === c.cssClass || this._removeUiClass(), this.options[b] = a.extend({}, this.options[b], c), this._refreshSize();
                    break;
                case"sizeFull":
                    this.options.fullWindow && this.options[b].cssClass !== c.cssClass && this._removeUiClass(), this.options[b] = a.extend({}, this.options[b], c), this._refreshSize();
                    break;
                case"autohide":
                    this.options[b] = a.extend({}, this.options[b], c), this._updateAutohide();
                    break;
                case"loop":
                    this._loop(c);
                    break;
                case"remainingDuration":
                    this.options[b] = c, this._updateInterface();
                    break;
                case"toggleDuration":
                    this.options[b] = c;
                    break;
                case"nativeVideoControls":
                    this.options[b] = a.extend({}, this.options[b], c), this.status.nativeVideoControls = this._uaBlocklist(this.options.nativeVideoControls), this._restrictNativeVideoControls(), this._updateNativeVideoControls();
                    break;
                case"noFullWindow":
                    this.options[b] = a.extend({}, this.options[b], c), this.status.nativeVideoControls = this._uaBlocklist(this.options.nativeVideoControls), this.status.noFullWindow = this._uaBlocklist(this.options.noFullWindow), this._restrictNativeVideoControls(), this._updateButtons();
                    break;
                case"noVolume":
                    this.options[b] = a.extend({}, this.options[b], c), this.status.noVolume = this._uaBlocklist(this.options.noVolume), this._updateVolume(), this._updateMute();
                    break;
                case"emulateHtml":
                    this.options[b] !== c && (this.options[b] = c, c ? this._emulateHtmlBridge() : this._destroyHtmlBridge());
                    break;
                case"timeFormat":
                    this.options[b] = a.extend({}, this.options[b], c);
                    break;
                case"keyEnabled":
                    this.options[b] = c, c || this !== a.jPlayer.focus || (a.jPlayer.focus = null);
                    break;
                case"keyBindings":
                    this.options[b] = a.extend(!0, {}, this.options[b], c);
                    break;
                case"audioFullScreen":
                    this.options[b] = c
            }
            return this
        },
        _refreshSize: function () {
            this._setSize(), this._addUiClass(), this._updateSize(), this._updateButtons(), this._updateAutohide(), this._trigger(a.jPlayer.event.resize)
        },
        _setSize: function () {
            this.options.fullWindow ? (this.status.width = this.options.sizeFull.width, this.status.height = this.options.sizeFull.height, this.status.cssClass = this.options.sizeFull.cssClass) : (this.status.width = this.options.size.width, this.status.height = this.options.size.height, this.status.cssClass = this.options.size.cssClass), this.element.css({
                width: this.status.width,
                height: this.status.height
            })
        },
        _addUiClass: function () {
            this.ancestorJq.length && this.ancestorJq.addClass(this.status.cssClass)
        },
        _removeUiClass: function () {
            this.ancestorJq.length && this.ancestorJq.removeClass(this.status.cssClass)
        },
        _updateSize: function () {
            this.internal.poster.jq.css({
                width: this.status.width,
                height: this.status.height
            }), !this.status.waitForPlay && this.html.active && this.status.video || this.html.video.available && this.html.used && this.status.nativeVideoControls ? this.internal.video.jq.css({
                width: this.status.width,
                height: this.status.height
            }) : !this.status.waitForPlay && this.flash.active && this.status.video && this.internal.flash.jq.css({
                width: this.status.width,
                height: this.status.height
            })
        },
        _updateAutohide: function () {
            var a = this, b = "mousemove.jPlayer", c = ".jPlayerAutohide", d = b + c, e = function () {
                a.css.jq.gui.fadeIn(a.options.autohide.fadeIn, function () {
                    clearTimeout(a.internal.autohideId), a.internal.autohideId = setTimeout(function () {
                        a.css.jq.gui.fadeOut(a.options.autohide.fadeOut)
                    }, a.options.autohide.hold)
                })
            };
            this.css.jq.gui.length && (this.css.jq.gui.stop(!0, !0),
                clearTimeout(this.internal.autohideId), this.element.unbind(c), this.css.jq.gui.unbind(c), this.status.nativeVideoControls ? this.css.jq.gui.hide() : this.options.fullWindow && this.options.autohide.full || !this.options.fullWindow && this.options.autohide.restored ? (this.element.bind(d, e), this.css.jq.gui.bind(d, e), this.css.jq.gui.hide()) : this.css.jq.gui.show())
        },
        fullScreen: function () {
            this._setOption("fullScreen", !0)
        },
        restoreScreen: function () {
            this._setOption("fullScreen", !1)
        },
        _fullscreenAddEventListeners: function () {
            var b = this, c = a.jPlayer.nativeFeatures.fullscreen;
            c.api.fullscreenEnabled && c.event.fullscreenchange && ("function" != typeof this.internal.fullscreenchangeHandler && (this.internal.fullscreenchangeHandler = function () {
                b._fullscreenchange()
            }), document.addEventListener(c.event.fullscreenchange, this.internal.fullscreenchangeHandler, !1))
        },
        _fullscreenRemoveEventListeners: function () {
            var b = a.jPlayer.nativeFeatures.fullscreen;
            this.internal.fullscreenchangeHandler && document.removeEventListener(b.event.fullscreenchange, this.internal.fullscreenchangeHandler, !1)
        },
        _fullscreenchange: function () {
            this.options.fullScreen && !a.jPlayer.nativeFeatures.fullscreen.api.fullscreenElement() && this._setOption("fullScreen", !1)
        },
        _requestFullscreen: function () {
            var b = this.ancestorJq.length ? this.ancestorJq[0] : this.element[0],
                c = a.jPlayer.nativeFeatures.fullscreen;
            c.used.webkitVideo && (b = this.htmlElement.video), c.api.fullscreenEnabled && c.api.requestFullscreen(b)
        },
        _exitFullscreen: function () {
            var b, c = a.jPlayer.nativeFeatures.fullscreen;
            c.used.webkitVideo && (b = this.htmlElement.video), c.api.fullscreenEnabled && c.api.exitFullscreen(b)
        },
        _html_initMedia: function (b) {
            var c = a(this.htmlElement.media).empty();
            a.each(b.track || [], function (a, b) {
                var d = document.createElement("track");
                d.setAttribute("kind", b.kind ? b.kind : ""), d.setAttribute("src", b.src ? b.src : ""), d.setAttribute("srclang", b.srclang ? b.srclang : ""), d.setAttribute("label", b.label ? b.label : ""), b.def && d.setAttribute("default", b.def), c.append(d)
            }), this.htmlElement.media.src = this.status.src, "none" !== this.options.preload && this._html_load(), this._trigger(a.jPlayer.event.timeupdate)
        },
        _html_setFormat: function (b) {
            var c = this;
            a.each(this.formats, function (a, d) {
                return c.html.support[d] && b[d] ? (c.status.src = b[d], c.status.format[d] = !0, c.status.formatType = d, !1) : void 0
            })
        },
        _html_setAudio: function (a) {
            this._html_setFormat(a), this.htmlElement.media = this.htmlElement.audio, this._html_initMedia(a)
        },
        _html_setVideo: function (a) {
            this._html_setFormat(a), this.status.nativeVideoControls && (this.htmlElement.video.poster = this._validString(a.poster) ? a.poster : ""), this.htmlElement.media = this.htmlElement.video, this._html_initMedia(a)
        },
        _html_resetMedia: function () {
            this.htmlElement.media && (this.htmlElement.media.id !== this.internal.video.id || this.status.nativeVideoControls || this.internal.video.jq.css({
                width: "0px",
                height: "0px"
            }), this.htmlElement.media.pause())
        },
        _html_clearMedia: function () {
            this.htmlElement.media && (this.htmlElement.media.src = "about:blank", this.htmlElement.media.load())
        },
        _html_load: function () {
            this.status.waitForLoad && (this.status.waitForLoad = !1, this.htmlElement.media.load()), clearTimeout(this.internal.htmlDlyCmdId)
        },
        _html_play: function (a) {
            var b = this, c = this.htmlElement.media;
            if (this.androidFix.pause = !1, this._html_load(), this.androidFix.setMedia) this.androidFix.play = !0, this.androidFix.time = a; else if (isNaN(a)) c.play(); else {
                this.internal.cmdsIgnored && c.play();
                try {
                    if (c.seekable && !("object" == typeof c.seekable && c.seekable.length > 0)) throw 1;
                    c.currentTime = a, c.play()
                } catch (d) {
                    return void(this.internal.htmlDlyCmdId = setTimeout(function () {
                        b.play(a)
                    }, 250))
                }
            }
            this._html_checkWaitForPlay()
        },
        _html_pause: function (a) {
            var b = this, c = this.htmlElement.media;
            if (this.androidFix.play = !1, a > 0 ? this._html_load() : clearTimeout(this.internal.htmlDlyCmdId), c.pause(), this.androidFix.setMedia) this.androidFix.pause = !0, this.androidFix.time = a; else if (!isNaN(a)) try {
                if (c.seekable && !("object" == typeof c.seekable && c.seekable.length > 0)) throw 1;
                c.currentTime = a
            } catch (d) {
                return void(this.internal.htmlDlyCmdId = setTimeout(function () {
                    b.pause(a)
                }, 250))
            }
            a > 0 && this._html_checkWaitForPlay()
        },
        _html_playHead: function (a) {
            var b = this, c = this.htmlElement.media;
            this._html_load();
            try {
                if ("object" == typeof c.seekable && c.seekable.length > 0) c.currentTime = a * c.seekable.end(c.seekable.length - 1) / 100; else {
                    if (!(c.duration > 0) || isNaN(c.duration)) throw"e";
                    c.currentTime = a * c.duration / 100
                }
            } catch (d) {
                return void(this.internal.htmlDlyCmdId = setTimeout(function () {
                    b.playHead(a)
                }, 250))
            }
            this.status.waitForLoad || this._html_checkWaitForPlay()
        },
        _html_checkWaitForPlay: function () {
            this.status.waitForPlay && (this.status.waitForPlay = !1, this.css.jq.videoPlay.length && this.css.jq.videoPlay.hide(), this.status.video && (this.internal.poster.jq.hide(), this.internal.video.jq.css({
                width: this.status.width,
                height: this.status.height
            })))
        },
        _html_setProperty: function (a, b) {
            this.html.audio.available && (this.htmlElement.audio[a] = b), this.html.video.available && (this.htmlElement.video[a] = b)
        },
        _flash_setAudio: function (b) {
            var c = this;
            try {
                a.each(this.formats, function (a, d) {
                    if (c.flash.support[d] && b[d]) {
                        switch (d) {
                            case"m4a":
                            case"fla":
                                c._getMovie().fl_setAudio_m4a(b[d]);
                                break;
                            case"mp3":
                                c._getMovie().fl_setAudio_mp3(b[d]);
                                break;
                            case"rtmpa":
                                c._getMovie().fl_setAudio_rtmp(b[d])
                        }
                        return c.status.src = b[d], c.status.format[d] = !0, c.status.formatType = d, !1
                    }
                }), "auto" === this.options.preload && (this._flash_load(), this.status.waitForLoad = !1)
            } catch (d) {
                this._flashError(d)
            }
        },
        _flash_setVideo: function (b) {
            var c = this;
            try {
                a.each(this.formats, function (a, d) {
                    if (c.flash.support[d] && b[d]) {
                        switch (d) {
                            case"m4v":
                            case"flv":
                                c._getMovie().fl_setVideo_m4v(b[d]);
                                break;
                            case"rtmpv":
                                c._getMovie().fl_setVideo_rtmp(b[d])
                        }
                        return c.status.src = b[d], c.status.format[d] = !0, c.status.formatType = d, !1
                    }
                }), "auto" === this.options.preload && (this._flash_load(), this.status.waitForLoad = !1)
            } catch (d) {
                this._flashError(d)
            }
        },
        _flash_resetMedia: function () {
            this.internal.flash.jq.css({width: "0px", height: "0px"}), this._flash_pause(NaN)
        },
        _flash_clearMedia: function () {
            try {
                this._getMovie().fl_clearMedia()
            } catch (a) {
                this._flashError(a)
            }
        },
        _flash_load: function () {
            try {
                this._getMovie().fl_load()
            } catch (a) {
                this._flashError(a)
            }
            this.status.waitForLoad = !1
        },
        _flash_play: function (a) {
            try {
                this._getMovie().fl_play(a)
            } catch (b) {
                this._flashError(b)
            }
            this.status.waitForLoad = !1, this._flash_checkWaitForPlay()
        },
        _flash_pause: function (a) {
            try {
                this._getMovie().fl_pause(a)
            } catch (b) {
                this._flashError(b)
            }
            a > 0 && (this.status.waitForLoad = !1, this._flash_checkWaitForPlay())
        },
        _flash_playHead: function (a) {
            try {
                this._getMovie().fl_play_head(a)
            } catch (b) {
                this._flashError(b)
            }
            this.status.waitForLoad || this._flash_checkWaitForPlay()
        },
        _flash_checkWaitForPlay: function () {
            this.status.waitForPlay && (this.status.waitForPlay = !1, this.css.jq.videoPlay.length && this.css.jq.videoPlay.hide(), this.status.video && (this.internal.poster.jq.hide(), this.internal.flash.jq.css({
                width: this.status.width,
                height: this.status.height
            })))
        },
        _flash_volume: function (a) {
            try {
                this._getMovie().fl_volume(a)
            } catch (b) {
                this._flashError(b)
            }
        },
        _flash_mute: function (a) {
            try {
                this._getMovie().fl_mute(a)
            } catch (b) {
                this._flashError(b)
            }
        },
        _getMovie: function () {
            return document[this.internal.flash.id]
        },
        _getFlashPluginVersion: function () {
            var a, b = 0;
            if (window.ActiveXObject) try {
                if (a = new ActiveXObject("ShockwaveFlash.ShockwaveFlash")) {
                    var c = a.GetVariable("$version");
                    c && (c = c.split(" ")[1].split(","), b = parseInt(c[0], 10) + "." + parseInt(c[1], 10))
                }
            } catch (d) {
            } else navigator.plugins && navigator.mimeTypes.length > 0 && (a = navigator.plugins["Shockwave Flash"], a && (b = navigator.plugins["Shockwave Flash"].description.replace(/.*\s(\d+\.\d+).*!/, "$1")));
            return 1 * b
        },
        _checkForFlash: function (a) {
            var b = !1;
            return this._getFlashPluginVersion() >= a && (b = !0), b
        },
        _validString: function (a) {
            return a && "string" == typeof a
        },
        _limitValue: function (a, b, c) {
            return b > a ? b : a > c ? c : a
        },
        _urlNotSetError: function (b) {
            this._error({
                type: a.jPlayer.error.URL_NOT_SET,
                context: b,
                message: a.jPlayer.errorMsg.URL_NOT_SET,
                hint: a.jPlayer.errorHint.URL_NOT_SET
            })
        },
        _flashError: function (b) {
            var c;
            c = this.internal.ready ? "FLASH_DISABLED" : "FLASH", this._error({
                type: a.jPlayer.error[c],
                context: this.internal.flash.swf,
                message: a.jPlayer.errorMsg[c] + b.message,
                hint: a.jPlayer.errorHint[c]
            }), this.internal.flash.jq.css({width: "1px", height: "1px"})
        },
        _error: function (b) {
            this._trigger(a.jPlayer.event.error, b), this.options.errorAlerts && this._alert("Error!" + (b.message ? "\n" + b.message : "") + (b.hint ? "\n" + b.hint : "") + "\nContext: " + b.context)
        },
        _warning: function (c) {
            this._trigger(a.jPlayer.event.warning, b, c), this.options.warningAlerts && this._alert("Warning!" + (c.message ? "\n" + c.message : "") + (c.hint ? "\n" + c.hint : "") + "\nContext: " + c.context)
        },
        _alert: function (a) {
            var b = "jPlayer " + this.version.script + " : id='" + this.internal.self.id + "' : " + a;
            this.options.consoleAlerts ? window.console && window.console.log && window.console.log(b) : alert(b)
        },
        _emulateHtmlBridge: function () {
            var b = this;
            a.each(a.jPlayer.emulateMethods.split(/\s+/g), function (a, c) {
                b.internal.domNode[c] = function (a) {
                    b[c](a)
                }
            }), a.each(a.jPlayer.event, function (c, d) {
                var e = !0;
                a.each(a.jPlayer.reservedEvent.split(/\s+/g), function (a, b) {
                    return b === c ? (e = !1, !1) : void 0
                }), e && b.element.bind(d + ".jPlayer.jPlayerHtml", function () {
                    b._emulateHtmlUpdate();
                    var a = document.createEvent("Event");
                    a.initEvent(c, !1, !0), b.internal.domNode.dispatchEvent(a)
                })
            })
        },
        _emulateHtmlUpdate: function () {
            var b = this;
            a.each(a.jPlayer.emulateStatus.split(/\s+/g), function (a, c) {
                b.internal.domNode[c] = b.status[c]
            }), a.each(a.jPlayer.emulateOptions.split(/\s+/g), function (a, c) {
                b.internal.domNode[c] = b.options[c]
            })
        },
        _destroyHtmlBridge: function () {
            var b = this;
            this.element.unbind(".jPlayerHtml");
            var c = a.jPlayer.emulateMethods + " " + a.jPlayer.emulateStatus + " " + a.jPlayer.emulateOptions;
            a.each(c.split(/\s+/g), function (a, c) {
                delete b.internal.domNode[c]
            })
        }
    }, a.jPlayer.error = {
        FLASH: "e_flash",
        FLASH_DISABLED: "e_flash_disabled",
        NO_SOLUTION: "e_no_solution",
        NO_SUPPORT: "e_no_support",
        URL: "e_url",
        URL_NOT_SET: "e_url_not_set",
        VERSION: "e_version"
    }, a.jPlayer.errorMsg = {
        FLASH: "jPlayer's Flash fallback is not configured correctly, or a command was issued before the jPlayer Ready event. Details: ",
        FLASH_DISABLED: "jPlayer's Flash fallback has been disabled by the browser due to the CSS rules you have used. Details: ",
        NO_SOLUTION: "No solution can be found by jPlayer in this browser. Neither HTML nor Flash can be used.",
        NO_SUPPORT: "It is not possible to play any media format provided in setMedia() on this browser using your current options.",
        URL: "Media URL could not be loaded.",
        URL_NOT_SET: "Attempt to issue media playback commands, while no media url is set.",
        VERSION: "jPlayer " + a.jPlayer.prototype.version.script + " needs Jplayer.swf version " + a.jPlayer.prototype.version.needFlash + " but found "
    }, a.jPlayer.errorHint = {
        FLASH: "Check your swfPath option and that Jplayer.swf is there.",
        FLASH_DISABLED: "Check that you have not display:none; the jPlayer entity or any ancestor.",
        NO_SOLUTION: "Review the jPlayer options: support and supplied.",
        NO_SUPPORT: "Video or audio formats defined in the supplied option are missing.",
        URL: "Check media URL is valid.",
        URL_NOT_SET: "Use setMedia() to set the media URL.",
        VERSION: "Update jPlayer files."
    }, a.jPlayer.warning = {
        CSS_SELECTOR_COUNT: "e_css_selector_count",
        CSS_SELECTOR_METHOD: "e_css_selector_method",
        CSS_SELECTOR_STRING: "e_css_selector_string",
        OPTION_KEY: "e_option_key"
    }, a.jPlayer.warningMsg = {
        CSS_SELECTOR_COUNT: "The number of css selectors found did not equal one: ",
        CSS_SELECTOR_METHOD: "The methodName given in jPlayer('cssSelector') is not a valid jPlayer method.",
        CSS_SELECTOR_STRING: "The methodCssSelector given in jPlayer('cssSelector') is not a String or is empty.",
        OPTION_KEY: "The option requested in jPlayer('option') is undefined."
    }, a.jPlayer.warningHint = {
        CSS_SELECTOR_COUNT: "Check your css selector and the ancestor.",
        CSS_SELECTOR_METHOD: "Check your method name.",
        CSS_SELECTOR_STRING: "Check your css selector is a string.",
        OPTION_KEY: "Check your option name."
    }
}), function (a, b) {
    jPlayerPlaylist = function (b, c, d) {
        var e = this;
        this.current = 0, this.loop = !1, this.shuffled = !1, this.removing = !1, this.cssSelector = a.extend({}, this._cssSelector, b), this.options = a.extend(!0, {
            keyBindings: {
                next: {
                    key: 39,
                    fn: function () {
                        e.next()
                    }
                }, previous: {
                    key: 37, fn: function () {
                        e.previous()
                    }
                }
            }
        }, this._options, d), this.playlist = [], this.original = [], this._initPlaylist(c), this.cssSelector.title = this.cssSelector.cssSelectorAncestor + " .jp-title", this.cssSelector.playlist = this.cssSelector.cssSelectorAncestor + " .jp-playlist", this.cssSelector.next = this.cssSelector.cssSelectorAncestor + " .jp-next", this.cssSelector.previous = this.cssSelector.cssSelectorAncestor + " .jp-previous", this.cssSelector.shuffle = this.cssSelector.cssSelectorAncestor + " .jp-shuffle", this.cssSelector.shuffleOff = this.cssSelector.cssSelectorAncestor + " .jp-shuffle-off", this.cssSelector.repeatone = this.cssSelector.cssSelectorAncestor + " .jp-repeatone", this.cssSelector.repeatoneOff = this.cssSelector.cssSelectorAncestor + " .jp-repeatone-off", this.options.cssSelectorAncestor = this.cssSelector.cssSelectorAncestor, this.options.repeat = function (a) {
            e.loop = a.jPlayer.options.loop
        }, a(this.cssSelector.jPlayer).bind(a.jPlayer.event.ready, function () {
            e._init()
        }), a(this.cssSelector.jPlayer).bind(a.jPlayer.event.ended, function () {
            var a = e.current >= 0 ? e.current : 0;
            e.options.playlistOptions.repeatone ? (e.select(a), e.play()) : e.next()
        }), a(this.cssSelector.jPlayer).bind(a.jPlayer.event.play, function () {
            a(this).jPlayer("pauseOthers")
        }), a(this.cssSelector.jPlayer).bind(a.jPlayer.event.resize, function (b) {
            b.jPlayer.options.fullScreen ? a(e.cssSelector.title).show() : a(e.cssSelector.title).hide()
        }), a(this.cssSelector.previous).click(function () {
            return e.previous(), a(this).blur(), !1
        }), a(this.cssSelector.next).click(function () {
            return e.next(), a(this).blur(), !1
        }), a(this.cssSelector.shuffle).click(function () {
            return e.shuffle(!0), !1
        }), a(this.cssSelector.shuffleOff).click(function () {
            return e.shuffle(!1), !1
        }).hide(), a(this.cssSelector.repeatone).click(function () {
            return e.options.playlistOptions.repeatone = !1, a(e.cssSelector.repeatone).hide(), a(e.cssSelector.repeatoneOff).show(), !1
        }), a(this.cssSelector.repeatoneOff).click(function () {
            return e.options.playlistOptions.repeatone = !0, a(e.cssSelector.repeatoneOff).hide(), a(e.cssSelector.repeatone).show(), !1
        }), this.options.fullScreen || a(this.cssSelector.title).hide(), a(this.cssSelector.playlist + " ul").empty(), this._createItemHandlers(), a(this.cssSelector.jPlayer).jPlayer(this.options)
    }, jPlayerPlaylist.prototype = {
        _cssSelector: {
            jPlayer: "#jquery_jplayer_1",
            cssSelectorAncestor: "#jp_container_1"
        },
        _options: {
            playlistOptions: {
                autoPlay: !1,
                loopOnPrevious: !1,
                shuffleOnLoop: !0,
                enableRemoveControls: !1,
                displayTime: "slow",
                addTime: "fast",
                removeTime: "fast",
                shuffleTime: "slow",
                itemClass: "jp-playlist-item",
                freeGroupClass: "jp-free-media",
                freeItemClass: "jp-playlist-item-free",
                removeItemClass: "jp-playlist-item-remove"
            }
        },
        option: function (a, c) {
            if (c === b) return this.options.playlistOptions[a];
            switch (this.options.playlistOptions[a] = c, a) {
                case"enableRemoveControls":
                    this._updateControls();
                    break;
                case"itemClass":
                case"freeGroupClass":
                case"freeItemClass":
                case"removeItemClass":
                    this._refresh(!0), this._createItemHandlers()
            }
            return this
        },
        _init: function () {
            var a = this;
            this._refresh(function () {
                a.options.playlistOptions.autoPlay ? a.play(a.current) : a.select(a.current)
            })
        },
        _initPlaylist: function (b) {
            this.current = 0, this.shuffled = !1, this.removing = !1, this.original = a.extend(!0, [], b), this._originalPlaylist()
        },
        _originalPlaylist: function () {
            var b = this;
            this.playlist = [], a.each(this.original, function (a) {
                b.playlist[a] = b.original[a]
            })
        },
        _refresh: function (b) {
            var c = this;
            if (b && !a.isFunction(b)) a(this.cssSelector.playlist + " ul").empty(), a.each(this.playlist, function (b) {
                a(c.cssSelector.playlist + " ul").append(c._createListItem(c.playlist[b]))
            }), this._updateControls(); else {
                var d = a(this.cssSelector.playlist + " ul").children().length ? this.options.playlistOptions.displayTime : 0;
                a(this.cssSelector.playlist + " ul").slideUp(d, function () {
                    var d = a(this);
                    a(this).empty(), a.each(c.playlist, function (a) {
                        d.append(c._createListItem(c.playlist[a]))
                    }), c._updateControls(), a.isFunction(b) && b(), c.playlist.length ? a(this).slideDown(c.options.playlistOptions.displayTime) : a(this).show()
                })
            }
        },
        _createListItem: function (b) {
            var c = this, d = "<li><div>";
            if (d += "<a href='javascript:;' class='" + this.options.playlistOptions.removeItemClass + "'>&times;</a>", b.free) {
                var e = !0;
                d += "<span class='" + this.options.playlistOptions.freeGroupClass + "'>(", a.each(b, function (b, f) {
                    a.jPlayer.prototype.format[b] && (e ? e = !1 : d += " | ", d += "<a class='" + c.options.playlistOptions.freeItemClass + "' href='" + f + "' tabindex='1'>" + b + "</a>")
                }), d += ")</span>"
            }
            return d += "<a href='javascript:;' class='" + this.options.playlistOptions.itemClass + "' tabindex='1'>" + b.title + (b.artist ? " <span class='jp-artist'>by " + b.artist + "</span>" : "") + "</a>", d += "</div></li>"
        },
        _createItemHandlers: function () {
            var b = this;
            a(this.cssSelector.playlist).off("click", "a." + this.options.playlistOptions.itemClass).on("click", "a." + this.options.playlistOptions.itemClass, function () {
                var c = a(this).parent().parent().index();
                return b.current !== c ? b.play(c) : a(b.cssSelector.jPlayer).jPlayer("play"), a(this).blur(), !1
            }), a(this.cssSelector.playlist).off("click", "a." + this.options.playlistOptions.freeItemClass).on("click", "a." + this.options.playlistOptions.freeItemClass, function () {
                return a(this).parent().parent().find("." + b.options.playlistOptions.itemClass).click(), a(this).blur(), !1
            }), a(this.cssSelector.playlist).off("click", "a." + this.options.playlistOptions.removeItemClass).on("click", "a." + this.options.playlistOptions.removeItemClass, function () {
                var c = a(this).parent().parent().index();
                return b.remove(c), a(this).blur(), !1
            })
        },
        _updateControls: function () {
            this.options.playlistOptions.enableRemoveControls ? a(this.cssSelector.playlist + " ." + this.options.playlistOptions.removeItemClass).show() : a(this.cssSelector.playlist + " ." + this.options.playlistOptions.removeItemClass).hide(), this.shuffled ? (a(this.cssSelector.shuffleOff).show(), a(this.cssSelector.shuffle).hide()) : (a(this.cssSelector.shuffleOff).hide(), a(this.cssSelector.shuffle).show()), this.options.playlistOptions.repeatone ? (a(this.cssSelector.repeatone).show(), a(this.cssSelector.repeatoneOff).hide()) : (a(this.cssSelector.repeatone).hide(), a(this.cssSelector.repeatoneOff).show())
        },
        _highlight: function (c) {
            this.playlist.length && c !== b && (a(this.cssSelector.playlist + " .jp-playlist-current").removeClass("jp-playlist-current"), a(this.cssSelector.playlist + " li:nth-child(" + (c + 1) + ")").addClass("jp-playlist-current").find(".jp-playlist-item").addClass("jp-playlist-current"), a(this.cssSelector.title + " li").html(this.playlist[c].title + (this.playlist[c].artist ? " <span class='jp-artist'>by " + this.playlist[c].artist + "</span>" : "")))
        },
        setPlaylist: function (a) {
            this._initPlaylist(a), this._init()
        },
        add: function (b, c) {
            a(this.cssSelector.playlist + " ul").append(this._createListItem(b)).find("li:last-child").hide().slideDown(this.options.playlistOptions.addTime), this._updateControls(), this.original.push(b), this.playlist.push(b), c ? this.play(this.playlist.length - 1) : 1 === this.original.length && this.select(0)
        },
        remove: function (c) {
            var d = this;
            return c === b ? (this._initPlaylist([]), this._refresh(function () {
                a(d.cssSelector.jPlayer).jPlayer("clearMedia")
            }), !0) : this.removing ? !1 : (c = 0 > c ? d.original.length + c : c, c >= 0 && c < this.playlist.length && (this.removing = !0, a(this.cssSelector.playlist + " li:nth-child(" + (c + 1) + ")").slideUp(this.options.playlistOptions.removeTime, function () {
                if (a(this).remove(), d.shuffled) {
                    var b = d.playlist[c];
                    a.each(d.original, function (a) {
                        return d.original[a] === b ? (d.original.splice(a, 1), !1) : void 0
                    }), d.playlist.splice(c, 1)
                } else d.original.splice(c, 1), d.playlist.splice(c, 1);
                d.original.length ? c === d.current ? (d.current = c < d.original.length ? d.current : d.original.length - 1, d.select(d.current)) : c < d.current && d.current-- : (a(d.cssSelector.jPlayer).jPlayer("clearMedia"), d.current = 0, d.shuffled = !1, d._updateControls()), d.removing = !1
            })), !0)
        },
        select: function (b) {
            b = 0 > b ? this.original.length + b : b, b >= 0 && b < this.playlist.length ? (this.current = b, this._highlight(b), a(this.cssSelector.jPlayer).jPlayer("setMedia", this.playlist[this.current])) : this.current = 0
        },
        play: function (c) {
            c = 0 > c ? this.original.length + c : c, c >= 0 && c < this.playlist.length ? this.playlist.length && (this.select(c), a(this.cssSelector.jPlayer).jPlayer("play")) : c === b && a(this.cssSelector.jPlayer).jPlayer("play")
        },
        pause: function () {
            a(this.cssSelector.jPlayer).jPlayer("pause")
        },
        next: function () {
            var a = this.current + 1 < this.playlist.length ? this.current + 1 : 0;
            this.loop ? 0 === a && this.shuffled && this.options.playlistOptions.shuffleOnLoop && this.playlist.length > 1 ? this.shuffle(!0, !0) : this.play(a) : a > 0 && this.play(a)
        },
        previous: function () {
            var a = this.current - 1 >= 0 ? this.current - 1 : this.playlist.length - 1;
            (this.loop && this.options.playlistOptions.loopOnPrevious || a < this.playlist.length - 1) && this.play(a)
        },
        shuffle: function (c, d) {
            var e = this;
            c === b && (c = !this.shuffled), (c || c !== this.shuffled) && a(this.cssSelector.playlist + " ul").slideUp(this.options.playlistOptions.shuffleTime, function () {
                e.shuffled = c, c ? e.playlist.sort(function () {
                    return .5 - Math.random()
                }) : e._originalPlaylist(), e._refresh(!0), d || !a(e.cssSelector.jPlayer).data("jPlayer").status.paused ? e.play(0) : e.select(0), a(this).slideDown(e.options.playlistOptions.shuffleTime)
            })
        }
    }
}(jQuery), function ($) {
    $.luoo = {
        cfg: {foo: "bar"}, tips: [], openedMsg: null, qrcode: null, init: function (a) {
            return this.root = a.root ? a.root : document.location.href, this.url_static = a.url_static ? a.url_static : this.root + "/static", this.res = a.res ? a.res : {}, this.cfg = a.cfg ? a.cfg : {}, this
        }, post: function (url, data, cback, object) {
            $.ajax({type: "post", url: url, data: data, dataType: "json"}).done(function (json) {
                try {
                    eval("var func = " + cback), func(json, object)
                } catch (e) {
                }
            })
        }, get: function (url, data, cback, object, async) {
            async = async === !0 ? !0 : !1, $.ajax({
                type: "get",
                url: url,
                data: data,
                dataType: "json",
                async: async
            }).done(function (json) {
                try {
                    eval("var func = " + cback), func(json, object)
                } catch (e) {
                }
            })
        }, browser: function () {
            var a = navigator.userAgent.toLowerCase();
            return {
                version: a.match(/(?:firefox|opera|safari|chrome|msie)[\/: ]([\d.]+)/)[1],
                safari: /version.+safari/.test(a),
                chrome: /chrome/.test(a),
                firefox: /firefox/.test(a),
                ie: /msie/.test(a),
                opera: /opera/.test(a)
            }
        }, destroy_tip: function (a) {
            0 != $.luoo.tips.length && $.each($.luoo.tips, function (b, c) {
                !c || a && c.id != a || c.destroy(), c && a && c.id == a ? $.luoo.tips.splice(b, 1) : $.luoo.tips.splice(0, $.luoo.tips.length)
            })
        }, msg: function (a, b, c, d, e, f) {
            var g = "undefined" == typeof c ? !1 : c, h = "undefined" == typeof d ? "none" : d,
                e = "undefined" == typeof e ? null : e, f = "undefined" == typeof f ? 410 : f,
                i = {title: a, content: b, ok: g, cancle: !1, lock: "none" != h, background: h, width: f, close: e};
            this.openedMsg = art.dialog(i)
        }, close_msg: function () {
            this.openedMsg && this.openedMsg.close()
        }
    }, $.fn.luooTab = function (a) {
        return $(".tab-item").removeClass("actived"), $(".tab-content-item").hide(), $(this).each(function () {
            var b = $(this), c = 0;
            a && (c = $("#tab-" + a).index()), $(".tab-item", b).eq(c).addClass("actived"), $(".tab-content-item", b).hide(), $(".tab-content-item", b).eq(c).show(), b.find(".tab-item").on("click", function () {
                var a = $(this), c = a.index();
                $(".tab-item", b).removeClass("actived"), a.addClass("actived"), $(".tab-content-item", b).hide(), $(".tab-content-item", b).eq(c).fadeIn(300)
            })
        })
    }, $.fn.getComments = function () {
        return $(this).each(function () {
            var a = $(this);
            $.luoo.post(a.data("url"), a.data(), "get_comment_callback", a), a.on("click", ".ajax-comment-pager a", function (b) {
                b.preventDefault(), $.luoo.post($(this).attr("href"), "", "get_comment_callback", a)
            }), $(".ln-comment-sort").click(function (b) {
                b.preventDefault(), $.luoo.post($(this).data("url"), "", "get_comment_callback", a), $(".ln-comment-sort").removeClass("current"), $(this).addClass("current")
            })
        })
    }, $.fn.luooPicturePager = function (a) {
        return $(this).each(function () {
            var b = $(this);
            a && b.wrap('<a href="' + a + '" title="下一篇"></a>')
        })
    }, $.fn.tip = function (a, b) {
        var c;
        return $(this).each(function () {
            var d = $(this), e = "object" == typeof a ? a : d.data(), f = e.tipid ? e.tipid : "",
                g = e.target ? e.target : d;
            f && $.luoo.destroy_tip(f);
            var h = {};
            e.adjustx && (h.x = e.adjustx), e.adjusty && (h.y = e.adjusty);
            var i = {classes: "qtip-luoo"};
            e.width && (i.width = e.width);
            var j = {solo: !0};
            if (e.show ? j.event = e.show : j.event = "click", e.hide) var k = e.hide; else var k = "click";
            if (e.posmy) var l = e.posmy; else var l = "top center";
            if (e.posat) var m = e.posat; else var m = "bottom center";
            var n = d.qtip({
                id: f, content: {
                    text: function (a, b) {
                        if (e.html) return e.html;
                        if (e.remote) $.ajax({url: e.remote, dataType: "json"}).done(function (a) {
                            b.set("content.text", a.data)
                        }).fail(function (a, c, d) {
                            b.set("content.text", c + ": " + d)
                        }); else if (e.ct) return $("#" + e.ct).length > 0 ? $("#" + e.ct).html() : e.ct;
                        return '<p style="padding: 20px 0; text-align: center"><img src="' + $.luoo.url_static + '/img/loading_W.gif"></p>'
                    }
                }, position: {my: l, at: m, target: g, adjust: h}, style: i, show: j, hide: k
            });
            c = n.qtip("api"), b !== !1 && c.show(), $.luoo.tips.push(c), $("body").on("click", function (a) {
                $(a.target).parents(".qtip").is($("#qtip-" + c.id)) || c.hide()
            })
        }), c
    }
}(jQuery), Array.prototype.indexOf || (Array.prototype.indexOf = function (a) {
    var b = this.length >>> 0, c = Number(arguments[1]) || 0;
    for (c = 0 > c ? Math.ceil(c) : Math.floor(c), 0 > c && (c += b); b > c; c++) if (c in this && this[c] === a) return c;
    return -1
}), "function" != typeof Array.prototype.reduce && (Array.prototype.reduce = function (a, b) {
    var c = b, d = 0, e = this.length;
    if ("undefined" == typeof b && (c = this[0], d = 1), "function" == typeof a) for (d; e > d; d++) this.hasOwnProperty(d) && (c = a(c, this[d], d, this));
    return c
}), "function" != typeof Array.prototype.map && (Array.prototype.map = function (a, b) {
    var c = [];
    if ("function" == typeof a) for (var d = 0, e = this.length; e > d; d++) c.push(a.call(b, this[d], d, this));
    return c
}), Object.keys || (Object.keys = function () {
    "use strict";
    var a = Object.prototype.hasOwnProperty, b = !{toString: null}.propertyIsEnumerable("toString"),
        c = ["toString", "toLocaleString", "valueOf", "hasOwnProperty", "isPrototypeOf", "propertyIsEnumerable", "constructor"],
        d = c.length;
    return function (e) {
        if ("object" != typeof e && ("function" != typeof e || null === e)) throw new TypeError("Object.keys called on non-object");
        var f, g, h = [];
        for (f in e) a.call(e, f) && h.push(f);
        if (b) for (g = 0; d > g; g++) a.call(e, c[g]) && h.push(c[g]);
        return h
    }
}()), $(document).ready(function () {
    check_login(), $("body").on("submit", ".form-ajax", function (evt) {
        evt.preventDefault();
        var $form = $(this), func = $form.attr("prepare");
        if (func) try {
            eval("var pcback = " + func);
            var re = pcback();
            if (!re) return !1
        } catch (e) {
            return !1
        }
        if ($form.data("disabled")) return !1;
        if ($form.data("disabled", !0), $form.is($(".form-valid")) && 0 == $form.data("valid-result")) return !1;
        var cback = function () {
        };
        return $form.attr("callback") && (cback = $form.attr("callback")), $.luoo.post($form.attr("action"), $form.serialize(), cback, $form), !0
    }), $("body").on("click", ".ajax", function (evt) {
        evt.preventDefault();
        var $this = $(this);
        if ($this.attr("confirm") && !confirm($this.attr("confirm"))) return !1;
        var cback = "common_ajax_callback";
        $this.attr("callback") && (cback = $this.attr("callback"));
        var data = {};
        return $this.data("param") && eval("data = " + $this.data("param")), $.luoo.post($this.attr("href"), data, cback, $this), !1
    }), $("body").on("click", ".btn-action-like", function (a) {
        a.preventDefault();
        var b = $(this);
        if ($("#qtip-" + b.data("tipid")).is("div")) return !1;
        var b = $(this);
        if (b.data("disabled")) return !1;
        if (b.data("confirm") && !confirm(b.data("confirm"))) return !1;
        var c = b.data("id"), d = b.data("type"), e = (b.data("fav"), "like_callback"),
            f = $.luoo.res.app[b.data("from_type")] || "", g = b.data("from_id") || "", h = [];
        return f && g && h.push({
            app_id: f,
            res_id: g
        }), b.data("cback") && (e = b.data("cback")), c ? (b.data("disabled", !0), $.luoo.post($.luoo.root + "/user/like", {
            id: c,
            res: $.luoo.res.app[d],
            from: h
        }, e, b), !1) : !1
    }), $("body").on("click", ".luoo-captcha-wrapper img, .luoo-captcha-wrapper a", function () {
        refresh_captcha()
    }), $("#luooWechatAccount").hover(function () {
        $(this).find(".wechat-qr").show()
    }, function () {
        $(this).find(".wechat-qr").hide()
    });
    var qqint;
    $("#luooQqAccount").hover(function () {
        $(this).find(".qq-group").show()
    }, function (a) {
        qqint = setTimeout(function () {
            $("#luooQqAccount").find(".qq-group").hide()
        }, 100)
    }), $(".qq-group").mouseenter(function () {
        clearTimeout(qqint), $(this).show()
    });
    var map = window.location.hash.substr(1);
    if ($(".util-luoo-tabs").luooTab(map), 1 != $.cookie("tipsclose") && $("#announcement").slideDown(200), $("#announcement .close").click(function () {
        $("#announcement").slideUp(200), $.cookie("tipsclose", 1)
    }), $(".foot-ct").is($("div"))) {
        var b2t_left = $(".foot-ct").offset().left - 0 + 980;
        $(window).scroll(function () {
            var a = $(document).scrollTop();
            a > 900 ? $("#backTop").css({
                left: b2t_left,
                bottom: 10
            }).fadeIn(300) : 300 > a && $("#backTop").fadeOut(300)
        })
    }
    $("#backTop").css("left", b2t_left), $("#backTop").click(function (a) {
        a.preventDefault(), $("html, body").animate({scrollTop: 0}, 300)
    }), $(".container").css("minHeight", $(window).height() - 422), $("body").on("mouseenter", "#lnMessage", function () {
        $(this).tip()
    }), $("body").on("mouseenter", ".logged-out-wrapper", function () {
        return $("#qtip-headLoginDialog").is($(":visible")) ? !1 : void $(this).tip()
    }), $("body").on("click", function (a) {
        var b = $(a.target);
        return b.parents("div").is($(".logged-out-wrapper")) ? !1 : void $("#registerDialog,#loginDialog").hide()
    }), $("body").on("click", ".btn-login-submit", function () {
        var a = $(this).parents("form");
        return $.trim(a.find('[name="name"]').val()) && $.trim(a.find('[name="password"]').val()) ? void a.submit() : ($(".btn-login-submit").hide(), $(".btn-login-msg").text("邮箱或密码不能为空").show(), !1)
    }), $("body").on("focus", ".input-passport", function () {
        $(".btn-login-submit").show(), $(".btn-login-msg").text("").hide()
    }), $("body").on("submit", "#register_form", function () {
        var a = $(this), b = a.find('[name="name"]').val(), c = a.find('[name="email"]').val(),
            d = a.find('[name="password"]').val();
        if (!$.trim(b) || !$.trim(d)) return $(".btn-register-submit").hide(), $(".btn-register-msg").text("请完善未填项目").show(), !1;
        var e = b + "|" + d, f = GibberishAES.enc(e, "fuckyouspam"), g = a.find('[name="_h"]').val(),
            h = a.find('[name="code"]'), i = a.find('[name="rule"]'), j = a.find('[name="code"]').val();
        if (h.prop("required") && "" == j) return $(".btn-register-submit").hide(), $(".btn-register-msg").text("请完善未填项目").show(), !1;
        if (!i.prop("checked")) return $(".btn-register-submit").hide(), $(".btn-register-msg").text("注册需同意协议").show(), !1;
        var k = {name: b, password: d, email: c, _h: g, code: j, rule: i.val(), _t: f};
        return a.data("disabled") ? !1 : (a.data("disabled", !0), $.luoo.post("/register", k, register_cback, a), !1)
    }), $("body").on("click", ".btn-register-submit", function () {
        $("#register_form").trigger("submit")
    }), $("body").on("submit", "#oauth_register_form", function () {
        var a = $(this), b = a.find('[name="name"]').val(), c = a.find('[name="email"]').val(),
            d = a.find('[name="password"]').val(), e = a.find('[name="ref"]').val();
        if (!$.trim(b) || !$.trim(c) || !$.trim(d)) return $("#btnSubmit").hide(), $("#btnMsg").text("请完善未填项目").show(), !1;
        var f = c + "|" + d, g = GibberishAES.enc(f, "fuckyouspam"), h = a.find('[name="rule"]');
        if (!h.prop("checked")) return $("#btnSubmit").hide(), $("#btnMsg").text("注册需同意协议").show(), !1;
        var i = {
            name: b,
            password: d,
            email: c,
            rule: "on",
            _t: g,
            avatar: a.find('[name="avatar"]').val(),
            platform: a.find('[name="platform"]').val(),
            tuid: a.find('[name="tuid"]').val(),
            tunionid: a.find('[name="tunionid"]').val(),
            token: a.find('[name="token"]').val(),
            url: a.find('[name="url"]').val()
        };
        return a.data("disabled") ? !1 : ($("#btnSubmit").hide(), $("#btnMsg").text("正在处理...").show(), a.data("disabled", !0), $.post("/register/oauth", i, function (b) {
            a.data("disabled", !1), b.status ? ($("#btnSubmit").hide(), $("#btnMsg").text(b.msg).show(), "" != e ? top.location.href = e : top.location.href = "/") : ($("#btnSubmit").hide(), $("#btnMsg").text(b.msg).show())
        }, "json"), !1)
    }), $("body").on("click", ".verify", function () {
        var a = $(this).attr("data-src"), b = (new Date).getTime();
        $(this).attr("src", a + "?" + b)
    }), $("body").on("focus", ".input-passport", function () {
        $(".btn-register-submit").show(), $(".btn-register-msg").text("").hide()
    }), $("body").on("mouseenter", "#lnAccountMore", function () {
        $(this).tip()
    }), $("body").on("click", ".btn-dialog-register, .btn-dialog-login", function () {
        if (0 == $.luoo.tips.length) return !1;
        var a = $(this).parents(".qtip").data("qtip-id");
        if (void 0 === a || null === a) return !1;
        var b;
        if ($.each($.luoo.tips, function (c, d) {
            d.id == a && (b = d.get("position.target"))
        }), !b) return !1;
        var c = $(this).data();
        c.target = b;
        var d = $(this).tip(c);
        d.set("events.hide", function (a, b) {
            $.luoo.destroy_tip(b.id)
        }), $.luoo.tips.push(d)
    }), $("body").on("click", ".btn-action-share", function (a) {
        a.preventDefault(),
            a.stopPropagation();
        var b = {};
        b.html = create_share_btns($(this).data()), $(this).tip(b)
    }), $("body").on("click", ".btn-action-linkout", function (a) {
        a.preventDefault();
        var b = $(this).data("href");
        return b ? void window.open(b) : !1
    }), $("body").on("click", "#feedback", function () {
        var a = $("#feedbackWrapper").html();
        $.luoo.msg("反馈建议", a), $(".captcha").attr("src", $(".captcha").data("src"))
    }), $("body").on("click", "#btnFeedbackSubmit", function () {
        $("#frmFeedback").submit()
    }), $("body").on("click", "#btnFeedbackCancle", function () {
        $.luoo.close_msg()
    }), $("body").on("keyup", function (a) {
        if (13 == a.keyCode) {
            if ($(".btn-register-submit").is($(":visible"))) return void $(".btn-register-submit").click();
            if ($(".btn-login-submit").is($(":visible"))) return void $(".btn-login-submit").click()
        }
    }), $(".search-form").submit(function () {
        return "" == $("input[name=q]").val() ? !1 : void 0
    }), $("body").on("click", ".btn-add-lyric", function () {
        $.luoo.get(url("single/add_lyric"), {sid: $(this).data("sid")}, function (a, b) {
            if (-1 == a.status) {
                var c = b.tip({remote: $.luoo.root + "/login/dialog", width: 235});
                c.set("events.hide", function (a, b) {
                    b.destroy(), $.luoo.destroy_tip(b.id)
                }), $.luoo.tip.push(c)
            } else a && ($.luoo.close_msg(), $.luoo.msg("添加歌词", a.data))
        }, $(this))
    }), $("body").on("focus", "#txtLyric", function () {
        var a = $(this).parents(".form-ajax");
        a.find(".btn-positive").show(), a.find(".btn-not-ready").text("").hide()
    }), $("body").on("click", "#cancleAddLyric", function () {
        $.luoo.close_msg()
    }), $("body").on("click", ".btn-single-report", function () {
        var a = $(this).data("sid"), b = $(this).data("sname");
        if (!a) return !1;
        $.luoo.close_msg();
        var c = '<div class="lyric-form-wrapper" id="singleReportWrapper"> <form action="' + url("single/report") + '" method="post" class="form-ajax" callback="single_report_cback"> <p class="input-group">歌曲：<span id="singleReportSname"></span></p> <p class="input-group">报错原因：</p> <p class="input-group"> <label><input type="radio" name="msg" value="歌词错误"> 歌词错误</label> </p> <p class="input-group"> <label><input type="radio" name="msg" value="歌曲信息错误"> 歌曲信息错误</label> </p> <p class="input-group"> <label><input type="radio" name="msg" value="歌曲无法试听"> 歌曲无法试听</label> </p> <p class="input-group"> <input type="radio" name="msg" id="singleReportMsgOther" value="1"> 其他： <input type="text" name="text" id="txtSingleReportMsg" style="width: 300px;" maxlength="200"> </p> <br> <p class="input-group"> <button class="btn btn-not-ready">提交</button> <button type="submit" class="btn btn-positive">提交</button> <button type="button" class="btn btn-negative" id="cancleAddLyric">取消</button> </p> <input type="hidden" name="sid" id="singleReportSid"><input type="hidden" name="sname" id="singleReportName"> </form> </div>';
        $.luoo.msg("歌曲报错", c), $("#singleReportSid").val(a), $("#singleReportName").val(b), $("#singleReportSname").text(b)
    }), $("body").on("click", "#txtSingleReportMsg", function () {
        $("#singleReportMsgOther").prop("checked", !0)
    }), $("body").on("click", "#singleReportWrapper", function () {
        $(this).find(".btn-positive").show(), $(this).find(".btn-not-ready").hide()
    }), $("body").on("click", ".btn-send-mail", function () {
        var a = $(this);
        return a.data("user") && a.data("uid") ? void $.luoo.get(url("mail/send"), {}, function (a, b) {
            if (-1 == a.status) {
                var c = b.tip({remote: $.luoo.root + "/login/dialog", width: 235});
                c.set("events.hide", function (a, b) {
                    b.destroy(), $.luoo.destroy_tip(b.id)
                }), $.luoo.tip.push(c)
            } else {
                $("#mailUserName").text(b.data("user")), $("#mailUserId").val(b.data("uid"));
                var d = $("#mailWrapper").html();
                $.luoo.msg("发送私信", d), $(".captcha").attr("src", $(".captcha").data("src"))
            }
        }, a) : ($.luoo.msg("提示", "未知收件人"), !1)
    }), $("body").on("click", "#btnSendMail", function () {
        $("#frmMail").submit()
    }), $("body").on("click", "#btnCancleMail", function () {
        $.luoo.close_msg()
    }),  $("body").on("click", ".btn-show-qr", function (a) {
        a.preventDefault(), $.luoo.qrcode.clear(), $.luoo.qrcode.makeCode($(this).data("href")), $.luoo.msg("分享到朋友圈", $("#qrcodeWrapper").html(), void 0, "#000", void 0, 250), $.luoo.destroy_tip()
    })
});

