function get_comment_callback(a, b) {
    "undefined" != typeof a.data && b.html(a.data), a.count && parseInt(a.count) > 0 && $("#commentCount").text("(" + a.count + ")"), a.quote_comment && setTimeout(function () {
        $("#commentEditor").focus(), $("#commentList .quote-item").animate({backgroundColor: "#fbdbdd"}, 500, function () {
            $("#commentList .quote-item").animate({backgroundColor: "#FFF"}, 500, function () {
                $("#commentList .quote-item").animate({backgroundColor: "#fbdbdd"}, 500, function () {
                    $("#commentList .quote-item").animate({backgroundColor: "#FFF"}, 500)
                })
            })
        })
    }, 300), a.page > 1 && $("html, body").animate({scrollTop: $(".comment-wrapper").position().top}, 300), $(".reply-content").each(function (a, b) {
        var c = $(b).height();
        c > 48 && $(b).data("height", c).addClass("long-reply").after('<p class="toggle-reply"><a class="expend-reply" href="javascript:;">+展开</a></p>')
    }), emojify.setConfig({
        img_dir: $.luoo.url_static + "/emoji",
        ignored_tags: {SCRIPT: 1, TEXTAREA: 1, A: 1, PRE: 1, CODE: 1}
    }), emojify.run(document.getElementById("commentList")), $(document.body).trigger("sticky_kit:recalc")
}

function vote_callback(a, b) {
    if (b.data("disabled", !1), -1 === a.status) {
        b.data({remote: url("login/dialog"), width: 235});
        var c = b.tip();
        c.set("events.hide", function (a, b) {
            b.destroy(), $.luoo.destroy_tip(b.id)
        }), $.luoo.tips.push(c)
    } else if (1 === a.status) {
        var d = b.siblings(".vote-count"), e = d.data("count") - 0 + 1;
        d.data("count", e).html(e + "赞").show(), b.find("span").html("赞了"), b.find("i").attr("class", "icon-vote-actived")
    } else if (2 === a.status) {
        b.find("span").html("赞"), b.find("i").attr("class", "icon-vote");
        var d = b.siblings(".vote-count"), f = d.data("count") - 0 - 1;
        0 > f && (f = 0), f > 0 ? d.data("count", f).html(f + "赞") : d.data("count", f).html(f + "赞").hide()
    } else a.msg && ($.luoo.close_msg(), $.luoo.msg("", '<p style="text-align: center; padding: 15px 0;">' + a.msg + "</p>", !1, "#000000"))
}

function comment_callback(a, b) {
    b.data("disabled", !1), -1 === a.status ? ($("#commentSubmit").tip(), $("#commentSubmit").removeClass("btn-not-ready").text("发布")) : 1 === a.status ? ($("#commentEditor").val(""), $("#commentSubmit").addClass("btn-not-ready").text("发布成功√"), setTimeout(function () {
        $("#commentSubmit").text("发布")
    }, 1500), $("#commentList").getComments()) : a.msg && ($("#commentSubmit").removeClass("btn-not-ready").text("发布"), $.luoo.close_msg(), $.luoo.msg("", '<p style="text-align: center; padding: 15px 0;">' + a.msg + "</p>", !1, "#000000"))
}

function delcom_callback(a, b) {
    if (1 === a.status) {
        var c = b.parents(".items").eq(0);
        b.parents(".item").eq(0).fadeOut(300, function () {
            b.parents(".item").eq(0).remove();
            var a = c.children(".item").length;
            0 >= a && c.parent(".sub-comment").hide()
        })
    } else a.msg && ($.luoo.close_msg(), $.luoo.msg("", '<p style="text-align: center; padding: 15px 0;">' + a.msg + "</p>", !1, "#000000"))
}

function report_callback(a, b) {
    if (b.data("disabled", !1), -1 == a.status) {
        b.data({remote: url("login/dialog"), width: 235});
        var c = b.tip();
        c.set("events.hide", function (a, b) {
            b.destroy(), $.luoo.destroy_tip(b.id)
        }), $.luoo.tips.push(c)
    } else a.status && (b.data("res", null).data("app", null), b.find(".report-status").text("已举报"))
}

!function (a, b) {
    function c(a, b, c) {
        var d = l[b.type] || {};
        return null == a ? c || !b.def ? null : b.def : (a = d.floor ? ~~a : parseFloat(a), isNaN(a) ? b.def : d.mod ? (a + d.mod) % d.mod : 0 > a ? 0 : d.max < a ? d.max : a)
    }

    function d(b) {
        var c = j(), d = c._rgba = [];
        return b = b.toLowerCase(), o(i, function (a, e) {
            var f, g = e.re.exec(b), h = g && e.parse(g), i = e.space || "rgba";
            return h ? (f = c[i](h), c[k[i].cache] = f[k[i].cache], d = c._rgba = f._rgba, !1) : void 0
        }), d.length ? ("0,0,0,0" === d.join() && a.extend(d, f.transparent), c) : f[b]
    }

    function e(a, b, c) {
        return c = (c + 1) % 1, 1 > 6 * c ? a + (b - a) * c * 6 : 1 > 2 * c ? b : 2 > 3 * c ? a + (b - a) * (2 / 3 - c) * 6 : a
    }

    var f,
        g = "backgroundColor borderBottomColor borderLeftColor borderRightColor borderTopColor color columnRuleColor outlineColor textDecorationColor textEmphasisColor",
        h = /^([\-+])=\s*(\d+\.?\d*)/, i = [{
            re: /rgba?\(\s*(\d{1,3})\s*,\s*(\d{1,3})\s*,\s*(\d{1,3})\s*(?:,\s*(\d?(?:\.\d+)?)\s*)?\)/,
            parse: function (a) {
                return [a[1], a[2], a[3], a[4]]
            }
        }, {
            re: /rgba?\(\s*(\d+(?:\.\d+)?)\%\s*,\s*(\d+(?:\.\d+)?)\%\s*,\s*(\d+(?:\.\d+)?)\%\s*(?:,\s*(\d?(?:\.\d+)?)\s*)?\)/,
            parse: function (a) {
                return [2.55 * a[1], 2.55 * a[2], 2.55 * a[3], a[4]]
            }
        }, {
            re: /#([a-f0-9]{2})([a-f0-9]{2})([a-f0-9]{2})/, parse: function (a) {
                return [parseInt(a[1], 16), parseInt(a[2], 16), parseInt(a[3], 16)]
            }
        }, {
            re: /#([a-f0-9])([a-f0-9])([a-f0-9])/, parse: function (a) {
                return [parseInt(a[1] + a[1], 16), parseInt(a[2] + a[2], 16), parseInt(a[3] + a[3], 16)]
            }
        }, {
            re: /hsla?\(\s*(\d+(?:\.\d+)?)\s*,\s*(\d+(?:\.\d+)?)\%\s*,\s*(\d+(?:\.\d+)?)\%\s*(?:,\s*(\d?(?:\.\d+)?)\s*)?\)/,
            space: "hsla",
            parse: function (a) {
                return [a[1], a[2] / 100, a[3] / 100, a[4]]
            }
        }], j = a.Color = function (b, c, d, e) {
            return new a.Color.fn.parse(b, c, d, e)
        }, k = {
            rgba: {props: {red: {idx: 0, type: "byte"}, green: {idx: 1, type: "byte"}, blue: {idx: 2, type: "byte"}}},
            hsla: {
                props: {
                    hue: {idx: 0, type: "degrees"},
                    saturation: {idx: 1, type: "percent"},
                    lightness: {idx: 2, type: "percent"}
                }
            }
        }, l = {"byte": {floor: !0, max: 255}, percent: {max: 1}, degrees: {mod: 360, floor: !0}}, m = j.support = {},
        n = a("<p>")[0], o = a.each;
    n.style.cssText = "background-color:rgba(1,1,1,.5)", m.rgba = n.style.backgroundColor.indexOf("rgba") > -1, o(k, function (a, b) {
        b.cache = "_" + a, b.props.alpha = {idx: 3, type: "percent", def: 1}
    }), j.fn = a.extend(j.prototype, {
        parse: function (e, g, h, i) {
            if (e === b) return this._rgba = [null, null, null, null], this;
            (e.jquery || e.nodeType) && (e = a(e).css(g), g = b);
            var l = this, m = a.type(e), n = this._rgba = [];
            return g !== b && (e = [e, g, h, i], m = "array"), "string" === m ? this.parse(d(e) || f._default) : "array" === m ? (o(k.rgba.props, function (a, b) {
                n[b.idx] = c(e[b.idx], b)
            }), this) : "object" === m ? (e instanceof j ? o(k, function (a, b) {
                e[b.cache] && (l[b.cache] = e[b.cache].slice())
            }) : o(k, function (b, d) {
                var f = d.cache;
                o(d.props, function (a, b) {
                    if (!l[f] && d.to) {
                        if ("alpha" === a || null == e[a]) return;
                        l[f] = d.to(l._rgba)
                    }
                    l[f][b.idx] = c(e[a], b, !0)
                }), l[f] && a.inArray(null, l[f].slice(0, 3)) < 0 && (l[f][3] = 1, d.from && (l._rgba = d.from(l[f])))
            }), this) : void 0
        }, is: function (a) {
            var b = j(a), c = !0, d = this;
            return o(k, function (a, e) {
                var f, g = b[e.cache];
                return g && (f = d[e.cache] || e.to && e.to(d._rgba) || [], o(e.props, function (a, b) {
                    return null != g[b.idx] ? c = g[b.idx] === f[b.idx] : void 0
                })), c
            }), c
        }, _space: function () {
            var a = [], b = this;
            return o(k, function (c, d) {
                b[d.cache] && a.push(c)
            }), a.pop()
        }, transition: function (a, b) {
            var d = j(a), e = d._space(), f = k[e], g = 0 === this.alpha() ? j("transparent") : this,
                h = g[f.cache] || f.to(g._rgba), i = h.slice();
            return d = d[f.cache], o(f.props, function (a, e) {
                var f = e.idx, g = h[f], j = d[f], k = l[e.type] || {};
                null !== j && (null === g ? i[f] = j : (k.mod && (j - g > k.mod / 2 ? g += k.mod : g - j > k.mod / 2 && (g -= k.mod)), i[f] = c((j - g) * b + g, e)))
            }), this[e](i)
        }, blend: function (b) {
            if (1 === this._rgba[3]) return this;
            var c = this._rgba.slice(), d = c.pop(), e = j(b)._rgba;
            return j(a.map(c, function (a, b) {
                return (1 - d) * e[b] + d * a
            }))
        }, toRgbaString: function () {
            var b = "rgba(", c = a.map(this._rgba, function (a, b) {
                return null == a ? b > 2 ? 1 : 0 : a
            });
            return 1 === c[3] && (c.pop(), b = "rgb("), b + c.join() + ")"
        }, toHslaString: function () {
            var b = "hsla(", c = a.map(this.hsla(), function (a, b) {
                return null == a && (a = b > 2 ? 1 : 0), b && 3 > b && (a = Math.round(100 * a) + "%"), a
            });
            return 1 === c[3] && (c.pop(), b = "hsl("), b + c.join() + ")"
        }, toHexString: function (b) {
            var c = this._rgba.slice(), d = c.pop();
            return b && c.push(~~(255 * d)), "#" + a.map(c, function (a) {
                return a = (a || 0).toString(16), 1 === a.length ? "0" + a : a
            }).join("")
        }, toString: function () {
            return 0 === this._rgba[3] ? "transparent" : this.toRgbaString()
        }
    }), j.fn.parse.prototype = j.fn, k.hsla.to = function (a) {
        if (null == a[0] || null == a[1] || null == a[2]) return [null, null, null, a[3]];
        var b, c, d = a[0] / 255, e = a[1] / 255, f = a[2] / 255, g = a[3], h = Math.max(d, e, f),
            i = Math.min(d, e, f), j = h - i, k = h + i, l = .5 * k;
        return b = i === h ? 0 : d === h ? 60 * (e - f) / j + 360 : e === h ? 60 * (f - d) / j + 120 : 60 * (d - e) / j + 240, c = 0 === j ? 0 : .5 >= l ? j / k : j / (2 - k), [Math.round(b) % 360, c, l, null == g ? 1 : g]
    }, k.hsla.from = function (a) {
        if (null == a[0] || null == a[1] || null == a[2]) return [null, null, null, a[3]];
        var b = a[0] / 360, c = a[1], d = a[2], f = a[3], g = .5 >= d ? d * (1 + c) : d + c - d * c, h = 2 * d - g;
        return [Math.round(255 * e(h, g, b + 1 / 3)), Math.round(255 * e(h, g, b)), Math.round(255 * e(h, g, b - 1 / 3)), f]
    }, o(k, function (d, e) {
        var f = e.props, g = e.cache, i = e.to, k = e.from;
        j.fn[d] = function (d) {
            if (i && !this[g] && (this[g] = i(this._rgba)), d === b) return this[g].slice();
            var e, h = a.type(d), l = "array" === h || "object" === h ? d : arguments, m = this[g].slice();
            return o(f, function (a, b) {
                var d = l["object" === h ? a : b.idx];
                null == d && (d = m[b.idx]), m[b.idx] = c(d, b)
            }), k ? (e = j(k(m)), e[g] = m, e) : j(m)
        }, o(f, function (b, c) {
            j.fn[b] || (j.fn[b] = function (e) {
                var f, g = a.type(e), i = "alpha" === b ? this._hsla ? "hsla" : "rgba" : d, j = this[i](), k = j[c.idx];
                return "undefined" === g ? k : ("function" === g && (e = e.call(this, k), g = a.type(e)), null == e && c.empty ? this : ("string" === g && (f = h.exec(e), f && (e = k + parseFloat(f[2]) * ("+" === f[1] ? 1 : -1))), j[c.idx] = e, this[i](j)))
            })
        })
    }), j.hook = function (b) {
        var c = b.split(" ");
        o(c, function (b, c) {
            a.cssHooks[c] = {
                set: function (b, e) {
                    var f, g, h = "";
                    if ("transparent" !== e && ("string" !== a.type(e) || (f = d(e)))) {
                        if (e = j(f || e), !m.rgba && 1 !== e._rgba[3]) {
                            for (g = "backgroundColor" === c ? b.parentNode : b; ("" === h || "transparent" === h) && g && g.style;) try {
                                h = a.css(g, "backgroundColor"), g = g.parentNode
                            } catch (i) {
                            }
                            e = e.blend(h && "transparent" !== h ? h : "_default")
                        }
                        e = e.toRgbaString()
                    }
                    try {
                        b.style[c] = e
                    } catch (i) {
                    }
                }
            }, a.fx.step[c] = function (b) {
                b.colorInit || (b.start = j(b.elem, c), b.end = j(b.end), b.colorInit = !0), a.cssHooks[c].set(b.elem, b.start.transition(b.end, b.pos))
            }
        })
    }, j.hook(g), a.cssHooks.borderColor = {
        expand: function (a) {
            var b = {};
            return o(["Top", "Right", "Bottom", "Left"], function (c, d) {
                b["border" + d + "Color"] = a
            }), b
        }
    }, f = a.Color.names = {
        aqua: "#00ffff",
        black: "#000000",
        blue: "#0000ff",
        fuchsia: "#ff00ff",
        gray: "#808080",
        green: "#008000",
        lime: "#00ff00",
        maroon: "#800000",
        navy: "#000080",
        olive: "#808000",
        purple: "#800080",
        red: "#ff0000",
        silver: "#c0c0c0",
        teal: "#008080",
        white: "#ffffff",
        yellow: "#ffff00",
        transparent: [null, null, null, 0],
        _default: "#ffffff"
    }
}(jQuery);
var emojies = ["+1", "-1", "100", "1234", "8ball", "a", "ab", "abc", "abcd", "accept", "aerial_tramway", "airplane", "alarm_clock", "alien", "ambulance", "anchor", "angel", "anger", "angry", "anguished", "ant", "apple", "aquarius", "aries", "arrow_backward", "arrow_double_down", "arrow_double_up", "arrow_down", "arrow_down_small", "arrow_forward", "arrow_heading_down", "arrow_heading_up", "arrow_left", "arrow_lower_left", "arrow_lower_right", "arrow_right", "arrow_right_hook", "arrow_up", "arrow_up_down", "arrow_up_small", "arrow_upper_left", "arrow_upper_right", "arrows_clockwise", "arrows_counterclockwise", "art", "articulated_lorry", "astonished", "athletic_shoe", "atm", "b", "baby", "baby_bottle", "baby_chick", "baby_symbol", "back", "baggage_claim", "balloon", "ballot_box_with_check", "bamboo", "banana", "bangbang", "bank", "bar_chart", "barber", "baseball", "basketball", "bath", "bathtub", "battery", "bear", "bee", "beer", "beers", "beetle", "beginner", "bell", "bento", "bicyclist", "bike", "bikini", "bird", "birthday", "black_circle", "black_joker", "black_large_square", "black_medium_small_square", "black_medium_square", "black_nib", "black_small_square", "black_square_button", "blossom", "blowfish", "blue_book", "blue_car", "blue_heart", "blush", "boar", "boat", "bomb", "book", "bookmark", "bookmark_tabs", "books", "boom", "boot", "bouquet", "bow", "bowling", "bowtie", "boy", "bread", "bride_with_veil", "bridge_at_night", "briefcase", "broken_heart", "bug", "bulb", "bullettrain_front", "bullettrain_side", "bus", "busstop", "bust_in_silhouette", "busts_in_silhouette", "cactus", "cake", "calendar", "calling", "camel", "camera", "cancer", "candy", "capital_abcd", "capricorn", "car", "card_index", "carousel_horse", "cat", "cat2", "cd", "chart", "chart_with_downwards_trend", "chart_with_upwards_trend", "checkered_flag", "cherries", "cherry_blossom", "chestnut", "chicken", "children_crossing", "chocolate_bar", "christmas_tree", "church", "cinema", "circus_tent", "city_sunrise", "city_sunset", "cl", "clap", "clapper", "clipboard", "clock1", "clock10", "clock1030", "clock11", "clock1130", "clock12", "clock1230", "clock130", "clock2", "clock230", "clock3", "clock330", "clock4", "clock430", "clock5", "clock530", "clock6", "clock630", "clock7", "clock730", "clock8", "clock830", "clock9", "clock930", "closed_book", "closed_lock_with_key", "closed_umbrella", "cloud", "clubs", "cn", "cocktail", "coffee", "cold_sweat", "collision", "computer", "confetti_ball", "confounded", "confused", "congratulations", "construction", "construction_worker", "convenience_store", "cookie", "cool", "cop", "copyright", "corn", "couple", "couple_with_heart", "couplekiss", "cow", "cow2", "credit_card", "crescent_moon", "crocodile", "crossed_flags", "crown", "cry", "crying_cat_face", "crystal_ball", "cupid", "curly_loop", "currency_exchange", "curry", "custard", "customs", "cyclone", "dancer", "dancers", "dango", "dart", "dash", "date", "de", "deciduous_tree", "department_store", "diamond_shape_with_a_dot_inside", "diamonds", "disappointed", "disappointed_relieved", "dizzy", "dizzy_face", "do_not_litter", "dog", "dog2", "dollar", "dolls", "dolphin", "door", "doughnut", "dragon", "dragon_face", "dress", "dromedary_camel", "droplet", "dvd", "e-mail", "ear", "ear_of_rice", "earth_africa", "earth_americas", "earth_asia", "egg", "eggplant", "eight", "eight_pointed_black_star", "eight_spoked_asterisk", "electric_plug", "elephant", "email", "end", "envelope", "envelope_with_arrow", "es", "euro", "european_castle", "european_post_office", "evergreen_tree", "exclamation", "expressionless", "eyeglasses", "eyes", "facepunch", "factory", "fallen_leaf", "family", "fast_forward", "fax", "fearful", "feelsgood", "feet", "ferris_wheel", "file_folder", "finnadie", "fire", "fire_engine", "fireworks", "first_quarter_moon", "first_quarter_moon_with_face", "fish", "fish_cake", "fishing_pole_and_fish", "fist", "five", "flags", "flashlight", "floppy_disk", "flower_playing_cards", "flushed", "foggy", "football", "footprints", "fork_and_knife", "fountain", "four", "four_leaf_clover", "fr", "free", "fried_shrimp", "fries", "frog", "frowning", "fu", "fuelpump", "full_moon", "full_moon_with_face", "game_die", "gb", "gem", "gemini", "ghost", "gift", "gift_heart", "girl", "globe_with_meridians", "goat", "goberserk", "godmode", "golf", "grapes", "green_apple", "green_book", "green_heart", "grey_exclamation", "grey_question", "grimacing", "grin", "grinning", "guardsman", "guitar", "gun", "haircut", "hamburger", "hammer", "hamster", "hand", "handbag", "hankey", "hash", "hatched_chick", "hatching_chick", "headphones", "hear_no_evil", "heart", "heart_decoration", "heart_eyes", "heart_eyes_cat", "heartbeat", "heartpulse", "hearts", "heavy_check_mark", "heavy_division_sign", "heavy_dollar_sign", "heavy_exclamation_mark", "heavy_minus_sign", "heavy_multiplication_x", "heavy_plus_sign", "helicopter", "herb", "hibiscus", "high_brightness", "high_heel", "hocho", "honey_pot", "honeybee", "horse", "horse_racing", "hospital", "hotel", "hotsprings", "hourglass", "hourglass_flowing_sand", "house", "house_with_garden", "hurtrealbad", "hushed", "ice_cream", "icecream", "id", "ideograph_advantage", "imp", "inbox_tray", "incoming_envelope", "information_desk_person", "information_source", "innocent", "interrobang", "iphone", "it", "izakaya_lantern", "jack_o_lantern", "japan", "japanese_castle", "japanese_goblin", "japanese_ogre", "jeans", "joy", "joy_cat", "jp", "key", "keycap_ten", "kimono", "kiss", "kissing", "kissing_cat", "kissing_closed_eyes", "kissing_heart", "kissing_smiling_eyes", "koala", "koko", "kr", "lantern", "large_blue_circle", "large_blue_diamond", "large_orange_diamond", "last_quarter_moon", "last_quarter_moon_with_face", "laughing", "leaves", "ledger", "left_luggage", "left_right_arrow", "leftwards_arrow_with_hook", "lemon", "leo", "leopard", "libra", "light_rail", "link", "lips", "lipstick", "lock", "lock_with_ink_pen", "lollipop", "loop", "loudspeaker", "love_hotel", "love_letter", "low_brightness", "m", "mag", "mag_right", "mahjong", "mailbox", "mailbox_closed", "mailbox_with_mail", "mailbox_with_no_mail", "man", "man_with_gua_pi_mao", "man_with_turban", "mans_shoe", "maple_leaf", "mask", "massage", "meat_on_bone", "mega", "melon", "memo", "mens", "metal", "metro", "microphone", "microscope", "milky_way", "minibus", "minidisc", "mobile_phone_off", "money_with_wings", "moneybag", "monkey", "monkey_face", "monorail", "moon", "mortar_board", "mount_fuji", "mountain_bicyclist", "mountain_cableway", "mountain_railway", "mouse", "mouse2", "movie_camera", "moyai", "muscle", "mushroom", "musical_keyboard", "musical_note", "musical_score", "mute", "nail_care", "name_badge", "neckbeard", "necktie", "negative_squared_cross_mark", "neutral_face", "new", "new_moon", "new_moon_with_face", "newspaper", "ng", "nine", "no_bell", "no_bicycles", "no_entry", "no_entry_sign", "no_good", "no_mobile_phones", "no_mouth", "no_pedestrians", "no_smoking", "non-potable_water", "nose", "notebook", "notebook_with_decorative_cover", "notes", "nut_and_bolt", "o", "o2", "ocean", "octocat", "octopus", "oden", "office", "ok", "ok_hand", "ok_woman", "older_man", "older_woman", "on", "oncoming_automobile", "oncoming_bus", "oncoming_police_car", "oncoming_taxi", "one", "open_book", "open_file_folder", "open_hands", "open_mouth", "ophiuchus", "orange_book", "outbox_tray", "ox", "package", "page_facing_up", "page_with_curl", "pager", "palm_tree", "panda_face", "paperclip", "parking", "part_alternation_mark", "partly_sunny", "passport_control", "paw_prints", "peach", "pear", "pencil", "pencil2", "penguin", "pensive", "performing_arts", "persevere", "person_frowning", "person_with_blond_hair", "person_with_pouting_face", "phone", "pig", "pig2", "pig_nose", "pill", "pineapple", "pisces", "pizza", "point_down", "point_left", "point_right", "point_up", "point_up_2", "police_car", "poodle", "poop", "post_office", "postal_horn", "postbox", "potable_water", "pouch", "poultry_leg", "pound", "pouting_cat", "pray", "princess", "punch", "purple_heart", "purse", "pushpin", "put_litter_in_its_place", "question", "rabbit", "rabbit2", "racehorse", "radio", "radio_button", "rage", "rage1", "rage2", "rage3", "rage4", "railway_car", "rainbow", "raised_hand", "raised_hands", "raising_hand", "ram", "ramen", "rat", "recycle", "red_car", "red_circle", "registered", "relaxed", "relieved", "repeat", "repeat_one", "restroom", "revolving_hearts", "rewind", "ribbon", "rice", "rice_ball", "rice_cracker", "rice_scene", "ring", "rocket", "roller_coaster", "rooster", "rose", "rotating_light", "round_pushpin", "rowboat", "ru", "rugby_football", "runner", "running", "running_shirt_with_sash", "sa", "sagittarius", "sailboat", "sake", "sandal", "santa", "satellite", "satisfied", "saxophone", "school", "school_satchel", "scissors", "scorpius", "scream", "scream_cat", "scroll", "seat", "secret", "see_no_evil", "seedling", "seven", "shaved_ice", "sheep", "shell", "ship", "shipit", "shirt", "shit", "shoe", "shower", "signal_strength", "six", "six_pointed_star", "ski", "skull", "sleeping", "sleepy", "slot_machine", "small_blue_diamond", "small_orange_diamond", "small_red_triangle", "small_red_triangle_down", "smile", "smile_cat", "smiley", "smiley_cat", "smiling_imp", "smirk", "smirk_cat", "smoking", "snail", "snake", "snowboarder", "snowflake", "snowman", "sob", "soccer", "soon", "sos", "sound", "space_invader", "spades", "spaghetti", "sparkle", "sparkler", "sparkles", "sparkling_heart", "speak_no_evil", "speaker", "speech_balloon", "speedboat", "squirrel", "star", "star2", "stars", "station", "statue_of_liberty", "steam_locomotive", "stew", "straight_ruler", "strawberry", "stuck_out_tongue", "stuck_out_tongue_closed_eyes", "stuck_out_tongue_winking_eye", "sun_with_face", "sunflower", "sunglasses", "sunny", "sunrise", "sunrise_over_mountains", "surfer", "sushi", "suspect", "suspension_railway", "sweat", "sweat_drops", "sweat_smile", "sweet_potato", "swimmer", "symbols", "syringe", "tada", "tanabata_tree", "tangerine", "taurus", "taxi", "tea", "telephone", "telephone_receiver", "telescope", "tennis", "tent", "thought_balloon", "three", "thumbsdown", "thumbsup", "ticket", "tiger", "tiger2", "tired_face", "tm", "toilet", "tokyo_tower", "tomato", "tongue", "top", "tophat", "tractor", "traffic_light", "train", "train2", "tram", "triangular_flag_on_post", "triangular_ruler", "trident", "triumph", "trolleybus", "trollface", "trophy", "tropical_drink", "tropical_fish", "truck", "trumpet", "tshirt", "tulip", "turtle", "tv", "twisted_rightwards_arrows", "two", "two_hearts", "two_men_holding_hands", "two_women_holding_hands", "u5272", "u5408", "u55b6", "u6307", "u6708", "u6709", "u6e80", "u7121", "u7533", "u7981", "u7a7a", "uk", "umbrella", "unamused", "underage", "unlock", "up", "us", "v", "vertical_traffic_light", "vhs", "vibration_mode", "video_camera", "video_game", "violin", "virgo", "volcano", "vs", "walking", "waning_crescent_moon", "waning_gibbous_moon", "warning", "watch", "water_buffalo", "watermelon", "wave", "wavy_dash", "waxing_crescent_moon", "waxing_gibbous_moon", "wc", "weary", "wedding", "whale", "whale2", "wheelchair", "white_check_mark", "white_circle", "white_flower", "white_large_square", "white_medium_small_square", "white_medium_square", "white_small_square", "white_square_button", "wind_chime", "wine_glass", "wink", "wolf", "woman", "womans_clothes", "womans_hat", "womens", "worried", "wrench", "x", "yellow_heart", "yen", "yum", "zap", "zero", "zzz"];
!function (a) {
    "use strict";
    var b = function (a) {
        var b, d, e;
        return b = function () {
            d = !1
        }, function () {
            var b = c(arguments);
            if (d) return void(e = b);
            d = !0;
            var f = this;
            b.unshift(function g() {
                if (e) {
                    var b = e;
                    e = void 0, b.unshift(g), a.apply(f, b)
                } else d = !1
            }), a.apply(this, b)
        }
    }, c = function (a) {
        var b;
        return b = Array.prototype.slice.call(a)
    }, d = function () {
        var b;
        return b = a("<div></div>").css(["color"]).color, "undefined" != typeof b ? function (a, b) {
            return a.css(b)
        } : function (b, c) {
            var d;
            return d = {}, a.each(c, function (a, c) {
                d[c] = b.css(c)
            }), d
        }
    }(), e = function (a) {
        return a
    }, f = function (a) {
        var b = {};
        return function (c, d) {
            b[c] ? d(b[c]) : a.call(this, c, function (a) {
                b[c] = (b[c] || []).concat(a), d.apply(null, arguments)
            })
        }
    }, g = function (a, b) {
        var c, d;
        if (a.indexOf) return -1 != a.indexOf(b);
        for (c = 0, d = a.length; d > c; c++) if (a[c] === b) return !0;
        return !1
    }, h = function () {
        function c(b, c) {
            var d;
            this.el = b.get(0), d = this.el === document.activeElement, this.$el = b, this.id = "textComplete" + h++, this.strategies = [], this.option = c, d ? (this.initialize(), this.$el.focus()) : this.$el.one("focus.textComplete", a.proxy(this.initialize, this))
        }

        var e, f, g, h;
        return e = {list: '<ul class="dropdown-menu"></ul>'}, f = {
            list: {
                position: "absolute",
                left: 0,
                zIndex: "100",
                display: "none"
            }
        }, g = a(e.list).css(f.list), h = 0, a.extend(c.prototype, {
            initialize: function () {
                var b, c, d, e;
                b = g.clone(), this.listView = new i(b, this), this.$el.on({
                    "keyup.textComplete": a.proxy(this.onKeyup, this),
                    "keydown.textComplete": a.proxy(this.listView.onKeydown, this.listView)
                }), d = this.option.appendTo, d ? this.listView.appendTo(d instanceof a ? d : a(d)) : this.listView.appendTo(a("body")), e = this.option.height, e && (b.css("overflow-y", "auto"), b.height(e)), c = {}, c["click." + this.id] = a.proxy(this.onClickDocument, this), c["keyup." + this.id] = a.proxy(this.onKeyupDocument, this), a(document).on(c)
            }, register: function (a) {
                this.strategies = this.strategies.concat(a)
            }, renderList: function (a) {
                this.clearAtNext && (this.listView.clear(), this.clearAtNext = !1), a.length && (this.listView.strategy = this.strategy, this.listView.shown || this.listView.setPosition(this.getCaretPosition()).clear().activate(), a = a.slice(0, this.strategy.maxCount), this.listView.render(a)), !this.listView.data.length && this.listView.shown && this.listView.deactivate()
            }, searchCallbackFactory: function (a) {
                var b = this;
                return function (c, d) {
                    b.renderList(c), d || (a(), b.clearAtNext = !0)
                }
            }, onKeyup: function (a) {
                this.skipSearch(a) || this.trigger(null, !0)
            }, trigger: function (a, b) {
                var c, d;
                if (a || (a = this.getTextFromHeadToCaret()), c = this.extractSearchQuery(a), c.length) {
                    if (d = c[1], b && this.term === d) return;
                    this.term = d, this.search(c)
                } else this.term = null, this.listView.deactivate()
            }, skipSearch: function (a) {
                switch (a.keyCode) {
                    case 40:
                    case 38:
                        return !0
                }
                if (a.ctrlKey) switch (a.keyCode) {
                    case 78:
                    case 80:
                        return !0
                }
            }, onSelect: function (b) {
                var c, d, e, f, g, h, i;
                if (c = this.getTextFromHeadToCaret(), i = c.length, this.el.isContentEditable) {
                    f = window.getSelection(), g = f.getRangeAt(0), h = g.cloneRange(), h.selectNodeContents(g.startContainer);
                    var j = h.toString();
                    d = j.substring(g.startOffset)
                } else d = this.el.value.substring(this.el.selectionEnd);
                if (e = this.strategy.replace(b), a.isArray(e) && (d = e[1] + d, e = e[0]), c = c.replace(this.strategy.match, e), i -= c.length, this.el.isContentEditable) {
                    for (var k = 0; i > k; ++k) document.execCommand("delete", !1);
                    d && document.execCommand("insertHTML", !1, d)
                } else this.$el.val(c + d), this.el.selectionStart = this.el.selectionEnd = c.length;
                this.$el.trigger("change").trigger("textComplete:select", b), this.el.focus()
            }, onClickDocument: function (a) {
                a.originalEvent && !a.originalEvent.keepTextCompleteDropdown && this.listView.deactivate()
            }, onKeyupDocument: function (a) {
                this.listView.shown && 27 === a.keyCode && (this.listView.deactivate(), this.$el.focus())
            }, destroy: function () {
                this.$el.off(".textComplete"), a(document).off("." + this.id), this.listView && this.listView.destroy(), this.$el.removeData("textComplete"), this.$el = null
            }, getCaretPosition: function () {
                var a, b;
                return a = this.getCaretRelativePosition(), b = this.$el.offset(), a.top += b.top, a.left += b.left, a
            }, getCaretRelativePosition: function () {
                var b, c, e, f, g, h, i, j, k, l;
                return this.el.isContentEditable ? (j = window.getSelection().getRangeAt(0).cloneRange(), k = document.createElement("span"), j.insertNode(k), j.selectNodeContents(k), j.deleteContents(), l = a(k), g = l.offset(), g.left -= this.$el.offset().left, g.top += l.height() - this.$el.offset().top) : (b = ["border-width", "font-family", "font-size", "font-style", "font-variant", "font-weight", "height", "letter-spacing", "word-spacing", "line-height", "text-decoration", "text-align", "width", "padding-top", "padding-right", "padding-bottom", "padding-left", "margin-top", "margin-right", "margin-bottom", "margin-left", "border-style", "box-sizing"], i = this.$el[0].scrollHeight > this.$el[0].offsetHeight, c = a.extend({
                    position: "absolute",
                    overflow: i ? "scroll" : "auto",
                    "white-space": "pre-wrap",
                    top: 0,
                    left: -9999,
                    direction: h
                }, d(this.$el, b)), e = a("<div></div>").css(c).text(this.getTextFromHeadToCaret()), f = a("<span></span>").text(".").appendTo(e), this.$el.before(e), g = f.position(), g.top += f.height() - this.$el.scrollTop(), e.remove()), h = this.$el.attr("dir") || this.$el.css("direction"), "rtl" === h && (g.left -= this.listView.$el.width()), g
            }, getTextFromHeadToCaret: function () {
                var a, b, c;
                if (this.el.isContentEditable) {
                    if (window.getSelection) {
                        var c = window.getSelection().getRangeAt(0), d = c.cloneRange();
                        d.selectNodeContents(c.startContainer), a = d.toString().substring(0, c.startOffset)
                    }
                } else b = this.el.selectionEnd, "number" == typeof b ? a = this.el.value.substring(0, b) : document.selection && (c = this.el.createTextRange(), c.moveStart("character", 0), c.moveEnd("textedit"), a = c.text);
                return a
            }, extractSearchQuery: function (a) {
                var b, c, d, e;
                for (b = 0, c = this.strategies.length; c > b; b++) if (d = this.strategies[b], e = a.match(d.match)) return [d, e[d.index]];
                return []
            }, search: b(function (a, b) {
                var c;
                this.strategy = b[0], c = b[1], this.strategy.search(c, this.searchCallbackFactory(a))
            })
        }), c
    }(), i = function () {
        function b(b, c) {
            this.data = [], this.$el = b, this.index = 0, this.completer = c, c.option.listPosition && (this.setPosition = c.option.listPosition), this.$el.on("mousedown.textComplete", "li.textcomplete-item", a.proxy(this.onClick, this)), this.$el.on("mouseover.textComplete", "li.textcomplete-item", a.proxy(this.onMouseover, this))
        }

        return a.extend(b.prototype, {
            shown: !1, render: function (b) {
                var c, d, e, f, h, i;
                for (c = "", this.strategy.header && (i = a.isFunction(this.strategy.header) ? this.strategy.header(b) : this.strategy.header, c += '<li class="textcomplete-header">' + i + "</li>"), d = 0, e = b.length; e > d && (h = b[d], g(this.data, h) || (f = this.data.length, this.data.push(h), c += '<li class="textcomplete-item" data-index="' + f + '"><a>', c += this.strategy.template(h), c += "</a></li>", this.data.length !== this.strategy.maxCount)); d++) ;
                this.strategy.footer && (i = a.isFunction(this.strategy.footer) ? this.strategy.footer(b) : this.strategy.footer, c += '<li class="textcomplete-footer">' + i + "</li>"), this.$el.append(c), this.data.length ? (this.activateIndexedItem(), this.setScroll()) : this.deactivate()
            }, clear: function () {
                return this.data = [], this.$el.html(""), this.index = 0, this
            }, activateIndexedItem: function () {
                this.$el.find(".active").removeClass("active"), this.getActiveItem().addClass("active")
            }, getActiveItem: function () {
                return a(this.$el.children(".textcomplete-item").get(this.index))
            }, activate: function () {
                return this.shown || (this.$el.show(), this.completer.$el.trigger("textComplete:show"), this.shown = !0), this
            }, deactivate: function () {
                return this.shown && (this.$el.hide(), this.completer.$el.trigger("textComplete:hide"), this.shown = !1, this.data = [], this.index = null), this
            }, setPosition: function (a) {
                var b;
                return this.strategy.placement.indexOf("top") > -1 ? (b = parseInt(this.$el.css("font-size")), a = {
                    top: "auto",
                    bottom: this.$el.parent().height() - a.top + b,
                    left: a.left
                }) : a.bottom = "auto", this.strategy.placement.indexOf("absleft") > -1 && (a.left = 0), this.strategy.placement.indexOf("absright") > -1 && (a.right = 0, a.left = "auto"), this.$el.css(a), this
            }, setScroll: function () {
                var a = this.getActiveItem(), b = a.position().top, c = a.outerHeight(), d = this.$el.innerHeight(),
                    e = this.$el.scrollTop();
                0 === this.index || this.index === this.data.length - 1 || 0 > b ? this.$el.scrollTop(b + e) : b + c > d && this.$el.scrollTop(b + c + e - d)
            }, select: function (a) {
                var b = this;
                this.completer.onSelect(this.data[a]), setTimeout(function () {
                    b.deactivate()
                }, 0)
            }, onKeydown: function (b) {
                if (this.shown) {
                    var c = b.ctrlKey || b.altKey || b.metaKey || b.shiftKey;
                    if (38 === b.keyCode || b.ctrlKey && 80 === b.keyCode) b.preventDefault(), 0 === this.index ? this.index = this.data.length - 1 : this.index -= 1, this.activateIndexedItem(), this.setScroll(); else if (40 === b.keyCode || b.ctrlKey && 78 === b.keyCode) b.preventDefault(), this.index === this.data.length - 1 ? this.index = 0 : this.index += 1, this.activateIndexedItem(), this.setScroll(); else if (c || 13 !== b.keyCode && 9 !== b.keyCode) {
                        if (33 === b.keyCode) {
                            b.preventDefault();
                            var d = 0, e = this.getActiveItem().position().top - this.$el.innerHeight();
                            this.$el.children().each(function (b) {
                                return a(this).position().top + a(this).outerHeight() > e ? (d = b, !1) : void 0
                            }), this.index = d, this.activateIndexedItem(), this.setScroll()
                        } else if (34 === b.keyCode) {
                            b.preventDefault();
                            var d = this.data.length - 1,
                                e = this.getActiveItem().position().top + this.$el.innerHeight();
                            this.$el.children().each(function (b) {
                                return a(this).position().top > e ? (d = b, !1) : void 0
                            }), this.index = d, this.activateIndexedItem(), this.setScroll()
                        }
                    } else b.preventDefault(), this.select(parseInt(this.getActiveItem().data("index"), 10))
                }
            }, onClick: function (b) {
                var c = a(b.target);
                b.preventDefault(), b.originalEvent.keepTextCompleteDropdown = !0, c.hasClass("textcomplete-item") || (c = c.parents("li.textcomplete-item")), this.select(parseInt(c.data("index"), 10))
            }, onMouseover: function (b) {
                var c = a(b.target);
                b.preventDefault(), c.hasClass("textcomplete-item") || (c = c.parents("li.textcomplete-item")), this.index = parseInt(c.data("index"), 10), this.activateIndexedItem()
            }, destroy: function () {
                this.deactivate(), this.$el.off("click.textComplete").remove(), this.$el = null
            }, appendTo: function (a) {
                a.css({position: "relative"}).append(this.$el)
            }
        }), b
    }();
    a.fn.textcomplete = function (b, c) {
        var d, g, i, j;
        if (j = "textComplete", c || (c = {}), "destroy" === b) return this.each(function () {
            var b = a(this).data(j);
            b && b.destroy()
        });
        for (d = 0, g = b.length; g > d; d++) i = b[d], i.template || (i.template = e), null == i.index && (i.index = 2), null == i.placement && (i.placement = ""), i.cache && (i.search = f(i.search)), i.maxCount || (i.maxCount = 10);
        return this.each(function () {
            var d, e;
            d = a(this), e = d.data(j), e || (e = new h(d, c), d.data(j, e)), e.register(b)
        })
    }
}(window.jQuery || window.Zepto), !function (a) {
    "use strict";
    var b = function () {
        function b(a) {
            return " " === a || "	" === a || "\r" === a || "\n" === a || "" === a
        }

        function c(a, b, c) {
            var d = i.createElement("img");
            d.setAttribute("title", ":" + c + ":"), d.setAttribute("alt", ":" + c + ":"), d.setAttribute("class", "emoji"), d.setAttribute("src", q.img_dir + "/" + c + ".png"), d.setAttribute("align", "absmiddle"), a.splitText(b.index), a.nextSibling.nodeValue = a.nextSibling.nodeValue.substr(b[0].length, a.nextSibling.nodeValue.length), d.appendChild(a.splitText(b.index)), a.parentNode.insertBefore(d, a.nextSibling)
        }

        function d(a) {
            if (a[1] && a[2]) {
                var b = a[2];
                if (l[b]) return b
            } else for (var c = 3; c < a.length - 1; c++) if (a[c]) return n[c - 2][1]
        }

        function e(a, b) {
            return "<img title=':" + b + ":' alt=':" + b + ":' class='emoji' src='" + q.img_dir + "/" + b + ".png' align='absmiddle' />"
        }

        function f() {
            this.lastEmojiTerminatedAt = -1
        }

        function g(a, b) {
            if (!a) return a;
            b || (b = e);
            var c = new f;
            return a.replace(p, function () {
                var a = Array.prototype.slice.call(arguments, 0, -2), d = arguments[arguments.length - 2],
                    e = arguments[arguments.length - 1], f = c.validate(a, d, e);
                return f ? b(arguments[0], f) : arguments[0]
            })
        }

        function h(a) {
            "undefined" == typeof a && (a = q.only_crawl_id ? i.getElementById(q.only_crawl_id) : i.body);
            for (var b, e = q.ignored_tags, g = i.createTreeWalker(a, NodeFilter.SHOW_TEXT | NodeFilter.SHOW_ELEMENT, function (a) {
                return 1 !== a.nodeType ? NodeFilter.FILTER_ACCEPT : e[a.tagName] || a.classList.contains("no-emojify") ? NodeFilter.FILTER_REJECT : NodeFilter.FILTER_SKIP
            }, !1), h = []; null !== (b = g.nextNode());) h.push(b);
            h.forEach(function (a) {
                for (var b, e = [], g = new f; null !== (b = p.exec(a.data));) g.validate(b, b.index, b.input) && e.push(b);
                for (var h = e.length; h-- > 0;) {
                    var i = d(e[h]);
                    c(a, e[h], i);
                }
            })
        }

        var i = a.window.document,
            j = "+1,-1,100,109,1234,8ball,a,ab,abc,abcd,accept,aerial_tramway,airplane,alarm_clock,alien,ambulance,anchor,angel,anger,angry,anguished,ant,apple,aquarius,aries,arrow_backward,arrow_double_down,arrow_double_up,arrow_down,arrow_down_small,arrow_forward,arrow_heading_down,arrow_heading_up,arrow_left,arrow_lower_left,arrow_lower_right,arrow_right,arrow_right_hook,arrow_up,arrow_up_down,arrow_up_small,arrow_upper_left,arrow_upper_right,arrows_clockwise,arrows_counterclockwise,art,articulated_lorry,astonished,atm,b,baby,baby_bottle,baby_chick,baby_symbol,baggage_claim,balloon,ballot_box_with_check,bamboo,banana,bangbang,bank,bar_chart,barber,baseball,basketball,bath,bathtub,battery,bear,bee,beer,beers,beetle,beginner,bell,bento,bicyclist,bike,bikini,bird,birthday,black_circle,black_joker,black_nib,black_square,black_square_button,blossom,blowfish,blue_book,blue_car,blue_heart,blush,boar,boat,bomb,book,bookmark,bookmark_tabs,books,boom,boot,bouquet,bow,bowling,bowtie,boy,bread,bride_with_veil,bridge_at_night,briefcase,broken_heart,bug,bulb,bullettrain_front,bullettrain_side,bus,busstop,bust_in_silhouette,busts_in_silhouette,cactus,cake,calendar,calling,camel,camera,cancer,candy,capital_abcd,capricorn,car,card_index,carousel_horse,cat,cat2,cd,chart,chart_with_downwards_trend,chart_with_upwards_trend,checkered_flag,cherries,cherry_blossom,chestnut,chicken,children_crossing,chocolate_bar,christmas_tree,church,cinema,circus_tent,city_sunrise,city_sunset,cl,clap,clapper,clipboard,clock1,clock10,clock1030,clock11,clock1130,clock12,clock1230,clock130,clock2,clock230,clock3,clock330,clock4,clock430,clock5,clock530,clock6,clock630,clock7,clock730,clock8,clock830,clock9,clock930,closed_book,closed_lock_with_key,closed_umbrella,cloud,clubs,cn,cocktail,coffee,cold_sweat,collision,computer,confetti_ball,confounded,confused,congratulations,construction,construction_worker,convenience_store,cookie,cool,cop,copyright,corn,couple,couple_with_heart,couplekiss,cow,cow2,credit_card,crocodile,crossed_flags,crown,cry,crying_cat_face,crystal_ball,cupid,curly_loop,currency_exchange,curry,custard,customs,cyclone,dancer,dancers,dango,dart,dash,date,de,deciduous_tree,department_store,diamond_shape_with_a_dot_inside,diamonds,disappointed,disappointed_relieved,dizzy,dizzy_face,do_not_litter,dog,dog2,dollar,dolls,dolphin,donut,door,doughnut,dragon,dragon_face,dress,dromedary_camel,droplet,dvd,e-mail,ear,ear_of_rice,earth_africa,earth_americas,earth_asia,egg,eggplant,eight,eight_pointed_black_star,eight_spoked_asterisk,electric_plug,elephant,email,end,envelope,es,euro,european_castle,european_post_office,evergreen_tree,exclamation,expressionless,eyeglasses,eyes,facepunch,factory,fallen_leaf,family,fast_forward,fax,fearful,feelsgood,feet,ferris_wheel,file_folder,finnadie,fire,fire_engine,fireworks,first_quarter_moon,first_quarter_moon_with_face,fish,fish_cake,fishing_pole_and_fish,fist,five,flags,flashlight,floppy_disk,flower_playing_cards,flushed,foggy,football,fork_and_knife,fountain,four,four_leaf_clover,fr,free,fried_shrimp,fries,frog,frowning,fu,fuelpump,full_moon,full_moon_with_face,game_die,gb,gem,gemini,ghost,gift,gift_heart,girl,globe_with_meridians,goat,goberserk,godmode,golf,grapes,green_apple,green_book,green_heart,grey_exclamation,grey_question,grimacing,grin,grinning,guardsman,guitar,gun,haircut,hamburger,hammer,hamster,hand,handbag,hankey,hash,hatched_chick,hatching_chick,headphones,hear_no_evil,heart,heart_decoration,heart_eyes,heart_eyes_cat,heartbeat,heartpulse,hearts,heavy_check_mark,heavy_division_sign,heavy_dollar_sign,heavy_exclamation_mark,heavy_minus_sign,heavy_multiplication_x,heavy_plus_sign,helicopter,herb,hibiscus,high_brightness,high_heel,hocho,honey_pot,honeybee,horse,horse_racing,hospital,hotel,hotsprings,hourglass,hourglass_flowing_sand,house,house_with_garden,hurtrealbad,hushed,ice_cream,icecream,id,ideograph_advantage,imp,inbox_tray,incoming_envelope,information_desk_person,information_source,innocent,interrobang,iphone,it,izakaya_lantern,jack_o_lantern,japan,japanese_castle,japanese_goblin,japanese_ogre,jeans,joy,joy_cat,jp,key,keycap_ten,kimono,kiss,kissing,kissing_cat,kissing_closed_eyes,kissing_face,kissing_heart,kissing_smiling_eyes,koala,koko,kr,large_blue_circle,large_blue_diamond,large_orange_diamond,last_quarter_moon,last_quarter_moon_with_face,laughing,leaves,ledger,left_luggage,left_right_arrow,leftwards_arrow_with_hook,lemon,leo,leopard,libra,light_rail,link,lips,lipstick,lock,lock_with_ink_pen,lollipop,loop,loudspeaker,love_hotel,love_letter,low_brightness,m,mag,mag_right,mahjong,mailbox,mailbox_closed,mailbox_with_mail,mailbox_with_no_mail,man,man_with_gua_pi_mao,man_with_turban,mans_shoe,maple_leaf,mask,massage,meat_on_bone,mega,melon,memo,mens,metal,metro,microphone,microscope,milky_way,minibus,minidisc,mobile_phone_off,money_with_wings,moneybag,monkey,monkey_face,monorail,moon,mortar_board,mount_fuji,mountain_bicyclist,mountain_cableway,mountain_railway,mouse,mouse2,movie_camera,moyai,muscle,mushroom,musical_keyboard,musical_note,musical_score,mute,nail_care,name_badge,neckbeard,necktie,negative_squared_cross_mark,neutral_face,new,new_moon,new_moon_with_face,newspaper,ng,nine,no_bell,no_bicycles,no_entry,no_entry_sign,no_good,no_mobile_phones,no_mouth,no_pedestrians,no_smoking,non-potable_water,nose,notebook,notebook_with_decorative_cover,notes,nut_and_bolt,o,o2,ocean,octocat,octopus,oden,office,ok,ok_hand,ok_woman,older_man,older_woman,on,oncoming_automobile,oncoming_bus,oncoming_police_car,oncoming_taxi,one,open_file_folder,open_hands,open_mouth,ophiuchus,orange_book,outbox_tray,ox,page_facing_up,page_with_curl,pager,palm_tree,panda_face,paperclip,parking,part_alternation_mark,partly_sunny,passport_control,paw_prints,peach,pear,pencil,pencil2,penguin,pensive,performing_arts,persevere,person_frowning,person_with_blond_hair,person_with_pouting_face,phone,pig,pig2,pig_nose,pill,pineapple,pisces,pizza,plus1,point_down,point_left,point_right,point_up,point_up_2,police_car,poodle,poop,post_office,postal_horn,postbox,potable_water,pouch,poultry_leg,pound,pouting_cat,pray,princess,punch,purple_heart,purse,pushpin,put_litter_in_its_place,question,rabbit,rabbit2,racehorse,radio,radio_button,rage,rage1,rage2,rage3,rage4,railway_car,rainbow,raised_hand,raised_hands,raising_hand,ram,ramen,rat,recycle,red_car,red_circle,registered,relaxed,relieved,repeat,repeat_one,restroom,revolving_hearts,rewind,ribbon,rice,rice_ball,rice_cracker,rice_scene,ring,rocket,roller_coaster,rooster,rose,rotating_light,round_pushpin,rowboat,ru,rugby_football,runner,running,running_shirt_with_sash,sa,sagittarius,sailboat,sake,sandal,santa,satellite,satisfied,saxophone,school,school_satchel,scissors,scorpius,scream,scream_cat,scroll,seat,secret,see_no_evil,seedling,seven,shaved_ice,sheep,shell,ship,shipit,shirt,shit,shoe,shower,signal_strength,six,six_pointed_star,ski,skull,sleeping,sleepy,slot_machine,small_blue_diamond,small_orange_diamond,small_red_triangle,small_red_triangle_down,smile,smile_cat,smiley,smiley_cat,smiling_imp,smirk,smirk_cat,smoking,snail,snake,snowboarder,snowflake,snowman,sob,soccer,soon,sos,sound,space_invader,spades,spaghetti,sparkler,sparkles,sparkling_heart,speak_no_evil,speaker,speech_balloon,speedboat,squirrel,star,star2,stars,station,statue_of_liberty,steam_locomotive,stew,straight_ruler,strawberry,stuck_out_tongue,stuck_out_tongue_closed_eyes,stuck_out_tongue_winking_eye,sun_with_face,sunflower,sunglasses,sunny,sunrise,sunrise_over_mountains,surfer,sushi,suspect,suspension_railway,sweat,sweat_drops,sweat_smile,sweet_potato,swimmer,symbols,syringe,tada,tanabata_tree,tangerine,taurus,taxi,tea,telephone,telephone_receiver,telescope,tennis,tent,thought_balloon,three,thumbsdown,thumbsup,ticket,tiger,tiger2,tired_face,tm,toilet,tokyo_tower,tomato,tongue,top,tophat,tractor,traffic_light,train,train2,tram,triangular_flag_on_post,triangular_ruler,trident,triumph,trolleybus,trollface,trophy,tropical_drink,tropical_fish,truck,trumpet,tshirt,tulip,turtle,tv,twisted_rightwards_arrows,two,two_hearts,two_men_holding_hands,two_women_holding_hands,u5272,u5408,u55b6,u6307,u6708,u6709,u6e80,u7121,u7533,u7981,u7a7a,uk,umbrella,unamused,underage,unlock,up,us,v,vertical_traffic_light,vhs,vibration_mode,video_camera,video_game,violin,virgo,volcano,vs,walking,waning_crescent_moon,waning_gibbous_moon,warning,watch,water_buffalo,watermelon,wave,wavy_dash,waxing_crescent_moon,waxing_gibbous_moon,wc,weary,wedding,whale,whale2,wheelchair,white_check_mark,white_circle,white_flower,white_large_square,white_square,white_square_button,wind_chime,wine_glass,wink,wink2,wolf,woman,womans_clothes,womans_hat,womens,worried,wrench,x,yellow_heart,yen,yum,zap,zero,zzz",
            k = j.split(/,/), l = k.reduce(function (a, b) {
                return a[b] = !0, a
            }, {}), m = {
                named: /:([a-z0-9A-Z_-]+):/,
                blush: /:-?\)/g,
                scream: /:-o/gi,
                smirk: /[:;]-?]/g,
                smiley: /[:;]-?d/gi,
                stuck_out_tongue_closed_eyes: /x-d/gi,
                stuck_out_tongue_winking_eye: /[:;]-?p/gi,
                rage: /:-?[\[@]/g,
                disappointed: /:-?\(/g,
                sob: /:['’]-?\(|:&#x27;\(/g,
                kissing_heart: /:-?\*/g,
                wink: /;-?\)/g,
                pensive: /:-?\//g,
                confounded: /:-?s/gi,
                flushed: /:-?\|/g,
                relaxed: /:-?\$/g,
                mask: /:-x/gi,
                heart: /<3|&lt;3/g,
                broken_heart: /<\/3|&lt;&#x2F;3/g,
                thumbsup: /:\+1:/g,
                thumbsdown: /:\-1:/g
            }, n = Object.keys(m).map(function (a) {
                return [m[a], a]
            }), o = n.map(function (a) {
                var b = a[0], c = b.source || b;
                return c = c.replace(/(^|[^\[])\^/g, "$1"), "(" + c + ")"
            }).join("|"), p = new RegExp(o, "gi"), q = {
                emojify_tag_type: "div",
                only_crawl_id: null,
                img_dir: "images/emoji",
                ignored_tags: {SCRIPT: 1, TEXTAREA: 1, A: 1, PRE: 1, CODE: 1}
            };
        return f.prototype = {
            validate: function (a, c, e) {
                function f() {
                    return g.lastEmojiTerminatedAt = j + c, h
                }

                var g = this, h = d(a);
                if (h) {
                    var i = a[0], j = i.length;
                    return 0 === c ? f() : e.length === i.length + c ? f() : b(e.charAt(c - 1)) ? f() : b(e.charAt(i.length + c)) ? f() : this.lastEmojiTerminatedAt === c ? f() : void 0
                }
            }
        }, {
            defaultConfig: q, emojiNames: k, setConfig: function (a) {
                Object.keys(q).forEach(function (b) {
                    b in a && (q[b] = a[b])
                })
            }, replace: g, run: h
        }
    }();
    return a.emojify = b, "function" == typeof define && define.amd && define([], function () {
        return b
    }), b
}(this), $(function () {
    var a = $("#commentForm"), b = $("#commentEditor"), c = Backbone.Model.extend({urlRoot: ""}),
        d = Backbone.Model.extend({defaults: {page: 0, total: 0, size: 0, totalPage: 0, rollPage: 3, count: 0}}),
        e = Backbone.Collection.extend({
            url: "", model: c, comment_reports: null, initialize: function () {
                var a = $(".comment-sort-item");
                this.url = $("#commentSortText").html() == a.eq(0).html() ? a.eq(0).data("url") : a.eq(1).data("url")
            }, parse: function (a) {
                return i.set({
                    page: a.page,
                    total: a.total,
                    size: a.size,
                    count: a.count,
                    totalPage: Math.ceil(a.total / a.size)
                }), this.comment_reports = a.data.comment_reports, a.data.list
            }
        }), f = Backbone.View.extend({
            template: _.template($("#sub-comment-template").html()),
            className: "item",
            events: {
                "click .btn-action-cancel-at": "clickBtnActionCancelAt",
                "click .btn-action-reply": "clickBtnActionReply"
            },
            initialize: function () {
                $(this.el).mousemove(this.showAction), $(this.el).mouseleave(this.hideAction)
            },
            render: function () {
                var a = this.model.toJSON();
                return a.comment_reports = j.comments.comment_reports, $(this.el).html(this.template(a)), this
            },
            clickBtnActionCancelAt: function (a) {
                a.preventDefault(), $(a.currentTarget).parents().eq(2).hide()
            },
            clickBtnActionReply: function (a) {
                var b = $(a.currentTarget).parents().eq(1).next();
                if (b.is(":hidden")) {
                    var c = b.find("textarea[name=content]");
                    b.show(), b.find(".editor-wrapper").show(), c.val(""), c.focus()
                } else b.hide()
            },
            showAction: function (a) {
                var b = $(a.currentTarget);
                b.find(".helper .hide").show()
            },
            hideAction: function (a) {
                var b = $(a.currentTarget);
                b.find(".helper .hide").hide()
            }
        }), g = Backbone.View.extend({
            template: _.template($("#comment-template").html()),
            className: "item",
            events: {
                "click .all-comment": "clickAllComment",
                "submit .form-comment-at": "submitFormCommentAt",
                "keyup .editor-comment-at": "keyupEditorCommentAt",
                "blur .editor-comment-at": "blurEditorCommentAt",
                "click .btn-at-comment-submit": "clickBtnAtCommentSubmit",
                "click .btn-action-cancel": "clickBtnActionCancel",
                "click .btn-action-reply": "clickReply",
                "click .btn-delcom": "clickDelcom",
                "click .btn-report": "clickReport",
                "click .btn-action-vote": "clickVote"
            },
            initialize: function () {
                this.comments = new e, this.comments.bind("add", this.append)
            },
            clickAllComment: function (a) {
                a.preventDefault();
                var b = $(a.currentTarget), d = b.data();
                $.post(d.url, d, function (a) {
                    if (a.status) {
                        b.parent().hide();
                        for (var d in a.data) {
                            var e = new c(a.data[d]), g = new f({model: e});
                            b.parent().prev().append(g.render().el)
                        }
                    }
                }, "json")
            },
            submitFormCommentAt: function (a) {
                a.preventDefault();
                var b = $(a.currentTarget);
                if (b.data("disabled")) return !1;
                var d = b.parent().find(".editor-comment-at");
                if (!$.trim(d.val())) return d.parent().addClass("valid-error"), !1;
                var e = b.find(".btn-at-comment-submit");
                return e.addClass("btn-not-ready").text("正在发布..."), b.data("disabled", !0), $.post(b.attr("action"), b.serialize(), function (a) {
                    if (b.data("disabled", !1), -1 === a.status) e.tip(), e.removeClass("btn-not-ready").text("评论"); else if (1 === a.status) {
                        d.val(""), e.addClass("btn-not-ready").text("发布成功√"), setTimeout(function () {
                            e.text("评论")
                        }, 500), b.hide();
                        var g = a.id, h = b.parents(".sub-comment").eq(0).find(".all-comment");
                        if (h.length > 0) {
                            var i = h.data();
                            $.post(i.url, i, function (a) {
                                if (a.status) {
                                    for (var d in a.data) {
                                        var e = new c(a.data[d]), i = new f({model: e});
                                        h.parent().prev().append(i.render().el)
                                    }
                                    $.post($.luoo.root + "/comment/get_one", "id=" + g, function (a) {
                                        if (1 == a.status) {
                                            var d = new f({model: new c(a.data)});
                                            b.parents(".item").find(".items").append(d.render().el), Math.abs($(d.el).offset().top - b.offset().top) > window.screen.availHeight && $("html, body").animate({scrollTop: $(d.el).offset().top - 80}, 500)
                                        }
                                    }, "json"), h.parent().remove()
                                }
                            }, "json")
                        } else $.post($.luoo.root + "/comment/get_one", "id=" + a.id, function (a) {
                            if (1 == a.status) {
                                var d = new f({model: new c(a.data)});
                                b.parents(".item").find(".items").append(d.render().el), Math.abs($(d.el).offset().top - b.offset().top) > window.screen.availHeight && $("html, body").animate({scrollTop: $(d.el).offset().top - 80}, 500)
                            }
                        }, "json")
                    } else a.msg && (e.removeClass("btn-not-ready").text("评论"), $.luoo.close_msg(), $.luoo.msg("", '<p style="text-align: center; padding: 15px 0;">' + a.msg + "</p>", !1, "#000000"))
                }, "json"), !1
            },
            keyupEditorCommentAt: function (a) {
                var b = $(a.currentTarget), c = b.parent().next().find(".btn-at-comment-submit");
                "" != $.trim(b.val()) ? c.removeClass("btn-not-ready") : c.addClass("btn-not-ready"), 13 === a.keyCode && a.ctrlKey === !0 && b.parents().eq(1).submit()
            },
            blurEditorCommentAt: function (a) {
                var b = $(a.currentTarget), c = b.parent().next().find(".btn-at-comment-submit");
                "" != $.trim(b.val()) ? c.removeClass("btn-not-ready") : c.addClass("btn-not-ready")
            },
            clickBtnAtCommentSubmit: function (b) {
                var c = $(b.currentTarget);
                if (c.hasClass("btn-not-ready")) return !1;
                var d = $("#qtip-" + c.data("tipid"));
                if (!a.data("login") && !d.is("div")) {
                    var e = c.tip();
                    return e.set("events.hide", function (a, b) {
                        $.luoo.destroy_tip(b.id)
                    }), $.luoo.tips.push(e), !1
                }
            },
            clickBtnActionCancel: function (a) {
                a.preventDefault(), $(a.currentTarget).parents().eq(2).hide(), $(a.currentTarget).parents().eq(2).next(".items").children().length <= 0 && $(a.currentTarget).parents().eq(3).hide()
            },
            clickReply: function (a) {
                var b = $(a.currentTarget).parents().eq(0).next(), c = b.find(".editor-wrapper").eq(0);
                if (c.is(":hidden")) {
                    b.children(".items").children().length <= 0 && b.find("form").css("border", "0");
                    var d = b.find("textarea[name=content]").eq(0);
                    b.show(), c.show(), d.val(""), d.focus()
                } else c.hide(), b.children(".items").children().length <= 0 && b.hide()
            },
            clickDelcom: function (a) {
                if (!confirm("确定要删除这条评论吗？")) return !1;
                a.preventDefault();
                var b = $(a.currentTarget), c = b.data(), d = "delcom_callback";
                b.attr("callback") && (d = b.attr("callback")), $.luoo.post($.luoo.root + "/comment/del", c, d, b)
            },
            clickReport: function (a) {
                a.preventDefault();
                var b = $(a.currentTarget);
                if ($("#qtip-" + b.data("tipid")).is("div")) return !1;
                if (b.data("disabled")) return !1;
                var c = b.data();
                if ("number" != typeof c.app || "number" != typeof c.res) return !1;
                var d = "report_callback";
                b.attr("callback") && (d = b.attr("callback")), b.data("disabled", !0), $.luoo.post($.luoo.root + "/data/report", c, d, b)
            },
            clickVote: function (a) {
                a.preventDefault();
                var b = $(a.currentTarget);
                if ($("#qtip-" + b.data("tipid")).is("div")) return !1;
                if (b.data("disabled")) return !1;
                var c = b.data(), d = "vote_callback";
                b.attr("callback") && (d = b.attr("callback")), b.data("disabled", !0), $.luoo.post($.luoo.root + "/vote", c, d, b)
            },
            render: function () {
                var a = this.model.toJSON();
                if (a.comment_reports = j.comments.comment_reports, $(this.el).html(this.template(a)), a.sub_comm) for (var b = 0; b < a.sub_comm.length; b++) this.comments.add(new c(a.sub_comm[b]), $(this.el).find(".items"));
                return this
            },
            append: function (a, b, c) {
                var d = new f({model: a});
                c.append(d.render().el)
            },
            prepend: function (a, b) {
                var d = new f({model: new c(a)});
                b.prepend(d.render().el)
            }
        }), h = Backbone.View.extend({
            el: "html",
            events: {
                "mouseleave .icon-more": "mouseleaveMoreMenu",
                "mousemove .icon-more": "mousemoveMoreMenu",
                "click body": "clickBody",
                "submit #commentForm": "submitCommentForm",
                "click #commentSubmit": "clickCommentSubmit",
                "blur #commentEditor": "blurCommentEditor",
                "keyup #commentEditor": "keyupCommentEditor",
                "click #commentSort": "clickCommentSort",
                "click .sync-cb": "clickSyncCb",
                "click .btn-action-comment-sync": "clickSyncComm",
                "click .comment-sort-item": "clickSort",
                "click a[data-page]": "clickPagination"
            },
            elItems: $("#commentItems"),
            elItemsHtml: $("<div></div>"),
            comments: new e,
            initialize: function () {
                var a = this;
                this.comments.fetch({
                    data: {p: 1}, success: function () {
                        a.elItems.html(a.elItemsHtml.children()), setTimeout(function () {
                            var a = $("#commentList .quote-item"), b = $("#commentList .quote-item .sub-comment"),
                                c = b.css("background");
                            b.css("background", "transparent"), a.animate({backgroundColor: "#fbdbdd"}, 500, function () {
                                a.animate({backgroundColor: "#FFF"}, 500, function () {
                                    a.animate({backgroundColor: "#fbdbdd"}, 500, function () {
                                        a.animate({backgroundColor: "#FFF"}, 500, function () {
                                            b.css("background", c)
                                        })
                                    })
                                })
                            })
                        }, 300)
                    }
                }), this.listenTo(this.model, "change", this.render), this.comments.bind("add", this.add)
            },
            mouseleaveMoreMenu: function (a) {
                $(a.currentTarget).children(".more-menu").hide()
            },
            mousemoveMoreMenu: function (a) {
                $(a.currentTarget).children(".more-menu").show()
            },
            clickBody: function (a) {
                var b = $("#commentSortMenu");
                b.is($(":visible")) && ($(".icon-sort-menu").html("&#9660;"), b.slideUp(200))
            },
            submitCommentForm: function (a) {
                a.preventDefault();
                var c = $(a.currentTarget);
                if (c.data("disabled")) return !1;
                var d = b;
                if (!$.trim(d.val())) return d.parent().addClass("valid-error"), !1;
                $("#commentSubmit").addClass("btn-not-ready").text("正在发布...");
                var e = {el: c, self: this};
                return c.data("disabled", !0), $.luoo.post(c.attr("action"), c.serialize(), function (a, b) {
                    b.el.data("disabled", !1), -1 === a.status ? ($("#commentSubmit").tip(), $("#commentSubmit").removeClass("btn-not-ready").text("发布")) : 1 === a.status ? ($("#commentEditor").val(""), $("#commentSubmit").addClass("btn-not-ready").text("发布成功√"), setTimeout(function () {
                        $("#commentSubmit").text("发布")
                    }, 1500), $.luoo.post($.luoo.root + "/comment/get_one", "id=" + a.id, function (a, b) {
                        1 == a.status && b.self.prepend(a.data, $("#commentItems"))
                    }, b)) : a.msg && ($("#commentSubmit").removeClass("btn-not-ready").text("发布"), $.luoo.close_msg(), $.luoo.msg("", '<p style="text-align: center; padding: 15px 0;">' + a.msg + "</p>", !1, "#000000"))
                }, e), !1
            },
            clickCommentSubmit: function (b) {
                var c = $(b.currentTarget);
                if (c.hasClass("btn-not-ready")) return !1;
                var d = $("#qtip-" + c.data("tipid"));
                if (!a.data("login") && !d.is("div")) {
                    var e = c.tip();
                    return e.set("events.hide", function (a, b) {
                        $.luoo.destroy_tip(b.id)
                    }), $.luoo.tips.push(e), !1
                }
            },
            blurCommentEditor: function (a) {
                var b = $(a.currentTarget);
                "" != $.trim(b.val()) ? $("#commentSubmit").removeClass("btn-not-ready") : $("#commentSubmit").addClass("btn-not-ready")
            },
            keyupCommentEditor: function (b) {
                var c = $(b.currentTarget);
                "" != $.trim(c.val()) ? $("#commentSubmit").removeClass("btn-not-ready") : $("#commentSubmit").addClass("btn-not-ready"), 13 === b.keyCode && b.ctrlKey === !0 && a.submit()
            },
            clickCommentSort: function (a) {
                var b = $("#commentSortMenu");
                b.is($(":visible")) ? ($(".icon-sort-menu").html("&#9660;"), b.slideUp(200)) : ($(".icon-sort-menu").html("&#9650;"), b.slideDown(200)), a.stopPropagation()
            },
            clickSyncCb: function (a) {
                var b = $(a);
                !b.data("auth") && b.prop("checked") && window.open($.luoo.root + "/login/oauth/site/" + b.data("site") + "/act/bind")
            },
            clickSyncComm: function (a) {
                a.preventDefault();
                var b = $(a.currentTarget);
                b.hasClass("actived") ? (b.removeClass("actived"), b.find(".input-comment-sync").val(0)) : $.luoo.get(b.attr("href"), "", function (a, b) {
                    if (1 == a.status) b.addClass("actived"), b.find(".input-comment-sync").val(1); else if (-1 == a.status) {
                        b.data({remote: url("login/dialog"), width: 235});
                        var c = b.tip();
                        c.set("events.hide", function (a, b) {
                            b.destroy(), $.luoo.destroy_tip(b.id)
                        }), $.luoo.tip.push(c)
                    } else -2 == a.status && (b.addClass("actived"), b.find(".input-comment-sync").val(1), a.forward && window.open(a.forward))
                }, b)
            },
            clickSort: function (a) {
                a.preventDefault();
                var b = $(a.currentTarget), c = this;
                this.comments.url = b.data("url"), c.elItemsHtml = $("<div></div>"), this.comments.fetch({
                    data: {p: 1},
                    success: function () {
                        c.elItems.html(c.elItemsHtml.children()), $("#commentSortText").text($(b).text())
                    }
                })
            },
            clickPagination: function (a) {
                var b = $(a.target), c = this;
                c.elItemsHtml = $("<div></div>");
                var d = b.data("page");
                c.comments.fetch({
                    data: {p: d}, success: function () {
                        c.elItems.html(c.elItemsHtml.children()), $("html, body").animate({scrollTop: $(".comment-wrapper").position().top}, 300)
                    }
                })
            },
            prepend: function (a, b) {
                var d = new g({model: new c(a)});
                b.prepend(d.render().el)
            },
            add: function (a) {
                var b = new g({model: a});
                a.get("quote") && $(b.el).addClass("quote-item"), j.elItemsHtml.append(b.render().el)
            },
            render: function () {
                var a = this.model.get("page"), b = this.model.get("totalPage"), c = this.model.get("rollPage"), d = "",
                    e = "", f = "";
                if ($("#volcount").html(this.model.get("count")), e = 1 == a ? '<a class="previous disabled" rel="nofollow" href="javascript:void(0);">上一页</a>' : '<a class="previous" href="javascript:void(0);" data-page="' + (a - 1) + '">上一页</a>', f = a == b ? '<a class="next disabled" rel="nofollow" href="javascript:void(0);">下一页</a>' : '<a class="next" href="javascript:void(0);" data-page="' + (a + 1) + '">下一页</a>', 10 >= b) for (var g = 1; b + 1 > g; g++) d += g == a ? '<a class="page actived" rel="nofollow" href="javascript:void(0);">' + a + "</a>" : '<a class="page" rel="nofollow" href="javascript:void(0);" data-page="' + g + '">' + g + "</a>"; else {
                    c >= a && (c += 3 - a), a >= b - c && (c += 3 - (b - a));
                    for (var g = 1; b + 1 > g; g++) g == a ? d += '<a class="page actived" rel="nofollow" href="javascript:void(0);">' + a + "</a>" : a - g >= c && 1 != g ? (d += '<span class="break">...</span>', g = a - c) : (g >= a + c && g != b && (d += '<span class="break">...</span>', g = b), d += '<a class="page" rel="nofollow" href="javascript:void(0);" data-page="' + g + '">' + g + "</a>")
                }
                var h = e + d + f;
                $(this.el).find("div#paginator").html(h)
            }
        }), i = new d, j = new h({model: i})
});