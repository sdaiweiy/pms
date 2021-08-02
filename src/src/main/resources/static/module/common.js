layui.define(['config', 'crab', 'layer'], function (exports) {

    var common = {

        /***
         * 获取工作的工时
         * @param d1
         * @param d2
         * @returns {number}
         */
        getWorkHour: function (d1, d2) {

            return 0;
        },
        bindSearchFormMoreCondition: function () {
            //更多点击事件
            $('.search-form-more-condition').click(function () {
                $('.search-form-more').toggle()
            })
        },
        dateFormat: function (fmt) {
            var o = {
                "M+": this.getMonth() + 1,                 //月份
                "d+": this.getDate(),                    //日
                "h+": this.getHours(),                   //小时
                "m+": this.getMinutes(),                 //分
                "s+": this.getSeconds(),                 //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds()             //毫秒
            };
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        },
        verifyTime: function (elem) {
            debugger
            var regStrs = [['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
                ['[^\\d\\.\\-]+$', ''], //禁止录入任何非数字和点
                ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
                ['^(\\d+\\.\\d{1}).+', '$1'] //禁止录入小数点后一位以上
            ];
            for (var i = 0; i < regStrs.length; i++) {
                var reg = new RegExp(regStrs[i][0]);
                elem.value = elem.value.replace(reg, regStrs[i][1]);
            }
        },
        overTimeFormat: function (elem) {
            debugger
            elem.value = this.timeFormat(elem.value);
        },
        timeFormat: function (v) {
            var negative = false;
            if (v < 0) {
                negative = true;
                v *= -1;
            }
            v += '';
            if (v === '') {
                v = '0.00';
            } else if (v === '0') {
                v = '0.00';
            } else if (v === '0.') {
                v = '0.00';
            } else if (/^0+\d+\.?\d*.*$/.test(v)) {
                v = v.replace(/^0+(\d+\.?\d*).*$/, '$1');
            } else if (/^0\.\d$/.test(v)) {
                v = v + '0';
            } else if (!/^\d+\.\d{2}$/.test(v)) {
                if (/^\d+\.\d{2}.+/.test(v)) {
                    v = v.replace(/^(\d+\.\d{2}).*$/, '$1');
                } else if (/^\d+$/.test(v)) {
                    v = v + '.00';
                } else if (/^\d+\.$/.test(v)) {
                    v = v + '00';
                } else if (/^\d+\.\d$/.test(v)) {
                    v = v + '0';
                } else if (/^[^\d]+\d+\.?\d*$/.test(v)) {
                    v = v.replace(/^[^\d]+(\d+\.?\d*)$/, '$1');
                } else if (/\d+/.test(v)) {
                    v = v.replace(/^[^\d]*(\d+\.?\d*).*$/, '$1');
                } else if (/^0+\d+\.?\d*$/.test(v)) {
                    v = v.replace(/^0+(\d+\.?\d*)$/, '$1');
                } else {
                    v = '0.00';
                }
            }
            return negative ? "-" + v : v;
        },
        dailyDateFormat(date) {
            if (/^(\d+)(\.\d{2})?$/.test(date)) {
                var c = date.charAt(date.length - 1);
                return date.substring(0, date.length - 2) + c;
            } else if (/^(\d+)(\.)?$/.test(date)) {
                return date + 0;
            } else if (/^(\d+)(\.\d{1})?$/.test(date)) {
                return date;
            } else {
                return 0;
            }
        }

    };


    exports('common', common);

});